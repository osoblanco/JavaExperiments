package com.company;
import  java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
public class Main extends JFrame implements ActionListener
{
    private JButton piece[][] = new JButton[4][4];

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        super("Main");
        setLayout(new GridLayout(4, 4));
        for (int row = 0; row < piece.length; row++)
            for (int col = 0; col < piece.length; col++) {
                piece[row][col] = new JButton("");
                piece[row][col].addActionListener(this);
                piece[row][col].setVisible(false);
                add(piece[row][col]);
            }
        shuffle();
        setSize(300, 300);
        setVisible(true);
    }
    public void shuffle() {
        Random random = new Random();
        int row, col;
        for (int value = 1; value < 16; value++) {
            do {
                row = random.nextInt(4);
                col = random.nextInt(4);
            } while (piece[row][col].isVisible());
            piece[row][col].setText("" + value);
            piece[row][col].setVisible(true);
        }
    }
    public void actionPerformed(ActionEvent click) {
        int row0 = 0, col0 = 0, rowN = 0, colN = 0;
        for (int row = 0; row < piece.length; row++)
            for (int col = 0; col < piece.length; col++)
                if (!piece[row][col].isVisible()) {
                    row0 = row;
                    col0 = col;
                }
                else if (piece[row][col] ==
                        click.getSource()) {
                    rowN = row;
                    colN = col;
                }
        if (Math.abs(row0 - rowN) +
                Math.abs(col0 - colN) == 1) {
            piece[row0][col0].setText(
                    piece[rowN][colN].getText());
            piece[row0][col0].setVisible(true);
            piece[rowN][colN].setVisible(false);
        }
    }


}
