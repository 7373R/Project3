package project3;

// Interface for keeping constant values
import java.awt.*;
import java.awt.image.ImageObserver;
import javax.swing.*;
import javax.swing.border.*;

interface MyConstants {

    // ----- Resource files
    static final String PATH = "src/main/java/project3/resources/";
    // static final String PATH = System.getProperty("user.dir") + "/resources/";
    static final String BUTTONPATH = PATH + "buttons/";

    // Music files
    static final String FILE_MUSIC1 = PATH + "music1.wav";
    static final String FILE_MUSIC2 = PATH + "music2.wav";

    // Panel + BG + Icon + Logo
    static final String FILE_BG = PATH + "mainBG.jpg";
    static final String FILE_PANELBG = PATH + "PanelBG.png";
    static final String FILE_ICON = PATH + "Icon.png";
    static final String FILE_LOGO = PATH + "Logo.png";

    // Buttons
    static final String FILE_PLAY = BUTTONPATH + "play.png";
    static final String FILE_PLAYPRESSED = BUTTONPATH + "playPressed.png";

    static final String FILE_OPTION = BUTTONPATH + "option.png";
    static final String FILE_OPTIONPRESSED = BUTTONPATH + "optionPressed.png";

    static final String FILE_EXIT = BUTTONPATH + "exit.png";
    static final String FILE_EXITPRESSED = BUTTONPATH + "exitPressed.png";

    static final String FILE_BACK = BUTTONPATH + "back.png";
    static final String FILE_BACKPRESSED = BUTTONPATH + "backPressed.png";

    static final String FILE_CREDITS = BUTTONPATH + "credits.png";
    static final String FILE_CREDITSPRESSED = BUTTONPATH + "creditsPressed.png";

    // Labels
    static final String FILE_CREDITSLABEL = PATH + "creditsLabel.png";
    static final String FILE_OPTIONSLABEL = PATH + "optionsLabel.png";

}

// Auxiliary class to resize image
class MyImageIcon extends ImageIcon {

    public MyImageIcon(String fname) {
        super(fname);
    }

    public MyImageIcon(Image image) {
        super(image);
    }

    public MyImageIcon resize(Dimension size) {
        Image oldimg = this.getImage();
        // Use SCALE_DEFAULT mode to support gif
        Image newimg = oldimg.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_DEFAULT);
        return new MyImageIcon(newimg);
    }
}

class MyPanel extends JPanel {

    private Image backgroundImage;

    public MyPanel(String imagePath) {
        // Load the image
        backgroundImage = new ImageIcon(imagePath).getImage();
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the image scaled to the panel's size
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

class MyButton extends JButton {

    private Image image;
    private ImageObserver imageObserver;

    MyButton(String defaultIcon, String pressedIcon, Dimension size) {
        super();
        ImageIcon icon = new MyImageIcon(defaultIcon).resize(size);
        ImageIcon pressed = new MyImageIcon(pressedIcon).resize(size);

        setIcon(icon);
        setPressedIcon(pressed);

        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);

        // image = icon.getImage();
        // imageObserver = icon.getImageObserver();
    }

    // @Override
    // public void paintComponent(Graphics g) {
    // super.paintComponent(g);
    // g.drawImage(image, 0, 0, getWidth(), getHeight(), imageObserver);
    // }
}

class OutlineLabel extends JLabel {

    private static final long serialVersionUID = 1L;

    private Color outlineColor = Color.WHITE;

    private boolean isPaintingOutline = false;
    private boolean forceTransparent = false;

    private final int thickness;

    public OutlineLabel(int thickness) {
        super();
        this.thickness = thickness;
        setBorder(thickness);
    }

    public OutlineLabel(String text, Color fillColor, Color outlineColor, int thickness) {
        super(text);
        this.thickness = thickness;
        setBorder(thickness);

        this.setForeground(fillColor);
        this.outlineColor = outlineColor;
    }

    public OutlineLabel(String text, int horizontalAlignment,
            int thickness) {
        super(text, horizontalAlignment);
        this.thickness = thickness;
        setBorder(thickness);
    }

    private void setBorder(int thickness) {
        Border border = getBorder();
        Border margin = new EmptyBorder(thickness, thickness + 3,
                thickness, thickness + 3);
        setBorder(new CompoundBorder(border, margin));
    }

    public Color getOutlineColor() {
        return outlineColor;
    }

    public void setOutlineColor(Color outlineColor) {
        this.outlineColor = outlineColor;
        this.invalidate();
    }

    @Override
    public Color getForeground() {
        if (isPaintingOutline) {
            return outlineColor;
        } else {
            return super.getForeground();
        }
    }

    @Override
    public boolean isOpaque() {
        if (forceTransparent) {
            return false;
        } else {
            return super.isOpaque();
        }
    }

    @Override
    public void paint(Graphics g) {
        String text = getText();
        if (text == null || text.length() == 0) {
            super.paint(g);
            return;
        }

        // 1 2 3
        // 8 9 4
        // 7 6 5
        if (isOpaque()) {
            super.paint(g);
        }

        forceTransparent = true;
        isPaintingOutline = true;
        g.translate(-thickness, -thickness);
        super.paint(g); // 1
        g.translate(thickness, 0);
        super.paint(g); // 2
        g.translate(thickness, 0);
        super.paint(g); // 3
        g.translate(0, thickness);
        super.paint(g); // 4
        g.translate(0, thickness);
        super.paint(g); // 5
        g.translate(-thickness, 0);
        super.paint(g); // 6
        g.translate(-thickness, 0);
        super.paint(g); // 7
        g.translate(0, -thickness);
        super.paint(g); // 8
        g.translate(thickness, 0); // 9
        isPaintingOutline = false;

        super.paint(g);
        forceTransparent = false;
    }

}

// class MyTextLabel extends JLabel {
//
// private Color outlineColor;
// private int outlineThickness;
//
// public MyTextLabel(String text, Color fillColor, Color outlineColor, int
// outlineThickness) {
// super(text);
// this.setForeground(fillColor);
// this.outlineColor = outlineColor;
// this.outlineThickness = outlineThickness;
// }
//
// @Override
// protected void paintComponent(Graphics g) {
// Graphics2D g2d = (Graphics2D) g.create();
// g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
// RenderingHints.VALUE_ANTIALIAS_ON);
//
// String text = getText();
// FontMetrics fm = g2d.getFontMetrics();
// int x = 0;
// int y = fm.getAscent();
//
// // Draw the outline multiple times for a thicker effect
// g2d.setColor(outlineColor);
// for (int i = -outlineThickness; i <= outlineThickness; i++) {
// for (int j = -outlineThickness; j <= outlineThickness; j++) {
// if (i != 0 || j != 0) {
// g2d.drawString(text, x + i, y + j);
// }
// }
// }
//
// // Draw the fill text
// g2d.setColor(getForeground());
// g2d.drawString(text, x, y);
//
// g2d.dispose();
// }
// }
