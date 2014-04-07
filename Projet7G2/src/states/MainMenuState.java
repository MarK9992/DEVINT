package states;

import main.Game;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import util.Preferences;

import java.awt.Font;

/**
 * Created by Marc KARASSEV on 26/03/14.
 *
 * @author Marc KARASSEV
 */
public class MainMenuState extends BasicGameState {

    private AppGameContainer container;
    private Game game;
    private int currentButton;

    private static Polygon upArrow;
    private static Polygon enterArrow;
    private static Polygon downArrow;

    private static final int ID = 1;
    private static final String PLAY = "JOUER";
    private static final String OPTIONS = "OPTIONS";
    private static final String QUIT = "QUITTER";
    private static final TrueTypeFont FONT = new TrueTypeFont(new Font("Arial", Font.BOLD, 60), true);
    private static final int PLAYX = (Game.FRAMEWIDTH - FONT.getWidth(PLAY)) / 2;
    private static final int PLAYY = (Game.FRAMEHEIGHT - 3 * FONT.getHeight() - 120) / 2;
    private static final int OPTIONSX = (Game.FRAMEWIDTH - FONT.getWidth(OPTIONS)) / 2;
    private static final int OPTIONSY = PLAYY + FONT.getHeight() + 60;
    private static final int QUITX = (Game.FRAMEWIDTH - FONT.getWidth(QUIT)) / 2;
    private static final int QUITY = OPTIONSY + FONT.getHeight() + 60;
    private static final int BUTTONX = OPTIONSX - 10;
    private static final int PLAYBUTTONY = PLAYY - 5;
    private static final int OPTIONSBUTTONY = OPTIONSY - 5;
    private static final int QUITBUTTONY = QUITY - 5;
    private static final int BUTTONWIDTH = FONT.getWidth(OPTIONS) + 20;
    private static final int BUTTONHEIGHT = FONT.getHeight() + 10;
    private static final int KEYSCHEMEXLEFT = Game.FRAMEWIDTH - 3 * BUTTONX / 4;
    private static final int KEYSCHEMEXRIGHT = Game.FRAMEWIDTH - BUTTONX / 2;
    private static final int UPSCHEMEYBOT = PLAYBUTTONY + BUTTONHEIGHT;
    private static final int ENTERSCHEMEYBOT = OPTIONSBUTTONY + BUTTONHEIGHT / 2;
    private static final int DOWNSCHEMEYBOT = QUITBUTTONY + BUTTONHEIGHT;

    public MainMenuState() {
        super();
        float[] upPoints = {KEYSCHEMEXRIGHT - 10, PLAYBUTTONY + 20, KEYSCHEMEXRIGHT + 10, PLAYBUTTONY + 20,
                KEYSCHEMEXRIGHT, PLAYBUTTONY};
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
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        g.setBackground(Preferences.getBackgroundColor());
        drawButtons(g);
        g.setColor(Preferences.getUtilityColor());
        g.setLineWidth(5);
        g.drawLine(KEYSCHEMEXRIGHT, UPSCHEMEYBOT, KEYSCHEMEXRIGHT, PLAYBUTTONY + 20);
        g.fill(upArrow);
        g.drawLine(KEYSCHEMEXRIGHT, OPTIONSBUTTONY, KEYSCHEMEXRIGHT, ENTERSCHEMEYBOT);
        g.drawLine(KEYSCHEMEXRIGHT, ENTERSCHEMEYBOT, KEYSCHEMEXLEFT, ENTERSCHEMEYBOT);
        g.fill(enterArrow);
        g.drawLine(KEYSCHEMEXRIGHT, DOWNSCHEMEYBOT - 20, KEYSCHEMEXRIGHT, QUITBUTTONY);
        g.fill(downArrow);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) {
        currentButton = 0;
        Preferences.getVoice().stop();
        Preferences.getVoice().playShortText("Choisissez ce que vous voulez faire. Jouer.");
    }

    private void drawButtons(Graphics g) {
        for (int i = 0; i < 3; i++) {
            drawButtonCase(i, g);
        }
        g.setColor(Preferences.getTextColor());
        g.setFont(FONT);
        g.drawString(PLAY, PLAYX, PLAYY);
        g.drawString(OPTIONS, OPTIONSX, OPTIONSY);
        g.drawString(QUIT, QUITX, QUITY);
    }

    private void drawButtonCase(int button, Graphics g) {
        if (currentButton == button) {
            g.setColor(Preferences.getHighlightColor());
        } else g.setColor(Preferences.getItemColor());
        switch (button) {
            case 0:
                g.fillRect(BUTTONX, PLAYBUTTONY, BUTTONWIDTH, BUTTONHEIGHT);
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
                Preferences.getVoice().stop();
                Preferences.getVoice().playShortText("Jouer.");
                try {
                    leave(container, game);
                    game.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                } catch (SlickException e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                Preferences.getVoice().stop();
                Preferences.getVoice().playShortText("Options.");
                try {
                    leave(container, game);
                    game.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                } catch (SlickException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                Preferences.getVoice().stop();
                Preferences.getVoice().playShortText("Quitter.");
                try {
                    leave(container, game);
                    System.exit(0);
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
                Preferences.getVoice().stop();
                currentButton = 2;
                Preferences.getVoice().playShortText("Quitter.");
                break;
            case 1:
                currentButton = 0;
                Preferences.getVoice().stop();
                Preferences.getVoice().playShortText("Jouer.");
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
                Preferences.getVoice().playShortText("Quitter.");
                break;
            case 2:
                currentButton = 0;
                Preferences.getVoice().stop();
                Preferences.getVoice().playShortText("Jouer.");
                break;
            default:
                System.err.println("bouton incorrect");
        }
    }

    private void onEscape() {
        Preferences.getVoice().stop();
        Preferences.getVoice().playShortText("Quitter.");
        try {
            leave(container, game);
            game.enterState(0, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
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
