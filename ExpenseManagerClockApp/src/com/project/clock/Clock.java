package com.project.clock;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Clock extends JFrame {
    private JLabel clockLabel;

    public Clock() {
        // Set up the JFrame window
        setTitle("Digital Clock");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Create and configure the clock label
        clockLabel = new JLabel();
        clockLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(clockLabel);

        // Timer to update the clock every second
        Timer timer = new Timer(1000, e -> updateClock());
        timer.start();
    }

    // Method to update the clock every second
    private void updateClock() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        clockLabel.setText(formatter.format(new Date()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Clock clock = new Clock();
            clock.setVisible(true);  // This ensures the clock window is shown
        });
    }
}
