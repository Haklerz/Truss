package com.haklerz.truss;

import java.awt.Color;
import java.awt.Graphics2D;

public class Draw {
    private Graphics2D graphics;
    private final int WIDTH, HEIGHT;

    public Draw(Setup window, Graphics2D graphics) {
        setGraphics(graphics);
        clear();
        this.WIDTH = window.getWidth();
        this.HEIGHT = window.getHeight();
    }

    void setGraphics(Graphics2D graphics) {
        this.graphics = graphics;
    }

    public void setColor(double r, double g, double b) {
        graphics.setColor(new Color((float) r, (float) g, (float) b));
    }
    
    public void setColor(double v) {
        setColor(v, v, v);
    }
    
    public void pixel(double x, double y) {
        graphics.fillRect(round(x), round(y), 1, 1);
    }

    public void rect(double x, double y, double width, double height) {
        graphics.drawRect(round(x), round(y), round(width), round(height));
    }

    public void rectFill(double x, double y, double width, double height) {
        graphics.fillRect(round(x), round(y), round(width), round(height));
    }

    public void circle(double x, double y, double r) {
        int d = round(r * 2);
        graphics.drawOval(round(x - r), round(y - r), d, d);
    }

    public void line(double x0, double y0, double x1, double y1) {
        graphics.drawLine(round(x0), round(y0), round(x1), round(y1));
    }

    private static int round(double x) {
        return (x >= 0) ? (int) (x + 0.5) : (int) (x - 0.5);
    }

    public void clear(double r, double g, double b) {
        setColor(r, g, b);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
    }

    public void clear() {
        clear(0, 0, 0);
    }

    public double getWidth() {
        return WIDTH;
    }

    public double getHeight() {
        return HEIGHT;
    }
}