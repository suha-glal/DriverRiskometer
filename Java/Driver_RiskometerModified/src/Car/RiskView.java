/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Car;
import javax.microedition.lcdui.*;
/**
 * the class diplay risk view which include the three risk factors.
 * Which are:
 * 1. speed
 * 2.calls
 * 3.key interactions.
 * @author Suha
 */
public class RiskView extends CustomItem{
 /**
 * represents the date, it has the following formate day(number)/month(letters)/year(number).
 */
String ttitle;
/**
 * user risk status sentence which include:
 * 1."So boring you're#safe !!!"
 * 2."You passed the school#bus driver test"
 * 3."You drive alive#"
 * 4."Not too safe and#not too risky"
 * 5."Menace to society#"
 * 6."Don't drive in#my neighborhood"
 * 7."Ticking bomb#"
 * if the sentence is long Str1 hold the part before '#'
 */
String Str1;
/**
 * if the sentence is long Str2 hold the part after '#'
 */
String Str2;
/**
 * represents the total risk value.
 */
int totalrisk;
/**
 * represents the key interactions durations risk value.
 */
int keydur;
/**
 * represents the number of key interactions risk value.
 */
int key;
/**
 * represents the call durations risk value.
 */
int calldur;
/**
 * represents number of calls risk value.
 */
int call;
/**
 * represents the speed risk value.
 */
int speed;
/**
 * initialize the class variables.
 * @param title represents the date, it has the following formate day(number)/month(letters)/year(number).
 * @param p1 user risk status sentence.
 * @param s1 represents the total risk value.
 * @param s2 represents the speed risk value.
 * @param s3 represents the number of key interactions risk value.
 * @param s4 represents the key interactions durations risk value.
 * @param s5 represents number of calls risk value.
 * @param s6 represents the call durations risk value.
 */
  public RiskView(String title,String p1,int s1,int s2,int s3,int s4,int s5,int s6){
    super(null);
ttitle=title;
int in=0;
in=p1.indexOf("#");
Str1=p1.substring(0, in);
Str2=p1.substring(in+1);
totalrisk=s1;
speed=s2;
key=s3;
keydur=s4;
call=s5;
calldur=s6;

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
    
     
        g.drawLine(0,20, 219, 20);


        g.setColor(0x660000);
        g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        g.drawString(ttitle, 3, 3, g.LEFT | g.TOP);


 
 
 g.drawString("Total Risk Level", 42, 58, g.LEFT | g.TOP);
drawRiskSquares(g,76,totalrisk,15,42);

g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));

g.drawString("Speed Risk Level:", 3, 94, g.LEFT | g.TOP);
drawRiskSquares(g,112,speed,13,0);
 g.drawString("Key interaction Risk level:", 3, 130, g.LEFT | g.TOP);
 int keymid=(key+keydur)/2;
drawRiskSquares(g,148,keymid,13,0);
//g.drawString("Key interaction duration Risk:", 3,166, g.LEFT | g.TOP);
//drawRiskSquares(g,184,keydur,13,0);
g.drawString("Calls Risk Level:", 3, 166, g.LEFT | g.TOP);
int callmid=(call+calldur)/2;
drawRiskSquares(g,184,call,13,0);
/* $$g.drawString("Calls Risk Level:", 3, 202, g.LEFT | g.TOP);
int callmid=(call+calldur)/2;
drawRiskSquares(g,220,call,13,0);*/
//g.drawString("Calls duration Risk Level:", 3, 238, g.LEFT | g.TOP);
//drawRiskSquares(g,256,calldur,13,0);

 drawSentenceHighlight(g,totalrisk);
 g.setColor(0x000000);
  g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
    // the distance between sentence is 15 pixle
 g.drawString(Str1, 3, 22, g.LEFT | g.TOP);

 g.drawString(Str2,3, 40, g.LEFT | g.TOP);

  }
  /**
   * draw the highlight for the status sentence.
   * @param g the Graphics object to be used for rendering the item
   * @param c specifiy the color of the sentence's highlighter.
   */
  void drawSentenceHighlight(Graphics g,int c)
  {
    if(c==0)
 g.setColor(0x00CC00);
    if(c==1)
 g.setColor(0x00FF33);
    if(c==2)
 g.setColor(0x99FF66);
    if(c==3)
 g.setColor(0xFF9900);
    if(c==4)
 g.setColor(0xFF6600);
    if(c==5)
 g.setColor(0xFF3333);
    if(c==6)
 g.setColor(0xFF0000);

  g.fillRect(3, 22, 215,18);
  if(Str2.length()>1)
  g.fillRect(3, 40, 215,18);
  }
  /**
   * draw squares and depending on the risk factor value the squares will be colored.
   * @param g  the Graphics object to be used for rendering the item
   * @param h the height where we draw the squeres
   * @param c specifiy the color of the squer that represent the risk
   * @param size the square size.
   * @param mrg margin in the x a
   */
void drawRiskSquares(Graphics g,int h,int c,int size,int mrg)
{
    
  g.setColor(0x777777);

if(c==0)
 g.setColor(0x00CC00);
 g.fillRect(3+ mrg, h, size,size);
 g.setColor(0x777777);

 if(c==1)
 g.setColor(0x00FF33);
 g.fillRect(19+ mrg, h, size,size);
 g.setColor(0x777777);

if(c==2)
 g.setColor(0x99FF66);
 g.fillRect(35+ mrg, h, size,size);
 g.setColor(0x777777);

if(c==3)
 g.setColor(0xFF9900);
 g.fillRect(51+ mrg, h,size,size);
 g.setColor(0x777777);

if(c==4)
 g.setColor(0xFF6600);
 g.fillRect(67+ mrg, h, size,size);
 g.setColor(0x777777);

if(c==5)
 g.setColor(0xFF3333);
 g.fillRect(83+ mrg, h, size,size);
 g.setColor(0x777777);

if(c==6)
 g.setColor(0xFF0000);
 g.fillRect(99+ mrg, h, size,size);
 g.setColor(0x777777);

 g.setColor(0x000000);
 g.drawString("1", 3+ mrg, h-1, g.LEFT | g.TOP);
 g.drawString("2", 19+ mrg, h-1, g.LEFT | g.TOP);
 g.drawString("3", 35+ mrg, h-1, g.LEFT | g.TOP);
 g.drawString("4", 51+ mrg, h-1, g.LEFT | g.TOP);
 g.drawString("5", 67+ mrg, h-1, g.LEFT | g.TOP);
 g.drawString("6", 83+ mrg, h-1, g.LEFT | g.TOP);
 g.drawString("7", 99+ mrg, h-1, g.LEFT | g.TOP);
  g.setColor(0x660000);
}//
}

