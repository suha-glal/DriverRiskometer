/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Car;
import javax.microedition.lcdui.*;
/**
 *Display speed statistics.
 * @author Suha
 */
public class speedTable extends CustomItem{
 /**
 * represents the day date, it has the following formate day(number)/month(letters)/year(number).
 */
String ttitle;
/**
    * percentage of time over all journys the driver speed is <= 60.
    */
    int s60;

    /**
    * percentage of time over all journys the driver speed is <= 80.
    */
    int s80;

/**
 *  percentage of time over all journys the driver speed is <= 100.
 */
    int s100;
/**
 *  percentage of time over all journys the driver speed is <= 120.
 */
   int s120;
/**
 *  percentage of time over all journys the driver speed is <= 140.
 */
  int s140;
/**
 *  percentage of time over all journys the driver speed is <= 160.
 */
   int s160;

  /**
 *  percentage of time over all journys the driver speed is > 200.
 */
   int s200;
/**
 *  percentage of time over all journys the driver speed is <= 200.
 */
    int sm200;



/**
 * initialize the class variables.
 * @param title represents the day date, it has the following formate day(number)/month(letters)/year(number).
 * @param ss6 percentage of time over all journys the driver speed is <= 60.
 * @param ss8 percentage of time over all journys the driver speed is <= 80.
 * @param ss100 percentage of time over all journys the driver speed is <= 100.
 * @param ss120 percentage of time over all journys the driver speed is <= 120.
 * @param ss140 percentage of time over all journys the driver speed is <= 140.
 * @param ss160 percentage of time over all journys the driver speed is <= 160.
 * @param lesq200 percentage of time over all journys the driver speed is <= 200.
 * @param mor200 percentage of time over all journys the driver speed is > 200.
 */
  public speedTable(String title,int ss6,int ss8,int ss100,int ss120,int ss140,int ss160,int lesq200,int mor200){
    super(null);
    ttitle=title;
s60=ss6;
s80=ss8;
s100=ss100;
s120=ss120;
s140=ss140;
s160=ss160;
s200=lesq200;
sm200=mor200;
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
    
     //horizontal lines
    g.drawLine(0,20, 219, 20);
    g.drawLine(0,45, 219, 45);
    g.drawLine(0,70, 219, 70);
    g.drawLine(0,95, 219, 95);
    g.drawLine(0,120, 219, 120);
    g.drawLine(0,145, 219, 145);
    g.drawLine(0,170, 219, 170);
    g.drawLine(0,195, 219, 195);
  //vertical
    g.drawLine(150,20, 150, 219);

   g.setColor(0x660000);
     g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
     g.drawString(ttitle, 3, 3, g.LEFT | g.TOP);
      g.setColor(0x000000);
  g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
  
  g.drawString("Speed <= 60", 3, 25, g.LEFT | g.TOP);
  g.drawString("60 < Speed <= 80", 3, 50, g.LEFT | g.TOP);
   g.drawString("80 < Speed <= 100", 3,75, g.LEFT | g.TOP);
   g.drawString("100 < Speed <= 120", 3, 100, g.LEFT | g.TOP);
   g.drawString("120 < Speed <= 140", 3, 125, g.LEFT | g.TOP);
   g.drawString("140 < Speed <= 160", 3, 150, g.LEFT | g.TOP);
   g.drawString("160 < Speed <= 200", 3, 175, g.LEFT | g.TOP);
   g.drawString("Speed > 200", 3, 200, g.LEFT | g.TOP);
  
   g.setColor(0xFF6600);
   g.drawString(""+s100+" %",153, 75, g.LEFT | g.TOP);
   g.drawString(""+s120+" %", 153, 100, g.LEFT | g.TOP);
   g.drawString(""+s140+" %", 153, 125, g.LEFT | g.TOP);
   g.setColor(0x009900);
   g.drawString(""+s60+" %",153, 25, g.LEFT | g.TOP);
   g.drawString(""+s80+" %",153, 50, g.LEFT | g.TOP);
   g.setColor(0xFF0000);
   g.drawString(""+s160+" %",153, 150, g.LEFT | g.TOP);
   g.drawString(""+s200+" %", 153, 175, g.LEFT | g.TOP);
   g.drawString(""+sm200+" %", 153, 200, g.LEFT | g.TOP);

  
  }

}
