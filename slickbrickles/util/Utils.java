/*
 * Utils.java
 *
 * Created on May 24, 2008, 12:54 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package slickbrickles.util;

import java.awt.Dimension;
import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.shapes.Circle;
import net.phys2d.raw.shapes.Line;
import net.phys2d.raw.shapes.Polygon;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import slickbrickles.ui.Main;

/**
 *
 * @author henry
 */
public class Utils {

    
    /** Creates a new instance of Utils */
    public static Vector2f v(Number x, Number y) {
		return new Vector2f(x.floatValue(), y.floatValue());
	}

	public static Dimension d(Number x, Number y) {
		return new Dimension(x.intValue(), y.intValue());
	}

	public static Vector2f v(Dimension d) {
		return v(d.getWidth(), d.getHeight());
	}

	public static Dimension d(Vector2f d) {
		return d(d.getX(), d.getY());
	}
        public static void drawMenuItem(String s, Font font, Graphics g, int level)
        {
            g.setFont(font);
            g.drawString(s, Main.state.SIZE_X-10-font.getWidth(s),Main.state.SIZE_Y-(font.getHeight(s))*level);
        }
	public static float range(Number minR, Number maxR) {
		float min = minR.floatValue();
		float max = maxR.floatValue();
		return (float)(min+(max-min)*Math.random());
	}

	public static Vector2f direction(Number rotation) {
		return v(Math.sin(rotation.doubleValue()),
		        -Math.cos(rotation.doubleValue()));
	}

	public static boolean oneIn(int num) {
		return num*Math.random() < 1;
	}

	public static Color randomColor() {
		return new Color((int)range(1,255),(int)range(1,255),(int)range(1,255));
	}
    public static void drawBody(Graphics g, Body body) {
		if (body.getShape() instanceof Box) {
			drawBoxBody(g,body,(Box) body.getShape());
		}
		if (body.getShape() instanceof Circle) {
			drawCircleBody(g,body,(Circle) body.getShape());
		}
		if (body.getShape() instanceof Line) {
			drawLineBody(g,body,(Line) body.getShape());
		}
		if (body.getShape() instanceof Polygon) {
			drawPolygonBody(g,body,(Polygon) body.getShape());
		}
	}
	
	/**
	 * Draw a polygon into the demo
	 * 
	 * @param g The graphics to draw the poly onto
	 * @param body The body describing the poly's position
	 * @param poly The poly to be drawn
	 */
	public static void drawPolygonBody(Graphics g, Body body, Polygon poly) {
		g.setColor(Color.black);

		ROVector2f[] verts = poly.getVertices(body.getPosition(), body.getRotation());
		for ( int i = 0, j = verts.length-1; i < verts.length; j = i, i++ ) {			
			g.drawLine(
					(int) (0.5f + verts[i].getX()),
					(int) (0.5f + verts[i].getY()), 
					(int) (0.5f + verts[j].getX()),
					(int) (0.5f + verts[j].getY()));
		}
	}

	/**
	 * Draw a line into the demo
	 * 
	 * @param g The graphics to draw the line onto
	 * @param body The body describing the line's position
	 * @param line The line to be drawn
	 */
	public static void drawLineBody(Graphics g, Body body, Line line) {
		g.setColor(Color.black);
//
//		float x = body.getPosition().getX();
//		float y = body.getPosition().getY();
//		float dx = line.getDX();
//		float dy = line.getDY();
//		
//		g.drawLine((int) x,(int) y,(int) (x+dx),(int) (y+dy));
		Vector2f[] verts = line.getVertices(body.getPosition(), body.getRotation());
		g.drawLine(
				(int) verts[0].getX(),
				(int) verts[0].getY(), 
				(int) verts[1].getX(),
				(int) verts[1].getY());
	}
	
	/**
	 * Draw a circle in the world
	 * 
	 * @param g The graphics contact on which to draw
	 * @param body The body to be drawn
	 * @param circle The shape to be drawn
	 */
	public static void drawCircleBody(Graphics g, Body body, Circle circle) {
		g.setColor(Color.black);
		float x = body.getPosition().getX();
		float y = body.getPosition().getY();
		float r = circle.getRadius();
		float rot = body.getRotation();
		float xo = (float) (Math.cos(rot) * r);
		float yo = (float) (Math.sin(rot) * r);
		
		g.drawOval((int) (x-r),(int) (y-r),(int) (r*2),(int) (r*2));
		g.drawLine((int) x,(int) y,(int) (x+xo),(int) (y+yo));
	}
	
	/**
	 * Draw a box in the world
	 * 
	 * @param g The graphics contact on which to draw
	 * @param body The body to be drawn
	 * @param box The shape to be drawn
	 */
	public static void drawBoxBody(Graphics g, Body body, Box box) {
		Vector2f[] pts = box.getPoints(body.getPosition(), body.getRotation());
		
		Vector2f v1 = pts[0];
		Vector2f v2 = pts[1];
		Vector2f v3 = pts[2];
		Vector2f v4 = pts[3];
		
		g.setColor(Color.black);
		g.drawLine((int) v1.x,(int) v1.y,(int) v2.x,(int) v2.y);
		g.drawLine((int) v2.x,(int) v2.y,(int) v3.x,(int) v3.y);
		g.drawLine((int) v3.x,(int) v3.y,(int) v4.x,(int) v4.y);
		g.drawLine((int) v4.x,(int) v4.y,(int) v1.x,(int) v1.y);
	}
    
}
