package com.haklerz.truss;

public class Test implements Game {

    public static void main(String[] args) {
        new Test();
    }

    public Test() {
        new Window("Test", 640, 360, this).run();
    }

    @Override
    public void update(float dt) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void draw(Texture graphics) {
        graphics.clear();
        graphics.color(1, 0, 0);
        graphics.rect(20, 20, 50, 70);
        
    }
}
