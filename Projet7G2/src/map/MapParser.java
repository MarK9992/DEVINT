package map;

import main.Game;
import util.Preferences;

import java.util.Scanner;

/**
 * Created by Tom Veniat on 15/05/2014.
 */
public class MapParser {
    private int height;
    private int width;

    private int gameHeight;
    private int gameWidth;


    private char[] inputMap;
    private int[] map;

    public MapParser(){
        Scanner reader= new Scanner(System.in);

        setSize(reader.nextLine());

        createMapDivision(reader);

        convertMap();

        printGrid();
    }

    private void convertMap() {
        map=new int[height*width];
        for (int i=0;i<inputMap.length;i++){
           map[i]=Character.getNumericValue(inputMap[i]);
        }
    }

    private void createMapDivision(Scanner reader) {
        StringBuilder sb =new StringBuilder();
        for (int i=0;i<height;i++){
            String s =reader.nextLine().trim();
            if (s.matches("[01]{"+width+"}")){
              sb.append(s);
            }else throw new IllegalArgumentException("Bad format");
        }
        inputMap= sb.toString().toCharArray();
    }

    public void setSize(String size) {
        if (size.matches("[1-9][0-9]* [1-9][0-9]*")){
            String dim[]=size.split(" ");
            gameWidth=Integer.parseInt(dim[0]);
            gameHeight=Integer.parseInt(dim[1]);
            width=gameWidth* Game.XTILEMAX;
            height=gameHeight* Game.YTILEMAX;
        }else throw new IllegalArgumentException("Bad format");
    }

    public void printGrid(){
        StringBuilder sb = new StringBuilder();
        for (int i=0 ; i <height*width; i++){
            if (i>0 && i%width==0){
                sb.append("\n");
            }
            sb.append(map[i]==0?' ':'▮');
        }
        System.out.println(sb.toString());
    }

    public static void main( String[] a ){
        new MapParser();
    }
}
