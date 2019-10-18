package com.uga.zj;

public class App {
    public static void main(String[] args) {
        // Assemble all the pieces of the MVC
        Home h = new Home(100);
        Model m = new Model(h);
        View v = new View("Dynamitc HomeFinder");
        Controller c = new Controller(m, v);
        c.initController();
    }
}
