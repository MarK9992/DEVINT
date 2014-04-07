package states;

import main.Game;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import util.Preferences;

import java.awt.Font;

/**
 * Created by Tom VENIAT on 04/04/14.
 *
 * @author Tom VENIAT
 */
public class PauseMenuState extends BasicGameState{
    private AppGameContainer container;
    private Game game;
    private int currentButton;

    private static Polygon upArrow;
    private static Polygon enterArrow;
    private static Polygon downArrow;

    private static final int ID = 4;
    private static final String BACK_TO_GAME = "RETOUR AU JEU";
    private static final String OPTIONS = "OPTIONS";
    private static final String BACK_TO_MENU = "MENU PRINCIPAL";
    private static final TrueTypeFont FONT = new TrueTypeFont(new Font("Arial", Font.BOLD, 60), true);
    private static final int BACK_TO_GAME_X = (Game.FRAMEWIDTH - FONT.getWidth(BACK_TO_GAME)) / 2;
    private static final int BACK_TO_GAME_Y = (Game.FRAMEHEIGHT - 3 * FONT.getHeight() - 120) / 2;
    private static final int OPTIONSX = (Game.FRAMEWIDTH - FONT.getWidth(OPTIONS)) / 2;
    private static final int OPTIONSY = BACK_TO_GAME_Y + FONT.getHeight() + 60;
    private static final int BACK_TO_MENU_X = (Game.FRAMEWIDTH - FONT.getWidth(BACK_TO_MENU)) / 2;
    private static final int BACK_TO_MENU_Y = OPTIONSY + FONT.getHeight() + 60;
    private static final int BUTTONX = BACK_TO_MENU_X - 10;
    private static final int BACK_TO_GAME_BUTTON_Y = BACK_TO_GAME_Y - 5;
    private static final int OPTIONSBUTTONY = OPTIONSY - 5;
    private static final int QUITBUTTONY = BACK_TO_MENU_Y - 5;
    private static final int BUTTONWIDTH = FONT.getWidth(BACK_TO_MENU) + 20;
    private static final int BUTTONHEIGHT = FONT.getHeight() + 10;
    private static final int KEYSCHEMEXLEFT = Game.FRAMEWIDTH - 3 * BUTTONX / 4;
    private static final int KEYSCHEMEXRIGHT = Game.FRAMEWIDTH - BUTTONX / 2;
    private static final int UPSCHEMEYBOT = BACK_TO_GAME_BUTTON_Y + BUTTONHEIGHT;
    private static final int ENTERSCHEMEYBOT = OPTIONSBUTTONY + BUTTONHEIGHT / 2;
    private static final int DOWNSCHEMEYBOT = QUITBUTTONY + BUTTONHEIGHT;

    public PauseMenuState() {
        super();
        float[] upPoints = {KEYSCHEMEXRIGHT - 10, BACK_TO_GAME_BUTTON_Y + 20, KEYSCHEMEXRIGHT + 10, BACK_TO_GAME_BUTTON_Y + 20,
                KEYSCHEMEXRIGHT, BACK_TO_GAME_BUTTON_Y};
        float[] enterPoints = {KEYSCHEMEXLEFT, ENTERSCHEMEYBOT - 10, KEYSCHEMEXLEFT, ENTERSCHEMEYBOT + 10,
                KEYSCHEMEXLEFT - 20, ENTERSCHEMEYBOT};
        float[] downPoints = {KEYSCHEMEXRIGHT - 10, DOWNSCHEMEYBOT - 20, KEYSCHEMEXRIGHT + 10, DOWNSCHEMEYBOT - 20,
                KEYSCHEMEXRIGHT, DOWNSCHEMEYBOT};
        upArrow = new Polygon(upPoints);
        enterArrow = new Polygon(enterPoints);
        downArrow = new Polygon(downPoints);
        currentButton = 0;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        if (gameContainer instanceof AppGameContainer) {
            container = (AppGameContainer) gameContainer;
        } else throw new SlickException("init containter");
        if (stateBasedGame instanceof Game) {
            game = (Game) stateBasedGame;
        } else throw new SlickException("init game");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, org.newdawn.slick.Graphics g) throws SlickException {
        g.setBackground(Preferences.getBackgroundColor());
        drawButtons(g);
        g.setColor(Preferences.getUtilityColor());
        g.setLineWidth(5);
        g.drawLine(KEYSCHEMEXRIGHT, UPSCHEMEYBOT, KEYSCHEMEXRIGHT, BACK_TO_GAME_BUTTON_Y + 20);
        g.fill(upArrow);
        g.drawLine(KEYSCHEMEXRIGHT, OPTIONSBUTTONY, KEYSCHEMEXRIGHT, ENTERSCHEMEYBOT);
        g.drawLine(KEYSCHEMEXRIGHT, ENTERSCHEMEYBOT, KEYSCHEMEXLEFT, ENTERSCHEMEYBOT);
        g.fill(enterArrow);
        g.drawLine(KEYSCHEMEXRIGHT, DOWNSCHEMEYBOT - 20, KEYSCHEMEXRIGHT, QUITBUTTONY);
        g.fill(downArrow);
    }

