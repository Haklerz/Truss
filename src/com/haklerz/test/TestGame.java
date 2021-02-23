package com.haklerz.test;

import com.haklerz.truss.Game;
import com.haklerz.truss.Renderer;
import com.haklerz.truss.Window;

public class TestGame implements Game {
    public static void main(String[] args) {
        new Window("Test", 64, 36, new TestGame()).run();
    }

    public TestGame() {
    }

    @Override
    public void loop(Renderer renderer) {

    }
}
