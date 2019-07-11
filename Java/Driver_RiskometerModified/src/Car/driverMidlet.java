
package Car;


import java.io.IOException;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.util.*;

/**
 *  This is the main class (MIDlet) were the j2me application starts executing
 * @author Suha
 */

public class driverMidlet extends MIDlet implements  CommandListener{
    /**
     * reference to the screen display
     */
    Display display;
    /**
     * the main application form.
     * The application logo is added to it.
     */
    Form start;
    /**
     * give a brief description of the application and its purpose.
     */
    Form About;
    /**
     * display the statistics of speed.
     */
    Form statdetailsform;
    /**
     * display the statistics of journey(No of Journys or Journy No,Average speed, Maxiumam Speed,No of call,Duration of calls,Key interactions, key interaction duration,Journys or Journy duration)
     */
    Form statjorform;
    /**
     * display your current speed when you are in the car dirving.
     */
    Form carform;
    /**
     * diplay the application main risk view.
     */
    Form elseform;
    /**
     * the form allow the user to specifiy date to check his speed and journy statistuics of that day.
     */
    Form choosedate;
    /**
     * display the one journy statistics(Journey No.,Average speed,Maximum speed,No of Calls,Duration of calls,Keys interactions,Key interactions Duration,Journy Duration)
     */
    Form jorform;
    /**
     *display statistics about the danger of using mobiles while driving.
     */
    Form mobileRisk;
    /**
     *  is a DateField that allow the user to choose a specific date to view his journys statistics.
     */
    DateField historydate;
    /**
     * check whether we can start the application.
     */
    boolean canStart;
    /**
     * check if the user started the application.
     * when the user first opens the application splashScreen = true which mean the logo is the main view but if he/she started the application splash=false because the main view is not the logo.
     */
    boolean splashScreen;
    /**
     * The list contian mnue options.
     * The list include:
     * 1.Overall: menu option that allow the user to see his over all risk and journy details.
     * 2.Today:
     * 3.History:
     * 4.Driving Distraction info:
     */
    private List infolist;
  /**
   * calculate the all journys statistics including speed statictics over all journys in the database.
   */  mainviewRiskoOverall mainrisko;

   /**
    * calculate journy and speed statistics per day and per journy.
    */
    mainviewRiskoOverLastJourny mainriskJor;

  
 
    //commands
    /**
     * pressing it will exit the application.
     */
    private Command exitCommand;
    /**
     * pressing it will go to the  previous screen display.
     */
    private Command backCommand;
    /**
     * pressing it will go to the infolist.
     */
    private Command infoCommand;
    /**
     * pressing it will start running the application and go to the main view.
     */
    private Command startCommand;
    /**
     * pressing it will take the user to the About form.
     */
    private Command AboutCommand;
    /**
     * pressing it will give the user his speed details.
     */
    private Command speedCommand;
    /**
     * pressing it will give the user his risk details.
     */
    private Command riskCommand;
    /**
     * will get the date user entered in history form and get the journy statistics for that date.
     */
    private Command okCommand;
    /**
     * used for usability study purpose user will not see it. We used it to collect all the journys data from the mobile record store(database).
     */
    private Command DataCommand;
    /**
     * pressing it will give the user statistics of every journy he took in the day he specifies in history or in Today menu option.
     */
    Command jorCommand;
    //lables
    /**
     * display the following string "You are:","In the car" when the user start driving.
     */
    StringItem carlab;
    /**
     * display the following string "This is your journy#:","? today" when the user start driving.
     */
    StringItem JorNolab;
     /**
     * display the following string "Your Current speed is:","km/h"" when the user start driving.
     */
    StringItem Speedlab;
     /**
     * display the following string "Your maximum speed is:","km/h" when the user start driving.
     */
    StringItem maxspeedlab;
     /**
     * display the following string "your Average speed is","km/h" when the user start driving.
     */
    StringItem Avgspeedlab;
    /**
     * display the following string "There is No speed data" when there is no speed data for the specified date.
     */
    StringItem nospeedlab;
    /**
     * display the following string "There is No Journys data" when there is no journy data for the specified date.
     */
    StringItem nojorlab;
   
   
    
