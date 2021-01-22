package com.haklerz.truss;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Transparency;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.*;

import javax.swing.JFrame;

public class Window implements Runnable {

    private final Game GAME;
    private final Canvas canvas;
    private final JFrame frame;
    private final int WIDTH;
    private final int HEIGHT;

    public Window(String title, int width, int height, Game game) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.GAME = game;

        this.canvas = new Canvas();
        canvas.setIgnoreRepaint(true);
        canvas.setSize(width, height);

        this.frame = new JFrame(title);
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
        GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice().getDefaultConfiguration();
        BufferedImage image = graphicsConfiguration.createCompatibleImage(WIDTH, HEIGHT, Transparency.OPAQUE);
        int[] buffer = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = i;
        }

        canvas.createBufferStrategy(2);
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();

        while (true) {
            Graphics graphics = bufferStrategy.getDrawGraphics();

            graphics.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);

            graphics.dispose();
            if (!bufferStrategy.contentsLost()) {
                bufferStrategy.show();
            }
        }
    }
}
