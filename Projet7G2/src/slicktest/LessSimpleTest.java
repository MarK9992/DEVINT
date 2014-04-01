package slicktest;

import org.newdawn.slick.*;

public class LessSimpleTest extends BasicGame {
    private final int width = 500;
    private final int height = 500;
    private final boolean fullScreen = false;
    private final double charWidth = 4.5;
    private Image hh;
    private Image mine;
    private Sound good;
    private int step;

    public LessSimpleTest() {
        super("Titre de la fenetre");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.hh = new Image("../ressources/pic/HH.jpg");
        this.mine = new Image("../ressources/pic/mine.jpg");
        this.good = new Sound("../ressources/sound/good.wav");
        this.step = 0;
    }

    @Override
    public void render(GameContainer container, Graphics g)
            throws SlickException {
        switch (this.step) {
            case 0:
            default:
                this.sayHello(g);
                break;
            case 1:
                this.draw1(g);
                break;
            case 2:
                this.draw2(g);
                break;
            case 3:
                this.sayGoodBye(g);
                break;
        }
    }

    private void sayHello(Graphics g) {
        g.setColor(Color.white);
        String hello = "Hello World !";
        int x = (this.width / 2) - (int) (hello.getBytes().length * this.charWidth);
        g.drawString(hello, x, (this.height / 2));
        this.pushEnter(g);
    }

    private void draw1(Graphics g) {
        int x = this.width / 2 - hh.getWidth() / 2;
        int y = 0;
        g.drawImage(hh, x, y);
        g.setColor(Color.yellow);
        String site = "http://www.declaration-of-peace.com/fr/";
        x = (this.width / 2) - (int) (site.getBytes().length * this.charWidth);
        y = hh.getHeight();
        g.drawString(site, x, y);
        this.pushEnter(g);
    }

    private void draw2(Graphics g) {
        int x = this.width - mine.getWidth();
        g.drawImage(mine, x, 0);
        g.setColor(Color.cyan);
        String site = "http://9gag.com/";
        g.drawString(site, 0, mine.getHeight() / 2);
        this.pushEnter(g);
    }

    private void sayGoodBye(Graphics g) {
        float widthRect = 2;
        float heightRect = 30;
        for (int i = 0; i < width; i++) {
            g.setColor(new Color(i, i, i));
            g.fillRect(i, 0, widthRect, heightRect);
        }
        g.setColor(Color.white);
        String gb = "Good Bye World !";
        int x = (this.width / 2) - (int) (gb.getBytes().length * this.charWidth);
        g.drawString(gb, x, (this.height / 3));
        this.pushEnter(g);
    }

    private void pushEnter(Graphics g) {
        g.setColor(Color.red);
        String msg = "Push enter to continue !";
        int x = (this.width / 2) - (int) (msg.getBytes().length * this.charWidth);
        g.drawString(msg, x, (this.height - 20));
    }

    @Override
    public void update(GameContainer container, int delta)
            throws SlickException {
        Input in = container.getInput();
        if (in.isKeyPressed(Input.KEY_ENTER))
            if (step <= 2) {
                good.play();
                step++;
            } else
                System.exit(0);
        if (in.isKeyPressed(Input.KEY_ESCAPE))
            System.exit(0);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public static void main(String[] args) {
        try {
            LessSimpleTest test = new LessSimpleTest();
            AppGameContainer app = new AppGameContainer(test);
            app.setDisplayMode(test.getWidth(), test.getHeight(), test.isFullScreen());
            app.setShowFPS(false);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}