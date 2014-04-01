package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.LoginState;

/**
 * Created by Marc KARASSEV on 25/03/14.
 *
 * @author Marc KARASSEV
 */
public class Game extends StateBasedGame {
    public static final int FRAMEWIDTH = 800;
    public static final int FRAMEHEIGHT = 600;
    public static final int TARGETFRAMERATE = 60;

    private LoginState login;
    private AppGameContainer container;

    public Game() {
        super("Retrouve ton chemin !");
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        if (gameContainer instanceof AppGameContainer) {
            container = (AppGameContainer) gameContainer;
        }
        login = new LoginState();
        container.setShowFPS(false);
        addState(login);
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
