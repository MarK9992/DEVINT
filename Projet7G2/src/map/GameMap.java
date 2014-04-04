package map;

import main.Game;
import objects.Hero;
import org.newdawn.slick.Graphics;

/**
 * Created by Marc KARASSEV on 24/03/14.
 * @author Marc KARASSEV
 */
public class GameMap {

    private static final int DEFAULTSPEED = 5;

    private MapDivision[][] map;
    private MapDivision division;
    private Hero hero;

    public GameMap() {
        map = null;
        division = new MapDivision();
        hero = new Hero((Game.FRAMEWIDTH - 50) / 2, (Game.FRAMEHEIGHT - 50) / 2, 50, 50);
    }

    public void renderDivision(Graphics g) {
        division.render(g);
        hero.draw(g);
    }

    public void moveHeroUp() {
        int[] t = positionToTile(hero.getX(), hero.getY() - 1);
        int speed= -DEFAULTSPEED;

        while(!division.isTileAccessible(t[0], t[1]) && speed != 0) {
            speed++;
        }
        if(speed != 0) {
            hero.moveV(speed);
        }
        else {
            System.err.println("tuile non accessible");
        }
    }

    public void moveHeroLeft() {
        int[] t = positionToTile(hero.getX() - 5, hero.getY());
        int speed = -DEFAULTSPEED;

        if(division.isTileAccessible(t[0], t[1])) {
            hero.moveH(speed);
        }
        else {
            System.err.println("tuile non accessible");
        }
    }

    public void moveHeroRight() {
        int[] t = positionToTile(hero.getX() + 5, hero.getY());
        int speed = DEFAULTSPEED;

        if(division.isTileAccessible(t[0], t[1])) {
            hero.moveH(speed);
        }
        else {
            System.err.println("tuile non accessible");
        }
    }

    public void moveHeroDown() {
        int[] t = positionToTile(hero.getX(), hero.getY() + 5);
        int speed = DEFAULTSPEED;

        if(division.isTileAccessible(t[0], t[1])) {
            hero.moveV(speed);
        }
        else {
            System.err.println("tuile non accessible");
        }
    }

    private int[] positionToTile(int x, int y) {
        int[] t = {x/50, y/50};
        return t;
    }
}
