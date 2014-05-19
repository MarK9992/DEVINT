package map;

import level.Direction;
import level.ObjectiveGestion;
import main.Game;
import objects.ObjectType;
import objects.Rock;
import states.MainGameState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom Veniat on 16/05/2014.
 */
public class MapCreator {
    private int width, height, firstX, firstY;
    private int[][] accessMap;
    private MapDivision[][] map ;
    private ObjectiveGestion objective;

    public MapCreator(){

    }

    public void initialize(GameMap game) {

        try {
            MapParser parser=new MapParser();
            width=parser.getGameWidth();
            height=parser.getGameHeight();

            accessMap=parser.getMap();
            firstX=parser.getFirstX();
            firstY=parser.getFirstY();

            objective=createObjective(parser.getObjective());

            map = new MapDivision[width][height];
            for(int j=0;j<height;j++){
                for (int i=0;i<width;i++){
                    map[i][j]=new MapDivision(accessMap[j*width+i]);
                }
            }
        } catch (Exception e) {
            width=4;
            height=4;
            map = new MapDivision[width][height];
            createHardcoreMap();
            createHardcoreObjective();
            firstX=1;
            firstY=2;
            e.printStackTrace();
        }

        initializeGame(game);

    }

    private ObjectiveGestion createObjective(int[] objInt) {

        Direction[] objDir=toDirectionArray(objInt);
        if (isValidPath(objDir)){
            return new ObjectiveGestion(toList(objDir));
        }else {
            throw new IllegalArgumentException("Bad objective List");
        }

    }

    private boolean isValidPath(Direction[] objDir) {
        boolean goodPath=true;
        int step=0,currentX=firstX,currentY=firstY;
        Direction dir;
        while (goodPath){
            if (step==objDir.length)
                return true;
            else{
                dir=objDir[step++];
                currentX+=dir.getMoveX();
                currentY+=dir.getMoveY();
                goodPath= (currentX>=0) && (currentY>=0) && (currentX<width) && (currentY<height);
            }

        }
        return false;
    }

    private List<Direction> toList(Direction[] objDir) {
        List<Direction> directionList=new ArrayList<Direction>();
        for (Direction d :objDir){
            directionList.add(d);
        }
        return directionList;
    }

    private Direction[] toDirectionArray(int[] objInt) {
        Direction[] result = new Direction[objInt.length];
        for (int i=0; i<objInt.length;i++){
            result[i]=Direction.valueOf(objInt[i]);
        }
        return result;
    }

    private void initializeGame(GameMap game) {
        game.setMapWidth(width);
        game.setMapHeight(height);
        game.setMap(map);
        game.setMapi(firstX);
        game.setMapj(firstY);
        game.setObjective(objective);
    }

    private void createHardcoreObjective() {
        ArrayList<Direction> list=new ArrayList<Direction>();
        list.add(Direction.UP);
        list.add(Direction.LEFT);
        list.add(Direction.UP);
        list.add(Direction.RIGHT);
        list.add(Direction.DOWN);
        this.objective=new ObjectiveGestion(list);
    }


