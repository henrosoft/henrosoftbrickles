/*
 * Button.java
 *
 * Created on May 28, 2008, 9:51 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package slickbrickles;

import java.awt.geom.Point2D;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author henry
 */
public class Button {
    
    private String myString;
    private Point2D.Float myPosition;
    private Color myColor;
    public Button(String s) {
        myString  = s;
        myColor = new Color(100,0,0);
    }
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
    }

    public void mouseClicked(int button, int x, int y, int clickCount) {
    }
    public void render(Graphics g)
    {
        g.drawString(myString, myPosition.x, myPosition.y);
    }
    
}
