package main.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import main.tictactoe.*;

public class TicTacToe implements ActionListener {
    JLabel titre = new JLabel();
    MyFrame frame = new MyFrame();
    JPanel panelTitre = new JPanel();
    JPanel panelBoutton = new JPanel();
    JButton[] tabBouttons = new JButton[9];
    boolean joueur1;

    public TicTacToe() {
        titre.setBackground(new Color(25, 25, 25));
        titre.setForeground(new Color(25, 255, 0));
        titre.setFont(new Font("Ink Free", Font.BOLD, 75));
        titre.setHorizontalAlignment(JLabel.CENTER);
        titre.setOpaque(true);

        panelTitre.setLayout(new BorderLayout());
        panelTitre.setBounds(0, 0, 800, 100);

        panelTitre.add(titre);
        frame.add(panelTitre, BorderLayout.NORTH);

        panelBoutton.setLayout(new GridLayout(3, 3));
        panelBoutton.setBackground(new Color(2, 5, 25));
        frame.add(panelBoutton);

        for (int i = 0; i < 9; i++) {
            tabBouttons[i] = new JButton();
            panelBoutton.add(tabBouttons[i]);
            tabBouttons[i].setFont(new Font("SansSerif", Font.BOLD, 120));
            tabBouttons[i].setFocusable(false);
            tabBouttons[i].addActionListener(this);
        }

        premierTour();
    }

    public void premierTour() {
        // try {
        // Thread.sleep(2000);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        if (new Random().nextInt(2) == 0) {
            this.joueur1 = true;
            titre.setText("joueur 1 : O");
        } else {
            this.joueur1 = false;
            titre.setText("joueur 2 : X");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == tabBouttons[i]) {
                if (joueur1) {
                    if (tabBouttons[i].getText().equals("")) {
                        tabBouttons[i].setForeground(new Color(162, 71, 71));
                        tabBouttons[i].setText("O");
                        joueur1 = false;
                        titre.setText("joueur 2 : X");
                        check();
                    }
                } else {
                    if (tabBouttons[i].getText().equals("")) {
                        tabBouttons[i].setForeground(new Color(45, 91, 131));
                        tabBouttons[i].setText("X");
                        titre.setText("joueur 1 : O");
                        joueur1 = true;
                        check();
                    }
                }
            }
        }
    }

    public void check() {
        // verticalement
        for (int i = 0; i < 2; i++) {
            if (tabBouttons[i].getText().equals(tabBouttons[i + 3].getText())
                    && tabBouttons[i].getText().equals(tabBouttons[i + 6].getText())
                    && !tabBouttons[i].getText().equals("")) {
                gagne(i, i + 3, i + 6);
            }
        }

        // horizontalement
        for (int i = 0; i <= 6; i += 3) {
            if (tabBouttons[i].getText().equals(tabBouttons[i + 1].getText())
                    && tabBouttons[i].getText().equals(tabBouttons[i + 2].getText())
                    && !tabBouttons[i].getText().equals("")) {
                gagne(i, i + 1, i + 2);
            }
        }

        // obliquement
        if (tabBouttons[0].getText().equals(tabBouttons[4].getText())
                && tabBouttons[0].getText().equals(tabBouttons[8].getText()) && !tabBouttons[0].getText().equals("")) {
            gagne(0, 4, 8);
        }
        if (tabBouttons[2].getText().equals(tabBouttons[4].getText())
                && tabBouttons[2].getText().equals(tabBouttons[6].getText()) && !tabBouttons[2].getText().equals("")) {
            gagne(2, 4, 6);
        }
    }

    public void gagne(int x, int y, int z) {
        tabBouttons[x].setBackground(new Color(125, 209, 121));
        tabBouttons[y].setBackground(new Color(125, 209, 121));
        tabBouttons[z].setBackground(new Color(125, 209, 121));

        if (tabBouttons[x].getText().equals("X")) {
            titre.setText("X gagne");
        } else {
            titre.setText("O gagne");
        }
        for (JButton boutton : tabBouttons) {
            boutton.setEnabled(false);
        }

    }
}
