package com.haklerz.truss;

import java.awt.Color;
import java.awt.Graphics2D;

public class Renderer {
    private Graphics2D graphics;

    void setGraphics(Graphics2D graphics) {
        this.graphics = graphics;
    }

    public void setColor(double r, double g, double b) {
        graphics.setColor(new Color((float) r, (float) g, (float) b));
    }

    public void rect(double x, double y, double width, double height) {
        graphics.drawRect(round(x), round(y), round(width), round(height));
    }

    public void circle(double x, double y, double r) {
        int d = round(r * 2);
        graphics.drawOval(round(x - r), round(y - r), d, d);
    }

    public void line(double x0, double y0, double x1, double y1) {
        graphics.drawLine(round(x0), round(y0), round(x1), round(y1));
    }

    public void clear() {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, 1280, 720);
    }

    private static int round(double x) {
        return (x >= 0) ? (int) (x + 0.5) : (int) (x - 0.5);
    }

}