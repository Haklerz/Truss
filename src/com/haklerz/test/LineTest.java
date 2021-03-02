package com.haklerz.test;

import com.haklerz.truss.Game;
import com.haklerz.truss.Input;
import com.haklerz.truss.Renderer;
import com.haklerz.truss.Window;

public class LineTest implements Game {

    public static void main(String[] args) {
        new Window("Line", 16 * 64, 9 * 64, new LineTest()).run();
    }

    @Override
    public void loop(Renderer renderer, Input input) {
        renderer.clear();
        renderer.setColor(255, 255, 255);
        renderer.line(40, 100, input.getMouseX(), input.getMouseY());
    }
    
}
