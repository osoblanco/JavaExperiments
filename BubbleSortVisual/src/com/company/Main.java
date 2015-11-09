package com.company;
import java.util.stream.*;
import java.applet.*;
import  javax.swing.*;
import  java.awt.*;
import  java.awt.event.*;
import  java.lang.*;
import  java.util.*;
import  java.applet.*;

public class Main extends JFrame  implements ActionListener{

    private int[] array = new int[100];
    private int countNum = 0;


    private JLabel[] numbers= new JLabel[100];

    private NumPanel screen = new NumPanel();

    private JPanel tools = new JPanel(new GridLayout(3,3));
    private JButton random = new JButton("Random");
    private JButton straight = new JButton("straight");
    private JButton sort= new JButton("Sort");
    private JTextField count = new JTextField(4);
    public Main()
    {

        setVisible(true);
        setSize(500, 400);
        setLayout(new GridLayout(2,1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        random.addActionListener(this);
        straight.addActionListener(this);
        sort.addActionListener(this);
        count.addActionListener(this);


        tools.add(random);
        tools.add(straight);
        tools.add(sort);
        tools.add(new JLabel("Count: "));
        tools.add(count);

        for (int row = 0; row < numbers.length; row++) {
            array[row]=0;
        }

        for (int row = 0; row < numbers.length; row++) {
            numbers[row] = new JLabel("0");
            numbers[row].setVisible(true);
            screen.add(numbers[row]);
        }

        this.add(tools);
        this.add(screen);



    }

    private class NumPanel extends JPanel {
        public NumPanel (){
            setVisible(true);
            setSize(400,10);
        }
    }


    public void actionPerformed(ActionEvent click)
    {
        if(click.getSource()==random)
        {
            this.fillRandom();

        }
        if(click.getSource()==straight)
        {
            this.fillStraight();

        }
        if(click.getSource()==sort)
        {
            this.Sort();
        }

    }

    private void fillRandom() {
        countNum = 0;
        Random rand = new Random();
        for(int i = 0; i < array.length; i++){
            array[i] = rand.nextInt(100);
        }
        for(int i = 0; i < numbers.length; i++){
            String str= ""+array[i];
            numbers[i].setText(str);
        }

    }
    private void fillStraight() {
        countNum = 0;
        for(int i = 0; i < array.length; i++){
            array[i] = i;
        }
        for(int i = 0; i < numbers.length; i++){
            String str= ""+array[i];
            numbers[i].setText(str);
        }

    }
    private void Sort() {
        countNum = 0;
        for(int i = 0; i < array.length; i++){
            for(int k=1;k<10000000;k++)
            {}
            for(int j=1;j<array.length-i;j++)
            {
                countNum++;
                if(array[j] < array[j-1]) {
                    for(int k=1;k<10000000;k++)
                    {}
                    int temp = array[j];
                    array[j] = array[j - 1];
                    numbers[j].setText(""+array[j]);
                    for(int k=1;k<10000000;k++)
                    {}
                        array[j - 1] = temp;
                    numbers[j - 1].setText("" + array[j - 1]);
                    for(int k=1;k<10000000;k++)
                    {}

                }

                count.setText(""+countNum);

            }
        }
    }


    public static void main(String args[]) {
        new Main();
    }
}