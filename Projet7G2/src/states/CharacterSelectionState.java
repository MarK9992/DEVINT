package states;

import main.*;
import main.Game;
import objects.Sprite;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import util.Preferences;

/**
 * Created by Marc KARASSEV on 10/05/2014.
 */
public class CharacterSelectionState extends BasicGameState {

    private static final int ID = 5, DARKSLIME = 0, ANGELSLIME = 1, FANGSLIME = 2, SLIME = 3;

    private static int currentCharacter;
    private AppGameContainer container;
    private main.Game game;

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
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) {
        currentCharacter = DARKSLIME;
        Preferences.getVoice().stop();
        Preferences.getVoice().playShortText("Choisissez votre personnage.");
    }

    @Override
    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_ENTER:
                onEnter();
                break;
            case Input.KEY_UP:
                //onUp();
                break;
            case Input.KEY_LEFT:
                //onLeft();
                break;
            case Input.KEY_RIGHT:
                //onRight();
                break;
            case Input.KEY_DOWN:
                //onDown();
                break;
            case Input.KEY_ESCAPE:
                //onEscape();
                break;
            case Input.KEY_F3:
                //onF3();
                break;
            case Input.KEY_F4:
                //onF4();
                break;
            default:
        }
    }

    private void onEnter() {
        switch (currentCharacter) {
            case DARKSLIME:

                break;
            case ANGELSLIME:
                break;
            case FANGSLIME:
                break;
            case SLIME:
                break;
            default:
                System.err.println("personnage incorrect");
        }
        try {
            leave(container, game);
            game.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public static Sprite getCharacter() {
        switch(currentCharacter) {
            case DARKSLIME:
                return Sprite.DARKSLIME;
            case ANGELSLIME:
                return Sprite.ANGELSLIME;
            case FANGSLIME:
                return Sprite.FANGSLIME;
            case SLIME:
                return Sprite.SLIME;
            default:
                return Sprite.DARKSLIME;
        }
    }
}
