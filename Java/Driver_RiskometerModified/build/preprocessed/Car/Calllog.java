package Car;

import javax.microedition.lcdui.*;
import javax.microedition.io.*;
import java.io.*;
/**
 * is the component responsible of communicating with the Symbian C++ calllog daemon.
 * @author Suha
 */
class Calllog {

/**
   * used to create a socket connection with the accelerometer daemon to send and receive data.
   */
StreamConnection conn;
/**
  * Represent the socket connection address.
  * <p> for example url="socket://127.0.0.1:8101"
  * <p> socket: is the protocol used.
  * <p> 127.0.0.1: is the standard IP address used for a loopback network connection. In other words, the connection is made to the same device.
  * <p> 8101: is the port number where the c++ daemon is listening for connection.
 */
 private static String url = "socket://127.0.0.1:8101";
 /**
  * number of calls the user made while driving sent by the symbian C++ calllog daemon.
  */
  int noCall;
  /**
   * duration of calls the user made while driving sent by the symbian C++ calllog daemon.
   */
  int callDur;
  /**
   * indicate if the java client finished reciving number and duration of calls from calllog daemon.
   * If finish = true then the client finished reciving data from calllog deamon else finish = false then it did not finish yet.
   */
  boolean finish;
  /**
   * reference to the midlet class.
   */
  driverMidlet drvm;
  /**
   * the constructor initilaize noCall to 0 and callDur to 0 and finish to false.
   * @param dm reference from the midlet class
   */
  Calllog(driverMidlet dm)
  {
        drvm=dm;
        noCall=0;
        callDur=0;
        finish=false;
  }
/**
 * notify calllog deamon to start counting the calls and their duration.
 */
  public void startcalllog()
  {
      finish=false;

       try{

    StreamConnection conn = (StreamConnection)Connector.open(url);

            OutputStream out = conn.openOutputStream();

          // "20091128:160000.0"
          byte[] buf ="s".getBytes();
            out.write(buf, 0, buf.length);
            out.flush();
            out.close();

            byte[] data = new byte[256];
            InputStream in = conn.openInputStream();

            int actualLength = in.read(data);



            String response = new String(data, 0, actualLength);
            in.close();
            conn.close();



      }//try
       //if unable to connect to calllog deamon notify watcher to start running it.
           catch(IOException ioe)
           {
           
               drvm.watch.notifyWatchertoRunCall();
               startcalllog();
            }//catch


  }//requestcalllog
  /**
    * notify calllog deamon to send number of calls and their duration.
    */
  public void endcalllog()
  {

       try{

    StreamConnection conn = (StreamConnection)Connector.open(url);

            OutputStream out = conn.openOutputStream();

          // "20091128:160000.0"
          byte[] buf ="e".getBytes();
            out.write(buf, 0, buf.length);
            out.flush();
            out.close();

            byte[] data = new byte[256];
            InputStream in = conn.openInputStream();

            int actualLength = in.read(data);



            String response = new String(data, 0, actualLength);
            in.close();
            conn.close();



            int in1=0;
            String num;
            if(response.indexOf('#')!=-1){
            in1=response.indexOf('#');
           num=response.substring(0, in1);
           noCall= Integer.parseInt(num);
           num=response.substring(in1+1,response.length());
           callDur= Integer.parseInt(num);
            }
             else
            {
                noCall=0;
                callDur=0;
            }
           finish=true;

      }//try
       //if unable to connect to calllog deamon notify watcher to start running it.
       catch(IOException ioe)
           {
            ioe.printStackTrace();
          Alert a=  new Alert("Call log server", "Unable to connect to Call log server",null,AlertType.WARNING);

          drvm.display.setCurrent(a,drvm.elseform);
          drvm.destroyApp(true);
          drvm.notifyDestroyed();
            }//catch


  }//endcalllog
  /**
    * notify calllog deamon to close.
    */
 public void close() {

     try{

    StreamConnection conn = (StreamConnection)Connector.open(url);

            OutputStream out = conn.openOutputStream();
          byte[] buf = "c".getBytes();
            out.write(buf, 0, buf.length);
            out.flush();
            out.close();

            byte[] data = new byte[256];
            InputStream in = conn.openInputStream();
            int actualLength = in.read(data);
            String response = new String(data, 0, actualLength);
            in.close();
            conn.close();

           }//try
           catch(IOException ioe)
           {
            ioe.printStackTrace();
          Alert a=  new Alert("Call log server", "Unable to connect to Call log server",null,AlertType.WARNING);

          drvm.display.setCurrent(a,drvm.elseform);

            }//catch

      }


}//end of class
