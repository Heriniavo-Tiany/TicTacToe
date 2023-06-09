package main.tictactoe;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Random;

public class TicTacToe {
    private static final int BOARD_SIZE = 9;
    private static final int NUM_ROWS = 3;
    private static final int NUM_COLS = 3;
    private static final String PLAYER_1_SYMBOL = "O";
    private static final String PLAYER_2_SYMBOL = "X";

    private boolean isPlayer1Turn;
    private JFrame mainFrame;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel boardPanel;
    private JButton[] buttons;

    public TicTacToe() {
        initialize();
    }

    private void initialize() {
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mainFrame = new JFrame();
        mainFrame.setTitle("Tic Tac Toe");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500, 500);

        titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLACK);
        titleLabel = new JLabel();
        titleLabel.setText("Tic Tac Toe");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Ink Free", Font.BOLD, 50));
        titlePanel.add(titleLabel);
        mainFrame.add(titlePanel, BorderLayout.NORTH);

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(NUM_ROWS, NUM_COLS));
        boardPanel.setBackground(Color.WHITE);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(10, 10, 10, 10);
        Border compound = new CompoundBorder(line, margin);
        boardPanel.setBorder(compound);
        buttons = new JButton[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("SansSerif", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].setBackground(Color.WHITE);
            buttons[i].addActionListener(e -> {
                int index = getButtonIndex(e.getSource());
                if (isPlayer1Turn) {
                    if (buttons[index].getText().equals("")) {
                        buttons[index].setForeground(new Color(0, 128, 0));
                        buttons[index].setText(PLAYER_1_SYMBOL);
                        titleLabel.setText(PLAYER_2_SYMBOL + " turn");
                        isPlayer1Turn = false;
                        if (checkForWin(PLAYER_1_SYMBOL)) {
                            JOptionPane.showMessageDialog(mainFrame,
                                    "Player 1 wins!", "Game Over", JOptionPane.PLAIN_MESSAGE);
                            resetBoard();
                        } else if (checkForTie()) {
                            JOptionPane.showMessageDialog(mainFrame,
                                    "It's a tie!", "Game Over", JOptionPane.PLAIN_MESSAGE);
                            resetBoard();
                        }
                    } else {
                        JOptionPane.showMessageDialog(mainFrame,
                                "This cell is already taken. Choose another one.",
                                "Invalid Move", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    if (buttons[index].getText().equals("")) {
                        buttons[index].setForeground(new Color(255, 0, 0));
                        buttons[index].setText(PLAYER_2_SYMBOL);
                        titleLabel.setText(PLAYER_1_SYMBOL + " turn");
                        isPlayer1Turn = true;
                        if (checkForWin(PLAYER_2_SYMBOL)) {
                            JOptionPane.showMessageDialog(mainFrame,
                                    "Player 2 wins!", "Game Over", JOptionPane.PLAIN_MESSAGE);
                            resetBoard();
                        } else if (checkForTie()) {
                            JOptionPane.showMessageDialog(mainFrame,
                                    "It's a tie!", "Game Over", JOptionPane.PLAIN_MESSAGE);
                            resetBoard();
                        }
                    } else {
                        JOptionPane.showMessageDialog(mainFrame,
                                "This cell is already taken. Choose another one.",
                                "Invalid Move", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            boardPanel.add(buttons[i]);
        }
        mainFrame.add(boardPanel, BorderLayout.CENTER);
        isPlayer1Turn = true;

        mainFrame.setVisible(true);
    }

    private int getButtonIndex(Object source) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (buttons[i] == source) {
                return i;
            }
        }
        return -1;
    }

    private boolean checkForWin(String symbol) {
        // Check rows
        for (int i = 0; i < BOARD_SIZE; i += 3) {
            if (buttons[i].getText().equals(symbol) &&
                    buttons[i + 1].getText().equals(symbol) &&
                    buttons[i + 2].getText().equals(symbol)) {
                return true;
            }
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getText().equals(symbol) &&
                    buttons[i + 3].getText().equals(symbol) &&
                    buttons[i + 6].getText().equals(symbol)) {
                return true;
            }
        }
        // Check diagonals
        if (buttons[0].getText().equals(symbol) &&
                buttons[4].getText().equals(symbol) &&
                buttons[8].getText().equals(symbol)) {
            return true;
        }
        if (buttons[2].getText().equals(symbol) &&
                buttons[4].getText().equals(symbol) &&
                buttons[6].getText().equals(symbol)) {
            return true;
        }
        return false;
    }

    private boolean checkForTie() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (buttons[i].getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    private void resetBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            buttons[i].setText("");
        }
        isPlayer1Turn = new Random().nextBoolean();
        if (isPlayer1Turn)
            titleLabel.setText(PLAYER_1_SYMBOL + " turn");
        else
            titleLabel.setText(PLAYER_2_SYMBOL + " turn");
    }

}