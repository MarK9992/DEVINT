package states;

import main.*;
import main.Game;
import map.GameMap;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
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
        map.getDivision().render(g);
        map.getHero().draw(g);
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
            case Input.KEY_F1:
                onF1();
                break;
            case Input.KEY_UP:
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
                break;
            default:
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
        map.getHero().moveUp();
    }

    private void onLeft() {
        map.getHero().moveLeft();
    }

    private void onRight() {
        map.getHero().moveRight();
    }

    private void onDown() {
        map.getHero().moveDown();
    }
}
