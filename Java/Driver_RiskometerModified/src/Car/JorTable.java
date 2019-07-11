/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Car;

import javax.microedition.lcdui.*;
/**
 * the class diplay the journey's statistics per journey.
 * @author Suha
 */
public class JorTable extends CustomItem{
 /**
 * represents the day date, it has the following formate day(number)/month(letters)/year(number).
 */
String ttitle;
/**
 * represents the journey number. For example if this is the first journey then jorNo equal 1.
 */
int jorNo;
/**
 * represents average speed for  a day.
 */
int avgspeed;
/**
 * represents the maximum speed for a day.
 */
int maxspeed;
/**
 * represents the number of calls for a day.
 */
int noofcall;
/**
 * represents the duration of calls for a day.
 */
int durcall;
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
 * initialize the class variables.
 * @param title represents the day date, it has the following formate day(number)/month(letters)/year(number).
 * @param s6 represents number of journeys for a day.
 * @param s8 represents average speed for a day.
 * @param s2 represents the maximum speed for a day.
 * @param s4 represents the number of calls for a day.
 * @param s44 represents the duration of calls for a day.
 * @param k represents the number of key interaction for a day.
 * @param kdu represents the duration of all key interactions of one day.
 * @param d represents the duration of all journeys of one day.
 */

  public JorTable(String title,int s6,int s8,int s2,int s4,int s44,int k,int kdu,int d){
    super(null);
    ttitle=title;
jorNo=s6;
avgspeed=s8;
maxspeed=s2;
noofcall=s4;
durcall=s44;
key=k;
keydu=kdu;
dur=d;

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
    
     
    g.drawLine(0,44, 219, 44);
  /*  g.drawLine(0,79, 219, 79);
    g.drawLine(0,114, 219, 114);
    g.drawLine(0,149, 219, 149);
    g.drawLine(0,184, 219, 184);
   g.drawLine(0,219, 219, 219);
   g.drawLine(0,254, 219, 254);

    g.drawLine(150,44, 150, 289);*/

   g.setColor(0x660000);
     g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
     g.drawString(ttitle, 5, 20, g.LEFT | g.TOP);
      g.setColor(0x000000);
  g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
  //75
  g.drawString("Journey No.:", 3, 50, g.LEFT | g.TOP);
  g.drawString("Average speed:", 3, 70, g.LEFT | g.TOP);
   g.drawString("Maximum speed:", 3, 90, g.LEFT | g.TOP);
   g.drawString("No of Calls:", 3, 110, g.LEFT | g.TOP);
   g.drawString("Duration of calls:", 3, 130, g.LEFT | g.TOP);
  g.drawString("Keys interactions:", 3, 150, g.LEFT | g.TOP);
  g.drawString("Key interactions", 3, 170, g.LEFT | g.TOP);
 g.drawString("Duration:", 3, 185, g.LEFT | g.TOP);
g.drawString("Journy Duration:", 3, 200, g.LEFT | g.TOP);

   g.setColor(0xFF6600);
    g.drawString(""+jorNo,148, 50, g.LEFT | g.TOP);
     g.drawString(""+avgspeed+" km/h",120, 70, g.LEFT | g.TOP);
   g.drawString(""+maxspeed+" km/h", 120, 90, g.LEFT | g.TOP);
    g.drawString(""+dur+" min", 148, 200, g.LEFT | g.TOP);
   g.setColor(0xFF0000);
   g.drawString(""+noofcall, 148, 110, g.LEFT | g.TOP);
   g.drawString(""+durcall+" min", 148, 130, g.LEFT | g.TOP);
   g.drawString(""+key+"", 148, 150, g.LEFT | g.TOP);
    g.drawString(""+keydu+" sec", 148, 185, g.LEFT | g.TOP);
   
  
  }

}
