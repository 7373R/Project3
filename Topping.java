package project3;

import javax.swing.*;
import java.util.Random;

class BowlLabel extends JLabel
{
    private MainApplication     parantFrame;
    private project3.ImageIcon  bowlImage;
    private boolean             horizontalMove = true, verticalMove = true;

    private int width       = Constants.BOWL_WIDTH;
    private int height      = Constants.BOWL_HEIGHT;
    private int curY        = 500;
    private int curX        = 390;
    private int speed       = 500;

    public BowlLabel(MainApplication pf){
        parantFrame = pf;

        bowlImage = new project3.ImageIcon(Constants.FILE_BOWL).resize(width, height);
        setIcon(bowlImage);
        setBounds(curX, curY, width , height - 70);
    }

//    public void setMoveConditions(boolean hm, boolean vm){
//        horizontalMove = hm;
//        verticalMove   = vm;
//    }

    public void setMoveConditions(int x, int y, boolean hm, boolean vm){
        curX = x; curY = y;
        setBounds(curX, curY, width, height-70);
        //setMoveConditions(hm, vm);
    }

    public void moveLeft() {
        if (curX > 0){
            setMoveConditions(curX-20, curY, horizontalMove, verticalMove);
        }
    }

    public void moveRight() {
        if (curX < Constants.FRAME_WIDTH - width){
            setMoveConditions(curX+20, curY, horizontalMove, verticalMove);
        }
    }

    

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class ToppingLabel extends JLabel
{
    private         MainApplication         parentFrame;
    private         final int               type; // 0 = bad item, 1 = good item
    private         final int               orders;
    protected       project3.ImageIcon      toppingImg;
    private         SoundEffect             getSound;
    private         SoundEffect             failesSound;
    private         boolean                 get;

    private     String []   imageFiles  = { Constants.FILE_EGG, Constants.FILE_PORK, Constants.FILE_SW,
                                            Constants.FILE_SHRIMP1, Constants.FILE_SHRIMP2, Constants.FILE_NARUTO,
                                            Constants.FILE_BOMB, Constants.FILE_POO};
    private     String []   soundFile   = { Constants.FILE_SFX_GET, Constants.FILE_SFX_FAIL};
    protected   int    []   getPoints   = { 1, -1 };

    private int width        = Constants.TOPPING_WIDTH;
    private int height       = Constants.TOPPING_HEIGHT;
    private int startY       = 0;
    protected int curX;
    protected int curY;
    protected int speed      = 200;

    public ToppingLabel(MainApplication pf)
    {
        Random rand = new Random();
        parentFrame = pf;
        get = false;
        curX = rand.nextInt(10, parentFrame.getWidth()-100);
        curY = startY;

        orders = rand.nextInt(8);
        if (orders <= 5) type = 0; // Edit form 6 to 5
        else type = 1;
        toppingImg = new project3.ImageIcon(imageFiles[orders]).resize(width, height);
        setIcon(toppingImg);

        setBounds(curX, curY, width, height-30);
        getSound   = new SoundEffect(soundFile[type]);
    }

    public void playGetSound()          { getSound.playOnce(); }
    public int getPoint() {
        return getPoints[type];
    }
    public boolean isGet() {
        return get;
    }

    public void setGet(boolean get) {
        this.get = get;
    }
}


public class Topping extends Thread{
    

}
