import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent; // ADD
import java.awt.event.ActionListener; // ADD

public class test extends JFrame{
    // public static void main(String[] args) {
    //     test t = new test();
    //     t.testFrame();
    // }

    public void testFrame(){
        JFrame frame = new JFrame("Pre Frame");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel label = new JLabel("Hello, World!", SwingConstants.CENTER);
        frame.add(label, BorderLayout.CENTER);

        frame.setVisible(true);
    }

}
