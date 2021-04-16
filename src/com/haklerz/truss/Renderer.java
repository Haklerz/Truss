package com.haklerz.truss;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
// import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Renderer {
    public final int WIDTH, HEIGHT;

    private Canvas canvas;
    private BufferedImage screen;
    private Graphics2D graphics;

    private Color strokeColor;
    private Color fillColor;

    Renderer(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;

        this.canvas = new Canvas(DefaultGraphicsConfiguration.getInstance());
        canvas.setIgnoreRepaint(true);
        canvas.setBackground(Color.decode("0x373f47"));
        canvas.setSize(width, height);

        this.screen = DefaultGraphicsConfiguration.getInstance().createCompatibleImage(width, height,
                Transparency.OPAQUE);
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
        if (!canvas.isShowing())
            return;

        BufferStrategy strategy = canvas.getBufferStrategy();
        Graphics2D canvasGraphics = (Graphics2D) strategy.getDrawGraphics();

        // Does not need to be recalculated every frame
        // TODO: Move this to a window event
        int scale = Math.max(Math.min(canvas.getWidth() / WIDTH, canvas.getHeight() / HEIGHT), 1);
        int x = (canvas.getWidth() - WIDTH * scale) / 2;
        int y = (canvas.getHeight() - HEIGHT * scale) / 2;

        canvasGraphics.drawImage(screen, x, y, WIDTH * scale, HEIGHT * scale, null);
        canvasGraphics.dispose();

        strategy.show();
        // Toolkit.getDefaultToolkit().sync(); // Maybe not needed?
    }

    private static int round(float x) {
        return x >= 0 ? (int) (x + 0.5f) : (int) (x - 0.5f);
    }

    public void stroke(float r, float g, float b, float a) {
        strokeColor = new Color(r, g, b, a);
    }

    public void stroke(float r, float g, float b) {
        strokeColor = new Color(r, g, b);
    }

    public void stroke(float v, float a) {
        strokeColor = new Color(v, v, v, a);
    }

    public void stroke(float v) {
        strokeColor = new Color(v, v, v);
    }

    public void stroke() {
        strokeColor = null;
    }

    public void fill(float r, float g, float b, float a) {
        fillColor = new Color(r, g, b, a);
    }

    public void fill(float r, float g, float b) {
        fillColor = new Color(r, g, b);
    }

    public void fill(float v, float a) {
        fillColor = new Color(v, v, v, a);
    }

    public void fill(float v) {
        fillColor = new Color(v, v, v);
    }

    public void fill() {
        fillColor = null;
    }

    public void pixel(float x, float y) {
        graphics.setColor(strokeColor);
        graphics.fillRect(round(x), round(y), 1, 1);
    }

    public void line(float x0, float y0, float x1, float y1) {
        graphics.setColor(strokeColor);
        graphics.drawLine(round(x0), round(y0), round(x1), round(y1));
    }

    public void rect(float x, float y, float width, float height) {
        if (fillColor != null) {
            graphics.setColor(fillColor);
            graphics.fillRect(round(x), round(y), round(width), round(height));
        }

        if (strokeColor != null) {
            graphics.setColor(strokeColor);
            graphics.drawRect(round(x), round(y), round(width), round(height));
        }
    }

    public void circle(float x, float y, float r) {
        if (fillColor != null) {
            graphics.setColor(fillColor);
            graphics.fillOval(round(x - r), round(y - r), round(r), round(r));
        }

        if (strokeColor != null) {
            graphics.setColor(strokeColor);
            graphics.drawOval(round(x - r), round(y - r), round(r), round(r));
        }
    }

    public void blit(Texture texture, float x, float y) {
        if (texture != null) {
            graphics.drawImage(texture.getImage(), round(x), round(y), null);
        }
    }

    public void print(String text, float x, float y) {
        if (strokeColor != null) {
            graphics.setColor(strokeColor);
            graphics.drawString(text, x, y);
        }
    }

    public void clear(float r, float g, float b) {
        graphics.setColor(new Color(r, g, b));
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
    }

    public void clear(float v) {
        graphics.setColor(new Color(v, v, v));
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
    }

    public void clear() {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
    }
}
