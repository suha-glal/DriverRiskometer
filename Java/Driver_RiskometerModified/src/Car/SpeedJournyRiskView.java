

package Car;

/**
 * The class manage displaying the journy and speed statistics based on all journys or for a specific journy.
 * @author Suha
 */
public class SpeedJournyRiskView {
    /**
     * represents number of journys.
     */
   int numberofjournys=0;
    /**
     * represents maximum speed.
     */
    int maxspeed=0;
    /**
     * represents average speed.
     */
    int averagespeed=0;
    /**
     * represents number of calls.
     */
    int noofcalls=0;
    /**
     * represents duration of calls.
     */
    int durationofcalls=0;
    /**
     * represents number of key interactions.
     */
    int noofkeycontact=0;
    /**
     * represents duration of key interactions.
     */
    int durationofkeycontact=0;
    /**
     * represents Journy duration.
     */
    int duration=0;
    /**
    * percentage of time over all journys the driver speed is <= 60.
    */
    int tspeed60=0;
    /**
    * percentage of time over all journys the driver speed is <= 80.
    */
    int tspeed80=0;
/**
 *  percentage of time over all journys the driver speed is <= 100.
 */
    int tspeed100=0;
/**
 *  percentage of time over all journys the driver speed is <= 120.
 */
    int tspeed120=0;
/**
 *  percentage of time over all journys the driver speed is <= 140.
 */
    int tspeed140=0;
/**
 *  percentage of time over all journys the driver speed is <= 160.
 */
    int tspeed160=0;
/**
 *  percentage of time over all journys the driver speed is <= 200.
 */
    int tspeed200=0;
/**
 *  percentage of time over all journys the driver speed is > 200.
 */
    int tspeedmore200=0;
    /**
     * holds speeds statistics per day.
     */
    int dayspeed[][];
    /**
     * holds speed statistics per journy.
     */
    int journyspeed[][][];
    /**
     * holds journys statistics per day.
     */
    int dayjourny[][];
    /**
     * holds every journy statistics.
     */
    int joJournys[][][];
    /**
     * we assumed that the maximum number of journys is 30.
     */
    int maxNoJourny=30;
    /**
     * reference to the midlet class.
     */
    driverMidlet drivermidlet;
    /**
     * calculates risk value for every risk factor.
     */
    Riskitems riskitem;
    /**
     * initialize the class variables and fetch the speed and the journys date from the record store and finally call journycalculation() and  speedcalculation() to calculate the statistics.
     * @param dm reference from the midlet class
     */
    SpeedJournyRiskView(driverMidlet dm)
    {
     drivermidlet=dm;
     riskitem=new Riskitems();
     dayspeed= new int[31][10];
     journyspeed= new int[31][maxNoJourny][10];
     dayjourny= new int[31][9];
     joJournys= new int[31][maxNoJourny][11];
      new Thread()
          {
              public void run()
              {
              
         drivermidlet.journydatabase.readRecords();
         journycalculation();
                  
              }//run
    }.start();

      new Thread()
          {
              public void run()
              {
                  
         drivermidlet.speeddatabase.readRecords();
         speedcalculation();
             
              }//run

    }.start();


    }//SpeedJournyRiskView
    /**
     *calculate speed statistics over all days,per day and per journy.
     */
void speedcalculation()
{
if(drivermidlet.speeddatabase.getNoofSpeedRecords()>0){

  int speedlessq60m=0,speedlessq80m=0,speedlessq100m=0,speedlessq120m=0,
          speedlessq140m=0,speedlessq160m=0,speedlessq200m=0,speedmore200=0;

     for(int i=0;i<31;i++)
        for(int j=0;j<10;j++)
            dayspeed[i][j]=0;


    for(int i=0;i<31;i++)
        for(int j=0;j<maxNoJourny;j++)
            for(int z=0;z<10;z++)
            journyspeed[i][j][z]=0;

 for(int i=0;i<drivermidlet.speeddatabase.getNoofSpeedRecords();i++)
 {
   Speedinfo sp=(Speedinfo) drivermidlet.speeddatabase.speedvec.elementAt(i);
    dayspeed[sp.dayofmonth-1][9]=sp.month;
    journyspeed[sp.dayofmonth-1][sp.journyNo-1][9]=sp.month;
   if(sp.speed<=60)
   {
       speedlessq60m++;

       dayspeed[sp.dayofmonth-1][0]++;//number of speed records per day
       dayspeed[sp.dayofmonth-1][1]++;

       journyspeed[sp.dayofmonth-1][sp.journyNo-1][0]++;//number of speed records per journy
       journyspeed[sp.dayofmonth-1][sp.journyNo-1][1]++;


   }
   else if(sp.speed<=80)
   {
       speedlessq80m++;

       dayspeed[sp.dayofmonth-1][0]++;
       dayspeed[sp.dayofmonth-1][2]++;

       journyspeed[sp.dayofmonth-1][sp.journyNo-1][0]++;
       journyspeed[sp.dayofmonth-1][sp.journyNo-1][2]++;
   }
   else if(sp.speed<=100)
   {
       speedlessq100m++;

       dayspeed[sp.dayofmonth-1][0]++;
       dayspeed[sp.dayofmonth-1][3]++;

       journyspeed[sp.dayofmonth-1][sp.journyNo-1][0]++;
       journyspeed[sp.dayofmonth-1][sp.journyNo-1][3]++;
   }
   else if(sp.speed<=120)
   {
       speedlessq120m++;

       dayspeed[sp.dayofmonth-1][0]++;
        dayspeed[sp.dayofmonth-1][4]++;

        journyspeed[sp.dayofmonth-1][sp.journyNo-1][0]++;
       journyspeed[sp.dayofmonth-1][sp.journyNo-1][4]++;
   }
   else if(sp.speed<=140)
   {
      speedlessq140m++;

       dayspeed[sp.dayofmonth-1][0]++;
        dayspeed[sp.dayofmonth-1][5]++;

        journyspeed[sp.dayofmonth-1][sp.journyNo-1][0]++;
       journyspeed[sp.dayofmonth-1][sp.journyNo-1][5]++;
   }
   else if(sp.speed<=160)
   {
       speedlessq160m++;

       dayspeed[sp.dayofmonth-1][0]++;
        dayspeed[sp.dayofmonth-1][6]++;

        journyspeed[sp.dayofmonth-1][sp.journyNo-1][0]++;
       journyspeed[sp.dayofmonth-1][sp.journyNo-1][6]++;
   }
   else if(sp.speed<=200)
   {
       speedlessq200m++;

       dayspeed[sp.dayofmonth-1][0]++;
        dayspeed[sp.dayofmonth-1][7]++;

        journyspeed[sp.dayofmonth-1][sp.journyNo-1][0]++;
       journyspeed[sp.dayofmonth-1][sp.journyNo-1][7]++;
   }
   else if(sp.speed>200)
   {
       speedmore200++;

       dayspeed[sp.dayofmonth-1][0]++;
        dayspeed[sp.dayofmonth-1][8]++;

        journyspeed[sp.dayofmonth-1][sp.journyNo-1][0]++;
       journyspeed[sp.dayofmonth-1][sp.journyNo-1][8]++;
   }
 }//for i

 int totalrecords=drivermidlet.speeddatabase.getNoofSpeedRecords();
tspeed60=(speedlessq60m*100)/totalrecords;
tspeed80=(speedlessq80m*100)/totalrecords;
tspeed100=(speedlessq100m*100)/totalrecords;
tspeed120=(speedlessq120m*100)/totalrecords;
tspeed140=(speedlessq140m*100)/totalrecords;
tspeed160=(speedlessq160m*100)/totalrecords;
tspeed200=(speedlessq200m*100)/totalrecords;
tspeedmore200=(speedmore200*100)/totalrecords;
}//if speeddatabase.getNoofSpeedRecords()>0

}//speedcalculation
/**
 * calculate journy statistics over all journys, per day and per journy.
 */
void journycalculation()
{
    if(drivermidlet.journydatabase.getNoofJournies()>0){
 
 for(int i=0;i<31;i++)
        for(int j=0;j<9;j++)
            dayjourny[i][j]=0;


    for(int i=0;i<31;i++)
        for(int j=0;j<maxNoJourny;j++)
            for(int z=0;z<11;z++)
              joJournys[i][j][z]=0;

 for(int j=0;j<drivermidlet.journydatabase.getNoofJournies();j++)
 {
    Journey jo=(Journey) drivermidlet.journydatabase.jurvec.elementAt(j);
// to get statistics over all journeys
    if(maxspeed<jo.maxspeed)
    maxspeed=jo.maxspeed;

    averagespeed+=jo.avgspeed;
    noofcalls+=jo.dialedorrecived;
    durationofcalls+=jo.callduration;
    noofkeycontact+=jo.keyintact;
    durationofkeycontact+=jo.Durkeyintact;
    duration+=jo.duration;

    
    dayjourny[jo.dayofmonth-1][0]++; //No. of journy per day
    joJournys[jo.dayofmonth-1][jo.journyNo-1][0]++;//

    dayjourny[jo.dayofmonth-1][1]+=jo.avgspeed;
    joJournys[jo.dayofmonth-1][jo.journyNo-1][1]+=jo.avgspeed;

    if(jo.maxspeed> dayjourny[jo.dayofmonth-1][2])
    dayjourny[jo.dayofmonth-1][2]=jo.maxspeed;

     if(jo.maxspeed> joJournys[jo.dayofmonth-1][jo.journyNo-1][2])
    joJournys[jo.dayofmonth-1][jo.journyNo-1][2]+=jo.maxspeed;

    dayjourny[jo.dayofmonth-1][3]+=jo.dialedorrecived;
    joJournys[jo.dayofmonth-1][jo.journyNo-1][3]+=jo.dialedorrecived;

    dayjourny[jo.dayofmonth-1][4]+=jo.callduration;
    joJournys[jo.dayofmonth-1][jo.journyNo-1][4]+=jo.callduration;

    dayjourny[jo.dayofmonth-1][5]+=jo.keyintact;
    joJournys[jo.dayofmonth-1][jo.journyNo-1][5]+=jo.keyintact;

    dayjourny[jo.dayofmonth-1][6]+=jo.Durkeyintact;
    joJournys[jo.dayofmonth-1][jo.journyNo-1][6]+=jo.Durkeyintact;

    dayjourny[jo.dayofmonth-1][7]+=jo.duration;
    joJournys[jo.dayofmonth-1][jo.journyNo-1][7]+=jo.duration;

    dayjourny[jo.dayofmonth-1][8]=jo.month;
    joJournys[jo.dayofmonth-1][jo.journyNo-1][8]=jo.month;

     joJournys[jo.dayofmonth-1][jo.journyNo-1][9]=jo.hour;

      joJournys[jo.dayofmonth-1][jo.journyNo-1][10]=jo.minute;
 }//for j

 averagespeed/=drivermidlet.journydatabase.getNoofJournies();
numberofjournys=drivermidlet.journydatabase.getNoofJournies();
    }//if
}//journycalculation
/**
 * Display journey's statistics in the screen either over all journeys or per day or per journey.
 * @param c if c=0 then  journey's statistics over all journys will be displayed, if c=1 journey's statistics per day will be displayed,if c=2 then journey's statistics per journy will displayed.
 * @param d the user spesified day of month.
 * @param m the user spesified month.
 * @param y the user spesified year.
 */
void JournyView(int c,int d,int m,int y)
{
    drivermidlet.statjorform.deleteAll();
    drivermidlet.jorform.deleteAll();

 if(drivermidlet.journydatabase.getNoofJournies()>0){
    if(c==0&& d==0)
    { drivermidlet.statjorform.removeCommand(drivermidlet.jorCommand);

      

  drivermidlet.statjorform.append(new SuJorTable("Over All",drivermidlet.journydatabase.getNoofJournies()
                    ,averagespeed,maxspeed,noofcalls
                    ,(durationofcalls),noofkeycontact,(durationofkeycontact),(duration)));

  drivermidlet.display.setCurrent( drivermidlet.statjorform);
  drivermidlet.statjorform.setCommandListener(drivermidlet);
    }//c==0
    else if(c==1)
    {

    // for(int i=30;i>=0;i--)

        if(dayjourny[d][0]>0 && dayjourny[d][8]== m && y==getYear())
        {
            drivermidlet.statjorform.addCommand(drivermidlet.jorCommand);
        drivermidlet.statjorform.append(new DayJorTable(""+(d+1)+" "+riskitem.getmonthname(dayjourny[d][8])+" "+getYear(),dayjourny[d][0]
                    ,/*dayjourny[d][1] avg speed /dayjourny[d][0](no of journey)*/(dayjourny[d][1]/dayjourny[d][0]),dayjourny[d][2],dayjourny[d][3]
                    ,dayjourny[d][4]/(60),dayjourny[d][5],dayjourny[d][6],dayjourny[d][7]));

        drivermidlet.display.setCurrent( drivermidlet.statjorform);
       drivermidlet.statjorform.setCommandListener(drivermidlet);
     }//if
        else
        {
        drivermidlet.statjorform.removeCommand(drivermidlet.jorCommand);

       drivermidlet.statjorform.append(drivermidlet.nojorlab);
       drivermidlet.display.setCurrent( drivermidlet.statjorform);
       drivermidlet.statjorform.setCommandListener(drivermidlet);
        }//else
       
    }//c==1
    else if(c==2)
    {

     //for(int i=30;i>=0;i--)
 if(dayjourny[d][0]>0 && dayjourny[d][8]== m&&y==getYear())
        {
        for(int j=maxNoJourny-1;j>=0;j--)
            if(joJournys[d][j][0]==1)
      drivermidlet.jorform.append(new JorTable(""+(d+1)+"/"+(joJournys[d][j][8]+1)+
              "/"+getYear()+
              " Time:"+joJournys[d][j][9]+":"+joJournys[d][j][10],
                    j+1,(joJournys[d][j][1]/joJournys[d][j][0]),
                    joJournys[d][j][2],joJournys[d][j][3]
                    ,(joJournys[d][j][4]/60),joJournys[d][j][5],
                    joJournys[d][j][6],joJournys[d][j][7]));
        }//if
    else
       drivermidlet.jorform.append(drivermidlet.nojorlab);

        drivermidlet.display.setCurrent(drivermidlet.jorform);
         drivermidlet.jorform.setCommandListener(drivermidlet);
    }//c==2
     }//if there is journys
    else
    { drivermidlet.statjorform.removeCommand(drivermidlet.jorCommand);

       drivermidlet.statjorform.append(drivermidlet.nojorlab);
       drivermidlet.display.setCurrent( drivermidlet.statjorform);
       drivermidlet.statjorform.setCommandListener(drivermidlet);
    }//else


}//JournyView
/**
 * Display speed's statistics in the screen either over all journeys or per day or per journey.
 * @param choice if choice=0 then  speed's statistics over all journys will be displayed, if choice=1 speed's statistics per day will be displayed,if choice=2 then speed's statistics per journy will displayed.
 * @param d the user spesified day of month.
 * @param m the user spesified month.
 * @param y the user spesified year.
 */
void speedView(int choice,int d,int m,int y)
{

 drivermidlet.statdetailsform.deleteAll();
 if(drivermidlet.journydatabase.getNoofJournies()>0)
 {
if(choice==0){
    Journey jo=(Journey) drivermidlet.journydatabase.jurvec.lastElement();
  drivermidlet.statdetailsform.append(new speedTable("Over All"/*+riskitem.getmonthname(jo.month)+" "+getYear()*/,tspeed60,tspeed80,tspeed100
         ,tspeed120,tspeed140,tspeed160,tspeed200,tspeedmore200));


          }//choice=0

else if(choice==1){
 //for(int i=30;i>=0;i--)
 if(dayspeed[d][0]>0&&dayspeed[d][9]==m && y==getYear() )
 {
     float d60=((float)dayspeed[d][1]/dayspeed[d][0])*100;
     float d80=((float)dayspeed[d][2]/dayspeed[d][0])*100;
     float d100=((float)dayspeed[d][3]/dayspeed[d][0])*100;
     float d120=((float)dayspeed[d][4]/dayspeed[d][0])*100;
     float d140=((float)dayspeed[d][5]/dayspeed[d][0])*100;
     float d160=((float)dayspeed[d][6]/dayspeed[d][0])*100;
     float d200=((float)dayspeed[d][7]/dayspeed[d][0])*100;
     float m200=((float)dayspeed[d][8]/dayspeed[d][0])*100;


     drivermidlet.statdetailsform.append(new speedTable("On "+(d+1)+" "+riskitem.getmonthname(dayspeed[d][9])+" "+getYear(),(int)(d60),(int)(d80)
         ,(int)(d100),(int)(d120),(int)(d140),(int)(d160),(int)(d200),(int)(m200)));


 }//if day>0
 else
 {drivermidlet.nospeedlab.setText("There is no speed data");
      drivermidlet.statdetailsform.append(drivermidlet.nospeedlab);
 }

}//if choice==1
else if(choice==2){
// for(int i=30;i>0;i--)
 for(int j=maxNoJourny-1;j>=0;j--)
   if(journyspeed[d][j][0]==1)
   {
     float j60=((float)journyspeed[d][j][1]/journyspeed[d][j][0])*100;
     float j80=((float)journyspeed[d][j][2]/journyspeed[d][j][0])*100;
     float j100=((float)journyspeed[d][j][3]/journyspeed[d][j][0])*100;
     float j120=((float)journyspeed[d][j][4]/journyspeed[d][j][0])* 100;
     float j140=((float)journyspeed[d][j][5]/journyspeed[d][j][0])* 100;
     float j160=((float)journyspeed[d][j][6]/journyspeed[d][j][0])* 100;
     float j200=((float)journyspeed[d][j][7]/journyspeed[d][j][0])* 100;
     float jm200=((float)journyspeed[d][j][8]/journyspeed[d][j][0])* 100;

Journey jo=(Journey)drivermidlet.journydatabase.jurvec.lastElement();

 drivermidlet.statdetailsform.append(new speedTable(""+(d+1)+"/"+(journyspeed[d][j][9]+1)+"/"+jo.year+",Journey# "+(j+1),(int)(j60),(int)(j80)
         ,(int)(j100),(int)(j120),(int)(j140),(int)(j160),(int)(j200),(int)(jm200)));
     }//if
}//if choice ==2

  }//ifdrivermidlet.speeddatabase.getNoofSpeedRecords()>0
  else{drivermidlet.nospeedlab.setText("There is no speed data");
      drivermidlet.statdetailsform.append(drivermidlet.nospeedlab);
  }

drivermidlet.display.setCurrent( drivermidlet.statdetailsform);
 drivermidlet.statdetailsform.setCommandListener(drivermidlet);
}//speedView
/**
 * Display risk's statistics in the screen either over all journeys or per day or per journey.
 * @param choice if choice=0 then  risk's statistics over all journys will be displayed, if choice=1 risk's statistics per day will be displayed,if choice=2 then risk's statistics per journy will displayed.
 * @param d the user spesified day of month.
 * @param m the user spesified month.
 * @param y the user spesified year.
 */
void riskView(int choice,int d,int m,int y)
{
    
 drivermidlet.statdetailsform.deleteAll();
 if(drivermidlet.journydatabase.getNoofJournies()>0)
 {
      int speedrisk=0,keyrisk=0,keydurrisk=0,callrisk=0,calldurrisk=0,totalrisk=0;
if(choice==0){

speedrisk=riskitem.speedRisklevel(tspeed60,tspeed80,tspeed100
         ,tspeed120,tspeed140,tspeed160,tspeed200,tspeedmore200);

            callrisk=riskitem.nofcallRisk(noofcalls,(numberofjournys*6));
            calldurrisk=riskitem.durofcallRisk(durationofcalls, (duration*60));
             keyrisk=riskitem.nofkeyRisk(noofkeycontact,(numberofjournys*6));
             keydurrisk=riskitem.durkeyRisk(durationofkeycontact,(duration*60));
             totalrisk=riskitem.finallRiskScore(speedrisk,keyrisk,keydurrisk,callrisk,calldurrisk);
             String riskstr=riskitem.riskString(totalrisk);

Journey jo=(Journey)drivermidlet.journydatabase.jurvec.lastElement();

          drivermidlet.statdetailsform.append(new RiskView("Over All"/*+riskitem.getmonthname(jo.month)+" "+getYear()*/,riskstr,totalrisk,speedrisk
         ,keyrisk,keydurrisk,callrisk,calldurrisk));
          

          }//choice=0
    if(choice==1){
// for(int i=30;i>=0;i--)
 if(dayspeed[d][0]>0 && dayspeed[d][9]==m&& y==getYear())
 {
     float d60=((float)dayspeed[d][1]/dayspeed[d][0])*100;
     float d80=((float)dayspeed[d][2]/dayspeed[d][0])*100;
     float d100=((float)dayspeed[d][3]/dayspeed[d][0])*100;
     float d120=((float)dayspeed[d][4]/dayspeed[d][0])*100;
     float d140=((float)dayspeed[d][5]/dayspeed[d][0])*100;
     float d160=((float)dayspeed[d][6]/dayspeed[d][0])*100;
     float d200=((float)dayspeed[d][7]/dayspeed[d][0])*100;
     float m200=((float)dayspeed[d][8]/dayspeed[d][0])*100;

      speedrisk=riskitem.speedRisklevel((int) d60,(int) d80,(int) d100
         ,(int) d120,(int) d140,(int) d160,(int) d200,(int) m200);

            callrisk=riskitem.nofcallRisk( dayjourny[d][3],(dayjourny[d][0]*6));
            calldurrisk=riskitem.durofcallRisk(dayjourny[d][4], dayjourny[d][7]*60);
             keyrisk=riskitem.nofkeyRisk(dayjourny[d][5],(dayjourny[d][0]*6));
             keydurrisk=riskitem.durkeyRisk(dayjourny[d][6], dayjourny[d][7]*60);
             totalrisk=riskitem.finallRiskScore(speedrisk,keyrisk,keydurrisk,callrisk,calldurrisk);
             String riskstr=riskitem.riskString(totalrisk);


          drivermidlet.statdetailsform.append(new RiskView("On "+(d+1)+" "+riskitem.getmonthname(dayjourny[d][8])+" "+getYear(),riskstr,totalrisk,speedrisk
         ,keyrisk,keydurrisk,callrisk,calldurrisk));
          
 }//if day>0
else{drivermidlet.nospeedlab.setText("There is no risk data");
      drivermidlet.statdetailsform.append(drivermidlet.nospeedlab);
  }

}//if choice==1
else if(choice==2){
 //for(int i=30;i>0;i--)
 for(int j=maxNoJourny-1;j>=0;j--)
   if(joJournys[d][j][0]==1)
   {
     float j60=((float)journyspeed[d][j][1]/journyspeed[d][j][0])*100;
     float j80=((float)journyspeed[d][j][2]/journyspeed[d][j][0])*100;
     float j100=((float)journyspeed[d][j][3]/journyspeed[d][j][0])*100;
     float j120=((float)journyspeed[d][j][4]/journyspeed[d][j][0])* 100;
     float j140=((float)journyspeed[d][j][5]/journyspeed[d][j][0])* 100;
     float j160=((float)journyspeed[d][j][6]/journyspeed[d][j][0])* 100;
     float j200=((float)journyspeed[d][j][7]/journyspeed[d][j][0])* 100;
     float jm200=((float)journyspeed[d][j][8]/journyspeed[d][j][0])* 100;


Journey jo=(Journey)drivermidlet.journydatabase.jurvec.lastElement();
speedrisk=riskitem.speedRisklevel((int)(j60),(int)(j80),
         (int)(j100),(int)(j120),(int)(j140),(int)(j160),(int)(j200),(int)(jm200));

            callrisk=riskitem.nofcallRisk( joJournys[d][j][3],(joJournys[d][j][0]*6));
            calldurrisk=riskitem.durofcallRisk(joJournys[d][j][4],joJournys[d][j][7]*60);
             keyrisk=riskitem.nofkeyRisk(joJournys[d][j][5],(joJournys[d][j][0]*6));
             keydurrisk=riskitem.durkeyRisk(joJournys[d][j][6],joJournys[d][j][7]*60);
             totalrisk=riskitem.finallRiskScore(speedrisk,keyrisk,keydurrisk,callrisk,calldurrisk);
             String riskstr=riskitem.riskString(totalrisk);

          drivermidlet.statdetailsform.append(new RiskView(""+(d+1)+"/"+(joJournys[d][j][8]+1)+"/"+jo.year+",Journey# "+(j+1),riskstr,totalrisk,speedrisk
         ,keyrisk,keydurrisk,callrisk,calldurrisk));


     }//if


}//if choice ==2

 }//ifdrivermidlet.speeddatabase.getNoofSpeedRecords()>0
  else{drivermidlet.nospeedlab.setText("There is no risk data");
      drivermidlet.statdetailsform.append(drivermidlet.nospeedlab);
  }
drivermidlet.display.setCurrent( drivermidlet.statdetailsform);
 drivermidlet.statdetailsform.setCommandListener(drivermidlet);

}//riskView
/**
 * get the current year.
 * @return
 */
public int getYear()
{
    Journey j=(Journey)drivermidlet.journydatabase.jurvec.lastElement();
 return j.year;
}
}//end of class
