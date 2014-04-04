package map;

import main.Game;
import org.newdawn.slick.Graphics;
import util.Preferences;

import java.util.ArrayList;

/**
 * Created by Marc KARASSEV on 24/03/14.
 *
 * @author Marc KARASSEV
 */
public class MapDivision {
    private static final int XTILEMAX = 16;
    private static final int YTILEMAX = 12;

    private ArrayList<MapObject> objects;
    private boolean[][] accessMap;

    public MapDivision() {
        accessMap = new boolean[XTILEMAX][YTILEMAX];
        for(int i = 0; i < XTILEMAX; i++) {
            for(int j = 0; j < YTILEMAX; j++) {
                accessMap[i][j] = true;
            }
        }
        for (int i = 0; i < 8; i++) {
            for(int j = 0; j < 2; j++) {
                accessMap[i][j] = false;
            }
        }
        for (int i = 11; i < XTILEMAX; i++) {
            for(int j = 0; j < 2; j++) {
                accessMap[i][j] = false;
            }
        }
        for (int i = 0; i < 4; i++) {
            for(int j = 5; j < YTILEMAX; j++) {
                accessMap[i][j] = false;
            }
        }
        for (int i = 11; i < XTILEMAX; i++) {
            for(int j = 9; j < YTILEMAX; j++) {
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

    public boolean isTileAccessible(int x, int y) {
        if (x < 0 || y < 0 || x > XTILEMAX-1 || y > YTILEMAX-1) {
            return false;
        }
        return accessMap[x][y];
    }
}
