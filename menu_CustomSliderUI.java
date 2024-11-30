// Ratchaya Haboonmee     ID 6613117
// Khunpas Chiewsakul     ID 6613248
// Pornphipat Pholprueksa ID 6613258
package project3;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;

public class menu_CustomSliderUI extends BasicSliderUI {

    private BasicStroke stroke = new BasicStroke(1f, BasicStroke.CAP_ROUND,
            BasicStroke.JOIN_ROUND, 0f, new float[] { 1f, 2f }, 0f);

    public menu_CustomSliderUI(JSlider b) {
        super(b);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        super.paint(g, c);
    }

    @Override
    protected Dimension getThumbSize() {
        return new Dimension(15, 45);
    }

    @Override
    public void paintTrack(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set track bounds and appearance
        Rectangle trackBounds = trackRect;
        int trackHeight = 15; // Set the desired thickness for the track
        int trackY = trackBounds.y + (trackBounds.height - trackHeight) / 2; // Center the track vertically

        // Set track color
        g2d.setColor(new Color(0, 0, 0, 0));
        g2d.fillRect(trackBounds.x, trackY, trackBounds.width, trackHeight);

        // Get the thumb position to calculate the trail
        int thumbPos = thumbRect.x + (thumbRect.width / 2);

        // Draw the trail in white (from the start to the thumb position)
        g2d.setColor(Color.WHITE);
        g2d.fillRect(trackBounds.x, trackY, thumbPos - trackBounds.x, trackHeight);

        // Draw the outline of the track
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2)); // Set outline thickness
        g2d.drawRect(trackBounds.x, trackY, trackBounds.width - 1, trackHeight - 1);

        // Optionally draw a gradient or customize further
        g2d.dispose();
    }

    public void paintThumb(Graphics g) {
        // Get Graphics2D object
        Graphics2D g2d = (Graphics2D) g;

        // Set the thumb color
        g2d.setColor(Color.WHITE);

        // Paint the thumb (a simple rectangle in this case)
        g2d.fillRect(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height);

        // Optionally, add a border or other visual effects to the thumb
        g2d.setColor(Color.BLACK);
        g2d.drawRect(thumbRect.x, thumbRect.y, thumbRect.width - 1, thumbRect.height - 1);
    }

    // @Override
    // public void paintTrack(Graphics g) {
    // Graphics2D g2d = (Graphics2D) g;
    // Stroke old = g2d.getStroke();
    // g2d.setStroke(stroke);
    // g2d.setPaint(Color.BLACK);
    // if (slider.getOrientation() == SwingConstants.HORIZONTAL) {
    // g2d.drawLine(trackRect.x, trackRect.y + trackRect.height / 2,
    // trackRect.x + trackRect.width, trackRect.y + trackRect.height / 2);
    // } else {
    // g2d.drawLine(trackRect.x + trackRect.width / 2, trackRect.y,
    // trackRect.x + trackRect.width / 2, trackRect.y + trackRect.height);
    // }
    // g2d.setStroke(old);
    // }
    //
    // @Override
    // public void paintThumb(Graphics g) {
    // Graphics2D g2d = (Graphics2D) g;
    // int x1 = thumbRect.x + 2;
    // int x2 = thumbRect.x + thumbRect.width - 2;
    // int width = thumbRect.width - 4;
    // int topY = thumbRect.y + thumbRect.height / 2 - thumbRect.width / 3;
    // GeneralPath shape = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
    // shape.moveTo(x1, topY);
    // shape.lineTo(x2, topY);
    // shape.lineTo((x1 + x2) / 2, topY + width);
    // shape.closePath();
    // g2d.setPaint(new Color(81, 83, 186));
    // g2d.fill(shape);
    // Stroke old = g2d.getStroke();
    // g2d.setStroke(new BasicStroke(2f));
    // g2d.setPaint(new Color(131, 127, 211));
    // g2d.draw(shape);
    // g2d.setStroke(old);
    // }
}