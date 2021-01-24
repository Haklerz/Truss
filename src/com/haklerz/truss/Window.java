package com.haklerz.truss;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Transparency;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.*;

import javax.swing.JFrame;

public class Window implements Runnable {

    private final Game game;
    private final Canvas canvas;
    private final JFrame frame;
    private final int WIDTH;
    private final int HEIGHT;

    public Window(String title, int width, int height, Game game) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.game = game;

        this.canvas = new Canvas();
        canvas.setIgnoreRepaint(true);
        canvas.setSize(width, height);
        canvas.setBackground(Color.decode("0x181d26"));

        this.frame = new JFrame(title);
        frame.setIgnoreRepaint(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.add(canvas);
        frame.pack();
        frame.setMinimumSize(frame.getSize());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void run() {
        GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice().getDefaultConfiguration();
        BufferedImage screen = graphicsConfiguration.createCompatibleImage(WIDTH, HEIGHT, Transparency.OPAQUE);
        int[] pixels = ((DataBufferInt) screen.getRaster().getDataBuffer()).getData();

        canvas.createBufferStrategy(2);
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();

        Renderer renderer = new Renderer(pixels, WIDTH, HEIGHT);

        while (true) {
            game.loop(renderer);

            Graphics graphics = bufferStrategy.getDrawGraphics();

            int s = Math.max(Math.min(canvas.getWidth() / WIDTH, canvas.getHeight() / HEIGHT), 1);
            int x = (canvas.getWidth() - WIDTH * s) >> 1;
            int y = (canvas.getHeight() - HEIGHT * s) >> 1;
            graphics.drawImage(screen, x, y, WIDTH * s, HEIGHT * s, null);

            graphics.dispose();
            if (!bufferStrategy.contentsLost()) {
                bufferStrategy.show();
            }
        }
    }
}
