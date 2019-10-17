package com.uga.zj;

public class House {
    private int price;
    private int x;
    private int y;
    private int bedrooms;

    public House(int x, int y, int price,int bedrooms){
        this.x = x;
        this.y = y;
        this.price = price;
        this.bedrooms = bedrooms;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }
}
