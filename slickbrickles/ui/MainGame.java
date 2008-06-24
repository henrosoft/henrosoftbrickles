/*
 * MainGame.java
 *
 * Created on May 20, 2008, 6:34 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package slickbrickles.ui;

import net.phys2d.raw.Body;
import net.phys2d.raw.BodyList;
import net.phys2d.raw.World;
import net.phys2d.raw.strategies.QuadSpaceStrategy;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import slickbrickles.Addable;
import slickbrickles.bodies.Ball;
import slickbrickles.bodies.Pad;
import slickbrickles.util.LevelMaker;
import slickbrickles.util.UserInfo;
import static slickbrickles.util.Utils.*;
/**
 *
 * @author henry
 */
public class MainGame extends BasicGameState{
    
    /** Creates a new instance of MainGame */
    protected Color myBackground;
    protected StateHandler game;
    protected static Pad thePad;
    protected World world;
    protected Font menuFont;
    protected Font centerFont;
    public static int ID = 22222;
    protected LevelMaker maker;
    protected boolean begun = false;
    protected boolean mouseEnabled = false;
    protected UserInfo info;
    public MainGame(StateHandler sbg) {
        super();
        game = sbg;
        info = new UserInfo("henrybrad");
        world = new World(v(0,0), 10, new QuadSpaceStrategy(20,5));
	world.enableRestingBodyDetection(.1f, .1f, .1f);
        world.setDamping(1);
        maker = new LevelMaker(world);
        myBackground = new Color(100,100,100);
    }
    public void setMouseEnabled(boolean m)
    {
        mouseEnabled = m;
    }
    public boolean getMouseEnabled()
    {
        return mouseEnabled;
    }
    public void setWorld(World world) {
        this.world = world;
    }
    public void begin()
    {
        begun = true;
    }
    public boolean hasBegun()
    {
        return begun;
    }
    public World getWorld() {
        return world;
    }
    public void resetPad()
    {
        BodyList bodies = world.getBodies();
        for(int i = 0; i<bodies.size(); i++)
        {
            if(bodies.get(i) instanceof Pad)
                ((Pad)bodies.get(i)).resetBall();
        }
    }
    public void remove(Body b)
    {
        world.remove(b);
        if(ballCount() <= 0)
            if(info.getLives() == 0)
            {
                info.reset();
                restart();
            }
            else
            {
                info.loseLife();
                begun = false;
                resetPad();
            }
    }
    public int ballCount()
    {
        int count = 0;
        BodyList bodies = world.getBodies();
        for(int i = 0; i<bodies.size(); i++)
        {
            if(bodies.get(i) instanceof Ball)
                count++;
        }
        return count;
    }
    public void add(Body b)
    {
        world.add(b);
    }
    public void restart()
    {
        world.clear();
        begun = false;
        maker.createLevel1();
    }
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException{
        menuFont = new TrueTypeFont(new java.awt.Font(null, java.awt.Font.BOLD, 20), true);
        centerFont = new TrueTypeFont(new java.awt.Font(null, java.awt.Font.BOLD, 50), true);
        info.init();
        maker.createLevel1();
    }
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        game.getContainer().getInput().addListener(this);
    }
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        game.getContainer().getInput().removeListener(this);
    }
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
       preWorld(gameContainer, g);
       g.setBackground(myBackground);
       BodyList bodies = world.getBodies();
       for(int i = 0; i<bodies.size(); i++)
       {
           if(bodies.get(i) instanceof Addable)
            ((Addable)bodies.get(i)).render(g);
       }
       postWorld(gameContainer, g);
    }
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        world.step();
        BodyList bodies = world.getBodies();
       for(int j = 0; j<bodies.size(); j++)
       {
           if(bodies.get(j) instanceof Addable)
            ((Addable)bodies.get(j)).update();
       }
    }

    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
       BodyList bodies = world.getBodies();
       for(int i = 0; i<bodies.size(); i++)
       {
           if(bodies.get(i) instanceof Addable)
            ((Addable)bodies.get(i)).mouseMoved(oldx,oldy,newx,newy);
       }
    }

    public void mousePressed(int button, int x, int y) {
       BodyList bodies = world.getBodies();
       for(int i = 0; i<bodies.size(); i++)
       {
           if(bodies.get(i) instanceof Addable)
            ((Addable)bodies.get(i)).mousePressed(button,x,y);
       }
    }

    public void keyPressed(int key, char c)
    {
       if(key == Input.KEY_Q)
           System.exit(0);
       else if(key == Input.KEY_R)
           restart();
       else if(key == Input.KEY_P)
           game.getContainer().pause();
       else if(key == Input.KEY_U)
           game.getContainer().resume();
       else if(key == Input.KEY_M)
           game.enterState(MainMenu.ID);
       else
       {
           BodyList bodies = world.getBodies();
           for(int i = 0; i<bodies.size(); i++)
           {
               if(bodies.get(i) instanceof Addable)
                ((Addable)bodies.get(i)).keyPressed(key,c);
           }
       }
    }
    public void keyReleased(int key, char c)
    {
       BodyList bodies = world.getBodies();
       for(int i = 0; i<bodies.size(); i++)
       {
           if(bodies.get(i) instanceof Addable)
            ((Addable)bodies.get(i)).keyReleased(key,c);
       }
    }

    private void preWorld(GameContainer gameContainer, Graphics g) {
        
    }

    private void postWorld(GameContainer gameContainer, Graphics g) {
        info.render(g);
        g.setColor(Color.black);
        if(!hasBegun())
            if(mouseEnabled)
                drawCenteredString("Click anywhere to fire the ball", centerFont, g, 1);
            else
                drawCenteredString("Press space to fire the ball", centerFont, g, 1);
        g.setColor(Color.red);
        drawBottomRight("Q - Quit", menuFont, g, 1);
        drawBottomRight("R - Restart", menuFont, g, 2);
        drawBottomRight("P - Pause", menuFont, g, 3);
        drawBottomRight("U - Unpause (resume)", menuFont, g, 4);
        drawBottomRight("M - Main Menu", menuFont, g, 5);
    }


    public int getID() {
        return ID;
    }
}
