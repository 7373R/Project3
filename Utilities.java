package project3;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.util.Random;


/// Define the Constants
interface Constants{

    //--Resource file
    static final String LocalPath       = System.getProperty("user.dir");
    static final String PATH            = LocalPath + "/resources/";
    static final String FILE_BGMAIN     = PATH + "ramen_restaurant.jpg";
    static final String FILE_BGGAME     = PATH + "1.jpg";
    static final String FILE_BOWL       = PATH + "bowl.png";
    static final String FILE_T1         = PATH + "T1.png";
    static final String FILE_T2         = PATH + "T2.png";
    static final String FILE_T3         = PATH + "T3.png";
    static final String FILE_SONG       = PATH + "Ishikari Lore - Kevin MacLeod.wav";
    static final String FILE_SFX_GET_GOOD    = PATH + "success.wav";
    static final String FILE_SFX_GET_BAD     = PATH + "male_hurt7-48124.wav";

    //--Sizes
    static final int FRAME_WIDTH = 1000;
    static final int FRAME_HEIGHT = 700;

    static final int BOWL_WIDTH = 200;
    static final int BOWL_HEIGHT = 150;

    static final int TOPPING_WIDTH  = 100;
    static final int TOPPING_HEIGHT = 100;

}

//
class ImageIcon extends javax.swing.ImageIcon
{
    public ImageIcon(String fname)  { super(fname); }
    public ImageIcon(Image image)   { super(image); }

    public ImageIcon resize(int width, int height)
    {
        Image oldimg = this.getImage();
        // Use SCALE_DEFAULT mode to support gif
        Image newimg = oldimg.getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT);
        return new ImageIcon(newimg);
    }
}

/// Sound Setting
class SoundEffect{
    private Clip    clip;
    private FloatControl gainControl;
    
    //Pre-load all sound files
    public SoundEffect(String filename){
        try {
            java.io.File file = new java.io.File(filename);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        }catch (Exception e){ e.printStackTrace(); }
    }

    //Play the sound
    public void playOnce()              { clip.setMicrosecondPosition(0); clip.start(); }
    public void playLoop()              { clip.loop(Clip.LOOP_CONTINUOUSLY); }
    public void stop()                  { clip.stop(); }
    //Set the volume
    public void setVolume(float gain){
        if (gain < 0.0f)  gain = 0.0f;
        if (gain > 1.0f)  gain = 1.0f;
        float dB = (float)(Math.log(gain) / Math.log(10.0) * 20.0);
        gainControl.setValue(dB);
    }
}


////////////////////////////////////////////////////////////////////
/// Class for Bowl 
class BowlLabel extends JLabel
{
    private MainApplication     parantFrame;
    private ImageIcon           bowlImage;
    private boolean             horizontalMove = true, verticalMove = true;

    //Set position of the image and the size of the bowl
    private int width       = Constants.BOWL_WIDTH;
    private int height      = Constants.BOWL_HEIGHT;
    private int curY        = 500;
    private int curX        = 390;
    private int speed       = 500;

    //Constructor
    public BowlLabel(MainApplication pf){
        parantFrame = pf;

        bowlImage = new ImageIcon(Constants.FILE_BOWL).resize(width, height);
        setIcon(bowlImage);
        setBounds(curX, curY, width, height);
    }

//    public void setMoveConditions(boolean hm, boolean vm){
//        horizontalMove = hm;
//        verticalMove   = vm;
//    }

    public void setMoveConditions(int x, int y, boolean hm, boolean vm){
        curX = x; curY = y;
        setBounds(curX, curY, width, height);
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

/// Class for Topping
class ToppingLabel extends JLabel
{
    private     MainApplication     parentFrame;
    int                             type; // 0 = bad item, 1 = good item
    protected   ImageIcon           toppingImg;
    private     SoundEffect         getSound;
    private     boolean             get;


    String []    imageFiles  = { Constants.FILE_T1, Constants.FILE_T2 };
    String []    soundFile   = {Constants.FILE_SFX_GET_BAD, Constants.FILE_SFX_GET_GOOD}; // ADD
    int    []    getPoints   = { -1, 1 };

    private int width        = Constants.TOPPING_WIDTH;
    private int height       = Constants.TOPPING_HEIGHT;
    private int startY       = 0;
    protected int curX;
    protected int curY;
    protected int speed        = 500;

    public ToppingLabel(MainApplication pf)
    {
        Random rand = new Random();
        parentFrame = pf;
        get = false;
        curX = rand.nextInt(10, parentFrame.getWidth()-100);
        curY = startY;

        if (curX % 2 == 0)                   { type = 0; }
        else                                 { type = 1; }
        toppingImg = new ImageIcon(imageFiles[type]).resize(width, height);
        setIcon(toppingImg);

        setBounds(curX, curY, width, height);
        getSound   = new SoundEffect(soundFile[type]); // ADD
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



