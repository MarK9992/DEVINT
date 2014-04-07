package objects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import util.Preferences;

/**
 * Created by Marc KARASSEV on 24/03/14.
 *
 * @author Marc KARASSEV
 */
public class Hero {

    private int x;
    private int y;
    private int width;
    private int height;
    private Sound moveSound;

    public Hero() {
        this(0, 0, 1, 1);
    }

    public Hero(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        try {
            moveSound = new Sound("../ressources/sound/smb_stomp.wav");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        g.setColor(Preferences.getHighlightColor());
        g.fillRect(x, y, width, height);
    }

    public void moveV(int y) {
        this.y += y;
        playMove();
    }

    public void moveH(int x) {
        this.x += x;
        playMove();
    }

    public int getUpLeftCornerX() { return x; }

    public int getUpRightCornerX() { return x + width; }

    public int getDownLeftCornerX() { return  x; }

    public int getDownRightCornerX() { return x + width; }

    public int getUpLeftCornerY() { return y; }

    public int getUpRightCornerY() { return y; }

    public int getDownLeftCornerY() { return  y + height; }

    public int getDownRightCornerY() { return y + height; }

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

    private void playMove() {
        if(!moveSound.playing()) {
            moveSound.play();
        }
    }
}
