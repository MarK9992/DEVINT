package map;

import main.Game;
import objects.ObjectType;
import objects.Rock;

/**
 * Created by Tom Veniat on 16/05/2014.
 */
public class MapCreator {
    private int width, height;
    private int[][] accessMap;
    MapDivision[][] map ;

    public MapCreator(){
        width=4;
        height=4;
        map = new MapDivision[width][height];
    }

    public void initialize(GameMap game) {

        game.setMapWidth(width);
        game.setMapHeight(height);

        MapParser parser=new MapParser();
        accessMap=parser.getMap();
        createHardcoreMap();

        game.setMap(map);
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