    private void drawButtons(org.newdawn.slick.Graphics g) {
        for (int i = 0; i < 3; i++) {
            drawButtonCase(i, g);
        }
        g.setColor(Preferences.getTextColor());
        g.setFont(FONT);
        g.drawString(BACK_TO_GAME, BACK_TO_GAME_X, BACK_TO_GAME_Y);
        g.drawString(OPTIONS, OPTIONSX, OPTIONSY);
        g.drawString(BACK_TO_MENU, BACK_TO_MENU_X, BACK_TO_MENU_Y);
    }

    private void drawButtonCase(int button, org.newdawn.slick.Graphics g) {
        if (currentButton == button) {
            g.setColor(Preferences.getHighlightColor());
        } else g.setColor(Preferences.getItemColor());
        switch (button) {
            case 0:
                g.fillRect(BUTTONX, BACK_TO_GAME_BUTTON_Y, BUTTONWIDTH, BUTTONHEIGHT);
                break;
            case 1:
                g.fillRect(BUTTONX, OPTIONSBUTTONY, BUTTONWIDTH, BUTTONHEIGHT);
                break;
            case 2:
                g.fillRect(BUTTONX, QUITBUTTONY, BUTTONWIDTH, BUTTONHEIGHT);
                break;
            default:
                System.err.println("bouton incorrect");
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) {
        currentButton = 0;
        Preferences.getVoice().stop();
        Preferences.getVoice().playShortText("Choisissez ce que vous voulez faire. Retour au jeu.");
    }

    @Override
    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_ENTER:
                onEnter();
                break;
            case Input.KEY_UP:
                onUp();
                break;
            case Input.KEY_DOWN:
                onDown();
                break;
            case Input.KEY_ESCAPE:
                onEscape();
                break;
            case Input.KEY_F1:
                onF1();
                break;
            case Input.KEY_F2:
                onF2();
                break;
            default:
        }
    }

    private void onEnter() {
        switch (currentButton) {
            case 0:
                try {
                    leave(container, game);
                    game.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                } catch (SlickException e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                try {
                    leave(container, game);
                    game.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                } catch (SlickException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    leave(container, game);
                    game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                } catch (SlickException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.err.println("bouton incorrect");
        }
    }

    private void onUp() {
        switch (currentButton) {
            case 0:
                currentButton = 2;
                Preferences.getVoice().stop();
                Preferences.getVoice().playShortText("Menu principal.");
                break;
            case 1:
                currentButton = 0;
                Preferences.getVoice().stop();
                Preferences.getVoice().playShortText("Retour au jeu.");
                break;
            case 2:
                currentButton = 1;
                Preferences.getVoice().stop();
                Preferences.getVoice().playShortText("Options.");
                break;
            default:
                System.err.println("bouton incorrect");
        }
    }

    private void onDown() {
        switch (currentButton) {
            case 0:
                currentButton = 1;
                Preferences.getVoice().stop();
                Preferences.getVoice().playShortText("Options.");
                break;
            case 1:
                currentButton = 2;
                Preferences.getVoice().stop();
                Preferences.getVoice().playShortText("Menu principal.");
                break;
            case 2:
                currentButton = 0;
                Preferences.getVoice().stop();
                Preferences.getVoice().playShortText("Retour au jeu.");
                break;
            default:
                System.err.println("bouton incorrect");
        }
    }

    private void onEscape() {
        try {
            leave(container, game);
            game.enterState(3, new FadeOutTransition(org.newdawn.slick.Color.black), new FadeInTransition(org.newdawn.slick.Color.black));
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private void onF1() {
        Preferences.changeColors();
    }

    private void onF2() {
        Preferences.changeVoice();
    }
}
