package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import states.*;

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
    private OptionMenuState options;
    private PauseMenuState pause;

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
        options = new OptionMenuState();
        pause = new PauseMenuState();
        container.setShowFPS(false);
        addState(login);
        addState(menu);
        addState(game);
        addState(options);
        addState(pause);
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
