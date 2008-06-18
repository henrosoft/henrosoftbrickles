/*
 * Ball.java
 *
 * Created on May 24, 2008, 11:59 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package slickbrickles.bodies;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.shapes.Circle;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import slickbrickles.*;
import slickbrickles.Addable;
import slickbrickles.bodies.Brick;
import slickbrickles.ui.Main;
import slickbrickles.ui.MainGame;
import static slickbrickles.util.Utils.*;
/**
 *
 * @author henry
 */
public class Ball extends Addable{
    private Color myColor;
    
    public Ball(float x, float y, float sx, float sy, float vx, float vy) {
        super(new Circle(sx/2f),10);
        setPosition(x,y);
        adjustVelocity(v(vx,vy));
        myColor = new Color(0,0,0);
        mySize = new Point2D.Float(sx,sy);
    }

    public void render(Graphics g) {
        g.setColor(myColor);
        g.fillOval(getPosition().getX()-mySize.x/2, getPosition().getY()-mySize.y/2, mySize.x, mySize.y);
    }

    public void update() { 
    }
    public Point2D.Float getSize() {
        return mySize;
    }
}
