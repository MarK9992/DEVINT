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
        width = sprite.getSouthDims()[0];
        height = sprite.getSouthDims()[1];
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
            short[] coords = sprite.getSouthCoords();
            g.drawImage(sheet, x, y, x + width, y + height, coords[0], coords[1], coords[0] + width, coords[1] + height);
        }
    }

    public void moveV(int y) {
        this.y += y;
        playMove();
    }

    public void moveH(int x) {
        this.x += x;
        playMove();
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;

    }
}
