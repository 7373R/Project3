// Ratchaya Haboonmee     ID 6613117
// Khunpas Chiewsakul     ID 6613248
// Pornphipat Pholprueksa ID 6613258
package project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Random;

public class menu_MainApplication {

    public static void main(String[] args) {
        System.out.println(Constants.PATH);
        new MainMenu();
    }
}

class MainMenu extends JFrame {

    private final int frameWidth = 1366, frameHeight = 768;

    private CardLayout cardLayout, cardLayout_2;

    private MainMenu currentFrame;
    // private JLabel BGLabel;
    private JPanel bgPanel;

    // private JPanel cardPanel;
    private JPanel mainPanel;
    private JPanel optionPanel;
    private JPanel creditPanel;
    private JPanel radioPanel;
    private JPanel starterPanel;

    private JLabel optionLabel;
    private JLabel volumeLabel;
    private JLabel musicLabel;
    private JLabel creditsLabel;
    private JLabel logoLabel;

    private JLabel bowlPreview;

    private JComboBox musicCombo;

    private JButton playButton;
    private JButton optionButton;
    private JButton exitButton;
    private JButton creditButton;
    private JButton BackButton_1;
    private JButton BackButton_2;
    private JButton BackButton_3;
    private JButton nextButton;

    private ButtonGroup group;

    private String bowlImage;

    private JSlider volumeSlider;

    private menu_SoundEffects music;
    private ImageIcon iconImg;
    private ImageIcon logoImg;

    private final Font titleFont = new Font("Malgun Gothic", Font.BOLD, 74);
    private final Font largeFont = new Font("Arial", Font.BOLD, 48);
    private final Font mediumFont = new Font("Arial", Font.BOLD, 32);
    private final Font smallFont = new Font("Arial", Font.BOLD, 24);

    private final Dimension buttonSize = new Dimension(250, 85);
    private final Dimension panelMaxSize = new Dimension(900, 580);
    private final Dimension sliderSize = new Dimension(650, 100);

    public MainMenu() {
        // Set up the JFrame
        setTitle("Game Main Menu");
        setSize(frameWidth, frameHeight); // Window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setResizable(false);

        currentFrame = this;

        // Play music
        if (music != null) {
            music.stop();
        }
        music = new menu_SoundEffects(MyConstants.FILE_MUSIC1);

        cardLayout = new CardLayout();

        // BackGround Label
        // BGImg = new MyImageIcon(MyConstants.FILE_BG).resize(frameWidth, frameHeight);
        bgPanel = new MyPanel(MyConstants.FILE_BG);
        bgPanel.setLayout(cardLayout);
        bgPanel.setBounds(getCenterPosX(500), getCenterPosY(580), 500, 580);

        iconImg = new ImageIcon(MyConstants.FILE_ICON).resize(64, 64);
        setIconImage(iconImg.getImage());

        // BGLabel = new JLabel();
        // BGLabel.setIcon(BGImg);
        // //BGLabel.setLayout(new BoxLayout(BGLabel, BoxLayout.Y_AXIS));
        // BGLabel.setLayout(null);
        // Card Layout
        // cardPanel = new MyPanel(MyConstants.FILE_PANELBG);
        // cardPanel = new JPanel();
        // //cardPanel.setMaximumSize(panelMaxSize);
        // //cardPanel.setBounds(getCenterPosX(500), getCenterPosY(580), 500, 580);
        // //cardPanel.setLayout(cardLayout);
        // cardPanel.setOpaque(false);
        // test();
        openMainPanel();
        openOptionPanel();
        openCreditPanel();
        openStarterPanel();

        bgPanel.add(mainPanel, "mainPanel");
        bgPanel.add(optionPanel, "optionPanel");
        bgPanel.add(creditPanel, "creditPanel");
        bgPanel.add(starterPanel, "starterPanel");

        // cardPanel.add(mainPanel, "mainPanel");
        // cardPanel.add(optionPanel, "optionPanel");
        // cardPanel.add(creditPanel, "creditPanel");
        // cardPanel.add(creditButton);
        // BGLabel.add(Box.createVerticalStrut(70)); // Spacer
        // BGLabel.add(cardPanel);
        // bgPanel.add(cardPanel);
        // currentFrame.add(BGLabel);
        currentFrame.add(bgPanel);
        validate();

        // Make visible
        setVisible(true);
    }

