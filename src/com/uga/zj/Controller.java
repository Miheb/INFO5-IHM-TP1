package com.uga.zj;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model m, View v){
        model = m;
        view = v;
        initView();
    }

    public void initView(){

    }
    public void initController(){
        view.getReset().addActionListener(e -> reset());
        view.getQuit().addActionListener(e -> quit());
        view.getGraphPanel().setList(model.getHome().getList());
        view.getGraphPanel().display();
        view.getDistanceToA().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.print("change value");
                int value = view.getDistanceToA().getValue();
                model.setDisToA(value * 10);
                paintWithCondition();
            }
        });
        view.getDistanceToB().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.print("change value");
                int value = view.getDistanceToB().getValue();
                model.setDisToB(value * 10);
                paintWithCondition();
            }
        });
        view.getBedrooms().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = view.getBedrooms().getValue();
                int upperValue = value + view.getBedrooms().getExtent();
                model.setBedMin(value);
                model.setBedMax(upperValue);
                paintWithCondition();
            }
        });

        view.getCost().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = view.getCost().getValue();
                int upperValue = value + view.getCost().getExtent();
                model.setCostMin(value);
                model.setCostMax(upperValue);
                paintWithCondition();
            }
        });
    }


    public void paintWithCondition(){
        model.A = view.getGraphPanel().getA();
        model.B = view.getGraphPanel().getB();
        ArrayList<House> houses = model.getHome().getList();
        ArrayList<House> newHouse = new ArrayList<House>();
        for(int i = 0; i < houses.size(); i++){
            House temp = houses.get(i);
            int disToA = (int) Math.sqrt(Math.pow((temp.getX() - model.A.getX()),2) + Math.pow((temp.getY() - model.A.getY()),2));
            int disToB = (int) Math.sqrt(Math.pow((temp.getX() - model.B.getX()),2) + Math.pow((temp.getY() - model.B.getY()),2));
            if(disToA  > model.getDisToA() || disToB > model.getDisToB() || temp.getPrice() > model.getCostMax()
            || temp.getPrice() < model.getCostMin() || temp.getBedrooms() > model.getBedMax() || temp.getBedrooms() < model.getBedMin()){
                continue;
            }else{
                newHouse.add(temp);
            }
        }
        view.getGraphPanel().setList(newHouse);
        view.getGraphPanel().display();
    }


    public void reset(){
        model.setDisToA(300);
        model.setDisToB(300);
        model.setBedMin(1);
        model.setBedMax(7);
        model.setCostMin(50);
        model.setCostMax(500);
        initController();
    }

    public void quit(){
        System.exit(0);
    }

}
