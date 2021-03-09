package com.haklerz.truss;

import java.awt.Color;
import java.awt.Graphics2D;

public class Test implements Game {

    public static void main(String[] args) {
        new Window("Yeet", 640, 360, new Test()).run();
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setBackground(Color.BLACK);

    }

}
