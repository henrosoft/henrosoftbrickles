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
    private int ID = 11111;
    public MainMenu() {
    }

    public int getID() {
        return ID;
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        stateBasedGame.getContainer().getInput().addListener(this);
        menuFont = new TrueTypeFont(new java.awt.Font(null, java.awt.Font.BOLD, 50), true);
    }

    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setBackground(new Color(0,0,0));
        graphics.setFont(menuFont);
        graphics.setColor(new Color(255,0,0));
        drawCenteredString("Press Space To Start The Game", graphics, 0);
    }
    public void drawCenteredString(String s, Graphics g, int level)
    {
        int x = (int)((Main.state.SIZE_X-menuFont.getWidth(s))/2.0);
        g.drawString(s,x,200+menuFont.getHeight(s)*level);
    }
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
    }
    public void keyPressed(int key, char c)
    {
       if(key == Input.KEY_SPACE)
           Main.state.enterState(22222);
    }
    
}
