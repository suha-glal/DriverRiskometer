/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Car;
import java.util.*;
/**
 * the class represents journey variables.
 * @author Suha
 */
public class Journey {
/**
* journey number.
*/
int journyNo;
/**
 * average speed for the journey.
 */
int avgspeed;
/**
 * maximum speed for the journey.
 */
int maxspeed;
/**
 * number of dialed or recived calls.
 */
int dialedorrecived;
/**
 * call duration.
 */
int callduration;
/**
 * number of key interactions.
 */
int keyintact;
/**
 * duration of key interaction.
 */
int Durkeyintact;
/**
 * the year the journey happened.
 */
int year;
/**
 * the month the journey happened.
 */
int month;
/**
 * the day of month the journey happened.
 */
int dayofmonth;
/**
 * the day of week the journey happened (Saturday,Sunday,.....)
 */
int dayofweek;
/**
 * the hour of day the journey happened.
 */
int hour;
/**
 * the minutes value
 */
int minute;
/**
 * journey duration in minutes.
 */
int duration;
/**
 * initislize the journey variables.
 * @param j journey number.
 * @param as average speed for the journey.
 * @param mxs maximum speed for the journey.
 * @param darc number of dialed or recived calls.
 * @param calldur call duration.
 * @param k number of key interactions.
 * @param durk duration of key interaction.
 * @param y the year the journey happened.
 * @param m the month the journey happened.
 * @param dm the day of month the journey happened.
 * @param dw the day of week the journey happened (Saturday,Sunday,.....)
 * @param h the hour of day the journey happened.
 * @param mi the minutes value
 * @param dur journey duration in minutes.
 */
Journey(int j,int as,int mxs,int darc,int calldur,int k,int durk,int y,int m,int dm,int dw,int h,int mi,int dur)
{
journyNo=j;
avgspeed=as;
maxspeed=mxs;
dialedorrecived=darc;
callduration=calldur;
keyintact=k;
Durkeyintact=durk;
year=y;
month=m;
dayofmonth=dm;
dayofweek=dw;
hour=h;
minute=mi;

    if(dur==0)
        duration=getDuration();
    else
        duration=dur;
}//Journey
/**
 * calculate the journey duration in  minutes.
 * @return
 */
int getDuration()
{
Date d= new Date();
  Calendar c = Calendar.getInstance();
  c.setTime(d);
 int hresult;
 int mresult;
  int result;
 int hour2=c.get(Calendar.HOUR_OF_DAY);
 int minute2=c.get(Calendar.MINUTE);

 if(hour2<hour)
 {
 hresult=24-(hour-hour2);
 }
 else
 {
  hresult=hour2-hour;
 }
 mresult=minute2-minute;
 result=(hresult*60)+(mresult);
 return result;
}
}//Journey
