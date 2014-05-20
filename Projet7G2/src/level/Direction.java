package level;

/**
 * Created by tom on 09/04/14.
 */
public enum Direction {
    UP(0,-1,"EnHaut"),
    DOWN(0,1,"EnBas"),
    RIGHT(1,0,"ADroite"),
    LEFT(-1,0,"AGauche");

    private int moveX;
    private int moveY;
    private String action;

    Direction(int x,int y,String action){
        this.moveX=x;
        this.moveY=y;
        this.action=action;
    }

    public static Direction valueOf(int i){
        switch (i){
            case 0:
                return UP;
            case 1:
                return RIGHT;
            case 2:
                return DOWN;
            case 3:
                return LEFT;
        }
        return null;
    }

    public Direction getOppositeDirection(){
        if (this.equals(Direction.UP)){
            return Direction.DOWN;
        }
        else if (this.equals(Direction.DOWN)){
            return Direction.UP;
        }
        else if (this.equals(Direction.LEFT)){
            return Direction.RIGHT;
        }
        else if (this.equals(Direction.RIGHT)){
            return Direction.LEFT;
        }
        return null;
    }

    public int getMoveX() {
        return moveX;
    }

    public int getMoveY() {
        return moveY;
    }

    public String getAction() {
        return action;
    }
}
