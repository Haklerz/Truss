package com.haklerz.truss;

import javax.swing.JFrame;

public class Runner {

    private final Game game;

    public Runner(Game game) {
        this.game = game;
    }

    public void run() {
        game.setup();

        JFrame frame = new JFrame();
        frame.setLocationRelativeTo(null);
        frame.setName("Truss Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}