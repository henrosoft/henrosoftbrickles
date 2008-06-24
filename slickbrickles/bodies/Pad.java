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
import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Box;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import slickbrickles.Addable;
import slickbrickles.ui.Main;
import slickbrickles.ui.StateHandler;
import static slickbrickles.util.Utils.*;
/**
 *
 * @author henry
 */
public class Pad extends Addable{
    private Color myColor;
    private float velocity = 0;
    private Ball ball;
    public Pad(float sx, float sy) {
        super(new Box(sx,sy),Body.INFINITE_MASS);
        setPosition(250,StateHandler.SIZE_Y-50);
        mySize = new Point2D.Float(sx,sy);
        myColor = new Color(0,0,0);
        Ball b = new Ball(getPosition().getX(), getPosition().getY()-mySize.y/2f-Ball.SIZE_Y/2f,0,0);
        Main.theGame.add(b);
        ball = b;
    }
    public void resetBall()
    {
        Ball b = new Ball(getPosition().getX(), getPosition().getY()-mySize.y/2f-Ball.SIZE_Y/2f,0,0);
        Main.theGame.add(b);
        ball = b;
    }
    public void render(Graphics g) {
        g.setColor(myColor);
        g.fillRect(getPosition().getX()-mySize.x/2, getPosition().getY()-mySize.y/2, mySize.x, mySize.y);
    }

    public void update() {
        if(getPosition().getX()-mySize.x/2.0f<=0 && velocity<0)
               velocity = 0;
        if(getPosition().getX()+mySize.x/2.0f>StateHandler.SIZE_X && velocity>0)
               velocity = 0;
        adjustPosition(v(velocity,0),1);
        if(!Main.theGame.hasBegun())
           ball.setPosition(getPosition().getX(), getPosition().getY()-mySize.y/2f-Ball.SIZE_Y/2f);
    }

    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
         if(Main.theGame.getMouseEnabled()/* &&
                newx+mySize.x/2.0f<=StateHandler.SIZE_X &&
                newx-mySize.x/2.0f>=0*/)
             setPosition(newx,getPosition().getY());
    }
    public void mousePressed(int button, int x, int y)
    {
        if(Main.theGame.getMouseEnabled() && !Main.theGame.hasBegun())
        {
            double vx = (Math.random()*200);
            double vy = (Math.random()*100)-200;
            ball.adjustVelocity(v(vx,vy));
            Main.theGame.begin();
        }
    }
    public Point2D.Float getSize() {
        return mySize;
    }
    public void keyPressed(int i, char c) {
        if(!Main.theGame.getMouseEnabled())
        {
            if(i == Input.KEY_LEFT)
                velocity = -8;
            if(i == Input.KEY_RIGHT)
                velocity = 8;
            if(i == Input.KEY_SPACE && !Main.theGame.hasBegun())
            {
                 double vx = (Math.random()*200);
                 double vy = (Math.random()*100)-200;
                 ball.adjustVelocity(v(vx,vy));
                 Main.theGame.begin();
            }
        }
    }
    public void keyReleased(int i, char c) {
           velocity = 0;
    }
}