    public void openMainPanel() {
        // Main Menu panel
        // mainPanel = new MyPanel(MyConstants.FILE_PANELBG);
        mainPanel = new JPanel();
        mainPanel.setMaximumSize(panelMaxSize);
        // mainPanel.setPreferredSize(panelMaxSize);
        // mainPanel.setBounds(getCenterPosX(500), getCenterPosY(580), 500, 580);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false);

        // Logo Label
        logoImg = new ImageIcon(MyConstants.FILE_LOGO).resize(500, 250);
        logoLabel = new JLabel(new ImageIcon(logoImg.getImage()));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Title Label
        // titleLabel = new OutlineLabel("RAMEN RUSH", Color.ORANGE, Color.BLACK, 4);
        // titleLabel.setFont(titleFont);
        // titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Play Button
        // playButton = new JButton();
        // playButton.setIcon(new
        // MyImageIcon(MyConstants.FILE_PLAY).resize(buttonSize));
        // playButton.setPressedIcon(new
        // MyImageIcon(MyConstants.FILE_PLAYHOVER).resize(buttonSize));
        // playButton.setContentAreaFilled(false);
        // playButton.setBorderPainted(false);
        // playButton.setFocusPainted(false);
        playButton = new MyButton(MyConstants.FILE_PLAY, MyConstants.FILE_PLAYPRESSED, buttonSize);
        playButton.setOpaque(false);
        playButton.setMaximumSize(buttonSize);
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.addActionListener(e -> {

            cardLayout.show(bgPanel, "starterPanel");

            // game_MainApplication.mainGame();
            // currentFrame.dispose();
            // music.stop();
            // music = null;
        });

        // Options Button
        optionButton = new MyButton(MyConstants.FILE_OPTION, MyConstants.FILE_OPTIONPRESSED, buttonSize);
        optionButton.setOpaque(false);
        optionButton.setMaximumSize(buttonSize);
        optionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionButton.addActionListener(e -> {
            cardLayout.show(bgPanel, "optionPanel");
        });

        // Credits Button
        creditButton = new MyButton(MyConstants.FILE_CREDITS, MyConstants.FILE_CREDITSPRESSED, buttonSize);
        creditButton.setOpaque(false);
        creditButton.setMaximumSize(buttonSize);
        creditButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        creditButton.addActionListener(e -> {
            // cardLayout.show(cardPanel, "creditPanel");
            cardLayout.show(bgPanel, "creditPanel");
        });

        // Exit Button
        exitButton = new MyButton(MyConstants.FILE_EXIT, MyConstants.FILE_EXITPRESSED, buttonSize);
        exitButton.setOpaque(false);
        exitButton.setMaximumSize(buttonSize);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(e -> {
            System.exit(0);
            currentFrame.dispose();
        });

        // Add components to the panel
        mainPanel.add(Box.createVerticalStrut(10)); // Spacer
        mainPanel.add(logoLabel);
        mainPanel.add(Box.createVerticalStrut(15)); // Spacer
        mainPanel.add(playButton);
        mainPanel.add(Box.createVerticalStrut(15)); // Spacer
        mainPanel.add(optionButton);
        mainPanel.add(Box.createVerticalStrut(15)); // Spacer
        mainPanel.add(creditButton);
        mainPanel.add(Box.createVerticalStrut(15)); // Spacer
        mainPanel.add(exitButton);
        validate();
    }

