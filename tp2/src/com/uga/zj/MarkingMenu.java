package com.uga.zj;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MarkingMenu {
    private MarkingMenuUI ui;
    private String[] listOfTab;
    private Point o;
    private Point cur;
    boolean active;

    public MarkingMenu(String[] list,Point o){
        this.listOfTab = list;
        this.o = o;
        ui = new MarkingMenuUI(this.listOfTab, this.o);
        active = true;
    }

    public String[] getListOfTab() {
        return listOfTab;
    }

    public void setUI(Graphics g){
        ui.paintMenu(g);
    }

    public void setCur(Point cur) {
        this.cur = cur;
    }

    public void updateUI(Graphics g){
        if(this.cur != null && active)
            ui.update(g,this.cur);
    }
    public int getSelectedItem(Point cur){
        return ui.getSelectedItem(cur);
    }

    public boolean isInMenu(Point cur){ return ui.isInMenu(cur);}

}
