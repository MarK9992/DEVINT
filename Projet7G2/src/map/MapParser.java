package map;

import main.Game;

import java.util.Scanner;

/**
 * Created by Tom Veniat on 15/05/2014.
 */
public class MapParser {
    private int height;
    private int width;

    private int gameHeight;
    private int gameWidth;


    private char[] inputGlobalMap;
    private int[] globalMap;
    private int[][] map;

    public MapParser(){
        Scanner reader= new Scanner(System.in);

        setSize(reader.nextLine());

        createInputMaps(reader);

        convertMap();

        printGrid();
        System.out.println();
        printDivs();
    }

    private void convertMap() {
        globalMap =new int[height*width];
        map=new int[Game.XTILEMAX*Game.YTILEMAX][gameWidth*gameHeight];
        int value,mapX,mapY,posMap,divX,divY,posDiv;
        for (int i=0;i< inputGlobalMap.length;i++){
            value=Character.getNumericValue(inputGlobalMap[i]);
            globalMap[i]=value;
            mapX=(i%width)/Game.XTILEMAX;
            mapY=i/(width*Game.YTILEMAX);
            posMap=mapY*gameWidth+mapX;
            divX=i%Game.XTILEMAX;
            divY=(i%(width*Game.YTILEMAX))/width;
            posDiv=divY*Game.XTILEMAX+divX;
            map[posDiv][posMap]=value;
        }
    }

    private void createInputMaps(Scanner reader) {
        StringBuilder sb =new StringBuilder();
        for (int i=0;i<height;i++){
            String s =reader.nextLine().trim();
            if (s.matches("[01]{"+width+"}")){
              sb.append(s);
            }else throw new IllegalArgumentException("Bad format");
        }
        inputGlobalMap = sb.toString().toCharArray();
    }

    public void setSize(String size) {
        if (size.matches("[1-9][0-9]* [1-9][0-9]*")){
            String dim[]=size.split(" ");
            gameWidth=Integer.parseInt(dim[0]);
            gameHeight=Integer.parseInt(dim[1]);
            width=gameWidth* Game.XTILEMAX;
            height=gameHeight* Game.YTILEMAX;
            map= new int[Game.XTILEMAX*Game.YTILEMAX][gameWidth*gameHeight];
        }else throw new IllegalArgumentException("Bad format");
    }

    public void printGrid(){
        StringBuilder sb = new StringBuilder();
        for (int i=0 ; i <height*width; i++){
            if (i>0 && i%width==0){
                sb.append("\n");
            }
            sb.append(globalMap[i]==0?' ':'▮');
        }
        System.out.println(sb.toString());
    }

    public void printDivs(){
        StringBuilder sb = new StringBuilder();
        int mapX,mapY,divX,divY,posMap,posDiv;
        for (int i=0 ; i <height*width; i++){
            if (i>0 && i%width==0){
                sb.append("\n");
            }
            mapX=(i%width)/Game.XTILEMAX;
            mapY=i/(width*Game.YTILEMAX);
            posMap=mapY*gameWidth+mapX;
            divX=i%Game.XTILEMAX;
            divY=(i%(width*Game.YTILEMAX))/width;
            posDiv=divY*Game.XTILEMAX+divX;
            sb.append(map[posDiv][posMap] == 0 ? ' ' : '▮');
        }
        System.out.println(sb.toString());
    }

    public static void main( String[] a ){
        int n = 114;
        int mapX=(n%10)/5;//Game.XTILEMAX;
        int mapY=(n/(10*4));//Game.YTILEMAX;
        int haut=n%(2*4*5)/10;
        int larg=(n%5);
        System.out.println(mapX+"  -  "+mapY);
        System.out.println(mapY*2+mapX);
        System.out.println(haut+"  -  "+larg);
        System.out.println(haut*5+larg);
        new MapParser();
    }
}
