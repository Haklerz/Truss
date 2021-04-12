package com.haklerz.truss;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Renderer {
    private final int WIDTH, HEIGHT;
    private final GraphicsConfiguration configuration;

    private Canvas canvas;
    private BufferedImage screen;
    private Graphics2D graphics;

    Renderer(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;

        this.configuration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
                .getDefaultConfiguration();

        this.canvas = new Canvas(configuration);
        canvas.setIgnoreRepaint(true);
        canvas.setBackground(Color.decode("0x373f47"));
        canvas.setSize(width, height);
        
        this.screen = configuration.createCompatibleImage(width, height, Transparency.OPAQUE);
    }

    Canvas getCanvas() {
        return canvas;
    }

    void initGraphics() {
        canvas.createBufferStrategy(2);
        graphics = screen.createGraphics();
    }

    void flip() {
        // TODO: Reduce heap reallocation

        BufferStrategy strategy = canvas.getBufferStrategy();
        if (strategy == null)
            return;

        Graphics2D canvasGraphics = (Graphics2D) strategy.getDrawGraphics();

        // Does not need to be recalculated every frame
        // TODO: Move this to a window event
        int scale = Math.max(Math.min(canvas.getWidth() / WIDTH, canvas.getHeight() / HEIGHT), 1);
        int x = (canvas.getWidth() - WIDTH * scale) / 2;
        int y = (canvas.getHeight() - HEIGHT * scale) / 2;

        canvasGraphics.drawImage(screen, x, y, WIDTH * scale, HEIGHT * scale, null);
        canvasGraphics.dispose();

        strategy.show();
        Toolkit.getDefaultToolkit().sync();
    }

    private static int round(float x) {
        return x >= 0 ? (int)(x + 0.5f) : (int)(x - 0.5f);
    }

    public void setColor(float r, float g, float b) {
        graphics.setColor(new Color(r, g, b));
    }

    public void line(float x0, float y0, float x1, float y1) {
        graphics.drawLine(round(x0), round(y0), round(x1), round(y1));
    }
}
