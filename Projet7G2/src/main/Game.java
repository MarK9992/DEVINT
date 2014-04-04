package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import states.LoginState;
import states.MainGameState;
import states.MainMenuState;

/**
 * Created by Marc KARASSEV on 25/03/14.
 *
 * @author Marc KARASSEV
 */
public class Game extends StateBasedGame {
    public static final int FRAMEWIDTH = 800;
    public static final int FRAMEHEIGHT = 600;
    public static final int TILE = 50;
    public static final int TARGETFRAMERATE = 60;

    private AppGameContainer container;
    private LoginState login;
    private MainMenuState menu;
    private MainGameState game;

    public Game() {
        super("Retrouve ton chemin !");
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        if (gameContainer instanceof AppGameContainer) {
            container = (AppGameContainer) gameContainer;
        }
        login = new LoginState();
        menu = new MainMenuState();
        game = new MainGameState();
        container.setShowFPS(false);
        addState(login);
        addState(menu);
        addState(game);
    }

    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(new Game());
            container.setDisplayMode(FRAMEWIDTH, FRAMEHEIGHT, false);
            container.setTargetFrameRate(TARGETFRAMERATE);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
