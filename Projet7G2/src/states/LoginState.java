package states;

import main.Game;
import org.newdawn.slick.Color;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Font;

/**
 * Created by Marc KARASSEV on 25/03/14.
 *
 * @author Marc KARASSEV
 */
public class LoginState extends BasicGameState {

    public static final int ID = 0;
    private static final String TITLE = "RETROUVE TON CHEMIN !";
    private static final String NEWPLAYER = "NOUVEAU JOUEUR";
    private static final TrueTypeFont TITLEFONT = new TrueTypeFont(new Font("Arial", Font.BOLD, 50), false);
    private static final TrueTypeFont BUTTONFONT = new TrueTypeFont(new Font("Arial", Font.BOLD, 40), false);
    private static final int TITLEX = (Game.FRAMEWIDTH - TITLEFONT.getWidth(TITLE)) / 2;
    private static final int TITLEY = Game.FRAMEHEIGHT / 6;
    private static final int NEWPLAYERX = (Game.FRAMEWIDTH - BUTTONFONT.getWidth(NEWPLAYER)) / 2;
    private static final int NEWPLAYERY = Game.FRAMEHEIGHT / 2;
    private static final int BUTTONX = NEWPLAYERX - 10;
    private static final int BUTTONY = NEWPLAYERY - 5;
    private static final int BUTTONWIDTH = BUTTONFONT.getWidth(NEWPLAYER) + 20;
    private static final int BUTTONHEIGHT = BUTTONFONT.getHeight() + 10;
    private static final int KEYSCHEMEXTOP = Game.FRAMEWIDTH - BUTTONX / 2;
    private static final int KEYSCHEMEXLEFT = Game.FRAMEWIDTH - 3 * BUTTONX / 4;
    private static final int KEYSCHEMEYBOT = BUTTONY + BUTTONHEIGHT / 2;
    private static Polygon enterArrow;

    public LoginState() {
        super();
        float[] arrowPoints = {KEYSCHEMEXLEFT, KEYSCHEMEYBOT - 10, KEYSCHEMEXLEFT, KEYSCHEMEYBOT + 10,
                KEYSCHEMEXLEFT - 20, KEYSCHEMEYBOT};
        enterArrow = new Polygon(arrowPoints);

    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setBackground(Color.white);
        graphics.setColor(Color.blue);
        graphics.setFont(TITLEFONT);
        graphics.drawString(TITLE, TITLEX, TITLEY);
        graphics.fillRect(BUTTONX, BUTTONY, BUTTONWIDTH, BUTTONHEIGHT);
        graphics.setFont(BUTTONFONT);
        graphics.setColor(Color.white);
        graphics.drawString(NEWPLAYER, NEWPLAYERX, NEWPLAYERY);
        graphics.setColor(Color.gray);
        graphics.drawLine(KEYSCHEMEXTOP, BUTTONY, KEYSCHEMEXTOP, KEYSCHEMEYBOT);
        graphics.drawLine(KEYSCHEMEXTOP, KEYSCHEMEYBOT, KEYSCHEMEXLEFT, KEYSCHEMEYBOT);
        graphics.fill(enterArrow);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_ENTER) {
            System.exit(0);
        }
    }
}
