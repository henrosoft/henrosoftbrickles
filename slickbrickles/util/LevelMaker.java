/*
 * LevelMaker.java
 *
 * Created on June 18, 2008, 7:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package slickbrickles.util;

import net.phys2d.raw.Body;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Line;
import net.phys2d.raw.strategies.QuadSpaceStrategy;
import slickbrickles.bodies.Ball;
import slickbrickles.bodies.Brick;
import slickbrickles.bodies.Pad;
import slickbrickles.bodies.SplitBrick;
import slickbrickles.bodies.UnbreakableBrick;
import slickbrickles.ui.Main;
import static slickbrickles.util.Utils.*;
/**
 *
 * @author henry
 */
public class LevelMaker {
    private World world;
    /** Creates a new instance of LevelMaker */
    public LevelMaker(World w) {
        world = w;
    }
    public void createLevel1()
    {
        createWalls();
        Body b = new Pad(300,5);
        world.add(b);
        b = new Ball(300,300,30,30,-120*2,-40*2);
        world.add(b);
        b = new Ball(400,400,30,30,50*2,-130*2);
        world.add(b);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if(j==3)
                    b = new UnbreakableBrick(100+i*60, 100 + j*30);
                else if(j==1 && (i!=0 && i!=5))
                    b = new SplitBrick(100+i*60, 100 + j*30);
                else
                    b = new Brick(100+i*60, 100 + j*30);
                world.add(b);
            }
        }
    }
    public void createWalls()
    {
        StaticBody w2 = new StaticBody(new Line(Main.state.SIZE_X,Main.state.SIZE_Y,Main.state.SIZE_X,0));
        StaticBody w3 = new StaticBody(new Line(Main.state.SIZE_X,0,0,0));
        StaticBody w4 = new StaticBody(new Line(0,0,0,Main.state.SIZE_Y));
        w2.setRestitution(1);
        w3.setRestitution(1);
        w4.setRestitution(1);
        world.add(w2);
        world.add(w3);
        world.add(w4);
    }
    
}
