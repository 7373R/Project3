// Ratchaya Haboonmee     ID 6613117
// Khunpas Chiewsakul     ID 6613248
// Pornphipat Pholprueksa ID 6613258
package project3;

import java.awt.Color;
import javax.swing.*;

public class menu_StarterPanel extends JPanel {

    private JButton closeButton;

    public menu_StarterPanel() {
        this.setVisible(true);
        this.setBackground(Color.red);

        closeButton = new JButton("Close");
        closeButton.addActionListener(e -> {
            this.setVisible(false);
        });

        this.add(closeButton);
    }

}