    //deamonds
    /**
     * get the user state and notify the application when the user starts driving.
     * @see Acceleration
     */
    private Acceleration accdaemon;
    /**
     * capture user key interactions while he is driving.
     * @see KeyCapture
     */
    KeyCapture keycapdaemon;
    /**
     * capture user number and duration of calls while he/she is driving.
     * @see Calllog
     */
    Calllog calllogdaemon;
    /**
     * use the gps to get the user current speed every 30 sec and save it in the record store while he/she is driving.
     * @see Retriever
     */
    Retriever retriever;
    /**
     * communicate with Watcher deamon and notify it when the application start and close.
     * @see Watcher
     */
    Watcher watch;
    /**
     * manage displaying journy and speed statistics.
     * @see SpeedJournyRiskView
     */
   SpeedJournyRiskView jorspeedriskviews;
   
    //databases
   /**
    * manage saving journys details in record store.
    * @see JournyRecordStore
    */
    JournyRecordStore journydatabase;
    /**
     * manage saving speed details in record store.
     * @see SpeedRecordStore
     */
    SpeedRecordStore speeddatabase;
    //Journay Info
    /**
     * represents the journy number for this day. For example if this is the first journy today journyNo=1 if it is the second journyNo=2.
     */
    int journyNo=0;
    /**
     * represents today year.
     */
    int year=0;
    /**
     * represents today month.
     */
    int month=0;
    /**
     * represents today day of month.
     */
    int dayofmonth=0;
    /**
     * represents today dayofmonth.
     */
    int dayofweek=0;
    /**
     * represents the current time's hours.
     */
    int hour=0;
    /**
     * represents the current time's minute.
     */
    int minute=0;
  /**
   * if the gps is runing and we are acquiring speeds data GPSruning equals true else it will equal false.
   */
   boolean GPSruning;
/**
 * This is the constructor for class driverMidlet
 * we initialize the screen display and create the start
 * form.Then we put the application log in the start form. We also add the startCommand
 * ,AboutCommand and the exitCommand.
 * @see #canStart
 * @see #start
 * @see #splashScreen
 * @see #startCommand
 * @see #AboutCommand
 * @see #exitCommand
 */
   public driverMidlet (){

    //initialize the screen display
    display = Display.getDisplay(this);

      canStart= false;
     
      start = new Form("Driver Riskometer");
      
      splashScreen=true;
     

   Image startlogo = null;
try {
      startlogo = Image.createImage("/images/drivometerlogo.png");
    }
    catch( IOException e ){
    }
     start.append(new ImageItem(null,startlogo,ImageItem.LAYOUT_CENTER,null));
     createCommands();
      start.addCommand(startCommand);
      start.addCommand(AboutCommand);
      start.addCommand(exitCommand);

      
   }//driverMidlet
/**
 * about function initialize the form About and add backcommand and DataCommand to it.
 * @see #About
 * @see #backCommand
 * @see #DataCommand
 */
   void about()
   {
   About=new Form("About Driver Riskometer");

   About.append("Welcome to Driver Riskometer !\nThis application helps you monitor your driving behavior " +
           ".It keeps logs of your speed, your received calls and dialed numbers, texting and" +
           " playing with the phone while driving."+
           "\nCopyright © 2010 Ashraf Khalil. All rights reserved.");
   About.addCommand(backCommand);
  // About.addCommand(DataCommand);
   }
   /**
    * starttheup function initialize all the application' forms and add commands, labels, images to these forms.
    * Also, in the function we intialize the main list view and add icons to it.
    * Also initialize mainrisko object,mainriskJor object,accdaemon object,calllogdaemon,keycapdaemon,journydatabase and speeddatabase
    * @see Acceleration
    * @see Calllog
    * @see mainviewRiskoOverall
    * @see mainviewRiskoOverLastJourny
    * @see ToAcceptGPS
    * @see Acceleration
    * @see KeyCapture
    * @see JournyRecordStore
    * @see SpeedRecordStore
    * @see #othersCounter
    * @see #carCounter
    * @see #GPSruning
    * @see #mainviewrisk(int)
    */
        void starttheup(){

        splashScreen=false;

        statdetailsform=new Form("Statistics");
        statjorform=new Form("Journeys Statistics");
        carform=new Form("Driver Riskometer");
        elseform=new Form("Driver Riskometer");
        choosedate= new Form("Driver Riskometer");
        jorform=new Form("Journeys details");
        mobileRisk=new  Form("Information");

       mobileRisk.append(new mainview());

       historydate=new DateField("Enter a Date:", DateField.DATE, TimeZone.getTimeZone("GMT"));

        choosedate.append(historydate);
Image overallimg = null;
Image todayimg = null;
Image historyimg = null;
Image distrationimg = null;
//adding icons to the menu list
    try {
        overallimg = Image.createImage("/images/overall_icon.png");
        todayimg  = Image.createImage("/images/today_icon.png");
        historyimg =Image.createImage("/images/historyl_icon.png");
        distrationimg =Image.createImage("/images/infol_icon.png");
    }
    catch( IOException e ){
    }
        infolist=new List("Menu:", List.IMPLICIT);
        infolist.append("Overall", overallimg);
        infolist.append("Today", todayimg);
         infolist.append("History",historyimg);
         infolist.append("Driving Distraction info", distrationimg );

       

        carlab=new StringItem("You are:","In the car");
        JorNolab =new StringItem("This is your journy#:","? today");
        Speedlab=new StringItem("Your Current speed is:","km/h");
        maxspeedlab=new StringItem("Your maximum speed is:","km/h");
        Avgspeedlab=new StringItem("your Average speed is","km/h");
       


       mainrisko=new mainviewRiskoOverall(this);
       mainriskJor=new mainviewRiskoOverLastJourny(this);

        nospeedlab=new StringItem("","There is No speed data");
        nojorlab=new StringItem("","There is No Journys data");

        carform.append(carlab);
        carform.append(JorNolab);
        carform.append(Speedlab);
        carform.append(maxspeedlab);
        carform.append(Avgspeedlab);
                  
            addCommands();
            ToAcceptGPS accgps= new ToAcceptGPS(this);
            accgps.start();
          accdaemon= new Acceleration("Accd",this);
          accdaemon.start();
           //call deamond
          calllogdaemon= new Calllog(this);

          keycapdaemon=new KeyCapture(this);    
         retriever=null;
          
        //databases
       journydatabase =new JournyRecordStore(true);
       speeddatabase= new SpeedRecordStore(true);

       //counters
//         othersCounter=0;
//         carCounter=0;
          GPSruning=false;
mainviewrisk(1);
          
    }
  /**
   * its like the main function in the desktop application and its the first function the stats runing after the class Constructer.
   *it sets the main form for diplay in the screen and handle its commands.
   *@see #start
   */
    public void startApp()
          {
             
        display.setCurrent(start);
        start.setCommandListener(this);
      
           }
    /**
     * manages the main view diplay which show the overall Risk or last journy Risk.
     * @param x if it equal 1 then new risk calculation will take place.
     * @see  mainviewRiskoOverLastJourny#startviewriskcalculation()
     * @see mainviewRiskoOverLastJourny#calculateGenralRisk()
     */
    public void mainviewrisk(final int x)
    {
 new Thread()
 {
     public void run(){
         if(x==1)
mainriskJor.startviewriskcalculation();

mainriskJor.calculateGenralRisk();
 }
 }.start();
    }//mainviewrisk
    /**
     * gets called when the acceleration readings indcate that the user is in the car.
     * It first change the boolen vrible GPSruning to true to show that the gps is runing, then it suspend reading from the accelormeter.
     * After that it initialze the gps handling client which is called  retriever and starts it. Finally handle the form car commands.
     * @see #GPSruning
     * @see Acceleration#suspend()
     * @see Retriever
     * @see Retriever#start()
     */
    public void car()
    {
        GPSruning=true;
        accdaemon.suspend();
        retriever= new Retriever(this);
        retriever.start();
        carform.setCommandListener(this);
           
    }//car
/**
 *called when the gps stops working so it set variable GPSruning to false and resume running the accelerometer client.
 * @see #GPSruning
 * @see Acceleration#resume()
 */
    public void notCar()
    {
       GPSruning=false;
       accdaemon.resume();
       //display.setCurrent(elseform);
       //elseform.setCommandListener(this);
    }
    /**
     * called to update the midlet with the user motion state.
     * If aX=4 then the user is in the car and function car() is called
     * @param aX represent the user motion  state if equal 4 then the user is in the car.
     * @see #car()
     */
    void Update(int aX)
{
 //if car   
 if(aX==4)
    {
        //Car
        car();
    }

}
/**
 * is called to initialize all the commands that will be used by the different forms.
 *  @see #exitCommand
 *  @see #speedCommand
 *  @see #riskCommand
 *  @see #startCommand
 *  @see #AboutCommand
 *  @see #backCommand
 *  @see #infoCommand
 *  @see #okCommand
 *  @see #jorCommand
 *  //@see #DataCommand
 */
private void createCommands()
{
       exitCommand = new Command("Exit", Command.EXIT, 1);
       speedCommand = new Command("Speed details",Command.SCREEN, 2);
       riskCommand = new Command("Risk details",Command.SCREEN, 2);
       startCommand = new Command("Start",Command.SCREEN, 1);
       AboutCommand=new Command("About",Command.SCREEN, 1);
       backCommand = new Command("Back",Command.BACK, 0);
       infoCommand=new Command("Driving info",Command.SCREEN, 1);
      okCommand= new Command("OK", Command.OK, 0);
      jorCommand=new Command("Journeys", Command.SCREEN, 2);
     DataCommand=new Command("Collect", Command.OK, 3);

}
/**
 * adds every command to specific form or list.
 * @see #elseform
 * @see #infolist
 * @see #statdetailsform
 * @see #statjorform
 * @see #mobileRisk
 * @see #carform
 * @see #jorform
 * @see #choosedate
 */
private void addCommands()
{
        elseform.addCommand( backCommand );
        elseform.addCommand(infoCommand );
        elseform.addCommand(exitCommand);

        infolist.addCommand(backCommand);

        statdetailsform.addCommand(backCommand);

        statjorform.addCommand(backCommand);
        statjorform.addCommand(riskCommand);
        statjorform.addCommand(speedCommand);
        statjorform.addCommand(jorCommand);

        mobileRisk.addCommand(backCommand);

        carform.addCommand(backCommand);

        jorform.addCommand(backCommand);

        choosedate.addCommand(okCommand);
        choosedate.addCommand(backCommand);
    }
/**
 * handle every command depending on the display that fired the command.
 * @param c the command example (exitCommand,AboutCommand,DataCommand)
 * @param d the diaplay example (form, list)
 * @see #AboutCommand
 * @see #DataCommand
 * @see #exitCommand
 * @see #infoCommand
 * @see #startCommand
 * @see #backCommand
 * @see
 */
public void commandAction(Command c, Displayable d) {

    if(c==AboutCommand)
    {
       
        about();
        display.setCurrent(About);
        About.setCommandListener(this);
      
    }
    else if(c==DataCommand)
    {
       FileConnectionClass f= new FileConnectionClass(this);

    }
    else if (c == exitCommand)
    {

        destroyApp(true);

    }
    else if (c == infoCommand )
    {
        infoCommandhandling();
     }//infoCommand
    else if (c ==startCommand)
    {
        startCommandhandling();
    }

    else if (c == backCommand )
    {
      backCommnadhandling(d);
    }//backCommand
    else if (c == infolist.SELECT_COMMAND && d == infolist)
    {
   infolistSELECT_COMMANDhandling();
    }//infolist.SELECT_COMMAND
    else if(c==okCommand)
    {
     okCommandhandling();
    }//else if okcommand
else if(c==jorCommand)
{
  jorCommandhandling();
}//else if jorCommand
else if(c==speedCommand )
{
  speedCommandhandling();
   
}//speedCommand
 else if(c==riskCommand )
{
riskCommandhandling();
}//riskCommand
}//commandAction
/**
 * handle pressing speedCommand.
 */
void speedCommandhandling()
{
    //display over all journys speed statistics
 if(infolist.getSelectedIndex()==0)
    {
          new Thread()
          {
              public void run()
              {
          jorspeedriskviews.speedView(0,0,0,0);
              }
    }.start();
    }//if
  //display Today speed statistics
    else if(infolist.getSelectedIndex()==1)
    {
         new Thread()
          {
              public void run()
              {
            Date d= new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            int todaymonth=c.get(Calendar.MONTH);
            int todaydayofmonth=c.get(Calendar.DAY_OF_MONTH);
            int todayy=c.get(Calendar.YEAR);
            jorspeedriskviews.speedView(1,(todaydayofmonth-1),todaymonth,todayy);

              }
    }.start();
    }//if
   //display speed statistics of the date the user entered
    else if(infolist.getSelectedIndex()==2)
    {
         new Thread()
          {
              public void run()
              {
                Date d=historydate.getDate();
                Calendar c = Calendar.getInstance();
                c.setTime(d);
                int todaymonth=c.get(Calendar.MONTH);
                int todaydayofmonth=c.get(Calendar.DAY_OF_MONTH);
                int todayy=c.get(Calendar.YEAR);
                jorspeedriskviews.speedView(1,(todaydayofmonth-1),todaymonth,todayy);



              }
    }.start();
    }//else
}//speedCommandhandling
/**
 * handle pressing jorCommand by display  every journy taken todays statistics or display every journy statistics for the day the user specified in history form.
 */
void jorCommandhandling()
{
  new Thread ()
{
public void run()
{
    Date d;
    Calendar c;
    int todaymonth=1,todaydayofmonth=0;
    int todayy=0;
    if(infolist.getSelectedIndex()==1)//display every journy taken todays statistics.
    {
        d= new Date();
        c = Calendar.getInstance();
        c.setTime(d);
        todaymonth=c.get(Calendar.MONTH);
        todaydayofmonth=c.get(Calendar.DAY_OF_MONTH);
        todayy=c.get(Calendar.YEAR);

    }
    else if(infolist.getSelectedIndex()==2)//display every journy statistics for the day the user specified in history form.
    {
        d=historydate.getDate();
        c = Calendar.getInstance();
        c.setTime(d);
        todaymonth=c.get(Calendar.MONTH);
        todaydayofmonth=c.get(Calendar.DAY_OF_MONTH);
        todayy=c.get(Calendar.YEAR);
    }

  jorspeedriskviews.JournyView(2,(todaydayofmonth-1),todaymonth,todayy);
}//run
}.start();

}//jorCommandhandling
/**
 * handle pressing riskCommand by display over all journy risk view or today risk view or the risk view for the date the user specifed.
 */
void riskCommandhandling()
{

    if(infolist.getSelectedIndex()==0)//display over all journy risk view.
    {
            new Thread()
            {
            public void run()
            {
                jorspeedriskviews.riskView(0,0,0,0);
            }
            }.start();
    }//if
    else if(infolist.getSelectedIndex()==1)//display today risk view.
    {
            new Thread()
            {
            public void run()
            {
                    Date d= new Date();
                    Calendar c = Calendar.getInstance();
                    c.setTime(d);
                    int todaymonth=c.get(Calendar.MONTH);
                    int todaydayofmonth=c.get(Calendar.DAY_OF_MONTH);
                    int todayy=c.get(Calendar.YEAR);
                    jorspeedriskviews.riskView(1,(todaydayofmonth-1),todaymonth,todayy);
            }
            }.start();
    }//if
    else if(infolist.getSelectedIndex()==2)//display the risk view for the date the user specifed.
    {
         new Thread()
          {
              public void run()
              {
                Date d=historydate.getDate();
                Calendar c = Calendar.getInstance();
                c.setTime(d);
                int todaymonth=c.get(Calendar.MONTH);
                int todaydayofmonth=c.get(Calendar.DAY_OF_MONTH);
                int todayy=c.get(Calendar.YEAR);
                jorspeedriskviews.riskView(1,(todaydayofmonth-1),todaymonth,todayy);
               }
        }.start();
    }//else
}//riskCommandhandling
/**
 * handling okCommand in choosedate. Pressing it will get the date user entered in history form and get the journy statistics for that date.
 */
void okCommandhandling()
{
     new Thread ()
        {
            public void run()
            {
                Date d=historydate.getDate();
                Calendar c = Calendar.getInstance();
                c.setTime(d);
                int todaymonth=c.get(Calendar.MONTH);
                int todaydayofmonth=c.get(Calendar.DAY_OF_MONTH);
                int todayy=c.get(Calendar.YEAR);
                jorspeedriskviews.JournyView(1,(todaydayofmonth-1),todaymonth, todayy);

            }//run
        }.start();
}//okCommandhandling
/**
 * handle selecting option from info list. If the user choose "over all", over all journy statistics will be displayed.
 * If the user choose "Today", today journy statistics will be displayed.if the user choose "History",then choosedate form will be displayed.
 * If the user choose "Driving Distraction info" then driving distraction study statistics will be displayed.
 */
void infolistSELECT_COMMANDhandling()
{
    new Thread()
    {
    public void run()
    {
     //   If the user choose "over all", over all journy statistics will be displayed.
    if(infolist.getSelectedIndex()==0)
    {
        jorspeedriskviews.JournyView(0,0,0,0);
    }
    //if the user choose "Today", today journy statistics will be displayed
    else if(infolist.getSelectedIndex()==1)
    {
        Date d= new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int todaymonth=c.get(Calendar.MONTH);
        int todaydayofmonth=c.get(Calendar.DAY_OF_MONTH);
        int todayy=c.get(Calendar.YEAR);
        jorspeedriskviews.JournyView(1,(todaydayofmonth-1),todaymonth,todayy);
    }
    //if the user choose "History",then choosedate form will be displayed.
    else if(infolist.getSelectedIndex()==2)
    {
        display.setCurrent(choosedate);
        choosedate.setCommandListener(driverMidlet.this);
    }//else if
    //if the user choose "Driving Distraction info" then driving distraction study statistics will be displayed
    else if(infolist.getSelectedIndex()==3)
    {
        display.setCurrent(mobileRisk);
        mobileRisk.setCommandListener(driverMidlet.this);
    }//elseif

    }//run
    }.start();
}//infolistSELECT_COMMANDhandling
/**
 * handle pressing startCommand by starting the watcher, if the watcher started it call starttheup() if not it close the application.
 * @see #canStart
 * @see #starttheup()
 * @see Watcher
 */
void startCommandhandling()
{
    watch=new Watcher(this);
    watch.notifyWatchertoStart();
             if(canStart==true)
                {
                starttheup();
                }
              else
                 notifyDestroyed();
}// startCommandhandling
/**
 * handle pressing infoCommand. Take the user to info list.
 */
void infoCommandhandling()
{
    //suspend accelerometer client
    accdaemon.suspend();
    display.setCurrent(infolist);
    infolist.setCommandListener(this);

    new Thread()
    {
      public void run()
      {
    jorspeedriskviews= new SpeedJournyRiskView(driverMidlet.this);
      }
    }.start();
}//infoCommandhandling
/**
 * handle pressing back button depending on the current screen display.
 * @param d the current screen display(jorform,carform,infolist,...)
 */
void backCommnadhandling(Displayable d)
{
if(d==About)
{//if the user was in the About form and pressed back will go to the start form.
    display.setCurrent(start);
    start.setCommandListener(this);
}
else if(d==jorform)//the user in the jorform which display every journy made on today.
{
    if(infolist.getSelectedIndex()==1)//the user came from infolist "Today" option
    {
//will dispay the view with total journy for today with their statistics
    new Thread()
    {
    public void run()
    {
        Date d= new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int todaymonth=c.get(Calendar.MONTH);
        int todaydayofmonth=c.get(Calendar.DAY_OF_MONTH);
        int todayy=c.get(Calendar.YEAR);
        jorspeedriskviews.JournyView(1,(todaydayofmonth-1),todaymonth,todayy);
    }//run
    }.start();
    }//if(infolist.getSelectedIndex()==1)
    else if(infolist.getSelectedIndex()==2)//the user came from infolist "History" option
    {
    new Thread()
    {
    public void run()
    {
        Date d=historydate.getDate();

        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int todaymonth=c.get(Calendar.MONTH);
        int todaydayofmonth=c.get(Calendar.DAY_OF_MONTH);
        int todayy=c.get(Calendar.YEAR);
        jorspeedriskviews.JournyView(1,(todaydayofmonth-1),todaymonth,todayy);
    }//run
    }.start();
    }//infolist.getSelectedIndex()==2


}//jorform
else if(d== carform)
{
    display.setCurrent(elseform);
    elseform.setCommandListener(this);
}
else if(d==elseform)
{
    display.setCurrent(null);
}
else if(d==infolist)
{
    mainviewrisk(0);
    if( GPSruning==false)
    accdaemon.resume();
}
else if(d== statdetailsform)
{
    if(infolist.getSelectedIndex()==0){
    new Thread()
    {
    public void run()
    {
        jorspeedriskviews.JournyView(0,0,0,0);
    }
    }.start();
    }//if
    else if(infolist.getSelectedIndex()==1)
    {

        new Thread()
        {
        public void run()
        {
            Date d= new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            int todaymonth=c.get(Calendar.MONTH);
            int todaydayofmonth=c.get(Calendar.DAY_OF_MONTH);
            int todayy=c.get(Calendar.YEAR);
            jorspeedriskviews.JournyView(1,(todaydayofmonth-1),todaymonth,todayy);
        }
        }.start();
    }
    else if(infolist.getSelectedIndex()==2)
    {
        new Thread()
        {
        public void run()
        {
            Date d=historydate.getDate();

            Calendar c = Calendar.getInstance();
            c.setTime(d);
            int todaymonth=c.get(Calendar.MONTH);
            int todaydayofmonth=c.get(Calendar.DAY_OF_MONTH);
            int todayy=c.get(Calendar.YEAR);
            jorspeedriskviews.JournyView(1,(todaydayofmonth-1),todaymonth,todayy);
        }
        }.start();
    }//elseif

}
else if(d== statjorform)//the user is in statjorform
{
    //if he press back and he choosed the first option(overall) of infolist or the second(Today) then he will go to infolist view
    if(infolist.getSelectedIndex()==0||infolist.getSelectedIndex()==1)
    {
        display.setCurrent(infolist);
        infolist.setCommandListener(this);
    }
    //if the user choosed the third list option (History)
    else if(infolist.getSelectedIndex()==2)
    {
        //pressing back will take him to choosedate form
        display.setCurrent(choosedate);
        choosedate.setCommandListener(driverMidlet.this);
    }

}//statjorform
else if(d==mobileRisk)//the user is in using mobile while driving risk form
{
    //pressing back will take him to the infolist
    display.setCurrent(infolist);
    infolist.setCommandListener(this);
}
else if(d==choosedate)//the user in choosedate form
{
    //pressing back will take him to the infolist form
    display.setCurrent(infolist);
    infolist.setCommandListener(this);
}//choosedate
}//backCommnadhandling
/**
 * calls calllogdaemon.startcalllog() in a new thread to prevent blocking the current thread.
 * @see Calllog#startcalllog()
 */
void calllogaquire()
{
    new Thread()
    {
        public void run()
        {
            calllogdaemon.startcalllog();
        }
    }.start();

}
/**
 * calls calllogdaemon.endcalllog() in a new thread to prevent blocking the current thread.
 * @see Calllog#endcalllog()
 */
void calllogrecive()
{
    new Thread()
    {
        public void run()
        {
            calllogdaemon.endcalllog();
        }
    }.start();

}
/**
 * calls keycapdaemon.keysaquire() in a new thread to prevent blocking the current thread.
 * @see KeyCapture#keysaquire()
 */
void keysaquire()
{
    new Thread()
    {
        public void run()
        {
            keycapdaemon.startkeylog();
        }
    }.start();

}
/**
 * calls keycapdaemon.endkeylog() in a new thread to prevent blocking the current thread.
 * @see KeyCapture#endkeylog()
 */
void keysrecive()
{
    new Thread()
    {
        public void run()
        {
            keycapdaemon.endkeylog();
        }
    }.start();

}
/**
 * initilize new journy by :
 *  1. setting the year value
 *  2. setting the month value
 *  3. setting the dayofmonth value
 *  4. setting the dayofweek value
 *  5. setting the hour value
 *  6. setting the minute value
 *  7. setting the journyNo value
 *  8. setting the year value
 *  9. setting JorNolab text value
 * call the functions calllogaquire() and keysaquire().
 * @see #getJournyNo(int, int, int)
 * @see #calllogaquire()
 * @see #keysaquire()
 */
void JournyInitalization()
{
Date d= new Date();
Calendar c = Calendar.getInstance();
c.setTime(d);
 year=c.get(Calendar.YEAR);
 month=c.get(Calendar.MONTH);
 dayofmonth=c.get(Calendar.DAY_OF_MONTH);
 dayofweek=c.get(Calendar.DAY_OF_WEEK);
 hour=c.get(Calendar.HOUR_OF_DAY);
 minute=c.get(Calendar.MINUTE);
 journyNo=getJournyNo(dayofmonth, month, year);
 JorNolab.setText(""+journyNo+ " today");
calllogaquire();
keysaquire();
}//JournyInitalization

/**
 * gets the current day of month, month ,and year and compare it with the last saved journy in the database day of month, month, and year value, respectivily.
 * if the values are equals then the current journy number equal (the last journy number + 1) else it will equal 1, meaning this is the first journy in a new day.
 * @param dayofmonth the current day of month value.
 * @param month the current month value.
 * @param year the current year value.
 * @return JournyNo No of the current journuy
 * @see JournyRecordStore
 */
int getJournyNo(int dayofmonth,int month,int year)
{
    //14 item
     int j=0,y=0,m=0,dm=0;
     // jurvec= new Vector();
      String buf;

   // Intentionally make this too small to test code below
      byte[] recData = new byte[1];
      int len;

try{
        int s= journydatabase.rs.getNumRecords();
       // System.out.print("s="+s);
        if (s==0)
            return 1;

        int i=s-13;
        if ( journydatabase.rs.getRecordSize(i) > recData.length)
          recData = new byte[ journydatabase.rs.getRecordSize(i)];

        len = journydatabase.rs.getRecord(i, recData, 0);
        buf= new String(recData, 0, len);

             j=Integer.parseInt(buf);
            // System.out.println("journyNo="+j);

        i=s-6;
        if ( journydatabase.rs.getRecordSize(i) > recData.length)
          recData = new byte[ journydatabase.rs.getRecordSize(i)];

        len =  journydatabase.rs.getRecord(i, recData, 0);
        buf= new String(recData, 0, len);


             y=Integer.parseInt(buf);
           //  System.out.println("year="+y);

         i=s-5;
        if ( journydatabase.rs.getRecordSize(i) > recData.length)
          recData = new byte[ journydatabase.rs.getRecordSize(i)];

        len =  journydatabase.rs.getRecord(i, recData, 0);
        buf= new String(recData, 0, len);
             m=Integer.parseInt(buf);
           //  System.out.println("month="+m);

        i=s-4;
        if ( journydatabase.rs.getRecordSize(i) > recData.length)
          recData = new byte[ journydatabase.rs.getRecordSize(i)];

        len =  journydatabase.rs.getRecord(i, recData, 0);
        buf= new String(recData, 0, len);
             dm=Integer.parseInt(buf);
            // System.out.println("dayof month="+dm);




    }//try
    catch (Exception e)
    {
     System.err.print(e.toString());
    }
        

    if(year==y&& month==m && dayofmonth==dm)
                 return (j+1);
             else
                 return 1;


}//getJournyNo

