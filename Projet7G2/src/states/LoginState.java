package states;

import main.Game;
import org.newdawn.slick.Color;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import util.Preferences;
import util.SoundPlayer;

import java.awt.Font;

/**
 * Created by Marc KARASSEV on 25/03/14.
 *
 * @author Marc KARASSEV
 */
public class LoginState extends BasicGameState {

    private AppGameContainer container;
    private Game game;
    private int currentButton;

    private static Polygon upArrow;
    private static Polygon enterArrow;
    private static Polygon downArrow;

    private static final int ID = 0;
    private static final String MAL_VOYANT = "MAL VOYANT";
    private static final String NON_VOYANT = "NON VOYANT";
    private static final String QUIT = "QUITTER";
    private static final TrueTypeFont FONT = new TrueTypeFont(new Font("Arial", Font.BOLD, 55), true);
    private static final int CHANGE_COLORS_X = (Game.FRAMEWIDTH - FONT.getWidth(MAL_VOYANT)) / 2;
    private static final int CHANGE_COLORS_Y = (Game.FRAMEHEIGHT - 3 * FONT.getHeight() - 120) / 2;
    private static final int CHANGE_HERO_X = (Game.FRAMEWIDTH - FONT.getWidth(NON_VOYANT)) / 2;
    private static final int CHANGE_HERO_Y = CHANGE_COLORS_Y + FONT.getHeight() + 60;
    private static final int QUITX = (Game.FRAMEWIDTH - FONT.getWidth(QUIT)) / 2;
    private static final int QUITY = CHANGE_HERO_Y + FONT.getHeight() + 60;
    private static final int BUTTONX = CHANGE_COLORS_X - 10;
    private static final int CHANGE_COLORS_BUTTON_Y = CHANGE_COLORS_Y - 5;
    private static final int CHANGE_HERO_BUTTON_Y = CHANGE_HERO_Y - 5;
    private static final int QUITBUTTONY = QUITY - 5;
    private static final int BUTTONWIDTH = FONT.getWidth(MAL_VOYANT) + 20;
    private static final int BUTTONHEIGHT = FONT.getHeight() + 10;
    private static final int KEYSCHEMEXLEFT = Game.FRAMEWIDTH - 3 * BUTTONX / 4;
    private static final int KEYSCHEMEXRIGHT = Game.FRAMEWIDTH - BUTTONX / 2;
    private static final int UPSCHEMEYBOT = CHANGE_COLORS_BUTTON_Y + BUTTONHEIGHT;
    private static final int ENTERSCHEMEYBOT = CHANGE_HERO_BUTTON_Y + BUTTONHEIGHT / 2;
    private static final int DOWNSCHEMEYBOT = QUITBUTTONY + BUTTONHEIGHT;

    public LoginState() {
        super();
        float[] upPoints = {KEYSCHEMEXRIGHT - 10, CHANGE_COLORS_BUTTON_Y + 20, KEYSCHEMEXRIGHT + 10, CHANGE_COLORS_BUTTON_Y + 20,
                KEYSCHEMEXRIGHT, CHANGE_COLORS_BUTTON_Y};
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
            System.out.println("I have a game !");
        } else throw new SlickException("init game");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        g.setBackground(Preferences.getBackgroundColor());
        drawButtons(g);
        g.setColor(Preferences.getUtilityColor());
        g.setLineWidth(5);
        g.drawLine(KEYSCHEMEXRIGHT, UPSCHEMEYBOT, KEYSCHEMEXRIGHT, CHANGE_COLORS_BUTTON_Y + 20);
        g.fill(upArrow);
        g.drawLine(KEYSCHEMEXRIGHT, CHANGE_HERO_BUTTON_Y, KEYSCHEMEXRIGHT, ENTERSCHEMEYBOT);
        g.drawLine(KEYSCHEMEXRIGHT, ENTERSCHEMEYBOT, KEYSCHEMEXLEFT, ENTERSCHEMEYBOT);
        g.fill(enterArrow);
        g.drawLine(KEYSCHEMEXRIGHT, DOWNSCHEMEYBOT - 20, KEYSCHEMEXRIGHT, QUITBUTTONY);
        g.fill(downArrow);
    }

    private void drawButtons(Graphics g) {
        for (int i = 0; i < 3; i++) {
            drawButtonCase(i, g);
        }
        g.setColor(Preferences.getTextColor());
        g.setFont(FONT);
        g.drawString(MAL_VOYANT, CHANGE_COLORS_X, CHANGE_COLORS_Y);
        g.drawString(NON_VOYANT, CHANGE_HERO_X, CHANGE_HERO_Y);
        g.drawString(QUIT, QUITX, QUITY);
    }

    private void drawButtonCase(int button, Graphics g) {
        if (currentButton == button) {
            g.setColor(Preferences.getHighlightColor());
        } else g.setColor(Preferences.getItemColor());
        switch (button) {
            case 0:
                g.fillRect(BUTTONX, CHANGE_COLORS_BUTTON_Y, BUTTONWIDTH, BUTTONHEIGHT);
                break;
            case 1:
                g.fillRect(BUTTONX, CHANGE_HERO_BUTTON_Y, BUTTONWIDTH, BUTTONHEIGHT);
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
            case Input.KEY_F3:
                onF3();
                break;
            case Input.KEY_F4:
                onF4();
                break;
            default:
        }
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) {
        currentButton = 0;
        SoundPlayer.getInstance().say("ChoisissezLeModeDeJeuMalVoyant.wav");
    }

    private void onEnter() {
        switch (currentButton) {
            case 0:
                try {
                Preferences.setHandicap(0);
                leave(container, game);
                game.reinitGame();
                game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
            } catch (SlickException e) {
                e.printStackTrace();
            }

                break;
            case 1:
                try {
                    Preferences.setHandicap(1);
                    leave(container, game);
                    game.reinitGame();
                    game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                } catch (SlickException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
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
                currentButton = 2;
                SoundPlayer.getInstance().say("Quitter.wav");
                break;
            case 1:
                currentButton = 0;
                SoundPlayer.getInstance().say("Malvoyants.wav");
                break;
            case 2:
                currentButton = 1;
                SoundPlayer.getInstance().say("NonVoyants.wav");
                break;
            default:
                System.err.println("bouton incorrect");
        }
    }

    private void onDown() {
        switch (currentButton) {
            case 0:
                currentButton = 1;
                SoundPlayer.getInstance().say("NonVoyants.wav");
                break;
            case 1:
                currentButton = 2;
                SoundPlayer.getInstance().say("Quitter.wav");
                break;
            case 2:
                currentButton = 0;
                SoundPlayer.getInstance().say("Malvoyants.wav");
                break;
            default:
                System.err.println("bouton incorrect");
        }
    }

    private void onEscape() {
        try {
            leave(container, game);
            System.exit(0);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private void onF1() {
        SoundPlayer.getInstance().say("ChoisissezLeModeDeJeu.wav");
    }

    private void onF3() {
        Preferences.changeColors();
    }

    private void onF4() {
        Preferences.changeVoice();
    }
}
