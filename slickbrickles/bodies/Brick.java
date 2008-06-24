/*
 * Brick.java
 *
 * Created on May 24, 2008, 6:31 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package slickbrickles.bodies;

import java.awt.geom.Point2D;
import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Box;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import slickbrickles.Addable;
import slickbrickles.ui.Main;

/**
 *
 * @author henry
 */
public class Brick extends Addable{
    public int SIZE_X = 55;
    public int SIZE_Y = 25;
    protected Color myColor;
    /** Creates a new instance of Brick */
    public Brick(float x, float y) {
        super(new Box(55, 25), Body.INFINITE_MASS);
        setPosition(x,y);
        myColor = new Color(255,0,0);
        mySize = new Point2D.Float(SIZE_X,SIZE_Y);
    }
    public void collided(Body b)
    {
        Main.theGame.remove(this);
    }
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(getPosition().getX()-mySize.x/2, getPosition().getY()-mySize.y/2, mySize.x, mySize.y);
        g.setColor(myColor);
        g.fillRect(getPosition().getX()+2-mySize.x/2, getPosition().getY()+2-mySize.y/2, mySize.x-3, mySize.y-3);
    }

    public Point2D.Float getSize() {
        return mySize;
    }
    public void update() {
    }
    
}
