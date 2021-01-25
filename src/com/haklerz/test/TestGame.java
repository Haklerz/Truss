package com.haklerz.test;

import java.util.Random;

import com.haklerz.truss.Game;
import com.haklerz.truss.Renderer;
import com.haklerz.truss.Window;

public class TestGame implements Game {
    public static void main(String[] args) {
        new TestGame();
    }

    private Window window;
    private Random rng;

    public TestGame() {
        this.window = new Window("Test", 640, 360, this);
        this.rng = new Random();

        window.run();
    }

    @Override
    public void loop(Renderer renderer) {
        // renderer.clear();
        rng.setSeed(321873);

        for (int i = 0; i < renderer.getWidth() * renderer.getHeight(); i++) {
            renderer.setColor(rng.nextDouble());
            renderer.setPixel(i % renderer.getWidth(), i / renderer.getWidth());
        }
    }
}
