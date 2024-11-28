package project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent; // ADD
import java.awt.event.ActionListener; // ADD
 
public class MainApplication extends JFrame implements KeyListener {
    private JPanel              contentpane;
    private JLabel              drawpane, timerLabel, pointCountLabel;
    private ImageIcon           backgroundImg;
    private SoundEffect         themeSound;
    private BowlLabel           bowlLabel;

    private MainApplication currentFrame;

    private int framewidth = Constants.FRAME_WIDTH;
    private int frameheight = Constants.FRAME_HEIGHT;
    private int timeRemaining = 60;
    private boolean isPaused = false;
    private JFrame pauseFrame; // ADD
    private Timer countdownTimer; // ADD

    private int totalPoint = 0;
    private int adjustedSpeed = 0;

    ///////////////////////////////////////////////
    // Main function
    public static void main(String[] args) {
        System.out.println(Constants.PATH);
        new MainApplication();
    }
    //////////////////////////////////////////////

    public MainApplication() {
        setTitle("Ramen Hunter");
        setSize(framewidth, frameheight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        currentFrame = this;

        contentpane = (JPanel) getContentPane();
        contentpane.setLayout(new BorderLayout());

        AddComponents();
        setVisible(true);
        
    }
    
    // Add components to the frame
    public void AddComponents() {
        backgroundImg = new ImageIcon(Constants.FILE_BGGAME).resize(framewidth, frameheight);
        drawpane = new JLabel();
        drawpane.setIcon(backgroundImg);
        drawpane.setLayout(null);
        
        themeSound = new SoundEffect(Constants.FILE_SONG);
        themeSound.playLoop();
        themeSound.setVolume(0.4f);
        
        bowlLabel = new BowlLabel(currentFrame);
        drawpane.add(bowlLabel);
        
        contentpane.add(drawpane, BorderLayout.CENTER);
        drawpane.repaint();

        addKeyListener(this);
        startCountdownTimer(); // ADD
        AddTopping();
        countPoint(0);
    }

    public void countPoint(int point) {
        // Initialize the timer labe
        totalPoint += point;
        pointCountLabel = new JLabel("Point: " + totalPoint);
        pointCountLabel.setFont(new Font("Serif", Font.BOLD, 20));
        pointCountLabel.setBounds(10, 40, 100, 30);
        drawpane.add(pointCountLabel);
        drawpane.repaint();
    };

    // Countdown timer function
    private void startCountdownTimer() {

        // Initialize the timer label
        timerLabel = new JLabel("Time: " + timeRemaining);
        timerLabel.setFont(new Font("Serif", Font.BOLD, 20));
        timerLabel.setBounds(10, 10, 100, 30);
        drawpane.add(timerLabel);

        // Initialize the countdown timer
        countdownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                timerLabel.setText("Time: " + timeRemaining);
                if (timeRemaining <= 0) {
                    ((Timer) e.getSource()).stop();
                    pauseGame();
                    gameOver();
                }
            }
        });
        countdownTimer.start();
    }

    // Game over function
    private void gameOver() {
        JOptionPane.showMessageDialog(this, "Time's up! Game Over.");
        pauseGame();
        System.exit(0);
    }

    // Pause the game function
    public void pauseGame() {
        isPaused = !isPaused; // Toggle pause state
        if (isPaused) {
            if (timeRemaining > 0) {
                themeSound.stop();
                countdownTimer.stop(); // Pause the timer
                PauseFrame();
            } else {
                themeSound.stop();
                countdownTimer.stop(); // Pause the timer
            }

        } else {
            // timer.awake();
            themeSound.playLoop();
            countdownTimer.start(); // Resume the timer

            if (pauseFrame != null) {
                pauseFrame.dispose(); // Close pause frame
            }
        }
    }

    public void PauseFrame(){
        pauseFrame = new JFrame("Game Pause"); // Initialize pause frame
        pauseFrame.setSize(300, 200);
        pauseFrame.setLayout(new FlowLayout());
        pauseFrame.setLocationRelativeTo(null);
        pauseFrame.setVisible(true);

        // Resume button
        JButton resumeButton = new JButton("Resume");
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseGame(); // Resume the game
                pauseFrame.dispose(); // Close pause frame
            }
        });
        resumeButton.setSize(100, 50);
        
        // Main menu button
        JButton mainMenuButton = new JButton("Main Menu");
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themeSound.stop();
                countdownTimer.stop(); // Pause the timer
                pauseFrame.dispose(); // Close pause frame
                currentFrame.dispose(); // Close current frame
                System.exit(0); // Exit the program
            }
        });
        mainMenuButton.setSize(100, 50);

        // Display total points and remaining time
        JLabel totalPointLabel = new JLabel("Total Points: " + totalPoint);
        totalPointLabel.setFont(new Font("Serif", Font.BOLD, 20));
        totalPointLabel.setHorizontalAlignment(SwingConstants.CENTER);
            
        JLabel remainingTimeLabel = new JLabel("Remaining Time: " + timeRemaining);
        remainingTimeLabel.setFont(new Font("Serif", Font.BOLD, 20));
        remainingTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
                
        pauseFrame.setLayout(new GridLayout(4, 3));


        pauseFrame.add(totalPointLabel);
        pauseFrame.add(remainingTimeLabel);
        pauseFrame.add(resumeButton);
        pauseFrame.add(mainMenuButton);
        pauseFrame.setVisible(true);
    }

    // Key event handling
    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                bowlLabel.moveRight();
                break;
            case KeyEvent.VK_LEFT:
                bowlLabel.moveLeft();
                break;
            case KeyEvent.VK_ESCAPE:
                pauseGame();
                break;

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    // Start a new thread to spawn toppings
    public void AddTopping() {
        Thread toppingSpawner = new Thread() {
            public void run() {
                while (true) {
                    if (!isPaused) {
                        ToppingLabel toppingLabel = new ToppingLabel(currentFrame);
                        drawpane.add(toppingLabel);
                        drawpane.repaint();
                        ToppingFall(toppingLabel);
                        
                        // Wait before spawning the next topping
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        toppingSpawner.start();
    }
    
    // Start a new thread to animate each topping's falling
    public void ToppingFall(ToppingLabel toppingLabel) {
        Thread toppingFallingThread = new Thread() {
            public void run() {
                while (!toppingLabel.isGet() && toppingLabel.curY < frameheight - 150) {
                    if (!isPaused) {
                        // Move topping down
                        adjustedSpeed = Math.max(toppingLabel.speed - (60 - timeRemaining) / 10, 10); // Minimum //
                                                                                                      // speed cap
                        toppingLabel.curY += 20;
                        toppingLabel.setLocation(toppingLabel.curX, toppingLabel.curY);
                    } else {
                        toppingLabel.setLocation(toppingLabel.curX, toppingLabel.curY);
                    }
                    // Check for intersection
                    if (bowlLabel.getBounds().intersects(toppingLabel.getBounds())) {
                        toppingLabel.setGet(true);
                        toppingLabel.playGetSound();
                        drawpane.remove(toppingLabel);
                        drawpane.remove(pointCountLabel);
                        countPoint(toppingLabel.getPoint());

                        // Adjust time based on topping order
                        if (toppingLabel.getOrder() == 8) {
                            timeRemaining += 5; // Add 5 seconds
                        } else if (toppingLabel.getOrder() == 9) {
                            timeRemaining -= 5; // Subtract 5 seconds
                        }
                        timerLabel.setText("Time: " + timeRemaining); // Update timer display

                        drawpane.repaint();
                        break;
                    }
                    try {
                        Thread.sleep(adjustedSpeed); // control falling speed
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
    
                // cleanup when topping exits the screen
                if (!toppingLabel.isGet()) {
                    drawpane.remove(toppingLabel);
                    drawpane.repaint();
                }
            }
        };
        toppingFallingThread.start();
    }
}
