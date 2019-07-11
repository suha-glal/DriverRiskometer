/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Car;

import javax.microedition.lcdui.*;
/**
 * the class display statistics over all journeys.
 * @author Suha
 */
public class SuJorTable extends CustomItem{
/**
 * represents the day date, it has the following formate day(number)/month(letters)/year(number).
 */
String ttitle;
/**
 * represents number of all journeys.
 */
int nofjor;
/**
 * represents average speed for  a day.
 */
int avgspeed;
/**
 * represents the maximum speed for a day.
 */
int maxspe;
/**
 * represents the number of calls for a day.
 */
int noofcall;
/**
 * represents the duration of calls for a day.
 */
int calldur;
/**
 * represents the number of key interaction for a day.
 */
int key;
/**
 * represents the duration of all key interactions of one day.
 */
int keydu;
/**
 * represents the duration of all journeys of one day.
 */
int dur;
/**
 * represents duration of the journeys' hours.
 */
int durhour;
/**
 * represents duration of the journeys' minutes.
 */
int durmin;
/**
 * represents duration of the key interaction duration's hours.
 */
int hourk;
/**
 * represents duration of the key interaction duration's minutes.
 */
int mink;
/**
 * represents duration of the calls duration's hours.
 */
int hourcal;
/**
 * represents duration of the call duration's minutes.
 */
int mincal;
/**
 * initialize the class variables.
 * @param title represents the day date, it has the following formate day(number)/month(letters)/year(number)
 * @param noofjor represents number of all journeys.
 * @param averagespeed represents average speed for  a day.
 * @param maxspeed represents the maximum speed for a day.
 * @param noofcalls represents the number of calls for a day.
 * @param durationofcalls represents the duration of calls for a day.
 * @param k represents the number of key interaction for a day.
 * @param kdu represents the duration of all key interactions of one day.
 * @param d represents the duration of all journeys of one day.
 */
  public SuJorTable(String title,int noofjor,int averagespeed,int maxspeed,int noofcalls,int durationofcalls,int k,int kdu,int d){
    super(null);
    ttitle=title;
nofjor=noofjor;
avgspeed=averagespeed;
maxspe=maxspeed;
noofcall=noofcalls;
calldur=durationofcalls;
key=k;
keydu=kdu;
dur=d;
durhour=0;
durmin=0;
hourk=0;
mink=0;
hourcal=0;
mincal=0;
durminhour(dur,calldur,keydu);
  }

  /**
 * return the minimum width of the content area, in pixels.
 * @return
 */
  public int getMinContentWidth(){
    return 220;
  }
/**
 * return the minimum height of the content area, in pixels.
 * @return
 */
  public int getMinContentHeight(){
    return 220;
  }
/**
 *return the preferred width of the content area, in pixels.
 * @return
 */
  public int getPrefContentWidth(int width){
    return getMinContentWidth();
  }
/**
 *return the preferred height of the content area, in pixels.
 * @return
 */
  public int getPrefContentHeight(int height){
    return getMinContentHeight();
  }
/**
 * convert journeys duration , key interaction duration and call duration to hours and minutes.
 * @param dura
 * @param durcall
 * @param durakey
 */
void durminhour(int dura,int durcall,int durakey)
{
    durhour=dura/60;
    durmin=dura-(durhour*60);

    hourk=durakey/3600;
    mink=(durakey-(hourk*3600))/60;

hourcal=durcall/3600;
mincal=(durcall-(hourcal*3600))/60;
}
/**
 *  paints every pixel within the given clip area.
 * @param g the Graphics object to be used for rendering the item
 * @param w current width of the item in pixels
 * @param h current height of the item in pixels
 */
  public void paint(Graphics g, int w, int h){

    g.setColor(0xFFCC66);
    g.setStrokeStyle(g.SOLID);
    g.fillRect(0, 0, w - 1, h - 1);
g.setColor(0x993300);
g.drawRect(0, 0, w - 1, h - 1);
    
     
    g.drawLine(0,25, 219, 25);
  /*  g.drawLine(0,79, 219, 79);
    g.drawLine(0,114, 219, 114);
    g.drawLine(0,149, 219, 149);
    g.drawLine(0,184, 219, 184);
   g.drawLine(0,219, 219, 219);
   g.drawLine(0,254, 219, 254);

    g.drawLine(150,44, 150, 289);*/

   g.setColor(0x660000);
     g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
     g.drawString(ttitle, 5, 5, g.LEFT | g.TOP);
      g.setColor(0x000000);
  g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
 int height=30;
  g.drawString("No of Journeys:", 3, height, g.LEFT | g.TOP);
  int height1=height+20;
  g.drawString("Average speed:", 3,  height1, g.LEFT | g.TOP);
   int height2=height1+20;
   g.drawString("Maximum speed:", 3,  height2, g.LEFT | g.TOP);
    int height3=height2+20;
   g.drawString("No of Calls:", 3,  height3, g.LEFT | g.TOP);
    int height4=height3+20;
   g.drawString("Duration of calls:", 3,  height4, g.LEFT | g.TOP);
    int height5=height4+20;
  g.drawString("Keys interactions:", 3,  height5, g.LEFT | g.TOP);
   int height6=height5+20;
  g.drawString("Key interactions", 3, height6, g.LEFT | g.TOP);
   int height7=height6+20;
 g.drawString("Duration:", 3, height7, g.LEFT | g.TOP);
  int height8=height7+20;
g.drawString("Journies Duration:", 3,  height8, g.LEFT | g.TOP);

   g.setColor(0xFF6600);
    g.drawString(""+nofjor,110, height, g.LEFT | g.TOP);
     g.drawString(""+avgspeed+" km/h",110, height1, g.LEFT | g.TOP);
   g.drawString(""+maxspe+" km/h", 110, height2, g.LEFT | g.TOP);
    g.drawString(""+durhour+" hr "+durmin+" min", 110, height8, g.LEFT | g.TOP);
   g.setColor(0xFF0000);
   g.drawString(""+noofcall, 110, height3, g.LEFT | g.TOP);
   g.drawString(""+hourcal+" hr "+mincal+" min", 110, height4, g.LEFT | g.TOP);
   g.drawString(""+key+"", 110, height5, g.LEFT | g.TOP);
    g.drawString(""+hourk+" hr "+mink+" min", 110, height7, g.LEFT | g.TOP);

   
  }

}
