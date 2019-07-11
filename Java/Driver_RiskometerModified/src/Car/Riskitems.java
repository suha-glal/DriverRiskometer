/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Car;

/**
 * calculates risk value for every risk factor.
 * @author Suha
 */
public class Riskitems {
/**
 *
 * @param month represents the month integer value. In java Calendar.MONTH are : 0 1 2 3 4 5 6 7 8 9 10 11
 * @return month name string
 */
public String getmonthname(int month)
{
  if(month==0)
     return "January";
 else if(month==1)
      return "February";
 else if(month==2)
      return "March";
 else if(month==3)
      return "April";
 else if(month==4)
      return "May";
 else if(month==5)
      return "June";
 else if(month==6)
      return "July";
 else if(month==7)
      return "August";
 else if(month==8)
      return "September";
 else if(month==9)
      return "October";
 else if(month==10)
      return "November";
 else
      return "December";
}
/**
 * calculate no. of calls risk level. We first get the value of (No. of calls/No. of journeys) and depending on this value we choose the risk level.
 * @param noc no. of calls
 * @param noofjor no. of journeys
 * @return no. of calls risk level
 */
int nofcallRisk(int noc,int noofjor)
{
   double fc;
    fc=(double)(noc)/noofjor;
int riskfactor=0;
if(noc==0)
    riskfactor=0;
else if(fc<=0.2)
    riskfactor=1;
else if(fc<=0.3)
    riskfactor=2;
else if(fc<=0.5)
    riskfactor=3;
else if(fc<=0.7)
    riskfactor=4;
else if(fc<=0.9)
    riskfactor=5;
else if(fc>0.9)
    riskfactor=6;

return (riskfactor);
}
/**
 * calculate duration of calls risk level. first we get the value of (duration of calls/duration of trip) and depending on the result we choose the risk level.
 * @param durcall duration of calls.
 * @param tripdur duration of the journey.
 * @return duration of call risklevel.
 */
int durofcallRisk(int durcall,int tripdur)
{
    double fc;
    fc=(double)(durcall)/tripdur;
int risklevel=0;
if(durcall==0)
    risklevel=0;
else if(fc<=0.2)
    risklevel=1;
else if(fc<=0.3)
    risklevel=2;
else if(fc<=0.4)
    risklevel=3;
else if(fc<=0.6)
    risklevel=4;
else if(fc<=0.8)
    risklevel=5;
else if(fc>0.8)
    risklevel=6;

return (risklevel);

}
/**
 *  calculate no. of key interactions risk level. first we get the value of (no. of key interation/no. of trip) and depending on the result we choose the risk level.
 * @param nok no. of key interactions
 * @param noofjor no of journey we want to calculate their risk level.
 * @return key interaction risk level
 */
int nofkeyRisk(int nok, int noofjor)
{
double fc;
    fc=(double)(nok)/noofjor;

int risklevel=0;

if(nok==0)
    risklevel=0;
else if(fc<=0.2)
    risklevel=1;
else if(fc<=0.3)
    risklevel=2;
else if(fc<=0.5)
    risklevel=3;
else if(fc<=0.7)
    risklevel=4;
else if(fc<=0.9)
    risklevel=5;
else if(fc>0.9)
    risklevel=6;

return (risklevel);

}
/**
 * calculate duration of key interactions risk level. first we get the value of (duration of key interactions/duration of trip) and depending on the result we choose the risk level.
 * @param durk duration of key interactions
 * @param tripdur duration of trip
 * @return duration of key interactions risk level
 */
int durkeyRisk(int durk,int tripdur)
{
 double fc;
    fc=(double)(durk)/tripdur;
int risklevel=0;
if(durk==0)
    risklevel=0;
else if(fc<=0.02)
    risklevel=1;
else if(fc<=0.04)
    risklevel=2;
else if(fc<=0.06)
    risklevel=3;
else if(fc<=0.08)
    risklevel=4;
else if(fc<=0.1)
    risklevel=5;
else if(fc>0.1)
    risklevel=6;

return (risklevel);

}
/**
 * calculate speed risk level. first we calculate speed score which equals:
 * <p>(1 x the percentage of time the speed was <= 60)+(2 x the percentage of time the speed was <= 80)+(3 x the percentage of time the speed was <= 100)+(4 x the percentage of time the speed was <= 120)+(5 x the percentage of time the speed was <= 140)+(6 x the percentage of time the speed was <= 160)+(7 x the percentage of time the speed was <= 200)+(8 x the percentage of time the speed was > 200).
 * <p> speed score give high weight for high speed to higher the risk level value. depending on the speed score we choose the risk level.
 * @param s60 the percentage of time the speed was <= 60
 * @param s80 the percentage of time the speed was <= 80
 * @param s100 the percentage of time the speed was <= 100
 * @param s120 the percentage of time the speed was <= 120
 * @param s140 the percentage of time the speed was <= 140
 * @param s160 the percentage of time the speed was <= 160
 * @param s200 the percentage of time the speed was <= 200
 * @param sm200 the percentage of time the speed was > 200
 * @return
 */
int speedRisklevel(int s60,int s80,int s100,int s120,int s140,int s160,int s200,int sm200 )
{
    int speedrisk=0;
int speedscore=(1*s60)+(2*s80)+(3*s100)+(4*s120)+(5*s140)+(6*s160)+(7*s200)+(8*sm200);


if(speedscore<=100)
    speedrisk=0;
else if(speedscore<=200)
    speedrisk=1;
else if(speedscore<=300)
    speedrisk=2;
else if(speedscore<=400)
    speedrisk=3;
else if(speedscore<=500)
    speedrisk=4;
else if(speedscore<=600)
    speedrisk=5;
else if(speedscore<=700)
    speedrisk=6;
else if(speedscore<=800)
    speedrisk=6;

      return speedrisk;
}//speedRisklevel
/**
 * get the total risk level that depends on all the other risk factors.
 * <p>If the speed risk level is less than 4 and key duration risk level less than 5 then the formal we use to calculate the total risk weight is:
 * <p> total risk weight=no. of key interaction risk level+(duration of key interaction risk level x 4)+(call duration risk level x 2)+(speed risk level x 3);
 *<p>If the speed risk level is less than 4 and key duration risk level greater than 5 then the formal we use to calculate the total risk weight is:
 * <p> total risk weight=(duration of key interaction risk level x 8)+(call duration risk level x 2)+(speed risk level x 1);
 * <p>If the speed risk level is greater of equal 4 then the formal we use to calculate the total risk  weight is:
 * <p> total risk  weight=(duration of key interaction risk level)+(call duration risk level)+(speed risk level x 9);
 *<p> Then depending on the total risk weight we choose the total risk level.
 * @param speedR speed risk level
 * @param keyR no. of key interaction risk level
 * @param keydurR duration of key interaction risk level
 * @param callR no. of calls risk level
 * @param calldurR call duration risk level
 * @return total risk level.
 */
int finallRiskScore(int speedR,int keyR,int keydurR,int callR,int calldurR)
{
    int Totriskweight=0;
    if(speedR<4)
    {
        if(keydurR<5)
            Totriskweight=keyR+(keydurR*4)+(calldurR*2)+(speedR*3);
        else
            Totriskweight=(keydurR*8)+(calldurR*2)+(speedR*1);
    }
    else
        Totriskweight=(keydurR)+(calldurR)+(speedR*9);

    int totalrisklevel=0;
if(Totriskweight==0)
    totalrisklevel=0;
else if(Totriskweight>=1 && Totriskweight<=11)
    totalrisklevel=1;
else if(Totriskweight>=12 && Totriskweight<=22)
    totalrisklevel=2;
else if(Totriskweight>=23 && Totriskweight<=33)
    totalrisklevel=3;
else if(Totriskweight>=34 && Totriskweight<=44)
    totalrisklevel=4;
else if(Totriskweight>=45 && Totriskweight<=55)
    totalrisklevel=5;
else if(Totriskweight>=56 && Totriskweight<=66)
    totalrisklevel=6;

    return totalrisklevel;
}/**
  *  get the corrsponding risk status string.
 * @param r risk level
 * @return risk status String.
 */
String riskString(int r)
{
    String risk="";
    if(r==0)
        risk=    "So boring you're#safe !!!";
    else if (r==1)
            risk="You passed the school#bus driver test";
    else if(r==2)
            risk="You drive alive#";
    else if(r==3)
            risk="Not too safe and#not too risky";
    else if(r==4)
            risk="Menace to society#";
   else if(r==5)
           risk="Don't drive in#my neighborhood";
   else if(r==6)
           risk="Ticking bomb#";

   return risk;
}//risk
}
