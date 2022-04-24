import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Main extends JFrame implements KeyListener {
    private Spaceship spaceship;
    private JLabel title;
    private final static int WINDOW_WIDTH = 1400;
    public final static int WINDOW_HEIGHT = 900;
    private final static int STARTING_X = 0;
    private final static int STARTING_Y = 0;
    private ArrayList<Bullet> bulletList;
    private ArrayList<Bullet> eggList;


    public Main() {
        this.setVisible(true);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.spaceship = new Spaceship();
        this.bulletList = new ArrayList<>();
        this.eggList = new ArrayList<>();
        this.title = new JLabel("CHICKEN INVADERS JR.");

        MainContent mainContent = new MainContent(STARTING_X,STARTING_Y,WINDOW_WIDTH, WINDOW_HEIGHT, spaceship, bulletList, eggList, title);
        this.add(mainContent);
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case Spaceship.RIGHT:
                this.spaceship.setDirection(Spaceship.RIGHT);
                break;

            case Spaceship.RIGHT_CAPS:
            this.spaceship.setDirection(Spaceship.RIGHT);
            break;

            case Spaceship.LEFT:
                this.spaceship.setDirection(Spaceship.LEFT);
                break;
            case Spaceship.LEFT_CAPS:
            this.spaceship.setDirection(Spaceship.LEFT);
            break;

            case Spaceship.UP:
                this.spaceship.setDirection(Spaceship.UP);
                break;

            case Spaceship.UP_CAPS:
            this.spaceship.setDirection(Spaceship.UP);
            break;

            case Spaceship.DOWN:
                this.spaceship.setDirection(Spaceship.DOWN);
                break;
            case Spaceship.DOWN_CAPS:
            this.spaceship.setDirection(Spaceship.DOWN);
            break;
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyChar()) {
            case KeyEvent.VK_SPACE:
                if (!this.title.isVisible() || this.spaceship.getLife() != 0) {
                    Bullet bullet = new Bullet(this.spaceship.getHead().getX() + 15, this.spaceship.getHead().getY(), true);
                    this.bulletList.add(bullet);
                }



        }
    }


    public static void main(String[] args) {
        new Main();
    }

}
