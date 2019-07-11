#include"AccelometerReadings.h"
 AccelometerReadings::AccelometerReadings(): CActive(CActive::EPriorityStandard)
{   
		
		x=0;
		y=0;
		z=0;
		
		
		// Initialize and register accelerometer sensors  
		RegisterSensors(); 
		
		iLoger.Connect();
       iLoger.CreateLog(_L("myLogs"),_L("MyAcc1"),EFileLoggingModeOverwrite);
									
       iLoger.Write(_L("a"));
		// Add to active scheduler
		CActiveScheduler::Add(this);
	
		
}//Listener
void AccelometerReadings::HandleDataEventL(TRRSensorInfo aSensor, TRRSensorEvent aEvent) {
		if (aSensor.iSensorId == Kacc && callerStatus==1) 
			{
			x = aEvent.iSensorData1;
			y = aEvent.iSensorData2;
			z = aEvent.iSensorData3;
			 buffer.Copy(_L(""));
			 buffer.AppendNum(x);
			 buffer.Append('!');
			 buffer.AppendNum(y);
			 buffer.Append('@');
			 buffer.AppendNum(z);
			 buffer.Append('#');
			 iLoger.Write(_L("r"));
			blank.Write(buffer, iStatus);
			SetActive();
			
			}
		
		
	}
AccelometerReadings::~AccelometerReadings()
{

Cancel();
iLoger.Write(_L("e"));
UnregisterSensors();
}
/*** Initializes and registers accelerometer sensors***/
void AccelometerReadings::RegisterSensors()   
	{   
	RArray<TRRSensorInfo> sensorList;   
	CleanupClosePushL(sensorList);   
	// Retrieve list of available sensors   
	CRRSensorApi::FindSensorsL(sensorList);  
	// Get number of sensors available   
	TInt sensorCount = sensorList.Count(); 
	for (TInt i = 0; i < sensorCount; i++) 
		{    
		// We are interested only in the accelerometer sensor now    
		if (sensorList[i].iSensorId == Kacc)  
			{     
			iAccelerometerSensor = CRRSensorApi::NewL(sensorList[i]);    
			
			// Register this control as accelerometer data listener    
			iAccelerometerSensor->AddDataListener(this);
			break;   
			}//if    
		} //for  
	CleanupStack::PopAndDestroy(); //sensorList    
		
}
/*** Unregisters accelerometer sensors. */
void AccelometerReadings::UnregisterSensors()   
	{    
	// Unregister accelerometer data listener  
	iAccelerometerSensor->RemoveDataListener();   
	delete iAccelerometerSensor;   
     iAccelerometerSensor = NULL;  
    }

void AccelometerReadings::Start()
	{
	        TUint KTestPort=8567;
		    TInetAddr addr(KInetAddrLoop, KTestPort);
			User::LeaveIfError(socketServ.Connect());
			CleanupClosePushL(socketServ);//if function leaves ensure socket serv session closes
			User::LeaveIfError(listener.Open(socketServ, KAfInet,KSockStream, KProtocolInetTcp));
			User::LeaveIfError(listener.Bind(addr));  
			User::LeaveIfError(listener.Listen(1));
			blank.Open(socketServ);
			listener.Accept(blank, iStatus); 
			User::WaitForRequest(iStatus);
			if(iStatus != KErrNone) User::Leave(KErrGeneral);
		    blank.RecvOneOrMore(buffer, 0, iStatus, dummyLength);
			User::WaitForRequest(iStatus);	
			if(iStatus!= KErrNone) User::Leave(KErrGeneral);
			if(	(buffer[0] == 'p') ) 
				{	
				callerStatus=1;
				}
			
	}
void AccelometerReadings::RunL()
	{
		
if(iStatus != KErrNone)
	{
	TBuf8<6>aaa;
	aaa.AppendNum(iStatus.Int());
	iLoger.Write(aaa);
	User::Leave(KErrGeneral);
	}
else	
	iLoger.Write(_L("s"));		
}//runl

void AccelometerReadings::DoCancel()
{
Cancel();

}
/*
 * //////////////
			while(running) 
					{
					blank.Open(socketServ);
					listener.Accept(blank, status); 
					User::WaitForRequest(status);
					if(status != KErrNone) User::Leave(KErrGeneral);
					gConsole->Printf(_L("Accepted connection!!\n"));		
					blank.RecvOneOrMore(buffer, 0, status, dummyLength);
					User::WaitForRequest(status);	
					if(status != KErrNone) User::Leave(KErrGeneral);
					if(	(buffer[0] == 'p') ) 
						{
						User::After(30000000);
						buffer.Copy(_L("k"));
						blank.Write(buffer, status);
						//User::WaitForRequest(status); 
						//if(status != KErrNone) User::Leave(KErrGeneral);
						blank.Close();		
						} 		
					else if( (buffer[0] == 'c') ) 
						{
						//gConsole->Printf(_L("Closing server!!\n"));
						running = EFalse;	
						buffer.Copy(KClosing);
						blank.Write(buffer, status);
								User::WaitForRequest(status); 
								if(status != KErrNone) User::Leave(KErrGeneral);
								blank.Close();		
						socketServ.Close();		
							User::Exit(KErrNone);
						} 
					

					
					}//end of while loop
 *
 **/
