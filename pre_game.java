package project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Arrays;

public class pre_game extends JFrame {

    private pre_game currentFrame;

    public void preFrame() {

        currentFrame = this;

        // Create a new frame
        JFrame frame = new JFrame("Pre-Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        // Create a new panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.setBackground(Color.BLACK);

        // Create a new label
        JLabel label = new JLabel("Select a game mode:");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label);

        // Create a new combo box
        String[] gameModes = {"Classic", "Time Trial", "Zen"};
        JComboBox<String> comboBox = new JComboBox<>(gameModes);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 18));
        comboBox.setBackground(Color.WHITE);
        comboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedMode = (String) e.getItem();
                System.out.println("Selected game mode: " + selectedMode);
            }
        });
        panel.add(comboBox);

        // Create a new button
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 18));
        startButton.setBackground(Color.GREEN);
        startButton.setForeground(Color.WHITE);
        startButton.addActionListener(e -> {
            String selectedMode = (String) comboBox.getSelectedItem();
            System.out.println("Starting game in " + selectedMode + " mode...");
            frame.dispose();
            game(selectedMode);
        });
        panel.add(startButton);

        // Add the panel to the frame
        frame.add(panel, BorderLayout.CENTER);

        // Center the frame
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);
    }

    public static void pregame(){
        new pre_game().preFrame();
    }
}