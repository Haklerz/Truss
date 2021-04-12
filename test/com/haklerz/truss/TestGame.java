package com.haklerz.truss;

public class TestGame implements Game {

    public static void main(String[] args) {
        new TestGame();
    }

    public TestGame() {
        Window window = new Window(this, "Test Game", 256, 144);

        new Thread(window).start();
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Renderer renderer) {

        
    }
}
