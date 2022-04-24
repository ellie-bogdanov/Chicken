import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Chicken {
    private int lives;
    private int x;
    private final int Y_ROW_1 = 50;
    private final int Y_ROW_2 = 200;
    private ImageIcon chicken;
    
    public final static int SPEED = 3;
    public final static int WIDTH = 130;
    public final static int HEIGHT = 130;
    private int destination;


    public Chicken(int x) {
        this.lives = 150;
        this.x = x;
        this.chicken = new ImageIcon(new ImageIcon("chicken.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT));

    }

    public int randomDestination() {
        Random rnd = new Random();
        destination = rnd.nextInt(MainContent.WINDOW_WIDTH);
        return destination;
    }

    public int getDestination() {
        return destination;
    }

    public void paint(Graphics g, MainContent mainContent) {
        this.chicken.paintIcon(mainContent ,g, this.x, this.Y_ROW_1);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY_ROW_1() {
        return Y_ROW_1;
    }

    public int getY_ROW_2() {
        return Y_ROW_2;
    }

    public ImageIcon getChicken() {
        return chicken;
    }

    public void setChicken(ImageIcon chicken) {
        this.chicken = chicken;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public boolean checkCollision(Spaceship spaceship) {
        boolean collision = false;
        Rectangle spaceObstacle = new Rectangle(spaceship.getHead().getX(),
                spaceship.getHead().getY(), spaceship.getHead().getWidth(), spaceship.getHead().getHeight());
        Rectangle chickenObstacle = new Rectangle(this.x, this.Y_ROW_1, this.chicken.getIconWidth(), this.chicken.getIconHeight());
        if (spaceObstacle.intersects(chickenObstacle)) {
            collision = true;
        }
        return collision;
    }

    public boolean checkBulletHit(Bullet bullet) {
        boolean collision = false;
        Rectangle bulletObstacle = new Rectangle(bullet.edge.getX(),
                bullet.edge.getY(), bullet.edge.getWidth(), bullet.edge.getHeight());
        Rectangle chickenObstacle = new Rectangle(this.x, this.Y_ROW_1, this.chicken.getIconWidth(), this.chicken.getIconHeight());
        if (bulletObstacle.intersects(chickenObstacle)) {
            collision = true;
        }
        return collision;
    }


}
