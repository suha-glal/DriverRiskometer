/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Car;
import javax.microedition.location.*;

/**
 * the class responsible of communicating with the mobile gps to get speed update.
 * @author Suha
 */
class Retriever extends Thread implements LocationListener{
    

/**
 * reference to the midlet class.
 */
    private driverMidlet midlet;
    /**
     * represents a source of the location information.
     */
    LocationProvider lp;
/**
 *  to show if the gps is running and we are aquring the speed update.
 */
    boolean queryingGPS;
    /**
     * to save current speed we get from the gps.
     */
    float speed=0;
    /**
     * the variable is used to count how many times the speed was less than 7 km/hr.
     */
    int time=0;
   
     // journy info
    /**
     * the variable save the maximum speed for the current journey.
     */
    float maxspeed;
    /**
     * the variable save the cumulative speed for the current journey.
     */
    float cumSpeed;
    /**
     * it count how many times we got speeds data from the gps.
     */
    int speedcounter;
    /**
     * the variable save the current speed in km/hr.
     */
    float kmhspeed;
    /**
     * the variable save the average speed of the journey.We get it by dividing cumSpeed by speedcounter.
     */
    int avgspeed;
    /**
     * the variable count how many times the gps gave a signal that showing that the mobile is in side a building or can not recive valid location.
     */
    int indoors;
/**
 * indicate that this is the first time we get speed from the gps.
 */
    boolean first;
 /**
 * represent that the journy ended and the data were saved succesfully, if it is equal true.
 */
    boolean last;
    /**
     * if the journey started the variable equal true ,if not the variable equal true.
     */
    boolean journyStarted;
    /**
     *initalize the variables.
     * @param midlets reference from the midlet class
     */
    public Retriever(driverMidlet midlets)
        {
           this.midlet = midlets;
           initalizeVaribles();
                     
         }
  /**
   * initialize the class varibles.
   */
void initalizeVaribles()
    {
    queryingGPS=false;
    first=true;
    last=false;
    journyStarted=false;
    maxspeed=0;
    cumSpeed=0;
    kmhspeed=0;
    speedcounter=0;
    avgspeed=0;
    time=0;
    indoors=0;
    }
/**
 * start the gps reciver and get location update every 15 sec.
 */
    public void run()
        {
         


 Criteria cr = new Criteria();
    cr.setHorizontalAccuracy(500);
    cr.setSpeedAndCourseRequired(true);


  try {
     
     lp = LocationProvider.getInstance(cr);
     lp.setLocationListener(Retriever.this,15 ,-1,-1);
 
       } 
  catch (Exception ex)
        {
      midlet.notCar();
  // midlet.mainviewrisk(1);
          }
  

        }

    /**
     * get continous speed update from the gps every 15 sec. Save speed update in speed record store and calculate the average speed and the maximum speed.
     * Also, check if the journey ended by getting speed update less than 7km/hr for 4 mins.
     * @param arg0
     * @param l
     */
 public void locationUpdated(LocationProvider arg0, Location l) {
    if(arg0.getState()==LocationProvider.AVAILABLE)
    {
          if(l.isValid()==false)
      {
          indoors++;
          //2 mins with no data
          if(indoors>=3){

              stopgps();
      if(journyStarted ==true && last==false)
               writejournytodatabase();
       
      midlet.notCar();
        midlet.mainviewrisk(1);
          }
      }

      else if (l != null && l.isValid()==true) {


     


speed =l.getSpeed();
 if(speed>=0 && speed!=Float.NaN){
 
  indoors=0;
    //convert the speed from m/s to km/h
    kmhspeed=(float)(speed*3.6);
    //sjould be 7
if(kmhspeed >= 7.0)
    {

 if(first==true)
  {
   midlet.JournyInitalization();
   journyStarted =true;
   
    }
    //write the speed infor to the database round up the speed
     midlet.speeddatabase.writeSpeedinfo((int)Math.ceil(kmhspeed), midlet.journyNo);

    //info for journy database
    cumSpeed+=kmhspeed;
    speedcounter++;

    //get the maximum speed
    if(maxspeed<kmhspeed)
        maxspeed=kmhspeed;


 midlet.Speedlab.setText(""+kmhspeed+" km/h");

 midlet.maxspeedlab.setText(""+maxspeed+" km/h");

avgspeed=(int)(Math.ceil(cumSpeed)/speedcounter);
 midlet.Avgspeedlab.setText(""+avgspeed+" km/h");




if(first==true)
 midlet.display.setCurrent(midlet.carform);

 first=false;
 time=0;
}// should be 7
else if(kmhspeed<7.0)
{
    time++;

 //should time 16 = 4 min
if(time>=16)
{    stopgps();
     writejournytodatabase();
     midlet.notCar();
     midlet.mainviewrisk(1);
}//if


}//else kmhspeed<=7
}//if speed NaN

}//if location not equal to null

    
    }
    else if((arg0.getState()==LocationProvider.TEMPORARILY_UNAVAILABLE)||(arg0.getState()==LocationProvider.OUT_OF_SERVICE))
    {

         stopgps();
             if(journyStarted ==true && last==false)
             writejournytodatabase();
             midlet.notCar();
             midlet.mainviewrisk(1);
       
     
    }
   
 }//location update

 /**
  *  Stop querying GPS data.
  */
 void stopgps()
{
     // Stop querying GPS data :
	  new Thread(){
        public void run(){
        lp.setLocationListener(null, -1, -1, -1);
	    }
	  }.start();
 }
 /**
  * Write the current journey data to the record store.First get number of calls and keys during the journey then write journey no,average speed ,maximum speed,number of calls, duration of calls,no. of key interactio duration of key interaction and journy duration.
  */
 void writejournytodatabase()
 {
   avgspeed=(int)(Math.ceil(cumSpeed)/speedcounter);


   midlet.calllogrecive();
   midlet.keysrecive();

while(midlet.calllogdaemon.finish==false)
{ 
}
while(midlet.keycapdaemon.finish==false)
{}

midlet.journydatabase.writeJournyinfo(midlet.journyNo,avgspeed,(int)maxspeed,
   midlet.calllogdaemon.noCall, midlet.calllogdaemon.callDur,midlet.keycapdaemon.nokeyact
   ,midlet.keycapdaemon.keyDur,midlet.year,midlet.month,midlet.dayofmonth
   ,midlet.dayofweek,midlet.hour,midlet.minute);

   last =true;
    initalizeVaribles();
    
}
 /**
  * Not supported by our implementation but this function is part of jsr 179 and you can get more information about it form the documentation.
  * @param arg0
  * @param arg1
  */
    public void providerStateChanged(LocationProvider arg0, int arg1) {
       throw new UnsupportedOperationException("Not supported yet.");
    
    }//providerStateChanged
   
}


