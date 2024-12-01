// Ratchaya Haboonmee     ID 6613117
// Khunpas Chiewsakul     ID 6613248
// Pornphipat Pholprueksa ID 6613258
package project3;

import javax.swing.*;
import java.util.Random;

class BowlLabel extends JLabel {
    private game_MainApplication parantFrame;
    private project3.ImageIcon bowlImage;
    private boolean horizontalMove = true, verticalMove = true;

    private int width = Constants.BOWL_WIDTH;
    private int height = Constants.BOWL_HEIGHT;
    private int curY = Constants.FRAME_HEIGHT - Constants.BOWL_HEIGHT;
    private int curX = 390;

     @Override
    public int getWidth() {
        return width;
    }
    
    public int getCurY() {
        return curY;
    }

    public BowlLabel(game_MainApplication pf, String imageFile) {
        parantFrame = pf;

        bowlImage = new project3.ImageIcon(imageFile).resize(width, height);
        System.out.println(imageFile);

        setIcon(bowlImage);
        setBounds(curX, curY, width, height - 70);
    }

    // public void setMoveConditions(boolean hm, boolean vm){
    // horizontalMove = hm;
    // verticalMove = vm;
    // }

    public void setMoveConditions(int x, int y, boolean hm, boolean vm) {
        curX = x;
        curY = y;
        setBounds(curX, curY, width, height - 70);
        // setMoveConditions(hm, vm);
    }

    public void moveLeft() {
        if (curX > 0) {
            setMoveConditions(curX - 20, curY, horizontalMove, verticalMove);
        }
    }

    public void moveRight() {
        if (curX < Constants.FRAME_WIDTH - width) {
            setMoveConditions(curX + 20, curY, horizontalMove, verticalMove);
        }
    }

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class ToppingLabel extends JLabel {
    private game_MainApplication parentFrame;
    private final int type; // 0 = bad item, 1 = good item
    private final int orders;
    protected ImageIcon toppingImg;
    private SoundEffect getSound;
    private boolean get;

    private String[] imageFiles = { Constants.FILE_EGG, Constants.FILE_PORK, Constants.FILE_SW,
            Constants.FILE_SHRIMP1, Constants.FILE_SHRIMP2, Constants.FILE_NARUTO,
            Constants.FILE_BOMB, Constants.FILE_POO,
            Constants.FILE_PLUSCLOCK, Constants.FILE_MINUSCLOCK,
            Constants.FILE_rEGG, Constants.FILE_rPORK, Constants.FILE_rSHRIMPS };
    private String[] soundFile = { Constants.FILE_SFX_GET, Constants.FILE_SFX_FAIL,
            Constants.FLIE_TIME_PLUS, Constants.FILE_TIME_LOSS, Constants.FILE_SFX_EXPLODE };
    protected int[] getPoints = { 1, -1, 0, 0, 0 };

    private int width = Constants.TOPPING_WIDTH;
    private int height = Constants.TOPPING_HEIGHT;
    private int startY = 0;
    protected int curX;
    protected int curY;
    protected int speed = 200;

    public ToppingLabel(game_MainApplication pf) {
        Random rand = new Random();
        parentFrame = pf;
        get = false;
        curX = rand.nextInt(10, parentFrame.getWidth() - 100);
        curY = startY;

        orders = rand.nextInt(13);
        if (orders <= 5)
            type = 0;
        else if (orders == 6)
            type = 4;
        else if (orders == 7 || orders >= 10 && orders <= 12)
            type = 1;
        else if (orders == 8)
            type = 2;
        else
            type = 3;

        toppingImg = new ImageIcon(imageFiles[orders]).resize(width, height);
        setIcon(toppingImg);

        setBounds(curX, curY, width, height);
        getSound = new SoundEffect(soundFile[type]);
    }

    public void playGetSound() {
        getSound.playOnce();
    }

    public int getPoint() {
        return getPoints[type];
    }

    public boolean isGet() {
        return get;
    }

    public void setGet(boolean get) {
        this.get = get;
    }

    public int getOrder() {
        return orders;
    }
}

public class game_Topping extends Thread {

}
