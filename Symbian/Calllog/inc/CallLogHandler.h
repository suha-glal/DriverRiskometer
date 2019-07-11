/*
 * CallLogHandler.h
 *
 *  Created on: Dec 28, 2009
 *      Author: Suha
 */

#ifndef CALLLOGHANDLER_H_
#define CALLLOGHANDLER_H_
#include <in_sock.h>
#include <es_sock.h>
#include "LogReader.h"
#include "MLogCallBack.h"
//#include <flogger.h>

class Callloghandler :public CActive,MLogCallBack
	{
public:
	static Callloghandler* NewL();
    static Callloghandler* NewLC();
	        
	 ~Callloghandler();
	 void Listening();
	 //from MLogCallBack
	void HandleLogEventL(const CLogEvent& anEvent);
	void LogProcessed(TInt aError);
	
public: 
	// from CActive
       void DoCancel();
       void RunL();
private:
	void ConstructL();
	Callloghandler();
   
	TRequestStatus callerStatus;
			 enum TLoadStates
			  {
			  EAccept,
			  EReceiving,
			  ERecConfi,
			  EClose,
			  ESending
			  	 };
	TBool running; 
	RSocketServ socketServ;
	RSocket listener;
	RSocket blank;
	TRequestStatus status;
	TSockXfrLength dummyLength;
	TBuf8<1024> buffer;
	//RFileLogger iLog;
	TInt NoDRc;
	TInt duration;
	TBuf<256>b;
	CCallLogReader* call;
	};

#endif /* CALLLOGHANDLER_H_ */
