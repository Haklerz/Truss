package com.haklerz.truss;

public class Renderer {
    private final int[] pixels;
    private final int WIDTH;
    private final int HEIGHT;

    private int currentColor;

    Renderer(int[] pixels, int width, int height) {
        this.pixels = pixels;
        this.WIDTH = width;
        this.HEIGHT = height;
    }

    private static int clamp(int x, int min, int max) {
        return (x < min) ? min : (x > max) ? max : x;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public void setColor(int r, int g, int b) {
        currentColor = clamp(r, 0, 255) << 16 | clamp(g, 0, 255) << 8 | clamp(b, 0, 255);
    }

    public void setPixel(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT)
            return;

        pixels[x + y * WIDTH] = currentColor;
    }

    public void fill(int x, int y, int width, int height) {
        // TODO: More bounds checking

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                setPixel(x + i, y + j);

    }

    public void clear() {
        for (int i = 0; i < WIDTH * HEIGHT; i++)
            pixels[i] = 0;
    }

    /**
     * Yeet
     */
    public void fade() {
        for (int i = 0; i < pixels.length; i++) {
            int pixel = pixels[i];
            int r = (pixel & 0xFF0000) >> 16;
            int g = (pixel & 0xFF00) >> 8;
            int b = pixel & 0xFF;

            if (r > 0)
                r--;

            if (g > 0)
                g--;

            if (b > 0)
                b--;

            pixel = r << 16 | g << 8 | b;
            pixels[i] = pixel;
        }
    }
}
