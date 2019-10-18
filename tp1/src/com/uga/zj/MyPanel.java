package com.uga.zj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

class MyPanel extends JPanel implements MouseListener
{
    private Image image;	//图像缓冲
    private Graphics og;
    private ArrayList<House> list;
    private House pointA;
    private House pointB;
    public int x1, y1, x2, y2;
    private int count =0;

    public MyPanel(){
        addMouseListener(this);
    }
    public void display()
    {
        if(og == null)
        {
            image = this.createImage(this.getWidth(),this.getHeight());
            if(image != null)og = image.getGraphics();
        }

        if(og != null)
        {
            super.paint(og);

            og.setColor(Color.YELLOW);
            for(int i=0;i< list.size();i++){
                House temp = list.get(i);
                og.fillOval(temp.getX(),temp.getY(),5,5);
            }
            og.setColor(Color.RED);
            if(pointA != null) {
                og.fillOval(pointA.getX(), pointA.getY(), 7, 7);
                og.drawString("A", pointA.getX(), pointA.getY());
            }
            if(pointB != null) {
                og.fillOval(pointB.getX(), pointB.getY(), 7, 7);
                og.drawString("B",pointB.getX(),pointB.getY());
            }

        }
        this.repaint();
    }


    public void paint(Graphics g)
    {
        g.drawImage(image, 0, 0, this);
    }



    public void setList(ArrayList<House> list){
        this.list = list;
    }

    public House getA(){
        return pointA;
    }

    public void setA(House A){
        this.pointA = A;
    }

    public House getB(){
        return pointB;
    }

    public void setB(House B){
        this.pointB = B;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.print("mouse clicked");
        if(count ==0) {
            x1 = e.getX();
            y1 = e.getY();
            count = 1;
            pointA = new House(x1, y1, 300, 5);
        }
        else {
            x2 = e.getX();
            y2 = e.getY();
            count = 0;
            pointB = new House(x2, y2, 300, 5);
        }
        this.display();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
