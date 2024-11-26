package project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainApplication extends JFrame implements KeyListener {
    private JPanel              contentpane;
    private JLabel              drawpane;
    private JComboBox           combo;
    private JToggleButton       []tb;
    private ButtonGroup         bgroup;
    private JButton             startButton, optionButton, rankButton;
    private JTextField          scoreText;
    private ImageIcon           backgroundImg;
    private SoundEffect         themeSound;
    private BowlLabel           bowlLabel;

    private MainApplication currentFrame;

    private int framewidth  = Constants.FRAME_WIDTH;
    private int frameheight = Constants.FRAME_HEIGHT;

    public static void main(String[] args) {
        new MainApplication();
    }

    public MainApplication()
    {
        setTitle("Ramen Hunter");
        setSize(framewidth,frameheight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        currentFrame = this;

        contentpane = (JPanel)getContentPane();
        contentpane.setLayout( new BorderLayout() );

        AddComponents();
        setVisible(true);

    }

    public void AddComponents()
    {
//      backgroundImg = new ImageIcon(Constants.FILE_BGGAME).resize(framewidth, frameheight);
//      drawpane.setIcon(backgroundImg);
        drawpane = new JLabel();
        drawpane.setLayout(null);

        themeSound = new SoundEffect(Constants.FILE_SONG);
        themeSound.playLoop();
        themeSound.setVolume(0.4f);

        bowlLabel = new BowlLabel(currentFrame);
        drawpane.add(bowlLabel);

        contentpane.add(drawpane, BorderLayout.CENTER);
        drawpane.repaint();

        addKeyListener(this);
        ToppingThread();

    }

    public void ToppingThread()
    {
        Thread toppingSpawner = new Thread(){
            public void run(){
                while (true){
                    ToppingLabel toppingLabel = new ToppingLabel(currentFrame);
                    drawpane.add(toppingLabel);
                    drawpane.repaint();

                    //Start a new thread to animate each topping's fall
                    Thread toppingFallingThread = new Thread(){
                        public void run()
                        {
                            while (!toppingLabel.isGet() && toppingLabel.curY < frameheight) 
                            {
                                //Move topping down
                                toppingLabel.curY += 20;
                                toppingLabel.setLocation(toppingLabel.curX, toppingLabel.curY);
                                //Check for intersection
                                if (bowlLabel.getBounds().intersects(toppingLabel.getBounds())) 
                                {
                                    toppingLabel.setGet(true);
                                    toppingLabel.playGetSound();
                                    drawpane.remove(toppingLabel);
                                    drawpane.repaint();
                                    break;
                                }
                                try{
                                    Thread.sleep(toppingLabel.speed); //control falling speed
                                } catch (InterruptedException e){
                                    e.printStackTrace();
                                }
                            }

                            //cleanup when topping exits the screen
                            if (!toppingLabel.isGet()) {
                                drawpane.remove(toppingLabel);
                                drawpane.repaint();
                            }
                        }
                    };
                
                    toppingFallingThread.start();

                    //Wait before spawning the next topping
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        toppingSpawner.start();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()){
            case KeyEvent.VK_RIGHT :
                bowlLabel.moveRight();
                break;
            case KeyEvent.VK_LEFT:
                bowlLabel.moveLeft();
                break;

        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

}



