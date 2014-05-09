package objects;

import org.newdawn.slick.*;
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
    private Image sheet;
    private short xSheet;
    private short ySheet;

    private static final Color TRANSP = new Color(34, 177, 76);
    private static final byte[][] DARKSLIMECOORD = {{70, 67}};
    private static final byte[][] DARKSLIMEDIM = {{36, 29}};

    public Hero() {
        this(0, 0, 1, 1);
    }

    public Hero(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        sheet = null;
        try {
            moveSound = new Sound("../ressources/sound/smb_stomp.wav");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public Hero(int x, int y, String sheet) {
        // TODO recentrer en fonction des sprites
        this.x = x;
        this.y = y;
        try {
            this.sheet = new Image(sheet, TRANSP);
            moveSound = new Sound("../ressources/sound/smb_stomp.wav");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        width = DARKSLIMEDIM[0][0];
        height = DARKSLIMEDIM[0][1];
    }

    public void draw(Graphics g) {
        if(sheet == null) {
            g.setColor(Preferences.getHighlightColor());
            g.fillRect(x, y, width, height);
        }
        else {
            g.drawImage(sheet, x, y, x + DARKSLIMEDIM[0][0], y + DARKSLIMEDIM[0][1], DARKSLIMECOORD[0][0], DARKSLIMECOORD[0][1],
                    DARKSLIMECOORD[0][0] + DARKSLIMEDIM[0][0], DARKSLIMECOORD[0][1] + DARKSLIMEDIM[0][1]);
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
