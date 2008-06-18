/*
 * UnbreakableBrick.java
 *
 * Created on May 25, 2008, 1:10 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package slickbrickles.bodies;

import net.phys2d.raw.Body;
import org.newdawn.slick.Color;
import slickbrickles.*;

/**
 *
 * @author henry
 */
public class UnbreakableBrick extends Brick{
    
    /** Creates a new instance of UnbreakableBrick */
    public UnbreakableBrick(float x, float y) {
        super(x,y);
        myColor = new Color(150,150,150);
    }
    public void collided(Body other)
    {
    }
    
}
