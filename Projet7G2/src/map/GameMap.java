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

import java.util.ArrayList;

/**
 * Created by Marc KARASSEV on 24/03/14.
 *
 * @author Marc KARASSEV, Tom VENIAT
 */
public class GameMap {

    private static final int MAPWIDTH = 4;
    private static final int MAPHEIGHT = 4;
    private static final int DEFAULTSPEED = 5;

    private MainGameState game;
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
        MapDivision div;

        this.game = game;
        map = new MapDivision[MAPWIDTH][MAPHEIGHT];
        //hero = new Hero(50, 50);
        hero = new Hero(Sprite.DARKSLIME);

        ArrayList<Direction> list=new ArrayList<Direction>();
        list.add(Direction.UP);
        list.add(Direction.LEFT);
        list.add(Direction.UP);
        list.add(Direction.RIGHT);
        list.add(Direction.DOWN);
        this.objective=new ObjectiveGestion(list);


        // Création dégueulasse des divisions

        div = new MapDivision("Allez à droite.");
        div.addObject(new Rock(0, 0, Game.FRAMEWIDTH, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 2 * Game.TILE, 4 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 8 * Game.TILE, 4 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 2 * Game.TILE, 2 * Game.TILE, 10 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(2 * Game.TILE, 10 * Game.TILE, 6 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        map[0][0] = div;


        div = new MapDivision("Allez en bas.");
        div.addObject(new Rock(0, 0, Game.FRAMEWIDTH, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 2 * Game.TILE, 4 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 8 * Game.TILE, 8 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 6 * Game.TILE, 4 * Game.TILE, 6 * Game.TILE, ObjectType.ROCK));
        map[0][1] = div;


        div = new MapDivision("Allez en bas.");
        div.addObject(new Rock(0, 0, Game.FRAMEWIDTH, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 6 * Game.TILE, 6 * Game.TILE, 6 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(10 * Game.TILE, 8 * Game.TILE, 6 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        map[0][2] = div;


        div = new MapDivision("Sortir à gauche.");
        div.addObject(new Rock(0, 0, Game.FRAMEWIDTH, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 8 * Game.TILE, 6 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(14 * Game.TILE, 0, 2 * Game.TILE, 12 * Game.TILE, ObjectType.ROCK));
        map[0][3] = div;

        div = new MapDivision("Sortir en bas.");
        div.addObject(new Rock(0, 0, 8 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 4 * Game.TILE, 4 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 8 * Game.TILE, 2 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 0, 4 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(14 * Game.TILE, 2 * Game.TILE, 2 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(6 * Game.TILE, 10 * Game.TILE, 10 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(8 * Game.TILE, 8 * Game.TILE, 8 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        map[1][0] = div;

        div = new MapDivision("Sortir à gauche.");
        div.addObject(new Rock(0, 0, 8 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 8 * Game.TILE, 8 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 0, 4 * Game.TILE, Game.FRAMEHEIGHT, ObjectType.ROCK));
        map[1][1] = div;

        div = new MapDivision("Sortir en bas.");
        div.addObject(new Rock(0, 0, 6 * Game.TILE, Game.FRAMEHEIGHT, ObjectType.ROCK));
        div.addObject(new Rock(10 * Game.TILE, 0, 6 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(10 * Game.TILE, 6 * Game.TILE, 6 * Game.TILE, 6 * Game.TILE, ObjectType.ROCK));
        map[1][2] = div;

        div = new MapDivision("Sortir à gauche.");
        div.addObject(new Rock(0, 0, 6 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 6 * Game.TILE, 8 * Game.TILE, 6 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(14 * Game.TILE, 0, 2 * Game.TILE, Game.FRAMEHEIGHT, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 8 * Game.TILE, 2 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        map[1][3] = div;

        div = new MapDivision("Sortir en bas.");
        div.addObject(new Rock(0, 0, 2 * Game.TILE, Game.FRAMEHEIGHT, ObjectType.ROCK));
        div.addObject(new Rock(6 * Game.TILE, 0, 10 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(2 * Game.TILE, 10 * Game.TILE, 6 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(14 * Game.TILE, 6 * Game.TILE, 2 * Game.TILE, 6 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 10 * Game.TILE, 4 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        map[2][0] = div;

        div = new MapDivision("Sortir en haut.");
        div.addObject(new Rock(0, 0, 8 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 0, 4 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 6 * Game.TILE, 4 * Game.TILE, 6 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 10 * Game.TILE, 4 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        map[2][1] = div;

        div = new MapDivision("Sortir à gauche.");
        div.addObject(new Rock(0, 0, 6 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(10 * Game.TILE, 0, 6 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 10 * Game.TILE, Game.FRAMEWIDTH, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(10 * Game.TILE, 8 * Game.TILE, 6 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        map[2][2] = div;

        div = new MapDivision("Sortir à gauche.");
        div.addObject(new Rock(0, 0, 8 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 2 * Game.TILE, 4 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(14 * Game.TILE, 0, 2 * Game.TILE, Game.FRAMEHEIGHT, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 0, 2 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 8 * Game.TILE, 4 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(8 * Game.TILE, 10 * Game.TILE, 6 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        map[2][3] = div;

        div = new MapDivision("Bien. Retournez au point de départ maintenant.");
        div.addObject(new Rock(0, 0, 2 * Game.TILE, 10 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(2 * Game.TILE, 0, 6 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 10 * Game.TILE, Game.FRAMEWIDTH, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 0, 4 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(14 * Game.TILE, 6 * Game.TILE, 2 * Game.TILE, 6 * Game.TILE, ObjectType.ROCK));
        map[3][0] = div;

        div = new MapDivision("Sortir en haut.");
        div.addObject(new Rock(0, 0, 4 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 0, 4 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 6 * Game.TILE, Game.FRAMEWIDTH, 6 * Game.TILE, ObjectType.ROCK));
        map[3][1] = div;

        div = new MapDivision("Sortir à gauche.");
        div.addObject(new Rock(0, 0, Game.FRAMEWIDTH, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 6 * Game.TILE, Game.FRAMEWIDTH, 6 * Game.TILE, ObjectType.ROCK));
        map[3][2] = div;

        div = new MapDivision("Sortir à gauche.");
        div.addObject(new Rock(0, 0, 4 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 6 * Game.TILE, 4 * Game.TILE, 6 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 10 * Game.TILE, Game.FRAMEWIDTH, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(8 * Game.TILE, 0, 8 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(10 * Game.TILE, 2 * Game.TILE, 6 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(14 * Game.TILE, 6 * Game.TILE, 2 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        map[3][3] = div;

        mapi = 2;
        mapj = 1;
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
        if (mapi>0){
            mapi--;
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
        if (mapj>0){
            mapj--;
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
        if (mapj<3){
            mapj++;
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
        if (mapi<3){
            mapi++;
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
}
