package objects;

import org.newdawn.slick.Graphics;
import util.Position;
import util.Preferences;

/**
 * Created by Marc KARASSEV on 24/03/14.
 *
 * @author Marc KARASSEV
 */
public class Hero {

    private Position position;
    private int width;
    private int height;

    public Hero() {
        this(0, 0, 1, 1);
    }

    public Hero(int x, int y, int width, int height) {
        position = new Position(x, y);
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        g.setColor(Preferences.getHighlightColor());
        g.fillRect(position.getX(), position.getY(), width, height);
    }

    public void moveUp() {
        position.setY(position.getY() - 5);
    }

    public void moveLeft() {
        position.setX(position.getX() - 5);
    }

    public void moveRight() {
        position.setX(position.getX() + 5);
    }

    public void moveDown() {
        position.setY(position.getY() + 5);
    }
}
