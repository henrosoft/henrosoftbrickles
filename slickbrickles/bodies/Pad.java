/*
 * Pad.java
 *
 * Created on May 23, 2008, 10:13 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package slickbrickles.bodies;

import java.awt.geom.Point2D;
import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Box;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import slickbrickles.*;
import slickbrickles.Addable;
import slickbrickles.ui.Main;
import slickbrickles.ui.MainGame;
import static slickbrickles.util.Utils.*;
/**
 *
 * @author henry
 */
public class Pad extends Addable{
    private Color myColor;
    private float velocity = 0;
    public Pad(float sx, float sy) {
        super(new Box(sx,sy),Body.INFINITE_MASS);
        setPosition(250,Main.state.SIZE_Y-50);
        mySize = new Point2D.Float(sx,sy);
        myColor = new Color(0,0,0);
    }

    public void render(Graphics g) {
        g.setColor(myColor);
        g.fillRect(getPosition().getX()-mySize.x/2, getPosition().getY()-mySize.y/2, mySize.x, mySize.y);
    }

    public void update() {
        if(getPosition().getX()-mySize.x/2.0f<=0 && velocity<0)
               velocity = 0;
        if(getPosition().getX()+mySize.x/2.0f>Main.state.SIZE_X && velocity>0)
               velocity = 0;
        adjustPosition(v(velocity,0),1);
    }

    public Point2D.Float getSize() {
        return mySize;
    }

    public void keyPressed(int i, char c) {
        if(i == Input.KEY_LEFT)
        {
            velocity = -8;
        }
        if(i == Input.KEY_RIGHT)
            velocity = 8;
    }
     public void keyReleased(int i, char c) {
           velocity = 0;
    }
}
