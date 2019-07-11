/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Car;

import javax.microedition.lcdui.*;
import java.io.IOException;
/**
 * shows driver riskometer logo.
 * @author Suha
 */
public class StartScreen extends Canvas {
    /**
     * draw the logo image to the screen display.
     * @param g
     */
     public void paint(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        Image logo = null;

    try {
      logo = Image.createImage("/images/Drivelogo.jpg" );
    }
    catch( IOException e ){
    }

        g.drawImage( logo, 0,0, UP);


}
}
