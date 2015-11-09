package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Main extends JFrame implements ActionListener{

    private int[] array = new int[100];
    private int countNum = 0;

    private NumPanel screen = new NumPanel();
    private JPanel tools = new JPanel(new GridLayout(3,3));
    private JButton random = new JButton("Random");
    private JButton straight = new JButton("Straight");
    private JButton start = new JButton("Sort");
    private JTextField count = new JTextField(4);

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        setVisible(true);
        setSize(400, 200);
        setLayout(new GridLayout(2,1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        System.out.println("Constructor invoked");
        random.addActionListener(this);
        straight.addActionListener(this);
        start.addActionListener(this);

        System.out.println("Listeners added");
        tools.add(random);
        tools.add(straight);
        tools.add(start);
        tools.add(new JLabel("Count: "));
        tools.add(count);


        this.add(screen);
        this.add(tools);
        System.out.println("Panels added");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == random) {
            fillRandom();
        }
        else if(e.getSource() == straight) {
            fillStraight();
        }
        else if(e.getSource() == start) {
            iterate();
            startSort();
        }
    }

    private void fillRandom() {
        countNum = 0;
        Random rand = new Random();
        for(int i = 0; i < array.length; i++){
            array[i] = rand.nextInt(100);
        }

        iterate();
    }

    private void fillStraight() {
        countNum = 0;
        for(int i = 0; i < array.length; i++){
            array[i] = i + 1;
        }
        iterate();
    }

    public void iterate() {
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count.setText(""+countNum);
        screen.paintComponent(screen.getGraphics());
        screen.repaint();
    }

    private void startSort() {
        int temp;
        for(int i = 0 ; i < array.length; i++) {
            for(int j = 1; j < array.length - i; j++) {
                countNum++;
                if(array[j] < array[j-1]) {
                    temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                    iterate();
                }
            }
        }
    }

    private class NumPanel extends JPanel {
        public NumPanel (){
            setVisible(true);
            setSize(400,10);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            String arrayString = "";
            for (int i = 0; i < Main.this.array.length; i++) {
                arrayString += " "+Main.this.array[i];
            }
            g.drawString(arrayString,10, 20);
        }
    }
}