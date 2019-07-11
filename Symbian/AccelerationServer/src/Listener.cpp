#include"Listener.h"
 Listener::Listener(/*CConsoleBase* con*/): CActive(CActive::EPriorityUserInput)
{   
		
			      

	   iLog.Connect();
       iLog.CreateLog(_L("myLogs"),_L("MySer"),EFileLoggingModeOverwrite);
									
		
		// Add to active scheduler
		CActiveScheduler::Add(this);
	
		
}//Listener
 Listener::~Listener()
{

Cancel();


}

void Listener::StartCountdown()
{
        //567 driver
        TUint KTestPort=8567;
	    TInetAddr addr(KInetAddrLoop, KTestPort);
		User::LeaveIfError(socketServ.Connect());
		CleanupClosePushL(socketServ);//if function leaves ensure socket serv session closes
		User::LeaveIfError(listener.Open(socketServ, KAfInet,KSockStream, KProtocolInetTcp));
		User::LeaveIfError(listener.Bind(addr));  
		User::LeaveIfError(listener.Listen(1));
		
		
		
callerStatus=EAccept;			
SetActive();
}


void Listener::RunL()
	{

_LIT8(KClosing, "closing");
if(iStatus==KErrNone)
switch(callerStatus)
	{
case EAccept:
	{
	iLog.Write(_L("B"));
    blank.Open(socketServ);
	listener.Accept(blank, iStatus); 
	callerStatus=EReceiving;	
	SetActive();
	}break;
case EReceiving:
	{	
	iLog.Write(_L("C"));
blank.RecvOneOrMore(buffer, 0,iStatus, dummyLength);
callerStatus=ERecConfi;
SetActive();
					           
	}break;
case ERecConfi:
	{
	iLog.Write(_L("D"));
if(	(buffer[0] == 'p') ) 
{
 buffer.Copy(_L("1!2@3#"));
 //buffer.AppendNum(accXYZ->x);
 //buffer.Append('!');
 //buffer.AppendNum(accXYZ->y);
 //buffer.Append('@');
 //buffer.AppendNum(accXYZ->z);
 //buffer.Append('#');
 	
 
 iLog.Write(buffer);
 blank.Write(buffer, iStatus);
 blank.Close();
 callerStatus=EAccept;
 		
}
else if(buffer[0]=='c')
	{
	callerStatus=EClose;
	blank.Write(KClosing, iStatus);
	}
SetActive();
							
							
	}break;

case EClose:
	{
	iLog.Write(_L("E"));
	buffer.Copy(KClosing);
	blank.Write(buffer, iStatus);	
	blank.Close();
	socketServ.Close();		
	User::Exit(KErrNone);
	} break;
}//switch
			

			
		
			
}

void Listener::DoCancel()
{
Cancel();

}