    public void pauseApp() {
    }
/**
 * before the application exit it make sure that the gps is not runing and if it was, it stops the gps and save the journy data.
 * Also, it check if splashScreen= false. This means the application started and the accelometer client,call log client and key capture client is running so it closes them.
 * Finally, call notifyDestroyed().
 * @param unconditional if true it destroy the application if it is false it will not destroy the application.
 * @see #splashScreen
 * @see Acceleration#stop()
 * @see Acceleration#close()
 * @see KeyCapture#close()
 * @see Calllog#close()
 */
    public void destroyApp(boolean unconditional) {

 if(retriever!=null && retriever.journyStarted==true && retriever.last==false)
       {
           retriever.stopgps();
            notCar();
       }
if(splashScreen==false)
{
       accdaemon.stop();
       accdaemon.close();

      
       keycapdaemon.close();

       calllogdaemon.close();
}
       notifyDestroyed();
       

 
    }//destory app
}
/*
 Calendar.MONTH 0 1 2 3 4 5 6 7 8 9 10 11
 Calendar.DAY_OF_WEEK sunday 1 monday 2 tuesday 3 wednesday 4 thuresday 5 friday 6 satarday 7
//C++ symbian
   _LIT(KEarlierDateTime,"19970829:235959.999999");
   TTime earlierTime(KEarlierDateTime);
   earlierTime=30/09/1997 23:59:59:999999
 */