    private void createHardcoreMap() {
        MapDivision div;

        // Création dégueulasse des divisions

        div = new MapDivision("Allez à droite.");
        div.addObject(new Rock(0, 0, Game.FRAMEWIDTH, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 2 * Game.TILE, 4 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 8 * Game.TILE, 4 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 2 * Game.TILE, 2 * Game.TILE, 10 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(2 * Game.TILE, 10 * Game.TILE, 6 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));


        map[0][0] = div;


        div = new MapDivision("Allez en bas.");
        div.addObject(new Rock(0, 0, Game.FRAMEWIDTH, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 2 * Game.TILE, 4 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 8 * Game.TILE, 8 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 6 * Game.TILE, 4 * Game.TILE, 6 * Game.TILE, ObjectType.ROCK));
        map[1][0] = div;


        div = new MapDivision("Allez en bas.");
        div.addObject(new Rock(0, 0, Game.FRAMEWIDTH, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 6 * Game.TILE, 6 * Game.TILE, 6 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(10 * Game.TILE, 8 * Game.TILE, 6 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        map[2][0] = div;


        div = new MapDivision("Sortir à gauche.");
        div.addObject(new Rock(0, 0, Game.FRAMEWIDTH, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 8 * Game.TILE, 6 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(14 * Game.TILE, 0, 2 * Game.TILE, 12 * Game.TILE, ObjectType.ROCK));
        map[3][0] = div;

        div = new MapDivision("Sortir en bas.");
        div.addObject(new Rock(0, 0, 8 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 4 * Game.TILE, 4 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 8 * Game.TILE, 2 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 0, 4 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(14 * Game.TILE, 2 * Game.TILE, 2 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(6 * Game.TILE, 10 * Game.TILE, 10 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(8 * Game.TILE, 8 * Game.TILE, 8 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        map[0][1] = div;

        div = new MapDivision("Sortir à gauche.");
        div.addObject(new Rock(0, 0, 8 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 8 * Game.TILE, 8 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 0, 4 * Game.TILE, Game.FRAMEHEIGHT, ObjectType.ROCK));
        map[1][1] = div;

        div = new MapDivision("Sortir en bas.");
        div.addObject(new Rock(0, 0, 6 * Game.TILE, Game.FRAMEHEIGHT, ObjectType.ROCK));
        div.addObject(new Rock(10 * Game.TILE, 0, 6 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(10 * Game.TILE, 6 * Game.TILE, 6 * Game.TILE, 6 * Game.TILE, ObjectType.ROCK));

        map[2][1] = div;

        div = new MapDivision("Sortir à gauche.");
        div.addObject(new Rock(0, 0, 6 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 6 * Game.TILE, 8 * Game.TILE, 6 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(14 * Game.TILE, 0, 2 * Game.TILE, Game.FRAMEHEIGHT, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 8 * Game.TILE, 2 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        map[3][1] = div;

        div = new MapDivision("Sortir en bas.");
        div.addObject(new Rock(0, 0, 2 * Game.TILE, Game.FRAMEHEIGHT, ObjectType.ROCK));
        div.addObject(new Rock(6 * Game.TILE, 0, 10 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(2 * Game.TILE, 10 * Game.TILE, 6 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(14 * Game.TILE, 6 * Game.TILE, 2 * Game.TILE, 6 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 10 * Game.TILE, 4 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        map[0][2] = div;

        div = new MapDivision("Sortir en haut.");
        div.addObject(new Rock(0, 0, 8 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 0, 4 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 6 * Game.TILE, 4 * Game.TILE, 6 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 10 * Game.TILE, 4 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));

        map[1][2] = div;

        div = new MapDivision("Sortir à gauche.");
        div.addObject(new Rock(0, 0, 6 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(10 * Game.TILE, 0, 6 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 10 * Game.TILE, Game.FRAMEWIDTH, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(10 * Game.TILE, 8 * Game.TILE, 6 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        map[2][2] = div;

        div = new MapDivision("Sortir à gauche.");
        div.addObject(new Rock(0, 0, 8 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 2 * Game.TILE, 4 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(14 * Game.TILE, 0, 2 * Game.TILE, Game.FRAMEHEIGHT, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 0, 2 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 8 * Game.TILE, 4 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(8 * Game.TILE, 10 * Game.TILE, 6 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        map[3][2] = div;

        div = new MapDivision("Bien. Retournez au point de départ maintenant.");
        div.addObject(new Rock(0, 0, 2 * Game.TILE, 10 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(2 * Game.TILE, 0, 6 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 10 * Game.TILE, Game.FRAMEWIDTH, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 0, 4 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(14 * Game.TILE, 6 * Game.TILE, 2 * Game.TILE, 6 * Game.TILE, ObjectType.ROCK));
        map[0][3] = div;

        div = new MapDivision("Sortir en haut.");
        div.addObject(new Rock(0, 0, 4 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(12 * Game.TILE, 0, 4 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 6 * Game.TILE, Game.FRAMEWIDTH, 6 * Game.TILE, ObjectType.ROCK));
        map[1][3] = div;

        div = new MapDivision("Sortir à gauche.");
        div.addObject(new Rock(0, 0, Game.FRAMEWIDTH, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 6 * Game.TILE, Game.FRAMEWIDTH, 6 * Game.TILE, ObjectType.ROCK));
        map[2][3] = div;

        div = new MapDivision("Sortir à gauche.");
        div.addObject(new Rock(0, 0, 4 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 6 * Game.TILE, 4 * Game.TILE, 6 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(0, 10 * Game.TILE, Game.FRAMEWIDTH, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(8 * Game.TILE, 0, 8 * Game.TILE, 2 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(10 * Game.TILE, 2 * Game.TILE, 6 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));
        div.addObject(new Rock(14 * Game.TILE, 6 * Game.TILE, 2 * Game.TILE, 4 * Game.TILE, ObjectType.ROCK));

        map[3][3] = div;

    }

}
