package map;

import level.Direction;
import level.ObjectiveGestion;
import level.Way;
import main.Game;
import objects.Hero;
import objects.ObjectType;
import objects.Rock;
import objects.Sprite;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import states.MainGameState;
import util.Preferences;
import util.SoundPlayer;

import java.util.ArrayList;

/**
 * Created by Marc KARASSEV on 24/03/14.
 *
 * @author Marc KARASSEV, Tom VENIAT
 */
public class GameMap {

    private int mapWidth;
    private int mapHeight;
    private static final int DEFAULTSPEED = 5;

    private MainGameState game;
    private MapCreator mapCreator;
    private MapDivision[][] map;
    private MapDivision division;
    private int mapi;
    private int mapj;
    private Hero hero;
    private String instruction;

    private ObjectiveGestion objective;

    public GameMap(MainGameState game) {

        this.game = game;
        hero = new Hero(Sprite.DARKSLIME);
        mapCreator=new MapCreator();
        mapCreator.initialize(this);

        division = map[mapi][mapj];
        Preferences.start=map[mapi][mapj];
        instruction = division.getInstruction();

        Preferences.stockedInstruction=objective.getFirstInstruction();
    }

    public void renderDivision(Graphics g) {
        division.render(g);
        hero.draw(g);
    }

    public void moveHeroUp() {
        int speed = DEFAULTSPEED;

        if ((Game.toTile(hero.getUpLeftCornerY() - speed) == -1)||(Preferences.getHandicap()==1)) {
            switchDivisionUp();
        } else {
            while (isUpTileAccessible(speed) && speed != 0) {
                speed--;
            }
            if (speed != 0) {
                hero.moveUp(speed);
            } else {
                SoundPlayer.getInstance().playBump();
            }
        }
    }

    public void moveHeroLeft() {
        int speed = DEFAULTSPEED;

        if ((Game.toTile(hero.getUpLeftCornerX() - speed) == -1)||(Preferences.getHandicap()==1)) {
            switchDivisionLeft();
        } else {
            while (isLeftTileAccessible(speed) && speed != 0) {
                speed--;
            }
            if (speed != 0) {
                hero.moveLeft(speed);
            } else {
                SoundPlayer.getInstance().playBump();
            }
        }
    }

    public void moveHeroRight() {
        int speed = DEFAULTSPEED;

        if ((Game.toTile(hero.getUpRightCornerX() + speed) == Game.XTILEMAX)||(Preferences.getHandicap()==1)) {
            switchDivisionRight();
        } else {
            while (isRightTileAccessible(speed) && speed != 0) {
                speed--;
            }
            if (speed != 0) {
                hero.moveRight(speed);
            } else {
                SoundPlayer.getInstance().playBump();
            }
        }
    }

    public void moveHeroDown() {
        int speed = DEFAULTSPEED;

        if ((Game.toTile(hero.getDownLeftCornerY() + speed) == Game.YTILEMAX)||(Preferences.getHandicap()==1)) {
            switchDivisionDown();
        } else {
            while (isDownTileAccessible(speed) && speed != 0) {
                speed--;
            }
            if (speed != 0) {
                hero.moveDown(speed);
            } else {
                SoundPlayer.getInstance().playBump();
            }
        }
    }

    private boolean isUpTileAccessible(int offset) {
        return !division.isTileAccessible(Game.toTile(hero.getUpLeftCornerX()), Game.toTile(hero.getUpLeftCornerY() - offset))
                || !division.isTileAccessible(Game.toTile(hero.getUpRightCornerX()), Game.toTile(hero.getUpRightCornerY() - offset));
    }

    private boolean isLeftTileAccessible(int offset) {
        return !division.isTileAccessible(Game.toTile(hero.getUpLeftCornerX() - offset), Game.toTile(hero.getUpLeftCornerY()))
                || !division.isTileAccessible(Game.toTile(hero.getDownLeftCornerX() - offset), Game.toTile(hero.getDownLeftCornerY()));
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
        SoundPlayer.getInstance().playSwitch();
        if (mapj>0){
            mapj--;
            String instru = objective.getNextInstruction(Direction.UP);
            if (!Preferences.retour)
                SoundPlayer.getInstance().say("Sortir" + instru + ".wav");
        }
        division = map[mapi][mapj];
        if (division.equals(Preferences.start)&&Preferences.retour)
            Preferences.game.win();
        if(Preferences.getHandicap()==0)
            hero.setY(Game.FRAMEHEIGHT - hero.getHeight());
    }

    private void switchDivisionLeft() {
        SoundPlayer.getInstance().playSwitch();
        if (mapi>0){
            mapi--;
            String instru=objective.getNextInstruction(Direction.LEFT);
            if (!Preferences.retour)
                SoundPlayer.getInstance().say("Sortir" + instru + ".wav");
        }
        division = map[mapi][mapj];
        if (division.equals(Preferences.start)&&Preferences.retour)
            Preferences.game.win();
        if(Preferences.getHandicap()==0)
            hero.setX(Game.FRAMEWIDTH - hero.getWidth());

    }

    private void switchDivisionRight() {
        SoundPlayer.getInstance().playSwitch();
        if (mapi<mapWidth-1){
            mapi++;
            String instru =objective.getNextInstruction(Direction.RIGHT);
            if (!Preferences.retour)
                SoundPlayer.getInstance().say("Sortir" + instru + ".wav");
        }
        division = map[mapi][mapj];
        if (division.equals(Preferences.start)&&Preferences.retour)
            Preferences.game.win();
        if(Preferences.getHandicap()==0)
            hero.setX(0);

    }

    private void switchDivisionDown() {
        SoundPlayer.getInstance().playSwitch();
        if (mapj<mapHeight-1){
            mapj++;
            String instru=objective.getNextInstruction(Direction.DOWN);
            if (!Preferences.retour)
                SoundPlayer.getInstance().say("Sortir" + instru + ".wav");
        }
        division = map[mapi][mapj];
        if (division.equals(Preferences.start)&&Preferences.retour)
            Preferences.game.win();
        if(Preferences.getHandicap()==0)
            hero.setY(0);
    }

    // Accesseurs

    public String getInstruction() { return instruction; }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public int getMapi() { return mapi; }

    public int getMapj() {
        return mapj;
    }

    public ObjectiveGestion getObjective() {
        return objective;
    }

    public Hero getHero() { return hero; }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public void setMap(MapDivision[][] map) {
        this.map = map;
    }

    public void setMapi(int mapi) {
        this.mapi = mapi;
    }

    public void setMapj(int mapj) {
        this.mapj = mapj;
    }

    public void setObjective(ObjectiveGestion objective) {
        this.objective = objective;
    }
}
