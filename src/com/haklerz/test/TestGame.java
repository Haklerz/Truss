package com.haklerz.test;

import com.haklerz.truss.Game;
import com.haklerz.truss.Renderer;
import com.haklerz.truss.Window;

public class TestGame implements Game {

    int f = 0;

    public static void main(String[] args) {
        new Window("Test", 128, 72, new TestGame()).run();
    }

    @Override
    public void loop(Renderer renderer) {
        int n = renderer.getWidth() * renderer.getHeight();

        renderer.clear();

        for (int i = 0; i < n; i++) {
            renderer.setColor(0.5 + 0.5 * Math.sin(0.0007 * f + 0.001 * i), 0.5 + 0.5 * Math.sin(0.001 * f + 0.0007 * i), 0.5 + 0.5 * Math.sin(0.0005 * f + 0.0003 * i));
            renderer.setPixel(i % n, i / n);
        }

        f++;
    }
}
