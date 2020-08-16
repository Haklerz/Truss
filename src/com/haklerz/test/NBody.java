package com.haklerz.test;

import com.haklerz.truss.*;

public class NBody implements Game {

    public static void main(String[] args) {
        new Runner(new NBody()).run();
    }

    @Override
    public void setup(Settings settings) {
        settings.setTitle("NBody Simulation");
        settings.setResolution(640 * 2, 360 * 2);
    }

    @Override
    public void update(Time time) {
    }

    @Override
    public void draw(Renderer renderer) {
    }
}