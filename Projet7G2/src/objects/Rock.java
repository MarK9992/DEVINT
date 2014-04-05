package objects;

import org.newdawn.slick.Graphics;
import util.Preferences;

/**
 * Created by Marc KARASSEV on 31/03/14.
 *
 * @author Marc KARASSEV
 */
public class Rock extends GameObject {

    public Rock() {
        this(0, 0, 1, 1, ObjectType.ROCK);
    }

    public Rock(int x, int y, int width, int height, ObjectType t) {
        super(x, y, width, height, t);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Preferences.getItemColor());
        g.fillRect(x, y, width, height);
    }

    @Override
    public void interact() {

    }
}
