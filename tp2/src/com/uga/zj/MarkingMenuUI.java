package com.uga.zj;

import java.awt.*;

public class MarkingMenuUI {
    private String[] listOfTab;
    private int x,y;
    private int r = 100;

    public MarkingMenuUI(String[] list, Point o){
        this.listOfTab = list;
        this.x = (int)o.getX();
        this.y = (int)o.getY();
    }

    public void paintMenu(Graphics g) {
        int d = r * 2;
        g.setColor(Color.GRAY);
        g.fillOval(x - r, y - r, d, d);
        g.setColor(Color.WHITE);
        g.drawOval(x - r, y - r, d, d);

        for (int i = 0; i < listOfTab.length; i++) {
            g.drawLine(x, y, x + (int) (r * (Math.cos(i * 2 * Math.PI / listOfTab.length))),
                    y + (int) (r * (Math.sin(i * 2 * Math.PI / listOfTab.length))));
            g.drawString(listOfTab[i],
                    x + (int) ((r / 2) * Math.cos((i * 2 * Math.PI / listOfTab.length) + Math.PI / listOfTab.length)),
                    y + (int) ((r / 2) * Math.sin((i * 2 * Math.PI / listOfTab.length) + Math.PI / listOfTab.length)));
        }
    }

    public void update(Graphics g, Point cur){
        int curX = (int)cur.getX();
        int curY = (int)cur.getY();

        paintMenu(g);

        double angle = Math.atan2(curY - y, curX - x);
        double temp = angle / (2 * Math.PI / listOfTab.length) + 1;
        int i = (int)Math.floor(temp);
        g.setColor(Color.BLACK);
        g.drawLine(x, y, x + (int) (r * (Math.cos((i-1) * 2 * Math.PI / listOfTab.length))),
                y + (int) (r * (Math.sin((i-1) * 2 * Math.PI / listOfTab.length))));
        g.drawLine(x, y, x + (int) (r * (Math.cos(i * 2 * Math.PI / listOfTab.length))),
                y + (int) (r * (Math.sin(i * 2 * Math.PI / listOfTab.length))));
    }

    public int getSelectedItem(Point cur){
        int curX = (int)cur.getX();
        int curY = (int)cur.getY();

        double angle = Math.atan2(curY - y, curX - x);
        int i = (int) Math.floor(angle / (2 * Math.PI / listOfTab.length));
        System.out.println(i);
        return i;

    }

    public boolean isInMenu(Point cur){
        int curX = (int)cur.getX();
        int curY = (int)cur.getY();

        int d = (int) Math.floor(Math.sqrt(Math.pow(curX-this.x,2) + Math.pow(curY-this.y,2)));

        return d <= r ? true : false;
    }
}
