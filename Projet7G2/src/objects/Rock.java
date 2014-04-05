package objects;

import org.newdawn.slick.Graphics;
import util.Preferences;

/**
 * Created by Marc KARASSEV on 31/03/14.
 *
 * @author Marc KARASSEV
 */
public class Rock extends GameObject {

    private int width;
    private int height;

    public Rock() {
        this(0, 0, 1, 1);
    }

    public Rock(int x, int y, int w, int h) {
        super(x, y);
        width = w;
        height = h;
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
