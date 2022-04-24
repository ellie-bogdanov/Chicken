

import java.awt.*;

public class Bullet {
    CustomRectangle base;
    Oval edge;
    boolean threadOpened;
    private boolean isPlayerBullet;
    final int BULLET_WIDTH = 10;
    final int BULLET_HEIGHT = 30;

    final int EGG_BULLET_WIDTH = 20;
    final int EGG_BULLET_HEIGHT = 30;


    
    public Bullet(int x, int y, boolean isPlayerBullet) {
        this.isPlayerBullet = isPlayerBullet;
        this.threadOpened = false;
        if(isPlayerBullet){
            this.base = new CustomRectangle(x, y, BULLET_WIDTH, BULLET_HEIGHT, Color.YELLOW);
            this.edge = new Oval(x, (y - 10), BULLET_WIDTH, BULLET_HEIGHT,Color.YELLOW);
        }
        else {
            this.edge = new Oval(x, y, EGG_BULLET_WIDTH, EGG_BULLET_HEIGHT, Color.WHITE);
        }
    }


    public void paint(Graphics g) {
        if(this.isPlayerBullet){
            this.base.paint(g);
        }
        this.edge.paint(g);
    }

    public void moveUpFast() {
        this.base.moveUpFast();
        this.edge.moveUpFast();
    }

    public void moveDownFast() {
        this.edge.moveDownFast();
    }

    public CustomRectangle getBase() {
        return base;
    }

    public void setBase(CustomRectangle base) {
        this.base = base;
    }

    public Oval getEdge() {
        return edge;
    }

    public void setEdge(Oval edge) {
        this.edge = edge;
    }

    public int getBULLET_WIDTH() {
        return BULLET_WIDTH;
    }

    public int getBULLET_HEIGHT() {
        return BULLET_HEIGHT;
    }

    public boolean isThreadOpened() {
        return threadOpened;
    }

    public void setThreadOpened(boolean threadOpened) {
        this.threadOpened = threadOpened;
    }
}
