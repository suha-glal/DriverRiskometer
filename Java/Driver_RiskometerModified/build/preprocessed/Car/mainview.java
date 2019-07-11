/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Car;

import javax.microedition.lcdui.*;
/**
 * the class display important statistics about the danger assosiated with using mobiles while driving.
 * @author Suha
 */
public class mainview extends CustomItem{

  public mainview(){
    super(null);
   
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
    g.drawLine(0,37, 219,37);
   // g.drawLine(0,59, 219,59);
    g.drawLine(0,75, 219,75);
     g.drawLine(0,125, 219, 125);
     g.drawLine(0,176, 219, 176);
     //g.drawLine(0,192, 219, 192);
   
  
   g.setColor(0x660000);
     g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
     g.drawString("Risk of using mobiles ", 3, 3, g.LEFT | g.TOP);
     g.drawString("while driving", 3, 20, g.LEFT | g.TOP);


  g.setColor(0xFFCC33);
   g.setStrokeStyle(g.SOLID);
    g.fillRect(1, 38, 218, 37);
   // g.fillRect(1, 65, 218, 15);

     g.setColor(0x993300);
    g.setStrokeStyle(g.SOLID);
    //vertical
    g.drawLine(100,37, 100, 249);

 g.setColor(0x000000);
 g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
 g.drawString("Mobile Task", 3,50, g.LEFT | g.TOP);
 g.drawString("Risk of Crash", 103,50, g.LEFT | g.TOP);
 //g.drawString("Light Vehicle/Cars", 3,60, g.LEFT | g.TOP);
 g.drawString("Dialing Cell", 3,79, g.LEFT | g.TOP);
g.drawString("Phone", 3,92, g.LEFT | g.TOP);
g.drawString("2.8 times as high",103,79, g.LEFT | g.TOP);
g.drawString("as non distracted",103,92, g.LEFT | g.TOP);
g.drawString("driving",103,105, g.LEFT | g.TOP);
g.drawString("Talking or",3,127, g.LEFT | g.TOP);
g.drawString("Listening to",3,141, g.LEFT | g.TOP);
g.drawString("Cell Phone",3,154, g.LEFT | g.TOP);
g.drawString("1.3 times as high",103,127, g.LEFT | g.TOP);
g.drawString("as non distracted",103,140, g.LEFT | g.TOP);
g.drawString("driving",103,153, g.LEFT | g.TOP);
 //g.drawString("Heavy Vehicles/Trucks",3,177, g.LEFT | g.TOP);
 g.drawString("Text",3,177, g.LEFT | g.TOP);
 g.drawString("messaging",3,190, g.LEFT | g.TOP);
g.drawString("23.2 times as high",103,177, g.LEFT | g.TOP);
g.drawString("as non distracted",103,190, g.LEFT | g.TOP);
g.drawString("driving",103,203, g.LEFT | g.TOP);
 /*
  
  g.drawString("60 < Speed <= 80", 3, 50, g.LEFT | g.TOP);
   g.drawString("80 < Speed <= 100", 3,75, g.LEFT | g.TOP);
   g.drawString("100 < Speed <= 120", 3, 100, g.LEFT | g.TOP);
   g.drawString("120 < Speed <= 140", 3, 125, g.LEFT | g.TOP);
   g.drawString("140 < Speed <= 160", 3, 150, g.LEFT | g.TOP);
   g.drawString("160 < Speed <= 200", 3, 175, g.LEFT | g.TOP);
   g.drawString("Speed > 200", 3, 200, g.LEFT | g.TOP);
  
   g.setColor(0xFF6600);*/
 /*  g.drawString(""+s100+" %",153, 75, g.LEFT | g.TOP);
   g.drawString(""+s120+" %", 153, 100, g.LEFT | g.TOP);
   g.drawString(""+s140+" %", 153, 125, g.LEFT | g.TOP);
   g.setColor(0x009900);
   g.drawString(""+s60+" %",153, 25, g.LEFT | g.TOP);
   g.drawString(""+s80+" %",153, 50, g.LEFT | g.TOP);
   g.setColor(0xFF0000);
   g.drawString(""+s160+" %",153, 150, g.LEFT | g.TOP);
   g.drawString(""+s200+" %", 153, 175, g.LEFT | g.TOP);
   g.drawString(""+sm200+" %", 153, 200, g.LEFT | g.TOP);*/

  
  }

}
