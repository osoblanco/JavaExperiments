package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Main extends JFrame implements ActionListener{

    private int[] array = new int[100];
    private int[] helper=new int[100];
    private int countNum = 0;

    private NumPanel screen = new NumPanel();
    private JPanel tools = new JPanel(new GridLayout(3,3));
    private JButton random = new JButton("Random");
    private JButton straight = new JButton("Straight");
    private JButton sort = new JButton("Sort");
    private JTextField count = new JTextField(4);

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        setVisible(true);
        setSize(1000, 200);
        setLayout(new GridLayout(2,1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        random.addActionListener(this);
        straight.addActionListener(this);
        sort.addActionListener(this);

        tools.add(random);
        tools.add(straight);
        tools.add(sort);
        tools.add(new JLabel("Count: "));
        tools.add(count);


        this.add(screen);
        this.add(tools);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == random) {
            fillRandom();
        }
        else if(e.getSource() == straight) {
            fillStraight();
        }
        else if(e.getSource() == sort) {
            iterate();
            this.mergesort(0,array.length-1);
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
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count.setText(""+countNum);
        screen.paintComponent(screen.getGraphics());
        screen.repaint();
    }

    private void mergesort(int low, int high) {
        // check if low is smaller then high, if not then the array is sorted
        if (low < high) {
            // Get the index of the element which is in the middle
            int middle = low + (high - low) / 2;
            // Sort the left side of the array
            mergesort(low, middle);
            // Sort the right side of the array
            mergesort(middle + 1, high);
            // Combine them both
            merge(low, middle, high);
        }
    }

    private void merge(int low, int middle, int high) {

        // Copy both parts into the helper array
        for (int i = low; i <= high; i++) {
            helper[i] = array[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
            countNum++;
            if (helper[i] <= helper[j]) {
                countNum++;
                array[k] = helper[i];
                i++;
                iterate();
            } else {
                countNum++;
                array[k] = helper[j];
                j++;
                iterate();
            }
            k++;
            iterate();

        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            array[k] = helper[i];
            k++;
            i++;
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