package objects;

import org.newdawn.slick.*;
import main.Game;
import util.Preferences;

/**
 * Created by Marc KARASSEV on 24/03/14.
 *
 * @author Marc KARASSEV
 */
public class Hero {

    // Fields

    private int x;
    private int y;
    private int width;
    private int height;
    private Sound moveSound;
    private Sprite sprite;
    private Image sheet;
    private short xSheet;
    private short ySheet;
    private byte direction;

    private static final byte DOWN = 0, LEFT = 1, RIGHT = 2, UP = 3;

    // Constructors

    public Hero() {
        this(1, 1);
    }

    public Hero(int width, int height) {
        this.width = width;
        this.height = height;
        x = (Game.FRAMEWIDTH - width) / 2;
        y = (Game.FRAMEHEIGHT - height) / 2;
        try {
            moveSound = new Sound("../ressources/sound/smb_stomp.wav");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public Hero(Sprite sprite) {
        this.sprite = sprite;
        try {
            sheet = new Image(sprite.getSheet(), Sprite.TRANSP);
            moveSound = new Sound("../ressources/sound/smb_stomp.wav");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        direction = 0;
        xSheet = sprite.getSouthCoords()[0];
        ySheet = sprite.getSouthCoords()[1];
        width = 2 * sprite.getSouthDims()[0];
        height = 2 * sprite.getSouthDims()[1];
        x = (Game.FRAMEWIDTH - width) / 2;
        y = (Game.FRAMEHEIGHT - height) / 2;
    }

    // Methods

    public void draw(Graphics g) {
        if(sprite == null) {
            g.setColor(Preferences.getHighlightColor());
            g.fillRect(x, y, width, height);
        }
        else {
            g.drawImage(sheet, x, y, x + width, y + height, xSheet, ySheet, xSheet + width / 2, ySheet + height / 2);
        }
    }

    public void moveUp(int y) {
        if(direction != UP) {
            setDirectionUP();
        }
        if(y > 0) {
            this.y -= y;
            playMove();
        }
    }

    public void moveDown(int y) {
        if(direction != DOWN) {
            setDirectionDOWN();
        }
        if(y > 0) {
            this.y += y;
            playMove();
        }
    }

    public void moveLeft(int x) {
        if(direction != LEFT) {
            setDirectionLEFT();
        }
        if(x > 0) {
            this.x -= x;
            playMove();
        }
    }

    public void moveRight(int x) {
        if(direction != RIGHT) {
            setDirectionRIGHT();
        }
        if(x > 0) {
            this.x += x;
            playMove();
        }
    }

    private void setDirectionUP() {
        direction = UP;
        xSheet = sprite.getNorthCoords()[0];
        ySheet = sprite.getNorthCoords()[1];
        width = 2 * sprite.getNorthDims()[0];
        height = 2 * sprite.getNorthDims()[1];
    }

    private void setDirectionLEFT() {
        direction = LEFT;
        xSheet = sprite.getWestCoords()[0];
        ySheet = sprite.getWestCoords()[1];
        width = 2 * sprite.getWestDims()[0];
        height = 2 * sprite.getWestDims()[1];
    }

    private void setDirectionRIGHT() {
        direction = RIGHT;
        xSheet = sprite.getWestCoords()[0];
        ySheet = sprite.getWestCoords()[1];
        width = 2 * sprite.getWestDims()[0];
        height = 2 * sprite.getWestDims()[1];
    }

    private void setDirectionDOWN() {
        direction = RIGHT;
        xSheet = sprite.getSouthCoords()[0];
        ySheet = sprite.getSouthCoords()[1];
        width = 2 * sprite.getSouthDims()[0];
        height = 2 * sprite.getSouthDims()[1];
    }

    private void playMove() {
        if(!moveSound.playing()) {
            moveSound.play();
        }
    }

    public int getUpLeftCornerX() { return x; }

    public int getUpRightCornerX() { return x + width; }

    public int getDownLeftCornerX() { return  x; }

    public int getDownRightCornerX() { return x + width; }

    public int getUpLeftCornerY() { return y; }

    public int getUpRightCornerY() { return y; }

    public int getDownLeftCornerY() { return  y + height; }

    public int getDownRightCornerY() { return y + height; }

    // Getters and setters

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() { return height; }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
        xSheet = sprite.getSouthCoords()[0];
        ySheet = sprite.getSouthCoords()[1];
        width = 2 * sprite.getSouthDims()[0];
        height = 2 * sprite.getSouthDims()[1];
    }
}
