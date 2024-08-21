package Projects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StreakHeatMap extends JFrame {
    private int totalDays;
    private int currentStreak;
    private int highestStreak;
    private JButton[] dayButtons;
    private JLabel totalDaysLabel;
    private JLabel highestStreakLabel;
    private String taskName;

    public StreakHeatMap() {
        this.setTitle("Streak Heat Map");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Input Panel for Task and Number of Days
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        JLabel taskLabel = new JLabel("Enter your task:");
        JTextField taskField = new JTextField();
        JLabel daysLabel = new JLabel("Enter number of days:");
        JTextField daysField = new JTextField();
        JButton startButton = new JButton("Start");

        inputPanel.add(taskLabel);
        inputPanel.add(taskField);
        inputPanel.add(daysLabel);
        inputPanel.add(daysField);
        inputPanel.add(new JLabel("")); // Placeholder
        inputPanel.add(startButton);

        this.add(inputPanel, BorderLayout.NORTH);

        startButton.addActionListener(e -> {
            taskName = taskField.getText();
            totalDays = Integer.parseInt(daysField.getText());
            setupDayButtons();
        });

        // Initialize labels
        totalDaysLabel = new JLabel("Total Active Days: 0");
        highestStreakLabel = new JLabel("Highest Streak: 0");

        JPanel statusPanel = new JPanel(new GridLayout(1, 2));
        statusPanel.add(totalDaysLabel);
        statusPanel.add(highestStreakLabel);

        this.add(statusPanel, BorderLayout.SOUTH);

        this.setSize(500, 400);
        this.setVisible(true);
    }

    private void setupDayButtons() {
        JPanel buttonPanel = new JPanel(new GridLayout(5, 5));  // Adjust the grid size as needed
        dayButtons = new JButton[totalDays];

        for (int i = 0; i < totalDays; i++) {
            dayButtons[i] = new JButton();
            dayButtons[i].setBackground(Color.GRAY);
            int dayIndex = i;
            dayButtons[i].addActionListener(e -> updateStreak(dayIndex));
            buttonPanel.add(dayButtons[i]);
        }

        this.add(buttonPanel, BorderLayout.CENTER);
        this.validate();
        this.repaint();
    }

    private void updateStreak(int dayIndex) {
        if (dayButtons[dayIndex].getBackground() == Color.GRAY) {
            dayButtons[dayIndex].setBackground(Color.GREEN);
            currentStreak++;
            highestStreak = Math.max(highestStreak, currentStreak);
        } else if (dayButtons[dayIndex].getBackground() == Color.GREEN) {
            dayButtons[dayIndex].setBackground(Color.RED);
            currentStreak = 0;
        }
        updateLabels();
    }

    private void updateLabels() {
        totalDaysLabel.setText("Total Active Days: " + currentStreak);
        highestStreakLabel.setText("Highest Streak: " + highestStreak);
    }

    public static void main(String[] args) {
        new StreakHeatMap();
    }
}
