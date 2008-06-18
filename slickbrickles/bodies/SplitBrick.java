/*
 * SplitBrick.java
 *
 * Created on May 25, 2008, 1:29 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package slickbrickles.bodies;

import java.awt.geom.Point2D;
import net.phys2d.raw.Body;
import org.newdawn.slick.Color;
import slickbrickles.*;
import slickbrickles.ui.Main;

/**
 *
 * @author henry
 */
public class SplitBrick extends Brick{
    
    /** Creates a new instance of SplitBrick */
    public SplitBrick(float x, float y) {
        super(x,y);
        myColor = new Color(0,0,255);
    }

    public void collided(Body b) {
        if(b instanceof Ball)
        {
            splitBall((Ball)b);
            super.collided(b);
        }
    }
    public void splitBall(Ball b)
    {
        Body newB = new Ball(getPosition().getX(),getPosition().getY(),b.getSize().x, b.getSize().y,getVelocity().getX()*1.2f,getVelocity().getY()/1.2f);
        Main.theGame.add(newB);
    }
    
}
