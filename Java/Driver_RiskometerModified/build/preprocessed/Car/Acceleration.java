
 
package Car;
import javax.microedition.lcdui.*;
import javax.microedition.io.*;
import java.io.*;
/**
 *  is the component responsible of communicating with the Symbian C++ acceleration daemon.
 * @author Suha
 */
class Acceleration implements Runnable {
  /**
   * used to run the stream connection code.
   */
  Thread thrd;
  /**
   * used to check if the communication with the accelerometer daemon suspended or not.
   * <p> if suspended= true the communication is suspended.
   * <p> if suspended= false the communication is not suspended.
   */
  boolean suspended;
 /**
   * used to check if the communication with the accelerometer daemon stopped or not.
   * <p> if stopped= ture the communication stopped.
   * <p> if stopped= false the communication is stopped.
   */
  boolean stopped;
/**
   * used to create a socket connection with the accelerometer daemon to send and receive data.
   */
 StreamConnection conn;
 /**
  * Represent the socket connection address.
  * <p> for example url="socket://127.0.0.1:8567"
  * <p> socket: is the protocol used.
  * <p> 127.0.0.1: is the standard IP address used for a loopback network connection. In other words, the connection is made to the same device.
  * <p> 8567: is the port number where the c++ daemon is listening for connection.
 */
 private static String url = "socket://127.0.0.1:8567";

  /**
   * to save the index of the first occurrence  of "!" or "#" in the string sent by the accelemoter daemon.
   */
  int in1=0;
  /**
   * to save the index of the first occurrence of "@" in the string sent by the accelemoter daemon.
   */
  int in2=0;
  /**
   * to save the x acceleration sent by the accelemoter daemon.
   */
  int accX=0;
   /**
   * to save the y acceleration sent by the accelemoter daemon.
   */
  int accY=0;
   /**
   * to save the Z acceleration sent by the accelemoter daemon.
   */
  int accZ=0;
  /**
   * to save number of integers in acc1[] array and show the index where the next value must be saved.
    */
  int ar1=0;
  /**
   * to save number of integers in acc2[] array and show the index where the next value must be saved.
   */
  int ar2=0;
  /**
   * to save the substring that represents an <code>int</code>.
   * <p> For example:String response="57!6#7#"
   * <p> num= response.substring(0, 3);
   * <p> then num="57";
   */
   String num="";
   /**
    * indicate which array should be sent to  printState function.
    * <p> For example: if take=1 then send acc1 and if take=2 then send acc2.
    */
   int take=1;
   /**
    * used to save <code>int</code> value that equals
    * <p>(int)Math.sqrt((double)(accX*accX)+(accY*accY)+(accZ*accZ));
    * <p> the array of this array is used to now the motion state whether in the car or not
    */
    int acc1[];
    /**
    * used to save <code>int</code> value that equals
    * <p>(int)Math.sqrt((double)(accX*accX)+(accY*accY)+(accZ*accZ));
    * <p> the array of this array is used to now the motion state whether in the car or not
    */
    int acc2[];
    /**
    * is used to check if the the mobile is in the car or not.
    */
    int car=0;
    /**
    * reference to the midlet class.
    */
   driverMidlet drv;
   /**
    * Class constructor
    * <p> initializition:
    * <p>thrd : create new Thread
    * <p>suspended : initial value is false
    * <p>stopped: initial value is false
    * <p>acc1: initial it to hold 120 value
    * <p>acc2: initial it to hold 120 value
    * @param name is the name of the thread that the code will execute in
    * @param d reference from the midlet class
    */
  Acceleration(String name,driverMidlet d) {
    thrd = new Thread(this, name);
    suspended = false;
    stopped = false;
    drv=d;
  
    acc1=new int[120];
    acc2=new int[120];
   
  }
  /**
   * Starts runing the thread thrd.
   */
  public void start()
  {
    
 thrd.start();
  }
/**
*  handle the communications with the accelerometer daemon. 
*<p>It sends 'p' to the daemon to request acceleration data. Once the acceleration data is received in the following format “number!number@number#”, it processes this string to get X acceleration, Y acceleration, and Z acceleration. It then compute the Euclidean distance for the acceleration using the following formula: 
*<p> int value= (int) Math.sqrt ((double)(accX*accX)+(accY*accY)+(accZ*accZ));
* <p>Then save the resulting value in array acc1 or acc2 depending on their turn. If array acc1 was filled with 120 value then it will be sent to printState function. Same goes for  array acc2 .This process is repeated until the thread is suspended or stopped
*@see #stopped
 * @see #suspended
 * @see #acc1
 * @see #acc2
 * @see #accX
 * @see #accY
 * @see #accZ
 * @see #ar1
 * @see #ar2
 * @see #conn
 * @see #url
 * @see #in1
 * @see #in2
 * @see #num
 * @see #take
 * @see #printState(int[])
 *
 */
   
