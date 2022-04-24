import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MainContent extends JPanel {
    private Spaceship spaceship;
    private Chicken[][] chickens;
    private ArrayList<Bullet> bulletList;
    private ArrayList<Bullet> eggList;

    private final int REFRESH_RATE = 1;
    private final int SHOOTING_RANDOMNESS = 100;
    private ImageIcon background;
    private JLabel title;
    private JLabel controls;
    private JLabel titleStatus;
    private JLabel livesCount;
    private JButton pressPlay;
    private boolean isGameOver = false;
    private boolean isGameWon = false;
    private static boolean isIterating = false;

    private static final int CONTROLS_X = 450;
    private static final int CONTROLS_Y = 600;
    private static final int CONTROLS_WIDTH = 700;
    private static final int CONTROLS_HEIGHT = 200;
    private static final int CONTROLS_FONT_SIZE = 30;

    private final static int NUM_OF_CHICKENS = 7;
    private final static int NUM_OF_CHICKEN_ROWS = 3;
    private final int BG_X = -50;
    public static final int WINDOW_WIDTH = 1400;
    private static final int WINDOW_HEIGHT = 900;
    private final int TITLE_X = 200;
    private final int TITLE_Y = 20;
    private final int TITLE_WIDTH = 1300;
    private final int TITLE_HEIGHT = 200;


    private final int LIVES_COUNT_X = 1200;
    private final int LIVES_COUNT_Y = 10;
    private final int LIVES_COUNT_WIDTH = 300;
    private final int LIVES_COUNT_HEIGHT = 50;
    private final int LIVES_COUNT_FONT_SIZE = 20;

    private final int STATUS_Y = 300;
    private final int STATUS_X = 520;

    private final int TITLE_SIZE = 80;
    private final int BUTTON_X = 800;
    private final int BUTTON_Y = 200;
    private final int BUTTON_WIDTH = 200;
    private final int BUTTON_HEIGHT = 100;
    private final int BUTTON_SIZE = 40;
    private final int STARTING_X = 0;
    private final int STARTING_Y = 0;



    public MainContent(int x, int y, int width, int height, Spaceship spaceship, ArrayList bulletList, ArrayList eggList, JLabel title) {
        this.setLayout(null);
        this.setBounds(x, y, width, height);
        this.setDoubleBuffered(true);
        this.background = new ImageIcon("ChickenBackground.jpg");
        this.spaceship = spaceship;
        this.bulletList = bulletList;
        this.eggList = eggList;

        this.title = title;
        this.title.setBounds(TITLE_X,TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT);
        Font titleFont = new Font("Title Font", Font.BOLD, TITLE_SIZE);
        this.title.setFont(titleFont);
        this.title.setForeground(Color.YELLOW);
        this.add(title);

        Font controlFont = new Font("Control Font", Font.BOLD, CONTROLS_FONT_SIZE);
        this.controls = new JLabel("WASD for movement and space for shooting");        //VICTORY OR DEFEAT SCREEN
        this.controls.setBounds(CONTROLS_X, CONTROLS_Y, CONTROLS_WIDTH, CONTROLS_HEIGHT);
        this.controls.setFont(controlFont);
        this.controls.setForeground(Color.WHITE);
        this.controls.setVisible(true);
        this.add(controls);

        this.titleStatus = new JLabel("VICTORY");        //VICTORY OR DEFEAT SCREEN
        this.titleStatus.setBounds(STATUS_X,STATUS_Y, TITLE_WIDTH, TITLE_HEIGHT);
        this.titleStatus.setFont(titleFont);
        this.titleStatus.setForeground(Color.YELLOW);
        this.titleStatus.setVisible(false);
        this.add(titleStatus);

        this.pressPlay = new JButton("PLAY");
        pressPlay.setBounds(BUTTON_X, BUTTON_Y, BUTTON_WIDTH,BUTTON_HEIGHT);
        Font playFont = new Font("Play Font", Font.BOLD, BUTTON_SIZE);
        pressPlay.setFont(playFont);
        this.add(pressPlay);

        openingAct();


        this.livesCount = new JLabel("HP left: " + this.spaceship.getLife());
        livesCount.setBounds(LIVES_COUNT_X, LIVES_COUNT_Y, LIVES_COUNT_WIDTH, LIVES_COUNT_HEIGHT);
        livesCount.setForeground(Color.YELLOW);
        Font livesCountFont = new Font("Lives Count Font", Font.BOLD, LIVES_COUNT_FONT_SIZE);
        livesCount.setFont(livesCountFont);
        livesCount.setVisible(false);
        this.add(livesCount);

        this.chickens = new Chicken[NUM_OF_CHICKEN_ROWS][NUM_OF_CHICKENS];
        for (int i = 0; i < chickens.length; i++) {
            for (int j = 0; j < chickens[i].length; j++) {
                chickens[i][j] = new Chicken(WINDOW_WIDTH / 2);
                chickens[i][j].randomDestination();
            }
        }

        mainGameLoop();
    }


    public void openingAct() {
        this.pressPlay.addActionListener((event) -> {
            while (this.spaceship.getHead().getY() != this.spaceship.STARTING_POINT_Y) {
                if (this.spaceship.getHead().getY() != this.spaceship.STARTING_POINT_Y) {
                    this.spaceship.moveDown();
                }
                if (this.spaceship.getHead().getX() != this.spaceship.STARTING_POINT_X) {
                    this.spaceship.moveRight();
                }
            }
            this.title.setVisible(false);
            this.controls.setVisible(false);
            this.pressPlay.setVisible(false);
            this.livesCount.setVisible(true);
        });
    }


    public void takeDamage() {
        if (this.spaceship.getMainEngine().getColor() == Color.YELLOW) {
            this.spaceship.getMainEngine().setColor(Color.RED);
            this.spaceship.getLeftEngine().setColor(Color.RED);
            this.spaceship.getRightEngine().setColor(Color.RED);
        } else {
            this.spaceship.getMainEngine().setColor(Color.YELLOW);
            this.spaceship.getLeftEngine().setColor(Color.YELLOW);
            this.spaceship.getRightEngine().setColor(Color.YELLOW);
        }
        if (this.spaceship.getLife() > 0) {
            this.spaceship.setLife(this.spaceship.getLife() - 1);
            this.livesCount.setText("HP left: " + this.spaceship.getLife());
        }
        try {
            Thread.sleep(REFRESH_RATE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void playerHit() {
        for (Bullet eBullet : eggList) {
            if(eBullet != null) {
                Rectangle bulletObstacle = new Rectangle(eBullet.edge.getX(), eBullet.edge.getY(), eBullet.edge.getWidth(), eBullet.edge.getHeight());

                Rectangle spaceObstacle = new Rectangle(spaceship.getBody().getX(), spaceship.getBody().getY(), spaceship.getBody().getWidth(), spaceship.getBody().getHeight());
                if (spaceObstacle.intersects(bulletObstacle)) {
                    takeDamage();
                }
            }
        }
    }
    public void chickenHit() {
        for (int i = 0; i < chickens.length; i++) {
            for (int j = 0; j < chickens[i].length; j++) {
                for (Bullet bullet : bulletList) {
                    if (chickens[i][j] != null) {
                        if (chickens[i][j].checkBulletHit(bullet)) {
                            chickens[i][j].setLives(chickens[i][j].getLives() - 1);
                            if (chickens[i][j].getLives() == 0) {
                                chickens[i][j] = null;
                            }
                        }
                    }
                }
            }

        }
    }


    public void mainGameLoop() {
        new Thread(() -> {
            while (true) {
                if (title.isVisible()) {
                    this.spaceship.setDirection(Spaceship.NONE);
                }
                if (this.spaceship.getWings().getX() == STARTING_X) {
                    this.spaceship.moveRight();
                    continue;
                }
                if ((this.spaceship.getWings().getX() + this.spaceship.getWings().getWidth()) == WINDOW_WIDTH) {
                    this.spaceship.moveLeft();
                }
                if (!isGameWon) {
                    if (this.spaceship.getHead().getY() == STARTING_Y) {
                        this.spaceship.moveDown();
                    }
                }
                if (!isGameOver) {
                    if ((this.spaceship.getRightEngine().getY() + this.spaceship.getRightEngine().getHeight()) == WINDOW_HEIGHT) {
                        this.spaceship.moveUp();
                    }
                }

                if (!title.isVisible() && !isGameOver) {
                    switch (this.spaceship.getDirection()) {
                        case Spaceship.RIGHT:
                            this.spaceship.moveRight();
                            break;
                        case Spaceship.LEFT:
                            this.spaceship.moveLeft();
                            break;
                        case Spaceship.UP:
                            this.spaceship.moveUp();
                            break;
                        case Spaceship.DOWN:
                            this.spaceship.moveDown();
                            break;
                    }
                }

                for (Chicken[] chickens : this.chickens) {
                    for (Chicken chicken : chickens) {
                        if (chicken != null) {
                            if (chicken.checkCollision(this.spaceship)) {
                                takeDamage();
                            }
                        }
                        if (this.spaceship.getHead().getY() > 180) {
                            if (this.spaceship.getMainEngine().getColor() == Color.RED) {
                                this.spaceship.getMainEngine().setColor(Color.YELLOW);
                                this.spaceship.getLeftEngine().setColor(Color.YELLOW);
                                this.spaceship.getLeftEngine().setColor(Color.YELLOW);
                            }
                        }
                    }
                }

                for (Bullet bullet : bulletList) {
                    if ((bullet.getBase().getY() + bullet.getBase().getHeight()) != STARTING_Y) {
                        bullet.moveUpFast();
                    } else {
                        bullet = null;
                    }
                }

                if (!isGameOver) {
                    isIterating = true;
                    ArrayList<Bullet> toRemove = new ArrayList<>();
                    Random rnd = new Random();
                    int randomChicken = rnd.nextInt(this.chickens[0].length);
                    int permissionToShoot = rnd.nextInt(SHOOTING_RANDOMNESS);
                    int randomChickenRow = rnd.nextInt(this.chickens.length);
                    if(this.chickens[randomChickenRow][randomChicken] != null){
                        if(permissionToShoot == 10){
                            if(!title.isVisible()){
                                Bullet bullet = new Bullet(this.chickens[randomChickenRow][randomChicken].getX(),
                                        this.chickens[randomChickenRow][randomChicken].getY_ROW_1() + Chicken.HEIGHT + 10, false);
                                this.eggList.add(bullet);
                            }
                        }
                    }
                    for (Bullet eggBullet : eggList) {
                        if ((eggBullet.getEdge().getY() - eggBullet.getEdge().getHeight()) != Main.WINDOW_HEIGHT) {
                            eggBullet.moveDownFast();
                        } else {
                            toRemove.add(eggBullet);
                        }
                    }
                    eggList.removeAll(toRemove);
                }
                isIterating = false;

                


                if (this.spaceship.getLife() == 0) {
                    isGameOver = true;
                    gameOver();
                    this.titleStatus.setText("DEFEAT");
                    this.titleStatus.setVisible(true);
                }

                boolean isAllDead = true;
                for (Chicken[] chickens : this.chickens) {
                    for (Chicken chicken : chickens) {
                        if (chicken != null) {
                            isAllDead = false;
                            this.isGameWon = true;
                            break;
                        }
                    }
                }
                if (isAllDead) {
                    victory();
                    titleStatus.setVisible(true);
                }
                try {
                    chickenHit();
                } catch (Exception e) {
                }


                playerHit();

                repaint();

                
                try {
                    if (isGameOver) {
                        Thread.sleep(1);
                    } else {
                        Thread.sleep(this.spaceship.FAST_SPEED);
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void gameOver() {
        this.spaceship.getMainEngine().setColor(Color.BLACK);
        this.spaceship.getLeftEngine().setColor(Color.BLACK);
        this.spaceship.getRightEngine().setColor(Color.BLACK);
        if (this.spaceship.getHead().getY() < WINDOW_HEIGHT) {
            this.spaceship.moveDown();
        }
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void victory() {
        this.spaceship.getMainEngine().setColor(Color.WHITE);
        this.spaceship.getLeftEngine().setColor(Color.WHITE);
        this.spaceship.getRightEngine().setColor(Color.WHITE);
        this.spaceship.moveUp();
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.background.paintIcon(this, g,BG_X,STARTING_Y);
        this.spaceship.paint(g);

        if (!this.title.isVisible()) {
            int y = 50;
            for (int i = 0; i < chickens.length; i++) {
                for (int j = 0; j < chickens[i].length; j++) {
                    if (chickens[i][j] != null) {
                        chickens[i][j].getChicken().paintIcon(this, g, chickens[i][j].getX(), y);
                        try {
                            if(chickens[i][j].getX() >= chickens[i][j].getDestination() - Chicken.SPEED && chickens[i][j].getX() <= chickens[i][j].getDestination() + Chicken.SPEED) {
                                chickens[i][j].randomDestination();
                            }
                        } catch (Exception e) {
                        }

                        if (j < (chickens[i].length)) {
                            if (chickens[i][j] != null) {
                                if(chickens[i][j].getX() < chickens[i][j].getDestination()) {
                                    chickens[i][j].setX(chickens[i][j].getX() + Chicken.SPEED);
                                }
                                else {
                                    chickens[i][j].setX(chickens[i][j].getX() - Chicken.SPEED);
                                }

                            }
                        }
                    }
                }
                y += 150;

            }
        }

        for (Bullet bullet : bulletList) {
            bullet.paint(g);
        }
        if (!isIterating) {
            try {
                for (Bullet eBullet : eggList) {
                    eBullet.paint(g);
                }
            } catch (Exception e) {
            }
        }
    }


    public JLabel getTitle() {
        return title;
    }

    public void setTitle(JLabel title) {
        this.title = title;
    }
}
