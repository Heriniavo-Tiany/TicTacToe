package main.tictactoe;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame{
    // Constants
    private static final Color BACKGROUND_COLOR = new Color(2, 50, 50);
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 800;
    private static final int GRID_SIZE = 3;

    // Panels and Components
    private JPanel gamePanel;
    private JButton[][] gridButtons;

    public MyFrame() {
        // Set up the frame properties
        setupFrame();

        // Set up the game panel
        setupGamePanel();
    }

    private void setupFrame() {
        // Set the default close operation, title, and size of the frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        // Set the background color and layout of the content pane
        getContentPane().setBackground(BACKGROUND_COLOR);
        setLayout(new BorderLayout());

        // Make the frame visible
        setVisible(true);
    }

    private void setupGamePanel() {
        // Create a panel to hold the game grid
        gamePanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        add(gamePanel, BorderLayout.CENTER);

        // Create the buttons for the game grid
        gridButtons = new JButton[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                gridButtons[i][j] = new JButton();
                gamePanel.add(gridButtons[i][j]);
            }
        }
    }
}
