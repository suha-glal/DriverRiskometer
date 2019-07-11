/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Car;

/**
 * repesent the speed information we save in the record store.
 * @author Suha
 */
public class Speedinfo {
    /**
     * journey No.
     */
    int journyNo;
    /**
     * the speed value.
     */
    int speed;
    /**
     * day of month value.
     */
    int dayofmonth;
    /**
     * month value. should be betweeon 0 and 11.
     */
    int month;
   /**
    * set the journyNo, speed, dayofmonth and month values.
    * @param j journey No.
    * @param s the speed value.
    * @param dm day of month value.
    * @param mon  month value.
    */
    Speedinfo(int j,int s,int dm,int mon)
    {
        journyNo=j;
        speed=s;
        dayofmonth=dm;
        month=mon;
    }


}
