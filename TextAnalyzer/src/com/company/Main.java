package com.company;
import java.util.stream.*;
import java.applet.*;
import  java.*;
import  javax.swing.*;
import  java.awt.*;
import  java.awt.event.*;
import  java.lang.*;
import  java.util.*;

public class Main extends JFrame  implements ActionListener{
    private JButton words = new JButton("Words");
    private JButton sents = new JButton("Sents");
    private JButton parags= new JButton("Parags");
    private JTextArea text= new JTextArea(20, 20);
    private JTextField count = new JTextField(4);
    public Main() {
        setLayout(new FlowLayout());
        add(words);
        add(sents);
        add(parags);
        add(text);
        add(new JLabel("Count:"));
        add(count);
        setSize(280, 420);
        setVisible(true);
        words.addActionListener(this);
        sents.addActionListener(this);
        parags.addActionListener(this);
        count.addActionListener(this);

    }
    public void actionPerformed(ActionEvent arg0)
    {
        if(arg0.getSource()==words)
        {System.out.println("words");}
        if(arg0.getSource()==count)
        {
            System.out.print(count.getText()+'\n');
            count.setText("");
        }

        String delim = " \t\n";
        if (arg0.getSource() == sents)
            delim = ".!?";
        else if (arg0.getSource() == parags)
            delim = "\n";
        StringTokenizer tokens = new
                StringTokenizer(text.getText(), delim);
        count.setText("" + tokens.countTokens());

    }
    public static void main(String args[]) {
        new Main();
    }
}