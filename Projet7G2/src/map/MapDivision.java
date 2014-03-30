package map;

import util.Position;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Marc KARASSEV on 24/03/14.
 * @author Marc KARASSEV
 */
public class MapDivision {
    private ArrayList<MapObject> objects;
    private boolean[][] accessMap;

    public MapDivision() {
        //TODO
    }

    public JPanel render() {
        //TODO
        return null;
    }

    public boolean isTileAccessible(Position p) {
        return accessMap[p.getX()][p.getY()];
    }
}
