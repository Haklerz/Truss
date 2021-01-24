package com.haklerz.truss;

public class Renderer {
    private final int[] pixels;
    private final int WIDTH;
    private final int HEIGHT;
    private int color;

    public Renderer(int[] pixels, int width, int height) {
        this.pixels = pixels;
        this.WIDTH = width;
        this.HEIGHT = height;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    private static int round(double x) {
        return x >= 0 ? (int) (x + 0.5) : (int) (x - 0.5);
    }

    private static int scaledClamp(double x) {
        int y = round(x * 255);
        if (y <= 0)
            return 0;

        if (y >= 255)
            return 255;

        return y;
    }

    public void setColor(double r, double g, double b) {
        color = scaledClamp(r) << 16 | scaledClamp(g) << 8 | scaledClamp(b);
    }

    public void setColor(double v) {
        int temp = scaledClamp(v);
        temp |= temp << 8;
        temp |= temp << 8;
        color = temp;
    }

    public void setPixel(double x, double y) {
        // TODO: Bounds checking

        pixels[round(x) + round(y) * WIDTH] = color;
    }

    public void fill(double x, double y, double width, double height) {
        // TODO: Bounds checking

        for (int i = 0; i < round(width); i++) {
            for (int j = 0; j < round(height); j++) {
                pixels[round(x) + i + (round(y) + j) * WIDTH] = color;
            }
        }
    }

    public void clear() {
        for (int i = 0; i < WIDTH * HEIGHT; i++) {
            pixels[i] = 0;
        }
    }
}
