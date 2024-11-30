package project3;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.awt.*;

interface Constants {
    // --Resource file

    static final String PATH = System.getProperty("user.dir") + "/resources/";
    // static final String PATH = "project3/src/main/java/project3/resources/";
    // static final String PATH = "src/main/java/project3/resources/";

    static final String FILE_ICON = PATH + "Icon.png";
    static final String FILE_BGMAIN = PATH + "ramen_restaurant.jpg";
    static final String FILE_BGGAME = PATH + "GameBG.png";
    static final String FILE_BOWL = PATH + "bowl.png";
    static final String FILE_EGG = PATH + "topping/Egg.png";
    static final String FILE_PORK = PATH + "topping/Pork.png";
    static final String FILE_SW = PATH + "topping/Seaweed.png";
    static final String FILE_SHRIMP1 = PATH + "topping/Shrimp.png";
    static final String FILE_SHRIMP2 = PATH + "topping/Shrimps.png";
    static final String FILE_NARUTO = PATH + "topping/Naruto.png";
    static final String FILE_rPORK = PATH + "topping/rPork.png";
    static final String FILE_rEGG = PATH + "topping/rEgg.png";
    static final String FILE_rSHRIMPS = PATH + "topping/rShrimps.png";
    static final String FILE_POO = PATH + "topping/Poo.png";
    static final String FILE_BOMB = PATH + "topping/Bomb.png";
    static final String FILE_PLUSCLOCK = PATH + "topping/Plus.png";
    static final String FILE_MINUSCLOCK = PATH + "topping/Minus.png";

    static final String FILE_SONG = PATH + "Ishikari Lore - Kevin MacLeod.wav";
    static final String FILE_SFX_GET = PATH + "achievement-bell.wav";
    static final String FILE_SFX_FAIL = PATH + "nope.wav";
    static final String FLIE_TIME_PLUS = PATH + "plusTime.wav";
    static final String FILE_TIME_LOSS = PATH + "video-game-points-lost.wav";
    static final String FILE_SFX_EXPLODE = PATH + "explosion-42132.wav";

    // --Sizes
    static final int FRAME_WIDTH = 1366;
    static final int FRAME_HEIGHT = 768;

    static final int BOWL_WIDTH = 200;
    static final int BOWL_HEIGHT = 200;

    static final int TOPPING_WIDTH = 100;
    static final int TOPPING_HEIGHT = 100;

}

class ImageIcon extends javax.swing.ImageIcon {
    public ImageIcon(String fname) {
        super(fname);
    }

    public ImageIcon(Image image) {
        super(image);
    }

    public ImageIcon resize(int width, int height) {
        Image oldimg = this.getImage();
        // Use SCALE_DEFAULT mode to support gif
        Image newimg = oldimg.getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT);
        return new ImageIcon(newimg);
    }
}

class SoundEffect {
    private Clip clip;
    private FloatControl gainControl;

    public SoundEffect(String filename) {
        try {
            java.io.File file = new java.io.File(filename);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playOnce() {
        clip.setMicrosecondPosition(0);
        clip.start();
    }

    public void playLoop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public void setVolume(float gain) {
        if (gain < 0.0f)
            gain = 0.0f;
        if (gain > 1.0f)
            gain = 1.0f;
        float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
        gainControl.setValue(dB);
    }
}
