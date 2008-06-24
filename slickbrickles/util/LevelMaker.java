/*
 * LevelMaker.java
 *
 * Created on June 18, 2008, 7:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package slickbrickles.util;

import java.util.LinkedList;
import java.util.List;
import net.phys2d.raw.Body;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Line;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import slickbrickles.bodies.Brick;
import slickbrickles.bodies.Pad;
import slickbrickles.ui.Main;
import slickbrickles.ui.StateHandler;
import slickbrickles.ui.MainGame;
import slickbrickles.ui.MainMenu;
import static slickbrickles.util.Utils.*;
/**
 *
 * @author henry
 */
public class LevelMaker extends BasicGameState{
    private World world;
    private Font menuFont;
    public static int ID = 33333;
    private List<Brick> bricks;
    private Brick cursor;
    private String position = "0 0";
    private int numBricks = 0;
    private boolean delay = true;
    /** Creates a new instance of LevelMaker */
    public LevelMaker(World w) {
        world = w;
    }
    public LevelMaker()
    {
        bricks = new LinkedList<Brick>();
        cursor = new Brick(-100,-100);
    }
    public void createWalls()
    {
        StaticBody w2 = new StaticBody(new Line(StateHandler.SIZE_X,StateHandler.SIZE_Y,StateHandler.SIZE_X,0));
        StaticBody w3 = new StaticBody(new Line(StateHandler.SIZE_X,0,0,0));
        StaticBody w4 = new StaticBody(new Line(0,0,0,StateHandler.SIZE_Y));
        w2.setRestitution(1);
        w3.setRestitution(1);
        w4.setRestitution(1);
        world.add(w2);
        world.add(w3);
        world.add(w4);
    }
    public int getID() {
        return ID;
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        menuFont = new TrueTypeFont(new java.awt.Font(null, java.awt.Font.BOLD, 25), true);
    }
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        game.getContainer().getInput().addListener(this);
    }
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        game.getContainer().getInput().removeListener(this);
    }
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setBackground(new Color(100,100,100));
        for(Brick b: bricks)
            b.render(graphics);
        cursor.render(graphics);
        postWorld(graphics);
    }
    public void postWorld(Graphics g)
    {
        drawBottomRight("Q - Quit", menuFont, g, 1);
        drawBottomRight("M - Menu", menuFont, g, 2);
        drawBottomRight("T - Test Level", menuFont, g, 3);
        drawBottomRight(position, menuFont, g, 4);
        drawBottomRight("" + numBricks, menuFont, g, 5);
    }
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
    }
    
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        int gridSeperation = 5;
        int y = ((int)newy/(cursor.SIZE_Y+gridSeperation));
        int x;
        if(y%2==0)
            x = ((int)newx/(cursor.SIZE_X+gridSeperation))*(cursor.SIZE_X+gridSeperation);
        else 
            x = (int)((double)((((int)newx/(cursor.SIZE_X+gridSeperation)))+.5)*(cursor.SIZE_X+gridSeperation));
        y *= (cursor.SIZE_Y+gridSeperation);
        cursor.setPosition(x,y);
        position = "" + x + " " + y;
    }
    public void mouseClicked(int button, int x, int y, int clickCount) {
    }
    public void mousePressed(int button, int x, int y) {
        if(delay)
        {
            bricks.add(cursor);
            cursor = new Brick(x,y);
            numBricks++;
            delay = false;
        }
        else
            delay = true;
    }
    public void keyPressed(int key, char c){
        if(key == Input.KEY_Q)
           System.exit(0);
        else if(key == Input.KEY_M)
           Main.state.enterState(MainMenu.ID);
        else if(key == Input.KEY_T)
           testLevel();
    }
    public void testLevel()
    {
        world = Main.theGame.getWorld();
        world.clear();
        createWalls();
        Body body = new Pad(300,5);
        world.add(body);
       // body = new Ball(300,300,-120*2,-40*2);
      //  world.add(body);
        for(Brick b: bricks)
        {
            
            world.add(b);
        }
        Main.state.enterState(MainGame.ID);
    }
    public void keyReleased(int key, char c){
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void createLevel1()
    {
        createWalls();
        Body b = new Pad(300,5);
        world.add(b);
       // b = new Ball(300,300,30,30,-120*2,-40*2);
       // world.add(b);
      //  b = new Ball(400,400,30,30,50*2,-130*2);
      //  world.add(b);
        double numCols = (StateHandler.SIZE_X-200)/60.0;
        double fractionHeight = .4;
        double numRows = (StateHandler.SIZE_Y*fractionHeight)/30.0;
        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
              //  if(j==3)
               //     b = new UnbreakableBrick(100+i*60, 100 + j*30);
               // else if(j==1 && (i!=0 && i!=5))
               //     b = new SplitBrick(100+i*60, 100 + j*30);
             //   else
                    b = new Brick(100+27+i*60, 100 + j*30);
                world.add(b);
            }
        }
    }
}
