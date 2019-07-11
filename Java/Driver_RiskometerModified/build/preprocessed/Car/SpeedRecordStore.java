/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Car;
import javax.microedition.rms.*;
import java.util.*;
/**
 * the class manage fetching speed information from the database. Also, manage writeing, opening closing and deleting the database.
 * @author Suha
 */
public class SpeedRecordStore {
  private RecordStore rs = null;
  static final String REC_STORE = "db_speed";
  public Vector speedvec;
  public Vector speedvecLastJorny;
  private int numOfspeedinfo=0;
  int numOflastjorSpeedRec=0;
  boolean spexist=false;
 /**    * open the record store.
    * @param it equal true if we want to open the database false if we do not want to open record store.
    */
 SpeedRecordStore (boolean open)
 {

     openRecStore(open);
     
 }
 /**
  * get number of journeys in the database.
  * @return number of journeys in the database.
  */
 public int getNoofSpeedRecords()
 {
    
    return numOfspeedinfo;

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
      rs = RecordStore.openRecordStore(REC_STORE, open );
    spexist=true;
    }
    catch (Exception e)
    {
        spexist=false;
      db(e.toString());
    }
  }
/**
 * close the record store.
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
 * @return
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
   * write speed information in the record store.
   * @param speed the user current speed
   * @param journyNo journy number
   */
public void writeSpeedinfo(int speed,int journyNo)
  {
  Date d= new Date();
  Calendar c = Calendar.getInstance();
  c.setTime(d);
 int month=c.get(Calendar.MONTH);
 int dayofmonth=c.get(Calendar.DAY_OF_MONTH);
// int hour=c.get(Calendar.HOUR_OF_DAY);
// int minute=c.get(Calendar.MINUTE);
   
   writeRecord(""+journyNo);
   writeRecord(""+month);
   writeRecord(""+dayofmonth);
   writeRecord(""+speed);
   //writeRecord(""+hour);
   //writeRecord(""+minute);
  
   

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
 * read the all the speed information saved in the recordstore.
 */
  public void readRecords()
  {
      int s=0,j=0,dm=0,month=0;

      speedvec= new Vector();
      String buf;
      int arrlen=0;
   // Intentionally make this too small to test code below
      byte[] recData = new byte[4];
      int len;
try{
         numOfspeedinfo=rs.getNumRecords()/4;
      for (int i = 1; i <= rs.getNumRecords(); i++)
      {
        if (rs.getRecordSize(i) > recData.length)
          recData = new byte[rs.getRecordSize(i)];

        len = rs.getRecord(i, recData, 0);
        buf= new String(recData, 0, len);
       
      
         if(i%4==1){
             j=Integer.parseInt(buf);
             //System.out.println("journyNo="+j);
         }
         else if(i%4==2)
         {
             month=Integer.parseInt(buf);
         }
         else if(i%4==3)
         {
        dm=Integer.parseInt(buf);
        //System.out.println("dayofmonth="+dm);
         }
        else if(i%4==0)
         {
             s=Integer.parseInt(buf);
            // System.out.println("speed(km/h)="+s);


           //System.out.println("------------------------------");
           Speedinfo sp= new Speedinfo (j,s,dm,month);
           speedvec.addElement(sp);
         }
      /*
         else if(i%5==4)
        {
            h=Integer.parseInt(buf);
            System.out.println("hour="+h);
         }
            else if (i%5==0)
        {
            mi=Integer.parseInt(buf);
            System.out.println("min="+mi);

           
        }*/
          
      }//for i

    }
    catch (Exception e)
    {
      db(e.toString());
    }
     //  return speedvec;
  }
  /**
   * read last record from record store.
   */
   public void readLastRecords()
  {
      int s=0,j=0,dm=0,month=0,jourNo=0;
numOflastjorSpeedRec=0;
      speedvecLastJorny= new Vector();
      String buf;

   // Intentionally make this too small to test code below
      byte[] recData = new byte[4];
      int len;
try{
        if (rs.getRecordSize(rs.getNumRecords()-3) > recData.length)
          recData = new byte[rs.getRecordSize((rs.getNumRecords()-3))];

        len = rs.getRecord((rs.getNumRecords()-3), recData, 0);
        buf= new String(recData, 0, len);
        jourNo=Integer.parseInt(buf);

      for (int i =rs.getNumRecords();i>0; i--)
      {
        if (rs.getRecordSize(i) > recData.length)
          recData = new byte[rs.getRecordSize(i)];

        len = rs.getRecord(i, recData, 0);
        buf= new String(recData, 0, len);


         if(i%4==1){
             j=Integer.parseInt(buf);
             //System.out.println("journyNo="+j);

              if(jourNo==j){
           Speedinfo sp= new Speedinfo (j,s,dm,month);
           System.out.println("j="+j);
           speedvecLastJorny.addElement(sp);
           numOflastjorSpeedRec++;
           }//if
           else
               break;
         }
         else if(i%4==2)
         {
             month=Integer.parseInt(buf);



         }
         else if(i%4==3)
         {
        dm=Integer.parseInt(buf);
        //System.out.println("dayofmonth="+dm);
         }
        else if(i%4==0)
         {
             s=Integer.parseInt(buf);
            // System.out.println("speed(km/h)="+s);


           }


      }//for i

    }
    catch (Exception e)
    {
      db(e.toString());
    }
     //  return speedvec;
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
