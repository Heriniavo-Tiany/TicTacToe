package main.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TicTacToe implements ActionListener {
    JLabel title = new JLabel();
    MyFrame frame = new MyFrame();
    JPanel panelTitle = new JPanel();
    JPanel panelButton = new JPanel();
    JButton[] buttonArray = new JButton[9];
    boolean player1Turn;

    public TicTacToe() {
        title.setBackground(new Color(25, 25, 25));
        title.setForeground(new Color(25, 255, 0));
        title.setFont(new Font("Ink Free", Font.BOLD, 75));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setOpaque(true);

        panelTitle.setLayout(new BorderLayout());
        panelTitle.setBounds(0, 0, 800, 100);

        panelTitle.add(title);
        frame.add(panelTitle, BorderLayout.NORTH);

        panelButton.setLayout(new GridLayout(3, 3));
        panelButton.setBackground(new Color(2, 5, 25));
        frame.add(panelButton);

        for (int i = 0; i < 9; i++) {
            buttonArray[i] = new JButton();
            panelButton.add(buttonArray[i]);
            buttonArray[i].setFont(new Font("SansSerif", Font.BOLD, 120));
            buttonArray[i].setFocusable(false);
            buttonArray[i].addActionListener(this);
        }

        firstRound();
    }

    public void firstRound() {
        if (new Random().nextInt(2) == 0) {
            this.player1Turn = true;
            title.setText("joueur 1 : O");
        } else {
            this.player1Turn = false;
            title.setText("joueur 2 : X");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttonArray[i]) {
                if (player1Turn) {
                    if (buttonArray[i].getText().equals("")) {
                        buttonArray[i].setForeground(new Color(162, 71, 71));
                        buttonArray[i].setText("O");
                        player1Turn = false;
                        title.setText("joueur 2 : X");
                        check();
                    }
                } else {
                    if (buttonArray[i].getText().equals("")) {
                        buttonArray[i].setForeground(new Color(45, 91, 131));
                        buttonArray[i].setText("X");
                        title.setText("joueur 1 : O");
                        player1Turn = true;
                        check();
                    }
                }
            }
        }
    }

    public void check() {
        // verticalement
        for (int i = 0; i < 2; i++) {
            if (buttonArray[i].getText().equals(buttonArray[i + 3].getText())
                    && buttonArray[i].getText().equals(buttonArray[i + 6].getText())
                    && !buttonArray[i].getText().equals("")) {
                win(i, i + 3, i + 6);
            }
        }

        // horizontalement
        for (int i = 0; i <= 6; i += 3) {
            if (buttonArray[i].getText().equals(buttonArray[i + 1].getText())
                    && buttonArray[i].getText().equals(buttonArray[i + 2].getText())
                    && !buttonArray[i].getText().equals("")) {
                win(i, i + 1, i + 2);
            }
        }

        // obliquement
        if (buttonArray[0].getText().equals(buttonArray[4].getText())
                && buttonArray[0].getText().equals(buttonArray[8].getText()) && !buttonArray[0].getText().equals("")) {
            win(0, 4, 8);
        }
        if (buttonArray[2].getText().equals(buttonArray[4].getText())
                && buttonArray[2].getText().equals(buttonArray[6].getText()) && !buttonArray[2].getText().equals("")) {
            win(2, 4, 6);
        }
    }

    public void win(int x, int y, int z) {
        buttonArray[x].setBackground(new Color(125, 209, 121));
        buttonArray[y].setBackground(new Color(125, 209, 121));
        buttonArray[z].setBackground(new Color(125, 209, 121));

        if (buttonArray[x].getText().equals("X")) {
            title.setText("X gagne");
        } else {
            title.setText("O gagne");
        }
        for (JButton btn : buttonArray) {
            btn.setEnabled(false);
        }

    }
}
