/*
 ============================================================================
 Name		: Capturingkeysbackground.cpp
 Author	  : suha
 Copyright   : Your copyright notice
 Description : Exe source file
 ============================================================================
 */

//  Include Files  

#include "keys.h"
#include"CapturingKeys.h"
#include <e32base.h>
#include <e32std.h>
#include <in_sock.h>//socket
#include <flogger.h>


class Key: public MKeyCallBack,CActive 
	{
public:
	static Key* NewL()	
		{	
		Key* self = Key::NewLC();
		CleanupStack::Pop(self);	
		return self;	
		}
	static Key* NewLC()	
		{	
		Key* self = new (ELeave) Key();	
		CleanupStack::PushL(self);
		//self->ConstructL();	
		return self;
		} 
	Key():CActive(EPriorityStandard)
		{
			//keyconsole=c;
	   TUint KTestPort=8123;
	   TInetAddr addr(KInetAddrLoop, KTestPort);
	   User::LeaveIfError(socketServ.Connect());
		    //CleanupClosePushL(socketServ);//if function leaves ensure socket serv session closes
	   User::LeaveIfError(listener.Open(socketServ, KAfInet,KSockStream, KProtocolInetTcp));
	   User::LeaveIfError(listener.Bind(addr));  
	   User::LeaveIfError(listener.Listen(1));
					
								keyinaction=0;
								keyDura=0;
								msDur=0;
								nowtime=NULL;
								prevtime=NULL;
								
		keyCap=CKeyCapturer::NewL(this);
			//iLog.Connect();
			//iLog.CreateLog(_L("myLogs"),_L("MykeyFile"),EFileLoggingModeOverwrite);
			CActiveScheduler::Add(this); 
			}
		 void StartKeyCaptuer() 
			 {
			        //iLog.Write(_L("1"));
			       
			        callerStatus=EAccept;
			 		blank.Open(socketServ);
			 		listener.Accept(blank, iStatus); 
			 		SetActive();
			 		
			 } 
			 	/*			
			 		
			 	
			 }	*/	
		 	void write(TInt i)
		 	{
		 	TInt msS=0;
		 	msS=keyDura*1000000;
		 	TInt msR=0;
		 	msR=msDur-msS;
		 	TInt reDur=0;
		 	reDur=msR/1000000;
		 	
		 	keyDura+=reDur;
		 	
		 	buffer.Copy(_L(""));
		 	buffer.AppendNum(keyinaction);
		 	buffer.Append(_L("#"));
		 	buffer.AppendNum(keyDura);
		 	callerStatus=ESending;		 								
		 	blank.Write(buffer, iStatus);
		 	SetActive();
		 	
		 	}//write
		 ~Key()
			 {
			 socketServ.Close();
			 blank.Close();
			 listener.Close();
			 blank.CancelAll();
			
			 //iLog.CloseLog();
			 //iLog.Close();
			 
			 }
		 void KeyCapturedL()
			 {
			 
			  //iLog.Write(_L("3"));

			 nowtime.HomeTime();
			 
						if(prevtime==NULL)
							{
							keyinaction++;
							
							
							}
						else {
						
						TTimeIntervalSeconds interval_sec;
						TInt err=nowtime.SecondsFrom(prevtime,interval_sec);
						
						//less than or equall 30 seconds the same interaction
						if(err==KErrNone){
						
						if(interval_sec.Int()<=30)
							{
							TTimeIntervalMicroSeconds ms;
							ms=nowtime.MicroSecondsFrom(prevtime);
							msDur+=ms.Int64();					
							keyDura+=interval_sec.Int();
							}
						else{
						
						   keyDura+=1;
						   keyinaction++;
						    }
						
						}//KErrNone
						
						
						}//prevtime!=NULL
			prevtime=nowtime;
				
			 }//keycapturedl
		 void RunL()
			 {
			 if(callerStatus==EAccept){
			 if(iStatus != KErrNone)
				User::Leave(KErrGeneral);
			 else{			 	
			 callerStatus=EReceiving;
			 blank.RecvOneOrMore(buffer, 0, iStatus, dummyLength);
			 			 		
			 			 		SetActive();
			 }//else
			 }//callerStatus==EAccept
			 else if(callerStatus==EReceiving)
				 {	
				 
				 if(iStatus != KErrNone)
				 	User::Leave(KErrGeneral);
				 else{
				 
				 if((buffer[0] == 's') ) 
						{
						callerStatus=ERecConfi;
						
						
						buffer.Copy(_L("s"));
						blank.Write(buffer, iStatus);
					  //iLog.Write(_L("2"));

						SetActive();
						}//if buffer[0] == 's'
				else if((buffer[0] == 'e')) 
						{  //iLog.Write(_L("4"));

							keyCap->Answer();
							//  iLog.Write(_L("5"));
							  
					  
						} 
				else if( (buffer[0] == 'c') ) 
						{
							
							 _LIT8(KClosing, "Server closing");
							buffer.Copy(KClosing);
							blank.Write(buffer, iStatus);
							 callerStatus=EClose;
							SetActive();
						} //elsebuffer[0] == 'c'
										
				 }//else
				 }//callerStatus==EReceiving
			 
			 else if(callerStatus==ERecConfi)
				 {	
				 if(iStatus != KErrNone)
				 	User::Leave(KErrGeneral);
				  else{
				 			
				        blank.Close();
				        keyinaction=0;
			 			 keyDura=0;
			 			msDur=0;
			 			 nowtime=NULL;
			 			 prevtime=NULL;
			 			 keyCap->Listen();
			 			 StartKeyCaptuer();
			 			}//else
			 	}//callerStatus=ERecConfi;
			 else if(callerStatus==ESending)
				 {
				 if(iStatus != KErrNone)
				 User::Leave(KErrGeneral);
				 else{
				 	blank.Close();	
				  StartKeyCaptuer();
				 	 }
				 		 	
				 }//callerStatus==ESending
			 else if (callerStatus==EClose)
				 {
				 if(iStatus != KErrNone)
				 User::Leave(KErrGeneral);
				  else{ 		
				  blank.Close();	
				  socketServ.Close();		
				  User::Exit(KErrNone);
				  }//else
				 				 						 			
				 				 						 			
				 }//callerStatus==EClose
			 }//Runl
		 void DoCancel()
		 {
		 blank.CancelAll();
		 }
private:
		 //CConsoleBase* keyconsole;
		 TInt keytouch;
		 RSocketServ socketServ;
		 RSocket listener;
		 RSocket blank;
		 TSockXfrLength dummyLength;
		 TBuf8<256> buffer;
		 TRequestStatus status;
		            
		 CKeyCapturer* keyCap;
		 	
		 //RFileLogger iLog;
		 
		 TInt keyinaction;
		 TInt keyDura;
		 TInt msDur;
		 TTime nowtime;
		 TTime prevtime;
		 
		 TRequestStatus callerStatus;
		 enum TLoadStates
		  {
		  EAccept,
		  EReceiving,
		  ERecConfi,
		  EClose,
		  ESending
		  };

	};

//  Local Functions

LOCAL_C void MainL()
	{
	//
	// add your program code here, example code below
	//
    
	Key* myKey= Key::NewL();
	
	myKey->StartKeyCaptuer();
	}

LOCAL_C void DoStartL()
	{
	// Create active scheduler (to run active objects)
	CActiveScheduler* scheduler = new (ELeave) CActiveScheduler();
	CleanupStack::PushL(scheduler);
	CActiveScheduler::Install(scheduler);

	MainL();
	CActiveScheduler::Start();
	// Delete active scheduler
	CleanupStack::PopAndDestroy(scheduler);
	}

//  Global Functions

GLDEF_C TInt E32Main()
	{
			
	// Create cleanup stack
	__UHEAP_MARK;
	CTrapCleanup* cleanup = CTrapCleanup::New();

	

	// Run application code inside TRAP harness, wait keypress when terminated
	TRAPD(mainError, DoStartL());
	
	delete cleanup;
	__UHEAP_MARKEND;
	return KErrNone;
	}