    public void openOptionPanel() {
        // Option Panel
        // optionPanel = new MyPanel(MyConstants.FILE_PANELBG);
        optionPanel = new JPanel();
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
        optionPanel.setOpaque(false);

        // optionLabel = new JLabel("OPTIONS");
        // optionLabel = new OutlineLabel("OPTIONS", Color.WHITE, Color.BLACK, 3);
        // optionLabel.setFont(titleFont);
        //// optionLabel.setForeground(Color.black);
        // optionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ImageIcon optionImg = new MyImageIcon(MyConstants.FILE_OPTIONSLABEL).resize(new Dimension(350, 150));
        optionLabel = new JLabel();
        optionLabel.setMaximumSize(new Dimension(345, 100));
        optionLabel.setIcon(optionImg);
        optionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        // optionLabel.setBounds(getCenterPosX(450),20,450,100);

        JPanel panel = new MyPanel(MyConstants.FILE_PANELBG);
        panel.setMaximumSize(new Dimension(900, 550));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        // Volume slider
        // volumeLabel = new JLabel("Music Volume");
        volumeLabel = new OutlineLabel("Volume", Color.WHITE, Color.BLACK, 2);
        volumeLabel.setFont(largeFont);
        // volumeLabel.setForeground(Color.black);
        volumeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100); // Default volume: 50%
        volumeSlider.setMajorTickSpacing(25);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);
        volumeSlider.setFont(smallFont);
        volumeSlider.setForeground(Color.DARK_GRAY);
        volumeSlider.setOpaque(false);
        volumeSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        volumeSlider.setMaximumSize(sliderSize);
        volumeSlider.setUI(new menu_CustomSliderUI(volumeSlider));
        volumeSlider.setFocusable(false);

        // Add listener to the slider
        volumeSlider.addChangeListener(e -> {
            int sliderValue = volumeSlider.getValue();
            music.setVolume(sliderValue);
        });

        // Radio Panel
        radioPanel = new JPanel();
        radioPanel.setMaximumSize(new Dimension(1000, 55));
        radioPanel.setOpaque(false);
        radioPanel.setLayout(new FlowLayout());

        // Radio Label
        // musicLabel = new JLabel("Change Music: ");
        musicLabel = new OutlineLabel("Change Music: ", Color.WHITE, Color.BLACK, 2);
        // musicLabel.setForeground(Color.black);
        musicLabel.setFont(mediumFont);
        musicLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Combobox
        String[] musicList = { "music_1", "music_2", "music_3", "music_4", "music_5" };
        musicCombo = new JComboBox<>(musicList);
        musicCombo.setSelectedIndex(0);
        musicCombo.setFont(smallFont);
        musicCombo.setOpaque(false);
        musicCombo.setFocusable(false);
        musicCombo.addActionListener(e -> {
            int index = musicCombo.getSelectedIndex();
            switch (index) {
                case 0:
                    music.switchAudio(MyConstants.FILE_MUSIC1);
                    break;
                case 1:
                    music.switchAudio(MyConstants.FILE_MUSIC2);
                    break;
                case 2:
                    music.switchAudio(MyConstants.FILE_MUSIC3);
                    break;
                case 3:
                    music.switchAudio(MyConstants.FILE_MUSIC4);
                    break;
                case 4:
                    music.switchAudio(MyConstants.FILE_MUSIC5);
                    break;
            }
        });

        // // RadioButton
        // music1 = new JRadioButton("Music_1");
        // music1.setFont(smallFont);
        // music1.setOpaque(false);
        // music1.setSelected(true);
        // music1.setFocusable(false);
        // music1.addItemListener(e -> {
        // if (e.getStateChange() == ItemEvent.SELECTED) {
        // music.switchAudio(MyConstants.FILE_MUSIC1);
        // }
        // });
        // music2 = new JRadioButton("Music_2");
        // music2.setFont(smallFont);
        // music2.setOpaque(false);
        // music2.setFocusable(false);
        // music2.addItemListener(e -> {
        // if (e.getStateChange() == ItemEvent.SELECTED) {
        // music.switchAudio(MyConstants.FILE_MUSIC2);
        // }
        // });
        // music3 = new JRadioButton("Music_3");
        // music3.setFont(smallFont);
        // music3.setOpaque(false);
        // music3.setFocusable(false);
        // music3.addItemListener(e -> {
        // if (e.getStateChange() == ItemEvent.SELECTED) {
        // music.switchAudio(MyConstants.FILE_MUSIC3);
        // }
        // });
        // musicGroup = new ButtonGroup();
        // musicGroup.add(music1);
        // musicGroup.add(music2);
        // musicGroup.add(music3);
        radioPanel.add(musicLabel);
        radioPanel.add(musicCombo);

        // radioPanel.add(music1);
        // radioPanel.add(music2);
        // radioPanel.add(music3);
        // Back Button
        BackButton_1 = new MyButton(MyConstants.FILE_BACK, MyConstants.FILE_BACKPRESSED, buttonSize);
        BackButton_1.setOpaque(false);
        BackButton_1.setMaximumSize(buttonSize);
        BackButton_1.setAlignmentX(Component.CENTER_ALIGNMENT);
        BackButton_1.addActionListener(e -> {
            cardLayout.show(bgPanel, "mainPanel");
        });

        panel.add(Box.createVerticalStrut(50)); // Spacer
        panel.add(volumeLabel);
        panel.add(Box.createVerticalStrut(5)); // Spacer
        panel.add(volumeSlider);
        panel.add(Box.createVerticalStrut(50)); // Spacer
        panel.add(radioPanel);
        panel.add(Box.createVerticalStrut(100)); // Spacer
        panel.add(BackButton_1);

        optionPanel.add(Box.createVerticalStrut(20)); // Spacer
        optionPanel.add(optionLabel);
        optionPanel.add(Box.createVerticalStrut(10)); // Spacer
        optionPanel.add(panel);
        validate();
    }

    public void openCreditPanel() {
        // creditPanel = new MyPanel(MyConstants.FILE_PANELBG);
        creditPanel = new JPanel();
        // creditPanel.setMaximumSize(panelMaxSize);
        // creditPanel.setLayout(new BoxLayout(creditPanel, BoxLayout.Y_AXIS));
        creditPanel.setLayout(null);
        creditPanel.setOpaque(false);

        // creditsLabel = new JLabel("Credits");
        // creditsLabel.setBackground(Color.black);
        // creditsLabel.setForeground(Color.white);
        // creditsLabel.setOpaque(true);
        // creditsLabel.setFont(titleFont);
        // creditsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ImageIcon creditsImg = new MyImageIcon(MyConstants.FILE_CREDITSLABEL).resize(new Dimension(450, 200));
        creditsLabel = new JLabel();
        creditsLabel.setIcon(creditsImg);
        creditsLabel.setBounds(getCenterPosX(450), 40, 450, 100);
        // creditsLabel.setSize(new Dimension(150,60));
        // creditsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel
        JPanel panel = new MyPanel(MyConstants.FILE_PANELBG);
        panel.setBounds(getCenterPosX(950), 180, 950, 350);
        // panel.setMaximumSize(new Dimension(800,500));
        // panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // panel.setLayout(null);
        panel.setOpaque(false);

        JLabel name1 = new OutlineLabel("Ratchaya Haboonmee     ID 6613117", Color.WHITE, Color.BLACK, 2);
        name1.setFont(largeFont);
        // name1.setBounds(30,50,900,100);
        name1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel name2 = new OutlineLabel("Khunpas Chiewsakul      ID 6613248", Color.WHITE, Color.BLACK, 2);
        name2.setFont(largeFont);
        // name2.setBounds(30,150,900,100);
        name2.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel name3 = new OutlineLabel("Pornphipat Pholprueksa ID 6613258", Color.WHITE, Color.BLACK, 2);
        name3.setFont(largeFont);
        // name3.setBounds(30,250,900,100);
        name3.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(45)); // Spacer
        panel.add(name1);
        panel.add(Box.createVerticalStrut(25)); // Spacer
        panel.add(name2);
        panel.add(Box.createVerticalStrut(25)); // Spacer
        panel.add(name3);

        // Back Button
        BackButton_2 = new MyButton(MyConstants.FILE_BACK, MyConstants.FILE_BACKPRESSED, buttonSize);
        BackButton_2.setOpaque(false);
        BackButton_2.setBounds(getCenterPosX(265), 550, 250, 85);
        // BackButton_2.setMaximumSize(buttonSize);
        // BackButton_2.setAlignmentX(Component.CENTER_ALIGNMENT);
        BackButton_2.addActionListener(e -> {
            cardLayout.show(bgPanel, "mainPanel");
        });

        // creditPanel.add(Box.createVerticalStrut(10)); // Spacer
        creditPanel.add(creditsLabel);
        // creditPanel.add(Box.createVerticalStrut(5)); // Spacer
        creditPanel.add(panel);
        // creditPanel.add(Box.createVerticalStrut(10)); // Spacer
        creditPanel.add(BackButton_2);
    }

    public void openStarterPanel() {
        starterPanel = new JPanel();
        starterPanel.setLayout(null);
        starterPanel.setOpaque(false);

        // Buttons
        BackButton_3 = new MyButton(MyConstants.FILE_BACK, MyConstants.FILE_BACKPRESSED, buttonSize);
        BackButton_3.setBounds(10, 620, 270, 100);
        BackButton_3.setOpaque(false);
        BackButton_3.setMaximumSize(buttonSize);
        BackButton_3.setAlignmentX(Component.CENTER_ALIGNMENT);
        BackButton_3.addActionListener(e -> {
            cardLayout.show(bgPanel, "mainPanel");
        });

        nextButton = new MyButton(MyConstants.FILE_NEXT, MyConstants.FILE_NEXTPRESSED, buttonSize);
        nextButton.setBounds(1080, 620, 270, 100);
        nextButton.setOpaque(false);
        nextButton.setMaximumSize(buttonSize);
        nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel
        JPanel panel_1 = new MyPanel(MyConstants.FILE_PANELBG_ALT);
        panel_1.setBounds(getCenterPosX(820), 50, 820, 600);
        panel_1.setLayout(null);
        panel_1.setOpaque(false);

        JLabel nameLabel = new OutlineLabel("Enter Name: ", Color.WHITE, Color.BLACK, 2);
        nameLabel.setBounds(30, 65, 360, 50);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 48));

        JTextField nameField = new JTextField(20);
        nameField.setText("Name");
        nameField.setBounds(340, 60, 410, 60);
        nameField.setFont(new Font("Arial", Font.PLAIN, 48));
        nameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nameField.getText().equals("Name")) {
                    nameField.setText("");
                }

            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nameField.getText().isEmpty()) {
                    nameField.setText("Name");
                }
            }
        });
        nextButton.addActionListener(e -> {

            Enumeration<AbstractButton> buttons = group.getElements();
            while (buttons.hasMoreElements()) {
                AbstractButton button = buttons.nextElement();
                if (button.isSelected()) {
                    // Cast to MyRadioButton to call returnIcon()
                    MyRadioButton myRadioButton = (MyRadioButton) button;
                    System.out.println("Selected Icon Path: " + myRadioButton.returnIcon());
                    if (myRadioButton.returnIcon() == Constants.FILE_RANDOM) {
                        Random random = new Random();
                        int bowl = random.nextInt(4);
                        switch (bowl) {
                            case 0:
                                bowlImage = Constants.FILE_BOWL_1;
                                break;
                            case 1:
                                bowlImage = Constants.FILE_BOWL_2;
                                break;
                            case 2:
                                bowlImage = Constants.FILE_BOWL_3;
                                break;
                            case 3:
                                bowlImage = Constants.FILE_BOWL_4;

                        }
                    } else
                        bowlImage = myRadioButton.returnIcon();
                    break;
                }
            }

            starterPanel.setVisible(false);
            game_MainApplication.mainGame(bowlImage, nameField.getText());
            currentFrame.dispose();
            music.stop();
            music = null;
        });

        JLabel selectBowlLabel = new OutlineLabel("Select Bowl", Color.WHITE, Color.BLACK, 2);
        selectBowlLabel.setBounds((820 - 295) / 2, 170, 295, 50);
        selectBowlLabel.setFont(new Font("Arial", Font.BOLD, 48));

        // Bowl
        bowlPreview = new JLabel();
        // ImageIcon bowlImage = new project3.ImageIcon(Constants.FILE_BOWL).resize(300,
        // 300);
        // bowlPreview.setIcon(bowlImage);
        bowlPreview.setBounds((820 - 300) / 2, 200, 300, 300);

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new FlowLayout());
        radioPanel.setBounds(5, 460, 810, 100);
        radioPanel.setOpaque(false);

        Dimension radioImage = new Dimension(90, 90);
        JRadioButton bowl_1 = new MyRadioButton("1", Constants.FILE_BOWL_1, radioImage, bowlPreview);
        JRadioButton bowl_2 = new MyRadioButton("2", Constants.FILE_BOWL_2, radioImage, bowlPreview);
        JRadioButton bowl_3 = new MyRadioButton("3", Constants.FILE_BOWL_3, radioImage, bowlPreview);
        JRadioButton bowl_4 = new MyRadioButton("4", Constants.FILE_BOWL_4, radioImage, bowlPreview);
        JRadioButton bowl_5 = new MyRadioButton("5", Constants.FILE_RANDOM, radioImage, bowlPreview);

        // Change bowl image at Constants.FILE_BOWL_(number)
        // JRadioButton bowl_1 = new MyRadioButton("1", Constants.FILE_BOWL_1,
        // radioImage, bowlPreview);
        // JRadioButton bowl_2 = new MyRadioButton("2", Constants.FILE_BOWL_2,
        // radioImage, bowlPreview);
        // JRadioButton bowl_3 = new MyRadioButton("3", Constants.FILE_BOWL_3,
        // radioImage, bowlPreview);
        // JRadioButton bowl_4 = new MyRadioButton("4", Constants.FILE_BOWL_4,
        // radioImage, bowlPreview);
        // JRadioButton bowl_5 = new MyRadioButton("5", Constants.FILE_BOWL_5,
        // radioImage, bowlPreview);
        bowl_1.setSelected(true);

        // Create a ButtonGroup to group the buttons
        group = new ButtonGroup();
        group.add(bowl_1);
        group.add(bowl_2);
        group.add(bowl_3);
        group.add(bowl_4);
        group.add(bowl_5);

        radioPanel.add(bowl_1);
        radioPanel.add(bowl_2);
        radioPanel.add(bowl_3);
        radioPanel.add(bowl_4);
        radioPanel.add(bowl_5);

        panel_1.add(nameLabel);
        panel_1.add(nameField);
        panel_1.add(selectBowlLabel);
        panel_1.add(bowlPreview);
        panel_1.add(radioPanel);

        starterPanel.add(panel_1);
        starterPanel.add(BackButton_3);
        starterPanel.add(nextButton);
    }

    public void test() {
        // Get the graphics environment
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        // Get all available font family names
        String[] fontNames = ge.getAvailableFontFamilyNames();

        // Print all available fonts
        System.out.println("Available Fonts:");
        Arrays.stream(fontNames).forEach(System.out::println);
    }

    public void centerComponent(JComponent c, int width, int length) {
        c.setBounds(getCenterPosX(width), getCenterPosY(length), width, length);
    }

    public int getCenterPosX(int length) {
        return (frameWidth - length) / 2;
    }

    public int getCenterPosY(int height) {
        return (frameHeight - height) / 2;
    }
}
