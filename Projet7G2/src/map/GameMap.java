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
        int speed= -DEFAULTSPEED;

        while((!division.isTileAccessible(Game.toTile(hero.getX()), Game.toTile(hero.getY() + speed))
                || !division.isTileAccessible(Game.toTile(hero.getX() + hero.getWidth()), Game.toTile(hero.getY() + speed)))
                && speed != 0) {
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
        int speed = -DEFAULTSPEED;

        while((!division.isTileAccessible(Game.toTile((hero.getX() + speed)), Game.toTile(hero.getY()))
                || !division.isTileAccessible(Game.toTile(hero.getX() + speed), Game.toTile(hero.getY() + hero.getHeight())))
                && speed != 0) {
            speed++;
        }
        if(speed != 0) {
            hero.moveH(speed);
        }
        else {
            System.err.println("tuile non accessible");
        }
    }

    public void moveHeroRight() {
        int speed = DEFAULTSPEED;

        while((!division.isTileAccessible(Game.toTile((hero.getX() + hero.getWidth() + speed)), Game.toTile(hero.getY()))
                || !division.isTileAccessible(Game.toTile(hero.getX() + hero.getWidth() + speed), Game.toTile(hero.getY() + hero.getHeight())))
                && speed != 0) {
            speed--;
        }
        if(speed != 0) {
            hero.moveH(speed);
        }
        else {
            System.err.println("tuile non accessible");
        }
    }

    public void moveHeroDown() {
        int speed = DEFAULTSPEED;

        while((!division.isTileAccessible(Game.toTile(hero.getX()), Game.toTile(hero.getY() + hero.getHeight() + speed))
                || !division.isTileAccessible(Game.toTile(hero.getX() + hero.getWidth()), Game.toTile(hero.getY() + hero.getHeight() + speed)))
                && speed != 0) {
            speed--;
        }
        if(speed != 0) {
            hero.moveV(speed);
        }
        else {
            System.err.println("tuile non accessible");
        }
    }
}
