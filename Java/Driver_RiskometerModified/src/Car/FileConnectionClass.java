package Car;
import java.io.*;
import javax.microedition.lcdui.*;
import javax.microedition.io.*;
import javax.microedition.io.file.*;
/**
 * the class is created to get all the speed and journeys data saved in riskometer record stores.
 * @author Suha
 */
public class FileConnectionClass  {
/**
 * specifiy the driver where the information fetched from record store will be saved.
 */
    String drive="file:///E:/";
    /**
     * specifiy the path to save the file that includes the fetched data.
     */
    String path="";

    /**
    * reference to the midlet class.
    */
    driverMidlet midlet;
    /**
     * alert to notify when the data is save correctly or can not be saved.
     */
    Alert a;
    /**
     * the constructor initialize the path and call createFolder and createFile function.
     * @param m reference from the midlet class
     */
   public FileConnectionClass(driverMidlet m) {
       midlet=m;
      
       path=drive+"RiskometerData/";
       createFolder(path);
      

if(createFile())
{
  a=  new Alert("Data", "The Data was collected Successfully!",null,AlertType.CONFIRMATION);
  a.setTimeout(Alert.FOREVER);
}else
{
 a=  new Alert("Data", "There is no database!",null,AlertType.CONFIRMATION);
 a.setTimeout(Alert.FOREVER);
}
  
   midlet.display.setCurrent(a,midlet.About);
   }
/**
 * create folder to save the file that the data will be saved in .
 * @param foldername the folder to create name.
 */
  public void createFolder(String foldername) {
   try {
      FileConnection filecon = (FileConnection)
         Connector.open(foldername);
      // Always check whether the file or directory exists.
      // Create the file if it doesn't exist.
      if(!filecon.exists()) {
         filecon.mkdir();
            }
      filecon.close();
   } catch(IOException ioe) {
   }
}

 /**
  * first fetch the speed and the journeys data from record store and write it to the file and finally save the file.
  * @return <code>boolean</code> if the data was saved correctly return true else return false.
  */
public boolean createFile() {
    
    SpeedRecordStore SRS= new SpeedRecordStore(false);
      JournyRecordStore JRS=new JournyRecordStore(false);
if(SRS.spexist==true &&JRS.jorexist==true){
    SRS.readRecords();
    JRS.readRecords();

   try {
      FileConnection fileconspeed = (FileConnection)
         Connector.open(path+"speed"+".txt");

      FileConnection fileconjourny = (FileConnection)
         Connector.open(path+"journy"+".txt");

      
      // Always check whether the file or directory exists.
      // Create the file if it doesn't exist.
      if(fileconspeed.exists())
          fileconspeed.delete();
          fileconspeed.create();
          
      if(fileconjourny.exists())
          fileconjourny.delete();
         fileconjourny.create();
             
     
             

     String sspeed="";
      String sjourny="";

      

      for (int i=0;i<SRS.speedvec.size();i++)
          {
            Speedinfo spi=(Speedinfo)SRS.speedvec.elementAt(i);
            sspeed+=spi.journyNo+"@"+spi.speed+"*"+spi.dayofmonth+"$"+spi.month+"%";
           }//i
    for (int i=0;i<JRS.jurvec.size();i++)
          {
            Journey jor=(Journey)JRS.jurvec.elementAt(i);
            sjourny+=jor.journyNo+" "+jor.maxspeed+" "+jor.avgspeed+" "+jor.dialedorrecived+" "+jor.callduration
                    +" "+jor.keyintact+" "+jor.Durkeyintact+" "+jor.duration+" "+jor.year+" "+jor.month+" "+jor.dayofmonth
                    +" "+jor.hour+" "+jor.minute+" ";

           }//i

    OutputStream osx = fileconspeed.openOutputStream();
    osx.write(sspeed.getBytes());
    osx.close();
      fileconspeed.close();

     OutputStream osy = fileconjourny.openOutputStream();
     osy.write(sjourny.getBytes());
     osy.close();
      fileconjourny.close();
 
       return true;
   } catch(IOException ioe) {
       return false;
   }
  
   }
   else
       return false;
}
  
  
}