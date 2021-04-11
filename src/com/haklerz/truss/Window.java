package com.haklerz.truss;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class Window implements Runnable {
    private final GraphicsConfiguration configuration;

    private JFrame frame;
    private Canvas canvas;
    private BufferedImage screen;
    private Game game;

    public Window(Game game, String title, int width, int height) {
        // Save callback to game
        this.game = game;

        // Get graphics configuration
        this.configuration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
                .getDefaultConfiguration();

        // Initialize screen buffer
        this.screen = getCompatibleImage(width, height);

        // Initialize canvas
        this.canvas = new Canvas(configuration);
        canvas.setBackground(Color.decode("0x0A0E14"));
        canvas.setSize(width, height);

        // Initialize frame
        this.frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Add canvas to frame
        frame.add(canvas);
        frame.pack();
        frame.setMinimumSize(frame.getSize());
    }

    @Override
    public void run() {
        // Make frame visible
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        Graphics2D screenGraphics = (Graphics2D) screen.getGraphics();

        while (canvas.isDisplayable()) {
            game.update();
            game.draw(screenGraphics);
            updateScreen();

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        frame.dispose();
    }

    /**
     * Draws the screen buffer to the canvas.
     */
    private void updateScreen() {
        // TODO: Optimize and make safe

        BufferStrategy strategy = canvas.getBufferStrategy();

        Graphics2D graphics = (Graphics2D) strategy.getDrawGraphics();
        graphics.drawImage(screen, 0, 0, screen.getWidth(), screen.getHeight(), null);
        graphics.dispose();

        strategy.show();
    }

    /**
     * Returns a compatible <code>BufferedImage</code>.
     * 
     * @param width  The width of the image
     * @param height The height of the image
     * @return The <code>BufferedImage</code>
     */
    private BufferedImage getCompatibleImage(int width, int height) {
        return configuration.createCompatibleImage(width, height, Transparency.OPAQUE);
    }
}
