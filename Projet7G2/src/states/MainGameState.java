package states;

import main.*;
import main.Game;
import map.GameMap;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import util.Preferences;

/**
 * Created by Marc KARASSEV on 27/03/14.
 *
 * @author Marc KARASSEV
 */
public class MainGameState extends BasicGameState {

    private AppGameContainer container;
    private main.Game game;

    private GameMap map;

    public MainGameState() {
        map = new GameMap();
    }

    @Override
    public int getID() {
        return 3;
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
        map.renderDivision(g);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if(container.getInput().isKeyDown(Input.KEY_LEFT)) {
            onLeft();
        }
        if(container.getInput().isKeyDown(Input.KEY_UP)) {
            onUp();
        }
        if(container.getInput().isKeyDown(Input.KEY_RIGHT)) {
            onRight();
        }
        if(container.getInput().isKeyDown(Input.KEY_DOWN)) {
            onDown();
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_ENTER:
                onEnter();
                break;
            case Input.KEY_F1:
                onF1();
                break;
            case Input.KEY_ESCAPE:
                onEscape();
                break;
            /*case Input.KEY_UP:
                onUp();
                break;
            case Input.KEY_LEFT:
                onLeft();
                break;
            case Input.KEY_RIGHT:
                onRight();
                break;
            case Input.KEY_DOWN:
                onDown();
                break;*/
            default:
        }
    }

    private void onEscape() {
        try {
            leave(container, game);
            game.enterState(4, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));;
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private void onEnter() {
        try {
            leave(container, game);
            System.exit(0);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private void onF1() {
        Preferences.changeColors();
    }

    private void onUp() {
        map.moveHeroUp();
    }

    private void onLeft() {
        map.moveHeroLeft();
    }

    private void onRight() {
        map.moveHeroRight();
    }

    private void onDown() {
        map.moveHeroDown();
    }
}
