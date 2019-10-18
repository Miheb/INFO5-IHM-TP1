package com.uga.zj;

public class Model {
    public Home home;
    public House A;
    public House B;

    public int disToA = 300;
    public int disToB = 300;

    public int bedMin = 1;
    public int bedMax = 7;

    public int costMin = 50;
    public int costMax = 500;

    public Model(Home home){
        this.home = home;
    }

    public int getDisToA() {
        return disToA;
    }

    public void setDisToA(int disToA) {
        this.disToA = disToA;
    }

    public int getDisToB() {
        return disToB;
    }

    public void setDisToB(int disToB) {
        this.disToB = disToB;
    }

    public int getBedMin() {
        return bedMin;
    }

    public void setBedMin(int bedMin) {
        this.bedMin = bedMin;
    }

    public int getBedMax() {
        return bedMax;
    }

    public void setBedMax(int bedMax) {
        this.bedMax = bedMax;
    }

    public int getCostMin() {
        return costMin;
    }

    public void setCostMin(int costMin) {
        this.costMin = costMin;
    }

    public int getCostMax() {
        return costMax;
    }

    public void setCostMax(int costMax) {
        this.costMax = costMax;
    }

    public Home getHome() {
        return home;
    }
}
