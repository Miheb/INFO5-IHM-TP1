package com.uga.zj;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.BoxView;

public class View {
    // View uses Swing framework to display UI to user
    private JFrame frame;
    private MyPanel graphPanel;
    private JPanel buttonsPanel1;
    private JPanel buttonsPanel2;
    private JPanel buttonsPanel3;
    private JPanel buttonsPanel4;
    private JPanel buttonsPanel5;
    private JPanel buttonsPanel6;
    private JPanel buttonsPanel7;
    private JLabel titleTextfield;

    private JButton reset;
    private JButton quit;
    private JButton save;
    private JButton print;

    private JLabel labelDisToA;
    private JSlider distanceToA;
    private JLabel labelDisToB;
    private JSlider distanceToB;
    private JLabel labelBedrooms;
    private RangeSlider bedrooms;
    private JLabel labelCost;
    private RangeSlider cost;

    private ButtonGroup lookat;
    private JLabel labelLookat;
    private JRadioButton hse;
    private JRadioButton th;
    private JRadioButton cnd;

    private ButtonGroup features;
    private JLabel labelFeatures;
    private JRadioButton grg;
    private JRadioButton fpl;
    private JRadioButton cac;
    private JRadioButton newbutton;

    public View(String title) {
        frame = new JFrame(title);
        frame.setLayout(null);
        frame.setSize(800, 700);
        //frame.setVisible(true);
        // Create UI elements
        graphPanel = new MyPanel();
        graphPanel.setBackground(Color.BLACK);
        graphPanel.setBounds(0,0,600,700);

        buttonsPanel5 = new JPanel();
        titleTextfield = new JLabel("Dynamic \n HomeFinder");
//        titleTextfield.setSize(160,20);
        reset = new JButton("Reset");
        quit = new JButton("Quit");
        save = new JButton("Save");
        print = new JButton("print");
        buttonsPanel5.add(titleTextfield);
        buttonsPanel5.add(reset);
        buttonsPanel5.add(quit);
        buttonsPanel5.add(save);
        buttonsPanel5.add(print);
        buttonsPanel5.setBounds(600,0,200,100);

        buttonsPanel1 = new JPanel();
        labelDisToA = new JLabel("Distance To A");
        labelDisToA.setSize(160,20);
        distanceToA = new JSlider(0,30,30);
        distanceToA.setMajorTickSpacing(5);
        distanceToA.setMinorTickSpacing(10);
        distanceToA.setPaintTicks(true);
        distanceToA.setPaintLabels(true);
        buttonsPanel1.add(labelDisToA);
        buttonsPanel1.add(distanceToA);
        buttonsPanel1.setBounds(600,100,200,100);


        buttonsPanel2 = new JPanel();
        labelDisToB = new JLabel("Distance To B");
//        labelDisToB.setSize(160,20);
        distanceToB = new JSlider(0,30,30);
        distanceToB.setMajorTickSpacing(5);
        distanceToB.setMinorTickSpacing(10);
        distanceToB.setPaintTicks(true);
        distanceToB.setPaintLabels(true);
        buttonsPanel2.add(labelDisToB);
        buttonsPanel2.add(distanceToB);
        buttonsPanel2.setBounds(600,200,200,100);


        buttonsPanel3 = new JPanel();
        labelBedrooms = new JLabel("Bedrooms");
//        labelBedrooms.setSize(160,20);
        bedrooms = new RangeSlider();
        bedrooms.setSize(1000,700);
        bedrooms.setMinimum(1);
        bedrooms.setMaximum(7);
        bedrooms.setMajorTickSpacing(2);
        bedrooms.setMinorTickSpacing(1);
        bedrooms.setPaintTicks(true);
        bedrooms.setPaintLabels(true);
        bedrooms.setValue(1);
        bedrooms.setUpperValue(7);
        buttonsPanel3.add(labelBedrooms);
        buttonsPanel3.add(bedrooms);
        buttonsPanel3.setBounds(600,300,200,100);


        buttonsPanel4 = new JPanel();
        labelCost = new JLabel("Cost");
//        labelCost.setSize(160,20);
        cost = new RangeSlider();
        cost.setSize(1000,700);
        cost.setMinimum(50);
        cost.setMaximum(500);
        cost.setMajorTickSpacing(100);
        cost.setMinorTickSpacing(50);
        cost.setPaintTicks(true);
        cost.setPaintLabels(true);
        cost.setValue(50);
        cost.setUpperValue(500);
        buttonsPanel4.add(labelCost);
        buttonsPanel4.add(cost);
        buttonsPanel4.setBounds(600,400,200,100);


        buttonsPanel6 = new JPanel();
        labelLookat = new JLabel("              Look at              ");
        lookat = new ButtonGroup();
        hse = new JRadioButton("HSE");
        th = new JRadioButton("TH");
        cnd = new JRadioButton("CND");
        lookat.add(hse);
        lookat.add(th);
        lookat.add(cnd);
        buttonsPanel6.add(labelLookat);
        buttonsPanel6.add(hse);
        buttonsPanel6.add(th);
        buttonsPanel6.add(cnd);
        buttonsPanel6.setBounds(600,500,200,50);


        buttonsPanel7 = new JPanel();
        labelFeatures = new JLabel("                   Features             ");
        labelFeatures.setBounds(0,0,200,30);
        buttonsPanel7.setLayout(null);
        features = new ButtonGroup();
        grg = new JRadioButton("GRG");
        grg.setBounds(20,30,80,30);
        fpl = new JRadioButton("FPL");
        fpl.setBounds(100,30,80,30);
        cac = new JRadioButton("CAC");
        cac.setBounds(20, 60,80,30);
        newbutton = new JRadioButton("NEW");
        newbutton.setBounds(100,60,80,30);
        buttonsPanel7.add(labelFeatures);
        features.add(grg);
        features.add(fpl);
        features.add(cac);
        features.add(newbutton);
        buttonsPanel7.add(grg);
        buttonsPanel7.add(fpl);
        buttonsPanel7.add(cac);
        buttonsPanel7.add(newbutton);
        buttonsPanel7.setBounds(600,550,200,150);





        frame.add(graphPanel);
        frame.add(buttonsPanel5);
        frame.add(buttonsPanel1);frame.add(buttonsPanel2);frame.add(buttonsPanel3);frame.add(buttonsPanel4);
        frame.add(buttonsPanel6);
        frame.add(buttonsPanel7);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        //graphPanel.display();

    }

    public JButton getReset() {
        return reset;
    }

    public JButton getQuit() {
        return quit;
    }

    public MyPanel getGraphPanel() {
        return graphPanel;
    }

    public JSlider getDistanceToA() {return distanceToA;}

    public JSlider getDistanceToB() {return distanceToB;}

    public RangeSlider getBedrooms() {return bedrooms;}

    public RangeSlider getCost() {return cost;}
}

