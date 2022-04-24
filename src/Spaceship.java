import java.awt.*;

public class Spaceship {
    private int life;

    private Oval head;
    private CustomRectangle body;
    private Oval wings;
    private Oval below;
    private Oval upRightGun;
    private Oval upLeftGun;
    private Oval downRightGun;
    private Oval downLeftGun;
    private CustomRectangle rightEngine;
    private CustomRectangle leftEngine;
    private CustomRectangle mainEngine;
    private Rectangle collision;
    public static final char RIGHT = 'd';
    public static final char LEFT = 'a';
    public static final char UP = 'w';
    public static final char DOWN = 's';
    public static final char NONE = 'x';

    public static final char RIGHT_CAPS = 'D';
    public static final char LEFT_CAPS = 'A';
    public static final char UP_CAPS = 'W';
    public static final char DOWN_CAPS = 'S';
    public static final char NONE_CAPS = 'X';

    char direction;
    final int STARTING_POINT_X = 680;
    final int STARTING_POINT_Y = 620;
    final int FAST_SPEED = 3;


    final int DIVISION = 2;
    final int MULTIPLICATION = 2;
    final int BODY_X = 500;
    final int BODY_Y = 300;
    final int BODY_WIDTH = 40;
    final int BODY_HEIGHT = 80;
    final int GUN_WIDTH = 10;
    final int GUN_HEIGHT = 40;
    final int GUN_DISTANCE_FROM_FRONT = 20;
    final int DOWN_GUN_MARGIN = 30;
    final int DOWN_GUN_HEIGHT = 340;
    final int ENGINE_WIDTH = 30;
    final int ENGINE_HEIGHT = 20;
    final int ENGINE_Y = 400;
    final int MAIN_ENGINE_X = 505;
    final int MAIN_ENGINE_Y = 350;
    final int WINGS_MARGIN = 60;
    final int WINGS_HEIGHT = 50;
    final int WINGS_Y = 355;
    final int BELOW_X = 460;
    final int BELOW_Y = 330;
    final int BELOW_WIDTH = 120;
    private static final int STARTING_LIFES = 100;

    public Oval getHead() {
        return head;
    }

    public Oval getWings() {
        return wings;
    }

    public Oval getBelow() {
        return below;
    }

    public Oval getUpRightGun() {
        return upRightGun;
    }

    public Oval getUpLeftGun() {
        return upLeftGun;
    }

    public Oval getDownLeftGun() {
        return downLeftGun;
    }

    public Oval getDownRightGun() {
        return downRightGun;
    }

    public CustomRectangle getRightEngine() {
        return rightEngine;
    }

    public CustomRectangle getLeftEngine() {
        return leftEngine;
    }

    public CustomRectangle getMainEngine() {
        return mainEngine;
    }

    public CustomRectangle getBody() {
        return body;
    }
    public Spaceship() {
        this.life = STARTING_LIFES;

        this.direction = NONE;
        this.head = new Oval(BODY_X,BODY_Y - BODY_HEIGHT/DIVISION,BODY_WIDTH,BODY_HEIGHT, Color.GRAY);
        this.body = new CustomRectangle(BODY_X, BODY_Y, BODY_WIDTH, BODY_HEIGHT, Color.GRAY);
        this.upRightGun = new Oval(BODY_X + BODY_WIDTH, BODY_Y - GUN_DISTANCE_FROM_FRONT,
                GUN_WIDTH, GUN_HEIGHT, Color.RED);
        this.upLeftGun = new Oval(BODY_X - GUN_WIDTH, BODY_Y - GUN_DISTANCE_FROM_FRONT,
                GUN_WIDTH, GUN_HEIGHT, Color.RED);
        this.downRightGun = new Oval(BODY_X + BODY_WIDTH + DOWN_GUN_MARGIN, DOWN_GUN_HEIGHT,
                GUN_WIDTH, GUN_HEIGHT, Color.RED);
        this.downLeftGun = new Oval(BODY_X - GUN_WIDTH - DOWN_GUN_MARGIN, DOWN_GUN_HEIGHT,
                GUN_WIDTH, GUN_HEIGHT, Color.RED);
        this.wings = new Oval(BODY_X - WINGS_MARGIN,WINGS_Y,
                BODY_WIDTH + WINGS_MARGIN * MULTIPLICATION,WINGS_HEIGHT, Color.GRAY);
        this.rightEngine = new CustomRectangle(BODY_X + BODY_WIDTH, ENGINE_Y,
                ENGINE_WIDTH, ENGINE_HEIGHT, Color.YELLOW);
        this.leftEngine = new CustomRectangle(BODY_X - ENGINE_WIDTH, ENGINE_Y, ENGINE_WIDTH, ENGINE_HEIGHT, Color.YELLOW);
        this.mainEngine = new CustomRectangle(MAIN_ENGINE_X, MAIN_ENGINE_Y, ENGINE_WIDTH, ENGINE_HEIGHT, Color.YELLOW);
        this.below = new Oval(BELOW_X, BELOW_Y, BELOW_WIDTH, WINGS_HEIGHT, Color.DARK_GRAY);
    }


    public void moveRight() {
        this.below.moveRight();
        this.body.moveRight();
        this.upRightGun.moveRight();
        this.upLeftGun.moveRight();
        this.head.moveRight();
        this.downLeftGun.moveRight();
        this.downRightGun.moveRight();
        this.leftEngine.moveRight();
        this.rightEngine.moveRight();
        this.wings.moveRight();
        this.mainEngine.moveRight();
    }


    public void moveLeft() {
        this.below.moveLeft();
        this.body.moveLeft();
        this.upRightGun.moveLeft();
        this.upLeftGun.moveLeft();
        this.head.moveLeft();
        this.downLeftGun.moveLeft();
        this.downRightGun.moveLeft();
        this.leftEngine.moveLeft();
        this.rightEngine.moveLeft();
        this.wings.moveLeft();
        this.mainEngine.moveLeft();
    }


    public void moveDown() {
        this.below.moveDown();
        this.body.moveDown();
        this.upRightGun.moveDown();
        this.upLeftGun.moveDown();
        this.head.moveDown();
        this.downLeftGun.moveDown();
        this.downRightGun.moveDown();
        this.leftEngine.moveDown();
        this.rightEngine.moveDown();
        this.wings.moveDown();
        this.mainEngine.moveDown();
    }


    public void moveUp() {
        this.below.moveUp();
        this.body.moveUp();
        this.upRightGun.moveUp();
        this.upLeftGun.moveUp();
        this.head.moveUp();
        this.downLeftGun.moveUp();
        this.downRightGun.moveUp();
        this.leftEngine.moveUp();
        this.rightEngine.moveUp();
        this.wings.moveUp();
        this.mainEngine.moveUp();
    }


    protected void paint(Graphics g) {
        this.below.paint(g);
        this.body.paint(g);
        this.upRightGun.paint(g);
        this.upLeftGun.paint(g);
        this.head.paint(g);
        this.downLeftGun.paint(g);
        this.downRightGun.paint(g);
        this.leftEngine.paint(g);
        this.rightEngine.paint(g);
        this.wings.paint(g);
        this.mainEngine.paint(g);

    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }
}
