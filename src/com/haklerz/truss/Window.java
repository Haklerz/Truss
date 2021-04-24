package com.haklerz.truss;

import javax.swing.JFrame;

public class Window implements Runnable {
    private Game game;
    private JFrame frame;
    private CanvasTexture graphics;

    public Window(String title, int width, int height, Game game) {
        this.game = game;

        this.frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setIgnoreRepaint(true);

        this.graphics = new CanvasTexture(width, height);
        frame.add(graphics);
        frame.pack();
        graphics.createBufferStrategy(2);
        frame.setMinimumSize(frame.getSize());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void run() {
        while (frame.isDisplayable()) {
            game.update(0);
            game.draw(graphics);
            graphics.flip();
        }
    }
}
