package Car;


import javax.microedition.lcdui.*;
import javax.microedition.io.*;
import java.io.*;
/**
 * is the component responsible of communicating with the Symbian C++ KeyCapture daemon.
 * @author Suha
 */
class KeyCapture {

/**
   * used to create a socket connection with the accelerometer daemon to send and receive data.
   */
StreamConnection conn;
/**
  * Represent the socket connection address.
  * <p> for example url="socket://127.0.0.1:8123"
  * <p> socket: is the protocol used.
  * <p> 127.0.0.1: is the standard IP address used for a loopback network connection. In other words, the connection is made to the same device.
  * <p> 8123: is the port number where the c++ daemon is listening for connection.
 */
 private static String url = "socket://127.0.0.1:8123";
  int nokeyact;
  int keyDur;
  boolean finish;
  /**
    * reference to the midlet class.
    */
driverMidlet drvm;
/**
 * the constructor initilaize nokeyact to 0 and keyDur to 0 and finish to false.
 * @param dm reference from the midlet class
 */
  KeyCapture(driverMidlet dm) {

    drvm=dm;
     nokeyact=0;
     keyDur=0;
     finish=false;
  }
/**
 * notify keylog deamon to start counting key interactions and their duration.
 */
  public void startkeylog()
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
       //if unable to connect to keylog deamon notify watcher to start running it.
         catch(IOException ioe)
           {
          
               drvm.watch.notifyWatchertoRunKey();
               startkeylog();
            }//catch


  }//requestcalllog
  /**
    * notify keylog deamon to send number key interactions and their duration.
    */
  public void endkeylog()
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

        nokeyact= Integer.parseInt(num);
           num=response.substring(in1+1,response.length());
             keyDur= Integer.parseInt(num);
            }
             else
            {
                nokeyact=0;
                  keyDur=0;
            }
           finish=true;

      }//try
       //if unable to connect to calllog deamon notify watcher to start running it.
           catch(IOException ioe)
           {
            ioe.printStackTrace();
          Alert a=  new Alert("Key capture server", "Unable to connect to Key capture server",null,AlertType.WARNING);

          drvm.display.setCurrent(a,drvm.elseform);
          drvm.destroyApp(true);
          drvm.notifyDestroyed();
            }//catch


  }//endcalllog
  /**
    * notify keylog deamon to close.
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
          Alert a=  new Alert("Key capture server", "Unable to connect to Key capture server",null,AlertType.WARNING);

          drvm.display.setCurrent(a,drvm.elseform);

            }//catch

      }


}//end of class
