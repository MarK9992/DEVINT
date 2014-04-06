package map;

import main.Game;
import objects.Hero;
import objects.ObjectType;
import objects.Rock;
import org.newdawn.slick.Graphics;

/**
 * Created by Marc KARASSEV on 24/03/14.
 *
 * @author Marc KARASSEV
 */
public class GameMap {

    private static final int MAPWIDTH = 4;
    private static final int MAPHEIGHT = 4;
    private static final int DEFAULTSPEED = 5;

    private MapDivision[][] map;
    private MapDivision division;
    private int mapi;
    private int mapj;
    private Hero hero;

    public GameMap() {
        MapDivision div;

        map = new MapDivision[MAPWIDTH][MAPHEIGHT];
        hero = new Hero((Game.FRAMEWIDTH - 50) / 2, (Game.FRAMEHEIGHT - 50) / 2, 50, 50);

        // Création dégueulasse des divisions

        div = new MapDivision();
        div.addObject(new Rock(0, 0, 8 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 0, Game.FRAMEWIDTH, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 6 * Game.TILE, 4 * Game.TILE, Game.FRAMEHEIGHT, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 10 * Game.TILE, Game.FRAMEWIDTH, Game.FRAMEHEIGHT, ObjectType.ROCK));
        map[2][1] = div;

        div = new MapDivision();
        div.addObject(new Rock(0, 0, 8 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 6 * Game.TILE, 8 * Game.TILE, Game.FRAMEHEIGHT, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 0, Game.FRAMEWIDTH, Game.FRAMEHEIGHT, ObjectType.ROCK));
        map[1][1] = div;

        div = new MapDivision();
        div.addObject(new Rock(0, 0, Game.FRAMEWIDTH, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 2 * Game.TILE, 4 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 8 * Game.TILE, 8 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 6 * Game.TILE, 4 * Game.TILE, 6 * Game.TILE, ObjectType.ROCK));
        map[0][1] = div;

        mapi = 2;
        mapj = 1;
        division = map[mapi][mapj];
    }

    public void renderDivision(Graphics g) {
        division.render(g);
        hero.draw(g);
    }

    public void moveHeroUp() {
        int speed = -DEFAULTSPEED;

        if (Game.toTile(hero.getY() + speed) == -1) {
            switchDivisionUp();
        } else {
            while (isUpTileAccessible(speed) && speed != 0) {
                speed++;
            }
            if (speed != 0) {
                hero.moveV(speed);
            } else {
                System.err.println("tuile non accessible");
            }
        }
    }

    public void moveHeroLeft() {
        int speed = -DEFAULTSPEED;

        if (Game.toTile(hero.getX() + speed) == -1) {
            switchDivisionLeft();
        } else {
            while (isLeftTileAccessible(speed) && speed != 0) {
                speed++;
            }
            if (speed != 0) {
                hero.moveH(speed);
            } else {
                System.err.println("tuile non accessible");
            }
        }
    }

    public void moveHeroRight() {
        int speed = DEFAULTSPEED;

        if (Game.toTile(hero.getX() + hero.getWidth() + speed) == -1) {
            switchDivisionRight();
        } else {
            while (isRightTileAccessible(speed) && speed != 0) {
                speed--;
            }
            if (speed != 0) {
                hero.moveH(speed);
            } else {
                System.err.println("tuile non accessible");
            }
        }
    }

    public void moveHeroDown() {
        int speed = DEFAULTSPEED;

        if (Game.toTile(hero.getY() + hero.getHeight() + speed) == Game.YTILEMAX) {
            switchDivisionDown();
        } else {
            while (isDownTileAccessible(speed) && speed != 0) {
                speed--;
            }
            if (speed != 0) {
                hero.moveV(speed);
            } else {
                System.err.println("tuile non accessible");
            }
        }
    }

    private boolean isUpTileAccessible(int offset) {
        return !division.isTileAccessible(Game.toTile(hero.getUpLeftCornerX()), Game.toTile(hero.getUpLeftCornerY() + offset))
                || !division.isTileAccessible(Game.toTile(hero.getUpRightCornerX()), Game.toTile(hero.getUpRightCornerY() + offset));
    }

    private boolean isLeftTileAccessible(int offset) {
        return !division.isTileAccessible(Game.toTile(hero.getUpLeftCornerX() + offset), Game.toTile(hero.getUpLeftCornerY()))
                || !division.isTileAccessible(Game.toTile(hero.getDownLeftCornerX() + offset), Game.toTile(hero.getDownLeftCornerY()));
    }

    private boolean isRightTileAccessible(int offset) {
        return !division.isTileAccessible(Game.toTile((hero.getUpRightCornerX() + offset)), Game.toTile(hero.getUpRightCornerY()))
                || !division.isTileAccessible(Game.toTile(hero.getDownRightCornerX() + offset), Game.toTile(hero.getDownRightCornerY()));
    }

    private boolean isDownTileAccessible(int offset) {
        return !division.isTileAccessible(Game.toTile((hero.getDownLeftCornerX())), Game.toTile(hero.getDownLeftCornerY() + offset))
                || !division.isTileAccessible(Game.toTile(hero.getDownRightCornerX()), Game.toTile(hero.getDownRightCornerY() + offset));
    }

    private void switchDivisionUp() {
        mapi--;
        division = map[mapi][mapj];
        hero.setY(Game.FRAMEHEIGHT - hero.getHeight());
    }

    private void switchDivisionLeft() {
        mapj--;
        division = map[mapi][mapj];
        hero.setX(Game.FRAMEWIDTH - hero.getWidth());
    }

    private void switchDivisionRight() {
        mapj++;
        division = map[mapi][mapj];
        hero.setX(0);
    }

    private void switchDivisionDown() {
        mapi++;
        division = map[mapi][mapj];
        hero.setY(0);
    }
}
