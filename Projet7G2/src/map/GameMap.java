package map;

import javax.swing.*;

import main.Game;
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
        hero = new Hero((Game.FRAMEWIDTH - 50) / 2, (Game.FRAMEHEIGHT - 50) / 2, 50, 50);
    }

    public JPanel moveHero() {
        //TODO
        return null;
    }

    public MapDivision getDivision() {
        return division;
    }

    public Hero getHero() {
        return hero;
    }
}
