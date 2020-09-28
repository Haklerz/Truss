package com.haklerz.truss;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Runner implements Runnable {

    private final Game game;
    private final Setup window;
    private final Canvas canvas;
    private final JFrame frame;

    public Runner(Game game) {
        this.game = game;

        this.window = new Setup();
        game.init(window);

        this.canvas = new Canvas();
        canvas.setIgnoreRepaint(true);
        canvas.setSize(window.getWidth(), window.getHeight());

        this.frame = new JFrame(window.getTitle());
        frame.setIgnoreRepaint(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(canvas);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void run() {
        canvas.createBufferStrategy(2);
        BufferStrategy buffer = canvas.getBufferStrategy();

        GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice().getDefaultConfiguration();
        BufferedImage backBuffer = graphicsConfiguration.createCompatibleImage(window.getWidth(),
                window.getHeight());

        Graphics2D graphics = backBuffer.createGraphics();
        Draw draw = new Draw(window, graphics);
        graphics.dispose();

        Time time = new Time(System.nanoTime());
        while (true) {
            time.update(System.nanoTime());

            graphics = backBuffer.createGraphics();
            draw.setGraphics(graphics);
            game.loop(time, draw);
            graphics.dispose();

            graphics = (Graphics2D) buffer.getDrawGraphics();
            graphics.drawImage(backBuffer, 0, 0, null);
            graphics.dispose();

            buffer.show();
            Thread.yield();
        }
    }
}