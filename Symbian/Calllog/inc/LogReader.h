/*
 * LogReader.h
 *
 *  Created on: Dec 27, 2009
 *      Author: Suha
 */

#ifndef LOGREADER_H_
#define LOGREADER_H_
#include <F32FILE.H>
#include <LOGVIEW.H>
#include <logcli.h>
 

 
#include "MLogCallBack.h"

 
class CCallLogReader: public CActive
{
    enum TCallLogReaderState
    {
        ECreatingView,
        EReadingEntries,
        EReadingLast
    };
 
    public: // constructors and destructor	
    	static CCallLogReader* NewL(MLogCallBack* aCallBack);
        static CCallLogReader* NewLC(MLogCallBack* aCallBack);
        ~CCallLogReader();
        void Start(/*TDesC & startTime*/);
        void End();
    public: // from CActive
        void DoCancel();
        void RunL();
        
    private: // constructors
    	CCallLogReader(MLogCallBack* aCallBack);
        void ConstructL();
        void DoneReadingL(TInt aError);
        void HandleLogEventL(const CLogEvent& anEvent);
    private: // data
        TCallLogReaderState iEngineState;
        CLogClient*         iLogClient; 
        CLogViewEvent*      iLogView;
        CLogFilter*         iLogFilter;
        MLogCallBack*       iCallBack;
        RFs                 iFsSession;
        
        
};

#endif /* LOGREADER_H_ */
