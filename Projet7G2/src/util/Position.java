package util;

/**
 * Created by Marc KARASSEV on 24/03/14.
 * @author Marc KARASSEV
 */
public class Position {
    private int x;
    private int y;

    public Position() {
        x = 0;
        y = 0;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}