  public void run() {
    
    try {
     
         
            while(!stopped||!suspended){
            try{

    conn = (StreamConnection)Connector.open(url);

          OutputStream out = conn.openOutputStream();
          byte[] buf = "p".getBytes();
            out.write(buf, 0, buf.length);
            out.flush();
            out.close();

            byte[] data = new byte[256];
            InputStream in = conn.openInputStream();
            int actualLength = in.read(data);
            String response = new String(data, 0, actualLength);
            in.close();
            conn.close();
           System.out.println(response);
           in1=response.indexOf('!');
           num=response.substring(0, in1);
           accX= Integer.parseInt(num);
           in2=response.indexOf('@');
           num=response.substring(in1+1, in2);
           accY= Integer.parseInt(num);
           in1=response.indexOf('#');
           num=response.substring(in2+1, in1);
           accZ= Integer.parseInt(num);
        if(take==1)
        {
            acc1[ar1]=(int)Math.sqrt((double)(accX*accX)+(accY*accY)+(accZ*accZ));
            ar1++;

        }
        else if(take==2)
        {
            acc2[ar2]=(int)Math.sqrt((double)(accX*accX)+(accY*accY)+(accZ*accZ));
            ar2++;
        }
        if(ar1==120)
          {
          take=2;
          ar1=0;
          new Thread()
          {
              public void run(){
                   printState(acc1);
              }
          }.start();


          }//if
           else if(ar2==120)
          {
          take=1;
          ar2=0;
          new Thread()
          {
              public void run(){
                   printState(acc2);
              }
          }.start();


          }//if
             }//try
           catch(IOException ioe)
           {
         
               drv.watch.notifyWatchertoRunAcc();
            }//catch

      //}
        synchronized (this) {
          while (suspended)
            wait();
          if (stopped)
            break;
        }
      }//while
    } catch (InterruptedException exc) {
      System.out.println(thrd.getName() + " interrupted.");
    }
    System.out.println("\n" + thrd.getName() + " exiting.");
    
  }//run
/**
 * called to stop the communication with the accelerometer daemon
 */
  synchronized void stop() {
    stopped = true;
    suspended = false;
    notify();
  }
/**
 * called to suspend the communication with the accelerometer daemon
 */
  synchronized void suspend() {
    suspended = true;
  }
/**
 * called to resume the communication with the accelerometer daemon after suspension
 */
  synchronized void resume() {
    suspended = false;
    notify();
  }
  /**
   * sends 'c' to the accelerometer daemon to indicate that the application is closing. This will cause the accelerometer daemon to close as well.
   */
  public void close() {

     try{

     conn = (StreamConnection)Connector.open(url);

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
        Alert a=  new Alert("Acc server", "Unable to connect to acc server.",null,AlertType.WARNING);

          drv.display.setCurrent(a,drv.elseform);

            }//catch

      }//close
  /**
   * printState gets array of 120 value of acceleration it sends this array to InTheCar function which will return int value.
   * If InTheCar sents 1 or 2 or 3 then the user is not int the car but if it sends 4 then the user maybe in the car.
   * <p> the function keeps updating motion state in the midlet by calling drv.Update( motionState)
   * <p> If InTheCar sent 1 or 2 or 3 the field car=0 but if it sent 4 then we will increment field car. If the field car value becomes greater or equal to 9 then the user is definitely in the car.
   * we used 9 consecutive reading from InTheCar to make sure that the user is in the car and minimize false negative.
   * @param accarr is array of acceleration values.
   * @see #InTheCar(int[])
   * @see driverMidlet#Update(int)
   */
  void printState(int[] accarr)
  {
        int motionState=0;

            motionState=InTheCar(accarr);
	if( motionState==1|| motionState==2|| motionState==3)
		car=0;
	else
	{
	car++;
	if(car>=9)
	 motionState=4;

	else
          motionState=3;
	}//else
	drv.Update( motionState);
  }
  /**
   * InTheCar recive array of acceleration and analyze it to know whether the user is in the car or not.
   * <p>First it create array of integers of 119 value called slopes. Then it compute the slop between every 2 concutive elements in arr[] and save the slop in slopes[].
   * At the same time it sums the absoulte value of the slopes. After that we compute slopch which represent the number of slopes change.
   * The car motion is charcatrised by the following:
   * <p>1)absslopsum>=650 .
   * <p>2)(slopch>=38 && slopch<=50).
   * @param arr array of acceleration values
   * @return <code>int</code> motion state if still sends 1, if walking sends 2, if motion sends 3, if car sends 4
   */
int InTheCar(int []arr)
	{
	
	int xsize=120;

	int [] slopes= new int[119];
        int state=0;

int i,absslopsum=0,slopch=0;

for(i=0;i<xsize-1;i++)
{
slopes[i]=(arr[i+1]-arr[i]);
absslopsum+=(int)Math.abs(slopes[i]);
}

for(i=0;i<xsize-3;i++)
{
    if(slopes[i]<slopes[i+1]&&slopes[i+1]>slopes[i+2])
slopch++;

}

if(absslopsum>=650)
{
if(slopch<=33&&slopch>=20)// walking
    state=2;
//car
else if(slopch>=38&&slopch<=50)//slopch>=34&&slopch<=50
    state=4;
else
    state=3;//movement
}
else if(absslopsum>=240&&absslopsum<=630)//still
{
state=1;
}
else//movement
{
    state=3;
}
return state;
	}//IntheCar
}//end of class
