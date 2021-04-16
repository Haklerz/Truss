package com.haklerz.proc;

import com.haklerz.truss.*;

public class ProcGame implements Game {

    public ProcGame() {
        
        new Window.Builder().setTitle("Proc").setResolution(128 * 2, 128 * 2).setGame(this).build().start();
    }

    public static void main(String[] args) {
        new ProcGame();
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Renderer renderer) {
    }
}