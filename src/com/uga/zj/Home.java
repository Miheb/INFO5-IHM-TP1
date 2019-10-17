package com.uga.zj;

import java.util.ArrayList;

public class Home {
    private int number;
    public static ArrayList<House> list;


    public Home(int n){
        this.number = n;
        list = new ArrayList<House>();
        for(int i = 0; i < number; i++){
            int x = (int) (Math.random() * 600);
            int y = (int) (Math.random() * 600);
            int price =(int) (Math.random() * 450) + 50;
            int bedrooms = (int) (Math.random() * 7);
            list.add(new House(x,y,price,bedrooms));
        }
    }

    public ArrayList<House> getList() {
        return list;
    }
}
