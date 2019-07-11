/*
 ============================================================================
 Name		: Calllog.cpp
 Author	  : suha
 Copyright   : Your copyright notice
 Description : Exe source file
 ============================================================================
 */

//  Include Files  

#include "Calllog.h"

#include"CallLogHandler.h"

#include <e32base.h>
#include <e32std.h>

//  Local Functions

LOCAL_C void MainL()
	{
	//
	// add your program code here, example code below
	//

	Callloghandler* icall= Callloghandler::NewL();
	icall->Listening();
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

	TRAPD(mainError, DoStartL());
	
	delete cleanup;
	__UHEAP_MARKEND;
	return KErrNone;
	
	
	}

