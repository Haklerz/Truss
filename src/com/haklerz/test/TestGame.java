package com.haklerz.test;

import java.awt.event.KeyEvent;

import com.haklerz.truss.Game;
import com.haklerz.truss.Input;
import com.haklerz.truss.Draw;
import com.haklerz.truss.Window;

public class TestGame implements Game {

    private int x, y;
    private long frameCounter = 0;
    private long startTime;

    public static void main(String[] args) {
        new Window("Test", 15, 7, new TestGame()).run();
    }

    public TestGame() {
        this.x = 20;
        this.y = 20;
    }

    @Override
    public void loop(Draw renderer, Input input) {
        if (System.nanoTime() - startTime > 1000000000 / 4) {
            System.out.println("fps: " + frameCounter * 4);
            frameCounter = 0;
            startTime = System.nanoTime();
        }

        renderer.setColor(0, 255, 0);
        if (input.isMouseButtonDown(1)) {
            renderer.fill(input.getMouseX(), input.getMouseY());
            renderer.fill(input.getMouseX() + 1, input.getMouseY());
            renderer.fill(input.getMouseX() - 1, input.getMouseY());
            renderer.fill(input.getMouseX(), input.getMouseY() + 1);
            renderer.fill(input.getMouseX(), input.getMouseY() - 1);
        }

        if (input.isMouseButtonDown(3)) {
            renderer.setColor(0, 0, 0);
            renderer.fill();
        }
        
        frameCounter++;
    }
}
