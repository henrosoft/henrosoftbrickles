/*
 * Ball.java
 *
 * Created on May 24, 2008, 11:59 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package slickbrickles.bodies;

import java.awt.geom.Point2D;
import net.phys2d.raw.shapes.Circle;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import slickbrickles.Addable;
import slickbrickles.ui.Main;
import slickbrickles.ui.StateHandler;
import static slickbrickles.util.Utils.*;
/**
 *
 * @author henry
 */
public class Ball extends Addable{
    public static int SIZE_X = 30;
    public static int SIZE_Y = 30;
    private Color myColor;
    
    public Ball(float x, float y,float vx, float vy) {
        super(new Circle(SIZE_X/2f),10);
        setPosition(x,y);
        adjustVelocity(v(vx,vy));
        myColor = new Color(0,0,0);
        mySize = new Point2D.Float(SIZE_X,SIZE_Y);
    }

    public void render(Graphics g) {
        g.setColor(myColor);
        g.fillOval(getPosition().getX()-mySize.x/2, getPosition().getY()-mySize.y/2, mySize.x, mySize.y);
    }

    public void update() { 
        if(getPosition().getY()>StateHandler.SIZE_Y)
            Main.theGame.remove(this);
    }
    public Point2D.Float getSize() {
        return mySize;
    }
}
