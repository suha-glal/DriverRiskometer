/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Car;
import javax.microedition.rms.*;
import java.util.*;
/**
 * the class manage fetching journeys information from the database. Also, manage writeing, opening closing and deleting the database.
 * @author Suha
 */
public class JournyRecordStore {

  public RecordStore rs = null;
  static final String REC_STORE = "db_journey";
  public Vector jurvec;
  private int numOfjourney=0;
  int lastjorRec=0;
  public Journey lastjor;
   boolean jorexist=false;
   /**
    * open the record store.
    * @param it equal true if we want to open the database false if we do not want to open record store.
    */
 JournyRecordStore (boolean open)
 {

     openRecStore(open);
    lastjor=null;
 }
 /**
  * get number of journeys in the database.
  * @return number of journeys in the database.
  */
public int getNoofJournies()
{
return numOfjourney;
}
/**
 * open the record store.
 * @param open it equal true if we want to open the database false if we do not want to open record store.
 */
public void openRecStore(boolean open)
  {
    try
    {
      // The second parameter indicates that the record store
      // should be created if it does not exist
      rs = RecordStore.openRecordStore(REC_STORE,open );
    jorexist=true;
    }
    catch (Exception e)
    {
        jorexist=false;
      db(e.toString());
    }
  }
/**
 * close  the record store.
 */
  public void closeRecStore()
  {
    try
    {
      rs.closeRecordStore();
    }
    catch (Exception e)
    {
      db(e.toString());
    }
  }
/**
 * delete the record store.
 * @return 1 if the record store was closed , 0 if we can not close it.
 */
  public int deleteRecStore()
  {
    if (RecordStore.listRecordStores() != null)
    {
      try
      {
        RecordStore.deleteRecordStore(REC_STORE);
        return 1;
      }
      catch (Exception e)
      {
        db(e.toString());
        return 0;
      }//catch
    }//if
    else return 1;
  }
  /**
   * write the journey information in the record store.
   * @param j journey number.
   * @param avgs jorney average speed.
   * @param mxs journey maximum speed.
   * @param darc number of calls.
   * @param calldur duration of calls.
   * @param k number of keys interaction.
   * @param duk duration of key interactions.
   * @param y the year the journey happend.
   * @param m the month the journey happend.
   * @param dm day of month the journay happend.
   * @param dw day of week the journey happend.
   * @param h hour of day the journey happend.
   * @param mi minute of day the journy happend.
   */
public void writeJournyinfo(int j,int avgs,int mxs,int darc,int calldur,int k,int duk,int y,int m,int dm,int dw,int h,int mi)
  {
 Journey jo= new Journey(j,avgs,mxs,darc,calldur,k,duk,y,m,dm,dw,h,mi,0);

   writeRecord(""+jo.journyNo);
   writeRecord(""+jo.avgspeed);
   writeRecord(""+jo.maxspeed);
   writeRecord(""+jo.dialedorrecived);
   writeRecord(""+jo.callduration);
   writeRecord(""+jo.keyintact);
   writeRecord(""+jo.Durkeyintact);
   writeRecord(""+jo.year);
   writeRecord(""+jo.month);
   writeRecord(""+jo.dayofmonth);
   writeRecord(""+jo.dayofweek);
   writeRecord(""+jo.hour);
   writeRecord(""+jo.minute);
   writeRecord(""+jo.duration);


  }
/**
 * write on record to the database.
 * @param str this string contains journey information
 */
  public void writeRecord(String str)
  {
    byte[] rec = str.getBytes();

    try
    {
      rs.addRecord(rec, 0, rec.length);
    }
    catch (Exception e)
    {
      db(e.toString());
    }
  }
/**
 * read read the records saved in the record store and save them in vector.
 */
  public void readRecords()
  {
      //14 item
     int j=0,avgs=0,mxs=0,darc=0,calldur=0,k=0,duk=0,y=0,m=0,dm=0,dw=0,h=0,mi=0,dur=0;
      jurvec= new Vector();
      String buf;
     
   // Intentionally make this too small to test code below
      byte[] recData = new byte[1];
      int len;
try{
    numOfjourney=rs.getNumRecords()/14;
      for (int i = 1; i <= rs.getNumRecords(); i++)
      {
        if (rs.getRecordSize(i) > recData.length)
          recData = new byte[rs.getRecordSize(i)];

        len = rs.getRecord(i, recData, 0);
        buf= new String(recData, 0, len);


         if(i%14==1){
             j=Integer.parseInt(buf);
             //System.out.println("journyNo="+j);
         }
         else if(i%14==2)
         {
             avgs=Integer.parseInt(buf);
             //System.out.println("Avg speed(km/h)="+avgs);
         }
else if(i%14==3)
         {
             mxs=Integer.parseInt(buf);
            // System.out.println("max speed(km/h)="+mxs);
         }
       
        else if(i%14==4)
         {
             darc=Integer.parseInt(buf);
             //System.out.println("No of dailed or recived calls="+darc);
         }
        else if(i%14==5)
         {
             calldur=Integer.parseInt(buf);
            // System.out.println("call duration="+calldur);
         }
        else if(i%14==6)
         {
             k=Integer.parseInt(buf);
             //System.out.println("key="+k);
         }
        else if(i%14==7)
         {
             duk=Integer.parseInt(buf);
             //System.out.println("key="+k);
         }
        else if(i%14==8)
         {
             y=Integer.parseInt(buf);
            // System.out.println("year="+y);
         }
        else if(i%14==9)
         {
             m=Integer.parseInt(buf);
             //System.out.println("month="+m);
         }
        else if(i%14==10)
         {
             dm=Integer.parseInt(buf);
             //System.out.println("dayof month="+dm);
         }
         else if(i%14==11)
        {
            dw=Integer.parseInt(buf);
            //System.out.println("dayof week="+dw);
         }
         else if(i%14==12)
        {
            h=Integer.parseInt(buf);
            //System.out.println("hour="+h);
         }
         else if(i%14==13)
        {
            mi=Integer.parseInt(buf);
            //System.out.println("min="+mi);
         }
            else if (i%14==0)
        {
            dur=Integer.parseInt(buf);
            //System.out.println("duration (sec)="+dur);

           //System.out.println("------------------------------");
           Journey jo= new Journey(j,avgs,mxs,darc,calldur,k,duk,y,m,dm,dw,h,mi,dur);
             jurvec.addElement(jo);
        }

      }//for i

    }
    catch (Exception e)
    {
      db(e.toString());
    }
     //  return jurvec;
  }
  /**
   * read last journey record from the record store.
   */
   public void readLastJorRecords()
  {
      //14 item
     int j=0,avgs=0,mxs=0,darc=0,calldur=0,k=0,duk=0,y=0,m=0,dm=0,dw=0,h=0,mi=0,dur=0;
      
      String buf;

   // Intentionally make this too small to test code below
      byte[] recData = new byte[1];
      int len;
try{
   lastjorRec=rs.getNumRecords()/14;
      for (int i =rs.getNumRecords(); i >=rs.getNumRecords()-13 ; i--)
      {
        if (rs.getRecordSize(i) > recData.length)
          recData = new byte[rs.getRecordSize(i)];

        len = rs.getRecord(i, recData, 0);
        buf= new String(recData, 0, len);


         if(i%14==1){
             j=Integer.parseInt(buf);
             //System.out.println("journyNo="+j);
              lastjor= new Journey(j,avgs,mxs,darc,calldur,k,duk,y,m,dm,dw,h,mi,dur);

         }
         else if(i%14==2)
         {
             avgs=Integer.parseInt(buf);
             //System.out.println("Avg speed(km/h)="+avgs);
         }
        else if(i%14==3)
         {
             mxs=Integer.parseInt(buf);
            // System.out.println("max speed(km/h)="+mxs);
         }

        else if(i%14==4)
         {
             darc=Integer.parseInt(buf);
             //System.out.println("No of dailed or recived calls="+darc);
         }
        else if(i%14==5)
         {
             calldur=Integer.parseInt(buf);
            // System.out.println("call duration="+calldur);
         }
        else if(i%14==6)
         {
             k=Integer.parseInt(buf);
             //System.out.println("key="+k);
         }
        else if(i%14==7)
         {
             duk=Integer.parseInt(buf);
             //System.out.println("key="+k);
         }
        else if(i%14==8)
         {
             y=Integer.parseInt(buf);
            // System.out.println("year="+y);
         }
        else if(i%14==9)
         {
             m=Integer.parseInt(buf);
             //System.out.println("month="+m);
         }
        else if(i%14==10)
         {
             dm=Integer.parseInt(buf);
             //System.out.println("dayof month="+dm);
         }
         else if(i%14==11)
        {
            dw=Integer.parseInt(buf);
            //System.out.println("dayof week="+dw);
         }
         else if(i%14==12)
        {
            h=Integer.parseInt(buf);
            //System.out.println("hour="+h);
         }
         else if(i%14==13)
        {
            mi=Integer.parseInt(buf);
            //System.out.println("min="+mi);
         }
            else if (i%14==0)
        {
            dur=Integer.parseInt(buf);


        }

      }//for i

    }
    catch (Exception e)
    {
      db(e.toString());
    }
     //  return jurvec;
  }
   /**
    * get last journey number.
    * @param dayofmonth day of month value.
    * @param month month value.
    * @param year year value.
    * @return last journey saved in the record store number.
    */
int getJournyNo(int dayofmonth,int month,int year)
{
    //14 item
     int j=0,y=0,m=0,dm=0;
      jurvec= new Vector();
      String buf;

   // Intentionally make this too small to test code below
      byte[] recData = new byte[1];
      int len;

try{
        int s=rs.getNumRecords();
        if (s==0)
            return 1;

        int i=s-13;
        if (rs.getRecordSize(i) > recData.length)
          recData = new byte[rs.getRecordSize(i)];

        len = rs.getRecord(i, recData, 0);
        buf= new String(recData, 0, len);

             j=Integer.parseInt(buf);
            // System.out.println("journyNo="+j);

        i=s-6;
        if (rs.getRecordSize(i) > recData.length)
          recData = new byte[rs.getRecordSize(i)];

        len = rs.getRecord(i, recData, 0);
        buf= new String(recData, 0, len);


             y=Integer.parseInt(buf);
             //System.out.println("year="+y);

         i=s-5;
        if (rs.getRecordSize(i) > recData.length)
          recData = new byte[rs.getRecordSize(i)];

        len = rs.getRecord(i, recData, 0);
        buf= new String(recData, 0, len);
             m=Integer.parseInt(buf);
            // System.out.println("month="+m);

        i=s-4;
        if (rs.getRecordSize(i) > recData.length)
          recData = new byte[rs.getRecordSize(i)];

        len = rs.getRecord(i, recData, 0);
        buf= new String(recData, 0, len);
             dm=Integer.parseInt(buf);
            // System.out.println("dayof month="+dm);




    }//try
    catch (Exception e)
    {
      db(e.toString());
    }

    if(year!=y||month!=m||dayofmonth!=dm)
                 return 1;
             else
                 return j++;


}

  /**
  * Simple message to console for debug/errors
  * When used with Exceptions we should handle the
  * error in a more appropriate manner.
  */
  private void db(String str)
  {
    System.err.println("Msg: " + str);
  }

}
 /*
  Date d= new Date();
  Calendar c = Calendar.getInstance();
  c.setTime(d);

 int year= c.get(Calendar.YEAR);
 int month=c.get(Calendar.MONTH);
 int dayofmonth=c.get(Calendar.DAY_OF_MONTH);
 int dayofweek=c.get(Calendar.DAY_OF_WEEK);
 int hour=c.get(Calendar.HOUR_OF_DAY);
 int minute=c.get(Calendar.MINUTE);
*/