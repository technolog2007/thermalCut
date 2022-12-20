package shpp.com.graf;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    JFrame frame;
    JButton contour;
    JButton dimensions;
    JLabel label;

    public void createWindow() {
        frame = new JFrame("Thermal cutting calculator");
        frame.setSize(500, 400);
        frame.setVisible(true);
        frame.setResizable(false);// робить розміри вікна незмінним
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(200, 200);
    }

    public void addStartButton() {
        this.contour = new JButton("Contour");
        this.dimensions = new JButton("Dimensions");
        frame.setLayout(null);
        contour.setBounds(30, frame.getHeight() / 2 - 50 / 2 - 23, 150, 50);
        frame.add(contour);
        dimensions.setBounds((frame.getWidth() - 150 - 15 - 30), frame.getHeight() / 2 - 50 / 2 - 23, 150, 50);
        frame.add(dimensions);
    }

    public void addStartLabel(String label) {
        this.label = new JLabel(label);
        Font font = new Font("BOLD", Font.BOLD, 20);
        this.label.setFont(font);
        this.label.setSize(250, 100);
        this.label.setBounds((frame.getWidth() / 2 - this.label.getWidth() / 2), 50, 400, 100);
        frame.add(this.label);
    }

}

