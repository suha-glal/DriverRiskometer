/*
 * CallLogHandler.cpp
 * 
 *  Created on: Dec 28, 2009
 *      Author: Suha
 */

#include"CallLogHandler.h"
Callloghandler* Callloghandler::NewL()
{
Callloghandler* self = Callloghandler::NewLC();
    CleanupStack::Pop(self);
    return self;
}
 
Callloghandler* Callloghandler::NewLC()
{
Callloghandler* self = new (ELeave) Callloghandler();
    CleanupStack::PushL(self);
    self->ConstructL();
    return self;
}
void Callloghandler::ConstructL(void)
{
    CActiveScheduler::Add(this);
 
             //set port number
            TUint KTestPort=8101;
            ///** Loopback address (127.0.0.1). */
    	    TInetAddr addr(KInetAddrLoop, KTestPort);
    	    User::LeaveIfError(socketServ.Connect());
    	    User::LeaveIfError(listener.Open(socketServ, KAfInet,KSockStream, KProtocolInetTcp));
    	    User::LeaveIfError(listener.Bind(addr));  
    	    User::LeaveIfError(listener.Listen(1));
    	   // iLog.Connect();
    	   // iLog.CreateLog(_L("myLogs"),_L("MyLogFile"),EFileLoggingModeOverwrite);
    	    
    	    NoDRc=0;
    	    duration=0;
    	    
}
Callloghandler::Callloghandler()
:CActive(CActive::EPriorityStandard){         
	    		
}//constroctor
void Callloghandler::DoCancel()
{
Cancel();
blank.Close();
listener.Close();
blank.CancelAll();
socketServ.Close();
		
//		iLog.CloseLog();
//		iLog.Close();
}
Callloghandler::~Callloghandler()
		{
		blank.Close();
		listener.Close();
		blank.CancelAll();
		socketServ.Close();
		
//		iLog.CloseLog();
//		iLog.Close();

		}
//startkeycapture
void Callloghandler::Listening(){

NoDRc=0;
duration=0;

callerStatus=EAccept;
blank.Open(socketServ);
listener.Accept(blank, iStatus); 
SetActive();
		
}//listen

	void Callloghandler::HandleLogEventL(const CLogEvent& anEvent)
	    {
	   
	   
	    
	    if(((anEvent.Direction()).Compare(_L("Incoming"))==0||(anEvent.Direction()).Compare(_L("Outgoing"))==0))
	    {
	 if(anEvent.EventType()==KLogCallEventTypeUid){
	    	NoDRc++;
	        duration+=anEvent.Duration();
	 }//if
	    	
	    }//if
	   
	    }
	void Callloghandler::LogProcessed(TInt aError)
		{
		callerStatus=ESending;		 
		if(aError==KErrNone)
			{
			buffer.Copy(_L(""));
			buffer.AppendNum(NoDRc);
			buffer.Append(_L("#"));
			buffer.AppendNum(duration);
			
			}
		else if(aError==2)
			{
			_LIT8(KCall, "n");
			buffer.Copy(KCall);
			
			}
			NoDRc=0;
			duration=0;
			blank.Write(buffer,iStatus);
			SetActive();
		}//logprocessed
	
	
	void Callloghandler::RunL()
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
							{  
							call->End();					 						  
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
					        NoDRc=0;
					        duration=0;
					        call=CCallLogReader::NewL(this);
					        call->Start();	
					        Listening();
				 			}//else
				 	}//callerStatus=ERecConfi;
				 else if(callerStatus==ESending)
					 {
					 if(iStatus != KErrNone)
					 User::Leave(KErrGeneral);
					 else{
					 	blank.Close();	
					 	call=NULL;
					 	Listening();
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
	
	

