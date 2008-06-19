/*
 * StateHandeler.java
 *
 * Created on June 18, 2008, 6:51 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package slickbrickles.ui;

import java.awt.Toolkit;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import slickbrickles.util.LevelMaker;

/**
 *
 * @author henry
 */
public class StateHandler extends StateBasedGame{
    public static final int SIZE_X = Toolkit.getDefaultToolkit().getScreenSize().width; 
    public static final int SIZE_Y = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static final int SLEEP_TIME = 10;
    private AppGameContainer myContainer;
    /** Creates a new instance of StateHandeler */
    public StateHandler() {
        super("Brickles");
        try {
            myContainer = new AppGameContainer(this,SIZE_X,SIZE_Y,true);
            myContainer.setTargetFrameRate((int)(1000/SLEEP_TIME));
            myContainer.setShowFPS(true);
        } catch (SlickException ex) {
            ex.printStackTrace();
        }
    }
    public AppGameContainer getContainer()
    {
        return myContainer;
    }
    public void start()
    {
        try {
            myContainer.start();
        } catch (SlickException ex) {
            ex.printStackTrace();
        }
    }
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        addState(new MainMenu());   
        addState(Main.theGame = new MainGame(this));
        addState(new LevelMaker());
        
    }
    
}
