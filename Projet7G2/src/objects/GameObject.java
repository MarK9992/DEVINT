package objects;

import org.newdawn.slick.Graphics;

/**
 * Created by Marc KARASSEV on 24/03/14.
 * @author Marc KARASSEV
 */
public abstract class GameObject {
    protected ObjectType type;
    protected int x;
    protected int y;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void draw(Graphics g);

    public abstract void interact();
}
