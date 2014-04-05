package map;

import main.Game;
import objects.GameObject;
import objects.Rock;
import org.newdawn.slick.Graphics;
import util.Preferences;

import java.util.ArrayList;

/**
 * Created by Marc KARASSEV on 24/03/14.
 *
 * @author Marc KARASSEV
 */
public class MapDivision {

    private ArrayList<GameObject> objects;
    private boolean[][] accessMap;

    public MapDivision() {
        objects = new ArrayList<GameObject>();
        accessMap = new boolean[Game.XTILEMAX][Game.YTILEMAX];
        for(int i = 0; i < Game.XTILEMAX; i++) {
            for(int j = 0; j < Game.YTILEMAX; j++) {
                accessMap[i][j] = true;
            }
        }
        objects.add(new Rock(0, 0, 8 * Game.TILE, 2 * Game.TILE));
        for (int i = 0; i < 8; i++) {
            for(int j = 0; j < 2; j++) {
                accessMap[i][j] = false;
            }
        }
        objects.add(new Rock(12 * Game.TILE, 0, Game.FRAMEWIDTH, 2 * Game.TILE));
        for (int i = 11; i < Game.XTILEMAX; i++) {
            for(int j = 0; j < 2; j++) {
                accessMap[i][j] = false;
            }
        }
        objects.add(new Rock(0, 6 * Game.TILE, 4 * Game.TILE, Game.FRAMEHEIGHT));
        for (int i = 0; i < 4; i++) {
            for(int j = 5; j < Game.YTILEMAX; j++) {
                accessMap[i][j] = false;
            }
        }
        objects.add(new Rock(12 * Game.TILE, 10 * Game.TILE, Game.FRAMEWIDTH, Game.FRAMEHEIGHT));
        for (int i = 11; i < Game.XTILEMAX; i++) {
            for(int j = 9; j < Game.YTILEMAX; j++) {
                accessMap[i][j] = false;
            }
        }
    }

    public void render(Graphics g) {
        g.setBackground(Preferences.getBackgroundColor());
        for(GameObject obj: objects) {
            obj.draw(g);
        }
    }

    public boolean isTileAccessible(int x, int y) {
        if (x < 0 || y < 0 || x > Game.XTILEMAX-1 || y > Game.YTILEMAX-1) {
            return false;
        }
        return accessMap[x][y];
    }
}