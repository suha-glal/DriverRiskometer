#ifndef __LISTENER_H__
#define __LISTENER_H__
 

#include <in_sock.h>
#include <flogger.h>



class Listener :public CActive
	{
public:
	
 Listener(/*CConsoleBase* con*/);
 ~Listener();
 void StartCountdown();
 
 void RunL();
 void DoCancel();

 
 



private:

	
TInt callerStatus;
		 enum TLoadStates
		  {
		  EAccept,
		  EReceiving,
		  ERecConfi,
		  EClose,
		  ESending
		  	};
////////////
RSocketServ socketServ;
RSocket listener;
RSocket blank;
TSockXfrLength dummyLength;
TBuf8<256> buffer;
RFileLogger iLog;
};
#endif 
