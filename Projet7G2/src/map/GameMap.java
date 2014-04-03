package map;

import javax.swing.*;
import objects.Hero;

/**
 * Created by Marc KARASSEV on 24/03/14.
 * @author Marc KARASSEV
 */
public class GameMap {
    private MapDivision[][] map;
    private MapDivision division;
    private Hero hero;

    public GameMap() {
        map = null;
        division = new MapDivision();
        hero = null;
    }

    public JPanel moveHero() {
        //TODO
        return null;
    }

    public MapDivision getDivision() {
        return division;
    }
}
