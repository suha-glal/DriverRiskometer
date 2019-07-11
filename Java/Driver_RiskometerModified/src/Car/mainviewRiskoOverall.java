/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Car;

/**
 * Display Risk's statistics over all journeys the user took.
 * @author Suha
 */
public class mainviewRiskoOverall {
   /**
     * represents number of calls.
     */
 int Enoofcalls=0;
 /**
     * represents duration of calls.
     */
 int Edurationofcalls=0;
 /**
     * represents number of key interactions.
     */
 int Enoofkeycontact=0;
 /**
  * represents duration of key interactions.
  */
 int Edurationofkeycontact=0;
 /**
  * represents duration of the journey.
  */
 int Eduration=0;
 /**
  * represents number of today's journys.
  */
 int Enumberofjournys=0;
 /**
  * percentage of last journy's time the driver speed is <= 60.
  */
 int tspeed60=0;
 /**
  * percentage of last journy's time the driver speed is <= 80.
  */
int tspeed80=0;
/**
 * percentage of last journy's time the driver speed is <= 100.
 */
int tspeed100=0;
/**
 * percentage of last journy's time the driver speed is <= 120.
 */
int tspeed120=0;
/**
 * percentage of last journy's time the driver speed is <= 140.
 */
int tspeed140=0;
/**
 * percentage of last journy's time the driver speed is <= 160.
 */
int tspeed160=0;
/**
 * percentage of last journy's time the driver speed is <= 200.
 */
int tspeed200=0;
/**
 * percentage of last journy's time the driver speed is >200.
 */
int tspeedmore200=0;
/**
 * calculates risk value for every risk factor.
 */
  Riskitems riskitem;
  /**
    * reference to the midlet class.
    */
  driverMidlet drivermidlet;
  /**
   * the constructor initialize new Riskitems object and save the midlet reference.
   * @param m reference from the midlet class
   */
  mainviewRiskoOverall(driverMidlet m)
  {
      riskitem= new Riskitems();
      drivermidlet= m;
}
  /**
   * calculate speed's and journey's statistics over all journeys.
   */
void startviewriskcalculation()
{
 Enoofcalls=0;Edurationofcalls=0;Enoofkeycontact=0;
            Edurationofkeycontact=0;Eduration=0;
            Enumberofjournys=0;
tspeed60=0;
tspeed80=0;
tspeed100=0;
tspeed120=0;
tspeed140=0;
tspeed160=0;
tspeed200=0;
tspeedmore200=0;

   //  new Thread()
       //   {
        //      public void run()
        //      {

         drivermidlet.journydatabase.readRecords();
        if(drivermidlet.journydatabase.getNoofJournies()>0){

 for(int j=0;j<drivermidlet.journydatabase.getNoofJournies();j++)
 {
    Journey jo=(Journey) drivermidlet.journydatabase.jurvec.elementAt(j);


    Enoofcalls+=jo.dialedorrecived;
    Edurationofcalls+=jo.callduration;
    Enoofkeycontact+=jo.keyintact;
    Edurationofkeycontact+=jo.Durkeyintact;
    Eduration+=jo.duration;
 }//for
 Enumberofjournys=drivermidlet.journydatabase.getNoofJournies();
 }//if

      
         drivermidlet.speeddatabase.readRecords();
        if(drivermidlet.speeddatabase.getNoofSpeedRecords()>0){

  int Espeedlessq60m=0,Espeedlessq80m=0,Espeedlessq100m=0,Espeedlessq120m=0,
          Espeedlessq140m=0,Espeedlessq160m=0,Espeedlessq200m=0,Espeedmore200=0;

    for(int i=0;i<drivermidlet.speeddatabase.getNoofSpeedRecords();i++)
 {
   Speedinfo sp=(Speedinfo) drivermidlet.speeddatabase.speedvec.elementAt(i);
      if(sp.speed<=60)
   {
       Espeedlessq60m++;

         }
   else if(sp.speed<=80)
   {
       Espeedlessq80m++;

       }
   else if(sp.speed<=100)
   {
       Espeedlessq100m++;

   }
   else if(sp.speed<=120)
   {
       Espeedlessq120m++;

      }
   else if(sp.speed<=140)
   {
      Espeedlessq140m++;

        }
   else if(sp.speed<=160)
   {
       Espeedlessq160m++;

       }
   else if(sp.speed<=200)
   {
       Espeedlessq200m++;

       }
   else if(sp.speed>200)
   {
       Espeedmore200++;

       }
 }//for i

 int totalrecords=drivermidlet.speeddatabase.getNoofSpeedRecords();
tspeed60=(Espeedlessq60m*100)/totalrecords;
tspeed80=(Espeedlessq80m*100)/totalrecords;
tspeed100=(Espeedlessq100m*100)/totalrecords;
tspeed120=(Espeedlessq120m*100)/totalrecords;
tspeed140=(Espeedlessq140m*100)/totalrecords;
tspeed160=(Espeedlessq160m*100)/totalrecords;
tspeed200=(Espeedlessq200m*100)/totalrecords;
tspeedmore200=(Espeedmore200*100)/totalrecords;
}//if speeddatabase.getNoofSpeedRecords()>0


         //     }//run

 //   }.start();


}//startcalculation
/**
 * Display risk's statistics in the screen over all journeys.
 */
void calculateGenralRisk()
{
    drivermidlet.elseform.deleteAll();
 if(drivermidlet.journydatabase.getNoofJournies()>0)
 {
      int speedrisk=0,keyrisk=0,keydurrisk=0,callrisk=0,calldurrisk=0,totalrisk=0;


speedrisk=riskitem.speedRisklevel(tspeed60,tspeed80,tspeed100
         ,tspeed120,tspeed140,tspeed160,tspeed200,tspeedmore200);

            callrisk=riskitem.nofcallRisk(Enoofcalls,(Enumberofjournys*6));
            calldurrisk=riskitem.durofcallRisk(Edurationofcalls, (Eduration*60));
             keyrisk=riskitem.nofkeyRisk(Enoofkeycontact,(Enumberofjournys*6));
             keydurrisk=riskitem.durkeyRisk(Edurationofkeycontact,(Eduration*60));
             totalrisk=riskitem.finallRiskScore(speedrisk,keyrisk,keydurrisk,callrisk,calldurrisk);
             String riskstr=riskitem.riskString(totalrisk);

Journey jo=(Journey)drivermidlet.journydatabase.jurvec.lastElement();

          drivermidlet.elseform.append(new RiskView(""+riskitem.getmonthname(jo.month)+" "+ jo.year,riskstr,totalrisk,speedrisk
         ,keyrisk,keydurrisk,callrisk,calldurrisk));

 }//if
 else
 {
            drivermidlet.elseform.append(new mainview());
 }//else
     drivermidlet.display.setCurrent(drivermidlet.elseform);
        drivermidlet.elseform.setCommandListener(drivermidlet);

}//general risk


}
