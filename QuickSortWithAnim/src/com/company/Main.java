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
            this.quickSort(array,0,array.length-1);
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
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count.setText(""+countNum);
        screen.paintComponent(screen.getGraphics());
        screen.repaint();
    }

    private void quickSort(int[] arr,int low,int high) {
        if (arr == null || arr.length == 0)
            return;

        if (low >= high)
            return;
         int i=low,j=high;
        int pivot=arr[(low+high)/2];
        while(i<=j) //
        {
            countNum++;
            while (arr[i]<pivot)
            {
                countNum++;
                i++;
            }
            while(arr[j]>pivot)
            {
                countNum++;
                j--;
            }
            if(i<=j)
            {
                countNum++;
                int temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
                i++;
                j--;
                iterate();
            }
        }

        if(low<j)
            quickSort(arr,low,j);
        if(high>i)
            quickSort(arr,i,high);

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