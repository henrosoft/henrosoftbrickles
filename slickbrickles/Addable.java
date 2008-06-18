/*
 * Addable.java
 *
 * Created on May 23, 2008, 10:14 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package slickbrickles;

import java.awt.geom.Point2D;
import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Shape;
import net.phys2d.raw.shapes.Shape;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.InputListener;
import slickbrickles.ui.MainGame;


public abstract class Addable extends Body implements InputListener{
    protected Point2D.Float mySize;
    public abstract void render(Graphics g);
    public abstract Point2D.Float getSize();
    public abstract void update();
    public Addable(Shape s, float m)
    {
        super(s,m);
        setRestitution(1);
        setFriction(0);
    }
    public void setInput(Input input) {
    }
    public boolean isAcceptingInput() {
       return true;
    }
    public void inputEnded() {
    }
    public void keyPressed(int i, char c) {
    }
    public void keyReleased(int i, char c) {
    }
    public void mouseWheelMoved(int i) {
    }
    public void mouseClicked(int i, int i0, int i1, int i2) {
    }
    public void mousePressed(int i, int i0, int i1) {
    }
    public void mouseReleased(int i, int i0, int i1) {
    }
    public void mouseMoved(int i, int i0, int i1, int i2) {
    }
    public void controllerLeftPressed(int i) {
    }
    public void controllerLeftReleased(int i) {
    }
    public void controllerRightPressed(int i) {
    }
    public void controllerRightReleased(int i) {
    }
    public void controllerUpPressed(int i) {
    }
    public void controllerUpReleased(int i) {
    }
    public void controllerDownPressed(int i) {
    }
    public void controllerDownReleased(int i) {
    }
    public void controllerButtonPressed(int i, int i0) {
    }
    public void controllerButtonReleased(int i, int i0) {
    }
}
