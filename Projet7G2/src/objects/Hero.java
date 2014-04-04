package objects;

import org.newdawn.slick.Graphics;
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

    public Hero() {
        this(0, 0, 1, 1);
    }

    public Hero(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        g.setColor(Preferences.getHighlightColor());
        g.fillRect(x, y, width, height);
    }

    public void moveV(int y) {
        this.y += y;
    }

    public void moveH(int x) {
        this.x += x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
