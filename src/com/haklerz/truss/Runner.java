package com.haklerz.truss;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Runner implements Runnable {

    private final Game game;
    private final Config settings;
    private final Canvas canvas;
    private final JFrame frame;

    public Runner(Game game) {
        this.game = game;

        this.settings = new Config();
        game.setup(settings);

        this.canvas = new Canvas();
        canvas.setIgnoreRepaint(true);
        canvas.setSize(settings.getWidth(), settings.getHeight());

        this.frame = new JFrame(settings.getTitle());
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
        BufferedImage backBuffer = graphicsConfiguration.createCompatibleImage(settings.getWidth(), settings.getHeight());

        Graphics2D graphics = backBuffer.createGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, settings.getWidth(), settings.getHeight());
        graphics.dispose();
        
        Time time = new Time(System.nanoTime());
        Renderer renderer = new Renderer();

        while (true) {
            time.update(System.nanoTime());
            game.update(settings, time);

            graphics = backBuffer.createGraphics();
            renderer.setGraphics(graphics);
            game.draw(settings, renderer);
            graphics.dispose();

            graphics = (Graphics2D) buffer.getDrawGraphics();
            graphics.drawImage(backBuffer, 0, 0, null);
            graphics.dispose();

            buffer.show();
            Thread.yield();
        }
    }
}