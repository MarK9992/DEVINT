package objects;

import org.newdawn.slick.Color;

/**
 * Created by Marc KARASSEV on 02/05/2014.
 *
 * Each sprite has four images related to their movement direction.
 * The coordinate and dimensions of these images in the sheet are stored in the fields COORD and DIM.
 * The lignes of these matrixes match to an image, they are ordered by the sprite's direction : SOUTH, WEST, NORTH, and EAST.
 * The first column is the x location for COORD or the width for DIM, the second column is the y location or the height.
 *
 * @author Marc KARASSEV
 */
public enum Sprite {

    // Constants

    DARKSLIME(0),
    ANGELSLIME(1),
    FANGSLIME(2),
    SLIME(3);

    // Fields

    private String sheet, reverseSheet;
    private final short[][] COORD = new short[4][2], DIM = new short[4][2];
    public static final Color TRANSP = new Color(34, 177, 76);

    // Constructors

    private Sprite(int type) {
        switch(type) {
            case 0:
                sheet = "../ressources/pic/dark slimes.png";
                reverseSheet = "../ressources/pic/dark slimes2.png";
                setDarkMatrixes();
                break;
            case 1:
                sheet = "../ressources/pic/angel slimes.png";
                reverseSheet = "../ressources/pic/angel slimes2.png";
                setAngelMatrixes();
                break;
            case 2:
                sheet = "../ressources/pic/fang slimes.png";
                reverseSheet = "../ressources/pic/fang slimes2.png";
                setFangMatrixes();
                break;
            case 3:
                sheet = "../ressources/pic/slimes.png";
                reverseSheet = "../ressources/pic/slimes2.png";
                setSlimeMatrixes();
                break;
            default:
        }
    }

    // Init methods

    private void setDarkMatrixes() {
        COORD[0][0] = 70;
        COORD[0][1] = 67;
        COORD[1][0] = 70;
        COORD[1][1] = 35;
        COORD[2][0] = 70;
        COORD[2][1] = 1;
        COORD[3][0] = 193;
        COORD[3][1] = 35;
        DIM[0][0] = 36;
        DIM[0][1] = 29;
        DIM[1][0] = 36;
        DIM[1][1] = 29;
        DIM[2][0] = 36;
        DIM[2][1] = 31;
        DIM[3][0] = 36;
        DIM[3][1] = 29;
    }

    private void setAngelMatrixes() {
        COORD[0][0] = 1;
        COORD[0][1] = 63;
        COORD[1][0] = 1;
        COORD[1][1] = 33;
        COORD[2][0] = 1;
        COORD[2][1] = 1;
        COORD[3][0] = 270;
        COORD[3][1] = 33;
        DIM[0][0] = 36;
        DIM[0][1] = 27;
        DIM[1][0] = 36;
        DIM[1][1] = 27;
        DIM[2][0] = 36;
        DIM[2][1] = 29;
        DIM[3][0] = 36;
        DIM[3][1] = 27;
    }

    private void setFangMatrixes() {
        COORD[0][0] = 1;
        COORD[0][1] = 69;
        DIM[0][0] = 34;
        DIM[0][1] = 32;
    }

    private void setSlimeMatrixes() {
        COORD[0][0] = 0;
        COORD[0][1] = 100;
        DIM[0][0] = 24;
        DIM[0][1] = 24;
    }

    // Methods

    public short[] getSouthCoords() {
        short[] coords = {COORD[0][0], COORD[0][1]};

        return coords;
    }

    public short[] getWestCoords() {
        short[] coords = {COORD[1][0], COORD[1][1]};

        return coords;
    }

    public short[] getNorthCoords() {
        short[] coords = {COORD[2][0], COORD[2][1]};

        return coords;
    }

    public short[] getEastCoords() {
        short[] coords = {COORD[3][0], COORD[3][1]};
        return coords;
    }

    public short[] getSouthDims() {
        short[] dims = {DIM[0][0], DIM[0][1]};

        return dims;
    }

    public short[] getWestDims() {
        short[] dims = {DIM[1][0], DIM[1][1]};

        return dims;
    }

    public short[] getNorthDims() {
        short[] dims = {DIM[2][0], DIM[2][1]};

        return dims;
    }

    public short[] getEastDims() {
        short[] dims = {DIM[3][0], DIM[3][1]};

        return dims;
    }

    // Getters and setters

    public String getSheet() {
        return sheet;
    }

    public String getReverseSheet() { return reverseSheet; }
}
