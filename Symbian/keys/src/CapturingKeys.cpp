#include"CapturingKeys.h"



CKeyCapturer* CKeyCapturer::NewL(MKeyCallBack* aObserver)	
	{	
	CKeyCapturer* self = CKeyCapturer::NewLC(aObserver);
	CleanupStack::Pop(self);	
	return self;	
	}
CKeyCapturer* CKeyCapturer::NewLC(MKeyCallBack* aObserver)	
	{	
	CKeyCapturer* self = new (ELeave) CKeyCapturer(aObserver);	
	CleanupStack::PushL(self);
	self->ConstructL();	
	return self;
	} 
CKeyCapturer::CKeyCapturer(MKeyCallBack* aObserver):CActive(EPriorityStandard),iObserver(aObserver),iHandle(-1)
	{
				//blank.Open(socketServ);
						//listener.Accept(blank, iStatus); 
						//User::WaitForRequest(iStatus);
						//if(status != KErrNone) User::Leave(KErrGeneral);
	           
						//iLogk.Connect();
						//iLogk.CreateLog(_L("myLogs"),_L("MyrunC"),EFileLoggingModeOverwrite);
										
	}
CKeyCapturer::~CKeyCapturer(){	
if(iHandle > -1)
	{		
	iWg.CancelCaptureKey(iHandle);
	} 
iHandle = -1; 

Cancel(); 	
iWg.Close();	
iWsSession.Close();




//iLogk.CloseLog();
//iLogk.Close();
} 
void CKeyCapturer::ConstructL(){	
CActiveScheduler::Add(this); 

User::LeaveIfError(iWsSession.Connect());
iWg=RWindowGroup(iWsSession);	
User::LeaveIfError(iWg.Construct((TUint32)&iWg, EFalse));
iWg.SetOrdinalPosition(-1);
iWg.EnableReceiptOfFocus(EFalse); 	
CApaWindowGroupName* wn=CApaWindowGroupName::NewLC(iWsSession);
wn->SetHidden(ETrue);	
wn->SetWindowGroupName(iWg);	
CleanupStack::PopAndDestroy(); 	


}
void CKeyCapturer::KeysCaptrued(){
User::LeaveIfError( iHandlen0 = iWg.CaptureKey( '0' , 0, 0 ) );
User::LeaveIfError( iHandlen1 = iWg.CaptureKey( '1' , 0, 0 ) );
User::LeaveIfError( iHandlen2 = iWg.CaptureKey( '2' , 0, 0 ) );
User::LeaveIfError( iHandlen3 = iWg.CaptureKey( '3' , 0, 0 ) );
User::LeaveIfError( iHandlen4 = iWg.CaptureKey( '4' , 0, 0 ) );
User::LeaveIfError( iHandlen5 = iWg.CaptureKey( '5' , 0, 0 ) );
User::LeaveIfError( iHandlen6 = iWg.CaptureKey( '6' , 0, 0 ) );
User::LeaveIfError( iHandlen7 = iWg.CaptureKey( '7' , 0, 0 ) );
User::LeaveIfError( iHandlen8 = iWg.CaptureKey( '8' , 0, 0 ) );
User::LeaveIfError( iHandlen9 = iWg.CaptureKey( '9' , 0, 0 ) );


User::LeaveIfError( iHandle2 = iWg.CaptureKey( '#' , 0, 0 ) );
User::LeaveIfError( iHandle3 = iWg.CaptureKey( '*' , 0, 0 ) );
User::LeaveIfError(iHandle4 = iWg.CaptureKey(EKeyDevice0, 0,0));	
User::LeaveIfError(iHandle5 = iWg.CaptureKey(EKeyDevice1, 0,0));
User::LeaveIfError(iHandle6 = iWg.CaptureKey(EKeyDevice2, 0,0));
User::LeaveIfError(iHandle7 = iWg.CaptureKey(EKeyDevice3, 0,0));
User::LeaveIfError(iHandle8 = iWg.CaptureKey(EKeyDevice4, 0,0));
User::LeaveIfError(iHandle9 = iWg.CaptureKey(EKeyDevice5, 0,0));
User::LeaveIfError(iHandle10 = iWg.CaptureKey(EKeyDevice6, 0,0));
User::LeaveIfError(iHandle11 = iWg.CaptureKey(EKeyApplication0, 0,0));
User::LeaveIfError(iHandle12 = iWg.CaptureKey(EKeyYes, 0,0));
User::LeaveIfError(iHandle13 = iWg.CaptureKey(EKeyNo, 0,0));	
User::LeaveIfError(iHandle14 = iWg.CaptureKey(EKeyLeftArrow, 0,0));	
User::LeaveIfError(iHandle15 = iWg.CaptureKey(EKeyRightArrow, 0,0));	
User::LeaveIfError(iHandle16 = iWg.CaptureKey(EKeyUpArrow, 0,0));	
User::LeaveIfError(iHandle17 = iWg.CaptureKey(EKeyDownArrow, 0,0));	
User::LeaveIfError(iHandle18 = iWg.CaptureKey(EKeyBackspace, 0,0));
User::LeaveIfError(iHandle19 = iWg.CaptureKey(EKeySliderDown, 0,0));
User::LeaveIfError(iHandle20 = iWg.CaptureKey(EKeySliderUp, 0,0));

/*iLogk.Write(_L("!"));
for(TUint i = '0'; i != '9'+1; i++)
	{
User::LeaveIfError( iHandle = iWg.CaptureKey( i , 0, 0 ) );
TBuf<8>b;
b.AppendNum(iHandle);
//iLogk.Write(b);
	}//for
User::LeaveIfError( iHandle = iWg.CaptureKey( '#' , 0, 0 ) );
User::LeaveIfError( iHandle = iWg.CaptureKey( '*' , 0, 0 ) );
User::LeaveIfError(iHandle = iWg.CaptureKey(EKeyDevice0, 0,0));	
User::LeaveIfError(iHandle = iWg.CaptureKey(EKeyDevice1, 0,0));
User::LeaveIfError(iHandle= iWg.CaptureKey(EKeyDevice2, 0,0));
User::LeaveIfError(iHandle= iWg.CaptureKey(EKeyDevice3, 0,0));
User::LeaveIfError(iHandle= iWg.CaptureKey(EKeyDevice4, 0,0));
User::LeaveIfError(iHandle= iWg.CaptureKey(EKeyDevice5, 0,0));
User::LeaveIfError(iHandle = iWg.CaptureKey(EKeyDevice6, 0,0));
User::LeaveIfError(iHandle = iWg.CaptureKey(EKeyApplication0, 0,0));
User::LeaveIfError(iHandle = iWg.CaptureKey(EKeyYes, 0,0));
User::LeaveIfError(iHandle = iWg.CaptureKey(EKeyNo, 0,0));	
User::LeaveIfError(iHandle = iWg.CaptureKey(EKeyLeftArrow, 0,0));	
User::LeaveIfError(iHandle = iWg.CaptureKey(EKeyRightArrow, 0,0));	
User::LeaveIfError(iHandle = iWg.CaptureKey(EKeyUpArrow, 0,0));	
User::LeaveIfError(iHandle = iWg.CaptureKey(EKeyDownArrow, 0,0));	
User::LeaveIfError(iHandle = iWg.CaptureKey(EKeyBackspace, 0,0));
User::LeaveIfError(iHandle = iWg.CaptureKey(EKeySliderDown, 0,0));
User::LeaveIfError(iHandle = iWg.CaptureKey(EKeySliderUp, 0,0));
iLogk.Write(_L("$"));
//iHandle = iWg.CaptureKey(EKeyIncVolume, 0,0);
//iHandle = iWg.CaptureKey(EKeyDecVolume, 0,0);
*/

} 
void CKeyCapturer::KeysCanceled(){
iWg.CancelCaptureKey(iHandlen0);
iWg.CancelCaptureKey(iHandlen1);
iWg.CancelCaptureKey(iHandlen2);
iWg.CancelCaptureKey(iHandlen3);
iWg.CancelCaptureKey(iHandlen4);
iWg.CancelCaptureKey(iHandlen5);
iWg.CancelCaptureKey(iHandlen6);
iWg.CancelCaptureKey(iHandlen7);
iWg.CancelCaptureKey(iHandlen8);
iWg.CancelCaptureKey(iHandlen9);
iWg.CancelCaptureKey(iHandle2);		
iWg.CancelCaptureKey(iHandle3);		
iWg.CancelCaptureKey(iHandle4);		
iWg.CancelCaptureKey(iHandle5);		
iWg.CancelCaptureKey(iHandle6);		
iWg.CancelCaptureKey(iHandle7);		
iWg.CancelCaptureKey(iHandle8);		
iWg.CancelCaptureKey(iHandle9);		
iWg.CancelCaptureKey(iHandle10);		
iWg.CancelCaptureKey(iHandle11);		
iWg.CancelCaptureKey(iHandle12);		
iWg.CancelCaptureKey(iHandle13);		
iWg.CancelCaptureKey(iHandle14);		
iWg.CancelCaptureKey(iHandle15);		
iWg.CancelCaptureKey(iHandle16);		
iWg.CancelCaptureKey(iHandle17);		
iWg.CancelCaptureKey(iHandle18);		
iWg.CancelCaptureKey(iHandle19);		
iWg.CancelCaptureKey(iHandle20);

}
void CKeyCapturer::RunL()
{

   	
   	TBuf<8> state;
   	state.AppendNum(iStatus.Int());
   	
   if (iStatus == KErrNone) 
	{
	
	   
		
	    TWsEvent e;
		iWsSession.GetEvent(e);
 			
			TInt wgId = iWsSession.GetFocusWindowGroup();
			iWsSession.SendEventToWindowGroup(wgId, e);
			iObserver->KeyCapturedL();
	
   
   
   	iWsSession.EventReady(&iStatus);
   	
   	SetActive();
	}//iStatus == KErrNone

   
	
}
void CKeyCapturer::DoCancel()
{	
iWsSession.EventReadyCancel();


}
void CKeyCapturer::Listen()
{

	
	KeysCaptrued();
	
	iWsSession.EventReady(&iStatus);
	SetActive();
	
		
}//listen
void CKeyCapturer::Answer()
{	


	
Cancel();
KeysCanceled();			


iObserver->write(0);
}//listen
