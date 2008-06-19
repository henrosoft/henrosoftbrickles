package slickbrickles.ui;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import slickbrickles.Button;
import slickbrickles.util.LevelMaker;
import static slickbrickles.util.Utils.*;
/*
 * MainMenu.java
 *
 * Created on May 28, 2008, 9:47 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author henry
 */
public class MainMenu extends BasicGameState{
    
    /** Creates a new instance of MainMenu */
    private Font menuFont;
    private ArrayList<Button> myButtons;
    public static int ID = 11111;
    public MainMenu() {
    }

    public int getID() {
        return ID;
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        menuFont = new TrueTypeFont(new java.awt.Font(null, java.awt.Font.BOLD, 50), true);
    }
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        game.getContainer().getInput().addListener(this);
    }
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        game.getContainer().getInput().removeListener(this);
    }
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setBackground(new Color(0,0,0));
        graphics.setFont(menuFont);
        graphics.setColor(new Color(255,0,0));
        drawCenteredString("Press space to start the game in arrow key mode", menuFont, graphics, 0);
        drawCenteredString("Click anywhere to start the game in mouse mode", menuFont, graphics, 1);
        drawCenteredString("Press 'M' to create a level", menuFont, graphics, 2);
        drawCenteredString("Press 'Q' to quit the game", menuFont, graphics, 3);
    }
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
    }
    public void mousePressed(int button, int x, int y)
    {
        Main.theGame.setMouseEnabled(true);
        Main.state.enterState(MainGame.ID);
    }
    public void keyPressed(int key, char c)
    {
       if(key == Input.KEY_M)
           Main.state.enterState(LevelMaker.ID);
       else if(key == Input.KEY_SPACE)
       {
           Main.theGame.setMouseEnabled(false);
           Main.state.enterState(MainGame.ID);
       }
       else if(key == Input.KEY_Q)
           System.exit(0);
    }
    
}
