package map;

import level.Direction;
import level.ObjectiveGestion;
import level.Way;
import main.Game;
import objects.Hero;
import objects.ObjectType;
import objects.Rock;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import states.MainGameState;
import util.Preferences;

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
    private Sound bumpSound;
    private Sound switchSound;
    private String instruction;

    private ObjectiveGestion objective;

    public GameMap(MainGameState game) {

        this.game = game;
       // map = new MapDivision[MAPWIDTH][MAPHEIGHT];
        hero = new Hero((Game.FRAMEWIDTH - 50) / 2, (Game.FRAMEHEIGHT - 50) / 2, 50, 50);
        mapCreator=new MapCreator();

        ArrayList<Direction> list=new ArrayList<Direction>();
        list.add(Direction.UP);
        list.add(Direction.LEFT);
        list.add(Direction.UP);
        list.add(Direction.RIGHT);
        list.add(Direction.DOWN);
        this.objective=new ObjectiveGestion(list);

        mapCreator.initialize(this);

        mapi = 1;
        mapj = 2;
        division = map[mapi][mapj];
        Preferences.start=map[mapi][mapj];
        try {
            bumpSound = new Sound("../ressources/sound/smb_bump.wav");
            switchSound = new Sound("../ressources/sound/smb2_enter_door.wav");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        instruction = division.getInstruction();

        Preferences.stockedInstruction=objective.getFirstInstruction();
    }

    public void renderDivision(Graphics g) {
        division.render(g);
        hero.draw(g);
    }

    public void moveHeroUp() {
        int speed = -DEFAULTSPEED;

        if ((Game.toTile(hero.getUpLeftCornerY() + speed) == -1)||(Preferences.getHandicap()==1)) {
            switchDivisionUp();
        } else {
            while (isUpTileAccessible(speed) && speed != 0) {
                speed++;
            }
            if (speed != 0) {
                hero.moveV(speed);
            } else {
                playBump();
            }
        }
    }

    public void moveHeroLeft() {
        int speed = -DEFAULTSPEED;

        if ((Game.toTile(hero.getUpLeftCornerX() + speed) == -1)||(Preferences.getHandicap()==1)) {
            switchDivisionLeft();
        } else {
            while (isLeftTileAccessible(speed) && speed != 0) {
                speed++;
            }
            if (speed != 0) {
                hero.moveH(speed);
            } else {
                playBump();
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
                hero.moveH(speed);
            } else {
                playBump();
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
                hero.moveV(speed);
            } else {
                playBump();
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
        playSwitch();
        if (mapj>0){
            mapj--;
            String instru = objective.getNextInstruction(Direction.UP);
            if (!Preferences.retour)
                Preferences.makeSivoxSay("Sortir "+instru+".");
        }
        division = map[mapi][mapj];
        if (division.equals(Preferences.start)&&Preferences.retour)
            Preferences.game.win();
        if(Preferences.getHandicap()==0)
        hero.setY(Game.FRAMEHEIGHT - hero.getHeight());

    }

    private void switchDivisionLeft() {
        playSwitch();
        if (mapi>0){
            mapi--;
            String instru=objective.getNextInstruction(Direction.LEFT);
            if (!Preferences.retour)
                Preferences.makeSivoxSay("Sortir "+instru+".");
        }
        division = map[mapi][mapj];
        if (division.equals(Preferences.start)&&Preferences.retour)
            Preferences.game.win();
        if(Preferences.getHandicap()==0)
        hero.setX(Game.FRAMEWIDTH - hero.getWidth());

    }

    private void switchDivisionRight() {
        playSwitch();
        if (mapi<mapWidth-1){
            mapi++;
            String instru =objective.getNextInstruction(Direction.RIGHT);
            if (!Preferences.retour)
                Preferences.makeSivoxSay("Sortir "+instru+".");
        }
        division = map[mapi][mapj];
        if (division.equals(Preferences.start)&&Preferences.retour)
            Preferences.game.win();
        if(Preferences.getHandicap()==0)
        hero.setX(0);

    }

    private void switchDivisionDown() {
        playSwitch();
        if (mapj<mapHeight-1){
            mapj++;
            String instru=objective.getNextInstruction(Direction.DOWN);
            if (!Preferences.retour)
                Preferences.makeSivoxSay("Sortir "+instru+".");
        }
        division = map[mapi][mapj];
        if (division.equals(Preferences.start)&&Preferences.retour)
            Preferences.game.win();
        if(Preferences.getHandicap()==0)
        hero.setY(0);
    }

    private void playBump() {
        if (!bumpSound.playing()) {
            bumpSound.play();
        }
    }

    private void playSwitch() {
        switchSound.play();
    }

 /*   private void playInstruction() {
        if(!game.getGoal1()) {
            instruction = division.getInstruction();
            Preferences.getVoice().playShortText(instruction);
        }
    }*/

    // Accesseurs

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public int getMapi() {
        return mapi;
    }

    public int getMapj() {
        return mapj;
    }

    public ObjectiveGestion getObjective() {
        return objective;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public void setMap(MapDivision[][] map) {
        this.map = map;
    }
}
