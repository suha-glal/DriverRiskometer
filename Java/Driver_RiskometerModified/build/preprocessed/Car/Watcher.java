package Car;


import javax.microedition.lcdui.*;
import javax.microedition.io.*;
import java.io.*;
/**
 * is the component responsible of communicating with the Symbian C++ Watcher daemon.
 * @author Suha
 */
class Watcher {

/**
   * used to create a socket connection with the accelerometer daemon to send and receive data.
   */
StreamConnection conn;
/**
  * Represent the socket connection address.
  * <p> for example url="socket://127.0.0.1:8998"
  * <p> socket: is the protocol used.
  * <p> 127.0.0.1: is the standard IP address used for a loopback network connection. In other words, the connection is made to the same device.
  * <p> 8998: is the port number where the c++ daemon is listening for connection.
 */
 private static String url = "socket://127.0.0.1:8998";
/**
  * reference to the midlet class.
  */
driverMidlet drvm;
/**
 * the constructor save refernce from the midlet.
 * @param dm reference from the midlet class
 */
  Watcher(driverMidlet dm) {

    drvm=dm;

  }
/**
 * notify the watcher deamon to start acceleration ,calllog and keylog deamons.
 */
  public void notifyWatchertoStart()
  {


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

     if(response.equals("D"))
    drvm.canStart=true;





           }//try
           catch(IOException ioe)
           {
            ioe.printStackTrace();
          Alert a=  new Alert("Starting", "Unable to start the application",null,AlertType.WARNING);

          drvm.display.setCurrent(a,drvm.elseform);

          drvm.notifyDestroyed();
            }//catch


  }//requestcalllog
  /**
   * notify watcher deamon to start the accelerometer deamon.
   */
  public void notifyWatchertoRunAcc()
  {


       try{

    StreamConnection conn = (StreamConnection)Connector.open(url);

            OutputStream out = conn.openOutputStream();

          // "20091128:160000.0"
          byte[] buf ="a".getBytes();
            out.write(buf, 0, buf.length);
            out.flush();
            out.close();

            byte[] data = new byte[256];
            InputStream in = conn.openInputStream();

            int actualLength = in.read(data);



            String response = new String(data, 0, actualLength);
            in.close();
            conn.close();

     if(response.equals("a"))
    drvm.canStart=true;





           }//try
           catch(IOException ioe)
           {
            ioe.printStackTrace();
          Alert a=  new Alert("Starting", "Unable to start the application",null,AlertType.WARNING);

          drvm.display.setCurrent(a,drvm.elseform);

          drvm.notifyDestroyed();
            }//catch


  }//requestcalllog
  /**
   * notify watcher to start calllog deamon.
   */
  public void notifyWatchertoRunCall()
  {


       try{

    StreamConnection conn = (StreamConnection)Connector.open(url);

            OutputStream out = conn.openOutputStream();

          // "20091128:160000.0"
          byte[] buf ="l".getBytes();
            out.write(buf, 0, buf.length);
            out.flush();
            out.close();

            byte[] data = new byte[256];
            InputStream in = conn.openInputStream();

            int actualLength = in.read(data);



            String response = new String(data, 0, actualLength);
            in.close();
            conn.close();

     if(response.equals("l"))
    drvm.canStart=true;





           }//try
           catch(IOException ioe)
           {
            ioe.printStackTrace();
          Alert a=  new Alert("Starting", "Unable to start the application",null,AlertType.WARNING);

          drvm.display.setCurrent(a,drvm.elseform);

          drvm.notifyDestroyed();
            }//catch


  }//requestcalllog
  /**
   * notify watcher to start keylog deamon.
   */
  public void notifyWatchertoRunKey()
  {


       try{

    StreamConnection conn = (StreamConnection)Connector.open(url);

            OutputStream out = conn.openOutputStream();

          // "20091128:160000.0"
          byte[] buf ="k".getBytes();
            out.write(buf, 0, buf.length);
            out.flush();
            out.close();

            byte[] data = new byte[256];
            InputStream in = conn.openInputStream();

            int actualLength = in.read(data);



            String response = new String(data, 0, actualLength);
            in.close();
            conn.close();

     if(response.equals("k"))
    drvm.canStart=true;





           }//try
           catch(IOException ioe)
           {
            ioe.printStackTrace();
          Alert a=  new Alert("Starting", "Unable to start the application",null,AlertType.WARNING);

          drvm.display.setCurrent(a,drvm.elseform);

          drvm.notifyDestroyed();
            }//catch


  }//requestcalllog
  /**
   * notify watcher deamon that the application closed.
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
