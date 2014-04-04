package map;

import main.Game;
import org.newdawn.slick.Graphics;
import util.Position;
import util.Preferences;

import java.util.ArrayList;

/**
 * Created by Marc KARASSEV on 24/03/14.
 *
 * @author Marc KARASSEV
 */
public class MapDivision {
    private ArrayList<MapObject> objects;
    private boolean[][] accessMap;

    public MapDivision() {
        accessMap = new boolean[16][12];
        for(int i = 0; i < 16; i++) {
            for(int j = 0; j < 12; j++) {
                accessMap[i][j] = true;
            }
        }
        for (int i = 0; i < 8; i++) {
            for(int j = 0; j < 2; j++) {
                accessMap[i][j] = false;
            }
        }
        for (int i = 11; i < 16; i++) {
            for(int j = 0; j < 2; j++) {
                accessMap[i][j] = false;
            }
        }
        for (int i = 0; i < 4; i++) {
            for(int j = 5; j < 12; j++) {
                accessMap[i][j] = false;
            }
        }
        for (int i = 11; i < 16; i++) {
            for(int j = 9; j < 12; j++) {
                accessMap[i][j] = false;
            }
        }
    }

    public void render(Graphics g) {
        /*for(MapObject obj: objects) {
            obj.draw(g);
        }*/
        g.setBackground(Preferences.getBackgroundColor());
        g.setColor(Preferences.getItemColor());
        g.fillRect(0, 0, Game.FRAMEWIDTH / 2, Game.FRAMEHEIGHT / 6);
        g.fillRect(3 * Game.FRAMEWIDTH / 4, 0, Game.FRAMEWIDTH, Game.FRAMEHEIGHT / 6);
        g.fillRect(0, Game.FRAMEHEIGHT / 2, Game.FRAMEWIDTH / 4, Game.FRAMEHEIGHT);
        g.fillRect(3 * Game.FRAMEWIDTH / 4, 5 * Game.FRAMEHEIGHT / 6, Game.FRAMEWIDTH, Game.FRAMEHEIGHT);
    }

    public boolean isTileAccessible(Position p) {
        int x = p.getX(), y = p.getY();

        if (x < 0 || y < 0 || x > 15 || y > 15) {
            return false;
        }
        return accessMap[x][y];
    }
}
