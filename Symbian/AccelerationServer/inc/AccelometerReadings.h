#ifndef __ACCELOMETERREADINGS_H__
#define __ACCELOMETERREADINGS_H__
 
#include <rrsensorapi.h>
#include <flogger.h>
#include <in_sock.h>

const TInt Kacc = 0x10273024; 
class AccelometerReadings :public CActive, public MRRSensorDataListener
	{
public:
	
		TInt x;
		TInt y;
		TInt z;
		
AccelometerReadings();
 ~AccelometerReadings();
 void Start();
 
 //CActive
 void RunL();
 void DoCancel();


 

private: 
	
	// Functions from base classes   
	/**From MRRSensorDataListener.         
	 ** Callback function for receiving sensor data events.            
	 ** @param aSensor identifies the sensor that created the event.       
	 ** @param aEvent contains data about created event.       
	 **/     
	void HandleDataEventL(TRRSensorInfo aSensor, TRRSensorEvent aEvent); 
	private:      
		/**        
		 * Initializes and registers accelerometer sensors.   
		 */      
		void RegisterSensors();    
		/* Unregisters accelerometer sensors. */ 
		void UnregisterSensors(); 
		private:  // Data     
CRRSensorApi* iAccelerometerSensor;
/////////////////////////
TInt callerStatus;
		 enum TLoadStates
		  {
		  EAccept,
		  EReceiving,
		  ERecConfi,
		  EClose,
		  ESending
		  	};
RSocketServ socketServ;
RSocket listener;
RSocket blank;
TSockXfrLength dummyLength;
TBuf8<256> buffer;
////////////////////////
RFileLogger iLoger;
};
#endif 
