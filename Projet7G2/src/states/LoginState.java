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

import java.awt.Font;

/**
 * Created by Marc KARASSEV on 25/03/14.
 *
 * @author Marc KARASSEV
 */
public class LoginState extends BasicGameState {

    private AppGameContainer container;
    private Game game;

    private static final int ID = 0;
    private static final String TITLE = "RETROUVE TON CHEMIN !";
    private static final String NEWPLAYER = "NOUVEAU JOUEUR";
    private static final TrueTypeFont TITLEFONT = new TrueTypeFont(new Font("Arial", Font.BOLD, 50), true);
    private static final TrueTypeFont BUTTONFONT = new TrueTypeFont(new Font("Arial", Font.BOLD, 40), true);
    private static final int TITLEX = (Game.FRAMEWIDTH - TITLEFONT.getWidth(TITLE)) / 2;
    private static final int TITLEY = Game.FRAMEHEIGHT / 6;
    private static final int NEWPLAYERX = (Game.FRAMEWIDTH - BUTTONFONT.getWidth(NEWPLAYER)) / 2;
    private static final int NEWPLAYERY = Game.FRAMEHEIGHT / 2;
    private static final int BUTTONX = NEWPLAYERX - 10;
    private static final int BUTTONY = NEWPLAYERY - 5;
    private static final int BUTTONWIDTH = BUTTONFONT.getWidth(NEWPLAYER) + 20;
    private static final int BUTTONHEIGHT = BUTTONFONT.getHeight() + 10;
    private static final int KEYSCHEMEXLEFT = Game.FRAMEWIDTH - 3 * BUTTONX / 4;
    private static final int KEYSCHEMEXRIGHT = Game.FRAMEWIDTH - BUTTONX / 2;
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
        if(gameContainer instanceof AppGameContainer) {
            container = (AppGameContainer) gameContainer;
        }
        else throw new SlickException("init containter");
        if(stateBasedGame instanceof Game) {
            game = (Game) stateBasedGame;
        }
        else throw new SlickException("init game");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        g.setBackground(Color.white);
        g.setColor(Color.blue);
        g.setFont(TITLEFONT);
        g.drawString(TITLE, TITLEX, TITLEY);
        g.fillRect(BUTTONX, BUTTONY, BUTTONWIDTH, BUTTONHEIGHT);
        g.setFont(BUTTONFONT);
        g.setColor(Color.white);
        g.drawString(NEWPLAYER, NEWPLAYERX, NEWPLAYERY);
        g.setColor(Color.darkGray);
        g.setLineWidth(5);
        g.drawLine(KEYSCHEMEXRIGHT, BUTTONY, KEYSCHEMEXRIGHT, KEYSCHEMEYBOT);
        g.drawLine(KEYSCHEMEXRIGHT, KEYSCHEMEYBOT, KEYSCHEMEXLEFT, KEYSCHEMEYBOT);
        g.fill(enterArrow);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    @Override
    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_ENTER: onEnter(); break;
            case Input.KEY_ESCAPE: onEscape(); break;
            default:
        }
    }

    private void onEnter() {
        try {
            leave(container, game);
            game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        } catch (SlickException e) {
            e.printStackTrace();
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
}
