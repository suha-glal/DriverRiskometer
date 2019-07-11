#include "LogReader.h"
 #include <aknnotewrappers.h>

 
CCallLogReader* CCallLogReader::NewL(MLogCallBack* aCallBack)
{
    CCallLogReader* self = CCallLogReader::NewLC(aCallBack);
    CleanupStack::Pop(self);
    return self;
}
 
CCallLogReader* CCallLogReader::NewLC(MLogCallBack* aCallBack)
{
    CCallLogReader* self = new (ELeave) CCallLogReader(aCallBack);
    CleanupStack::PushL(self);
    self->ConstructL();
    return self;
}
 
CCallLogReader::CCallLogReader(MLogCallBack* aCallBack)
:CActive(CActive::EPriorityStandard),iCallBack(aCallBack)
{
}
 
CCallLogReader::~CCallLogReader()
{	

    Cancel();
    delete iLogView, iLogView = NULL;
    delete iLogFilter, iLogFilter = NULL;	
    delete iLogClient, iLogClient = NULL;		
    iFsSession.Close();
}
 
void CCallLogReader::ConstructL(void)
{
    CActiveScheduler::Add(this);
 
    User::LeaveIfError(iFsSession.Connect());
 
    iLogClient = CLogClient::NewL(iFsSession);
    iLogView = CLogViewEvent::NewL(*iLogClient);
    iLogFilter = CLogFilter::NewL();
   
}
 
void CCallLogReader::DoCancel()
{
    if(iLogView)
        iLogView->Cancel();
 
    if(iLogClient)
        iLogClient->Cancel();
}
 
void CCallLogReader::RunL()
{
    if(iStatus != KErrNone)
        DoneReadingL(iStatus.Int());
    else
        switch (iEngineState)
        {
            case ECreatingView:
               if(iLogView)
               {
                   // The filtered view has been successfully created
                   // so issue a request to start processing logs backwards	
                   if(iLogView->LastL(iStatus))
                   {	
                       iEngineState = EReadingEntries;
                       SetActive();
                   }
                   else
                       DoneReadingL(2);
               }
               break;
 
            case EReadingLast:
               if(iLogView)
               {
                   iLogView->FirstL(iStatus);
                   iEngineState = EReadingEntries;
                   SetActive();
               }
               break;
 
            case EReadingEntries:
               if(iLogView)
               {
                   // since we are working from last-to-first
                   // you could also delete entries at this point if necessary
               iCallBack->HandleLogEventL(iLogView->Event());
 
                   iEngineState = EReadingEntries;
                   if(iLogView->PreviousL(iStatus))
                       SetActive();
                   else
                       DoneReadingL(KErrNone);
               }
               break;
 
           default:
              break;
        }
}
void CCallLogReader::Start(/*TDesC & startTime*/){

iLogFilter->SetEventType(KLogCallEventTypeUid);
TTime now; 			
now.UniversalTime(); // for UTC

 iLogFilter->SetStartTime(now);
 
 
}
void CCallLogReader::End()
{
if(iLogView->SetFilterL(*iLogFilter, iStatus))
    {		
        iEngineState = ECreatingView;
        SetActive();
    }
    else
        DoneReadingL(2);
}

void CCallLogReader::DoneReadingL(TInt aError)
{
iCallBack->LogProcessed(aError);	
}

