#ifndef __CAPTURINGKEYS_H__
#define __CAPTURINGKEYS_H__
#include <w32std.h>
#include <apgwgnam.h> //CApaWindowGroupName
#include <e32base.h> 

#include <flogger.h>

class MKeyCallBack 
{

public:
	
	virtual void KeyCapturedL() = 0;
	virtual void write(TInt i)=0;
};
class CKeyCapturer : public CActive 
{

public:
	static CKeyCapturer* NewL(MKeyCallBack* aObserver);	
	static CKeyCapturer* NewLC(MKeyCallBack* aObserver);
	virtual ~CKeyCapturer();
	void Listen();
	void Answer();
	
	
	private:
		CKeyCapturer(MKeyCallBack* aObserver);
		void ConstructL();	
		void RunL();	
		void DoCancel();	
		
		void KeysCaptrued();
		void KeysCanceled();
		private:	
			MKeyCallBack* 	iObserver;
			RWsSession     	iWsSession;
			RWindowGroup    iWg; 
			TInt 			iHandle;
			TInt  timeNo;
			
			TInt 			iHandlen0;
			TInt 			iHandlen1;
			TInt 			iHandlen2;
			TInt 			iHandlen3;
			TInt 			iHandlen4;
			TInt 			iHandlen5;
			TInt 			iHandlen6;
			TInt 			iHandlen7;
			TInt 			iHandlen8;
			TInt 			iHandlen9;
			TInt 			iHandle2;		
			TInt 			iHandle3;		
			TInt 			iHandle4;		
			TInt 			iHandle5;		
			TInt 			iHandle6;		
			TInt 			iHandle7;		
			TInt 			iHandle8;		
			TInt 			iHandle9;		
			TInt 			iHandle10;		
			TInt 			iHandle11;		
			TInt 			iHandle12;		
			TInt 			iHandle13;		
			TInt 			iHandle14;		
			TInt 			iHandle15;		
			TInt 			iHandle16;		
			TInt 			iHandle17;		
			TInt 			iHandle18;		
			TInt 			iHandle19;		
			TInt 			iHandle20;		
									
                 
			 ////////////////
			//RFileLogger iLogk;
					
		     TInt running;
             
            
             
};
#endif  
