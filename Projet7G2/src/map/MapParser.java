package map;

import main.Game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

        File inputFile = new File("../ressources/maps/Map_4_4_01.txt");


        try {
            FileReader reader = new FileReader(inputFile);
            BufferedReader in = new BufferedReader(reader);

            setSize(in.readLine());

            createInputMaps(in);

            in.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
/*
        Scanner reader = new Scanner(System.in);

        setSize(reader.nextLine());

        createInputMaps(reader);
*/
        convertMap();

    }

    private void convertMap() {
        globalMap =new int[height*width];
        map=new int[gameWidth*gameHeight][Game.XTILEMAX*Game.YTILEMAX];
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
            map[posMap][posDiv]=value;
        }
    }

    private void createInputMaps(BufferedReader reader) throws IOException {
        StringBuilder sb =new StringBuilder();
        String s;
        for (int i=0;i<height;i++){
            s = reader.readLine().trim();
            if (s.matches("[01]{"+width+"}")){
              sb.append(s);
            }else throw new IllegalArgumentException("Bad format");
        }
        inputGlobalMap = sb.toString().toCharArray();
    }

    private void createInputMaps(Scanner reader){
        StringBuilder sb =new StringBuilder();
        String s;
        for (int i=0;i<height;i++){
            s = reader.nextLine().trim();
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
            map= new int[gameWidth*gameHeight][Game.XTILEMAX*Game.YTILEMAX];
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
            sb.append(map[posMap][posDiv] == 0 ? ' ' : '▮');
        }
        System.out.println(sb.toString());
    }

    public static void main( String[] a ){


        int[][] tab =new int[10][5];
        for (int[] t :tab )
            System.out.println(t.length);

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

    public int[][] getMap() {
        return map;
    }
}
