package util;

import org.newdawn.slick.Color;
import t2s.SIVOXDevint;

/**
 * Created by Marc KARASSEV on 26/03/14.
 *
 * @author Marc KARASSEV
 */
public class Preferences {

    private static Preferences instance = new Preferences();

    private static int currentVoice;
    private static SIVOXDevint voice;

    private static int currentSetOfColor;
    private static Color backgroundColor;
    private static Color itemColor;
    private static Color highlightColor;
    private static Color textColor;
    private static Color utilityColor;

    public static int handicap=0;


    private Preferences() {
        currentVoice = 1;
        voice = new SIVOXDevint(currentVoice);

        currentSetOfColor = 0;
        backgroundColor = Color.lightGray;
        itemColor = Color.black;
        highlightColor = Color.blue;
        textColor = Color.white;
        utilityColor = Color.darkGray;
    }

    public static void changeColors() {
        if (currentSetOfColor == 0) {
            // jeu de couleurs gris/rose
            currentSetOfColor = 1;
            backgroundColor = Color.pink; // hot pink
            itemColor = Color.darkGray;
            highlightColor = Color.lightGray; // Dark Slate Gray
            textColor = Color.black;
            utilityColor = Color.white;
        } else if (currentSetOfColor == 1) {
            // jeu de couleurs noir/jaune
            currentSetOfColor = 2;
            backgroundColor = Color.yellow;
            itemColor = Color.white;
            highlightColor = Color.black;
            textColor = Color.darkGray;
            utilityColor = Color.lightGray;
        } else {
            // je de couleurs de base
            currentSetOfColor = 0;
            backgroundColor = Color.lightGray;
            highlightColor = Color.blue;
            textColor = Color.white;
            itemColor = Color.black;
            utilityColor = Color.darkGray;
        }
        voice.stop();
        voice.playShortText("Couleurs mises à jour.");
    }

    public static void changeVoice() {
        if(currentVoice == 7)
            currentVoice = 0;
        else
            currentVoice++;
        voice.setVoix(currentVoice);

        voice.stop();
        voice.playShortText("Voix changée.");
    }

    public static Color getBackgroundColor() {
        return backgroundColor;
    }

    public static Color getHighlightColor() {
        return highlightColor;
    }

    public static Color getTextColor() {
        return textColor;
    }

    public static Color getItemColor() {
        return itemColor;
    }

    public static Color getUtilityColor() {
        return utilityColor;
    }

    public static SIVOXDevint getVoice() {
        return voice;
    }

    public static void setHandicap(int i) {
        handicap=i;
    }

    public static int getHandicap(){
        return handicap;
    }
}
