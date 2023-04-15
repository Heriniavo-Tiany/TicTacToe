package main.tictactoe;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame{
        public MyFrame() {
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setTitle("Tic Tac Toe");
            this.setSize(800, 800);
            this.getContentPane().setBackground(new Color(2,50,50));
            this.setLayout(new BorderLayout());
            this.setVisible(true);
        }
}
