/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Car;
import javax.microedition.location.*;


/**
 * is used to let the use accept using gps data at the start of the application.
 * @author Suha
 */
public class ToAcceptGPS extends Thread implements LocationListener{
/**
 * reference to the midlet class.
 */
    private driverMidlet midlet;
 /**
 * represeent that the journy started.if it is equal true.
 */

    LocationProvider lp;

  /**
   * the constructor get a refernce to the midlet class.
   * @param midlets reference from the midlet class
   */
    public ToAcceptGPS(driverMidlet midlets)
        {
           this.midlet = midlets;
           }

/**
 * start querying GPS data.
 */
    public void run()
        {



 Criteria cr = new Criteria();
    cr.setHorizontalAccuracy(500);
    cr.setSpeedAndCourseRequired(true);


  try {

     lp = LocationProvider.getInstance(cr);
     lp.setLocationListener(ToAcceptGPS.this,15 ,-1,-1);

       }
  catch (Exception ex)
        {
      midlet.notCar();
 
          }

        }
    /**
     * get one location data from the gps and stop the gps.
     * @param arg0
     * @param l
     */
 public void locationUpdated(LocationProvider arg0, Location l) {
    
          
              stopgps();


 }//location update
 /**
  *Stop querying GPS data.
  */
 void stopgps()
{
     
	  new Thread(){
        public void run(){
        lp.setLocationListener(null, -1, -1, -1);
	    }
	  }.start();
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
