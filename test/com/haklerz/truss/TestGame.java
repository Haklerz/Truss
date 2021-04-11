package com.haklerz.truss;

import java.awt.Color;
import java.awt.Graphics2D;

public class TestGame implements Game {

    public static void main(String[] args) {
        new TestGame();
    }

    public TestGame() {
        Window window = new Window(this, "Test Game", 640, 360);

        new Thread(window).start();
    }

    @Override
    public void update() {
        System.out.println("Updated");
    }

    @Override
    public void draw(Graphics2D graphics) {
        System.out.println("Drew");
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, 640, 360);
    }
}
