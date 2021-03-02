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
    /*
    public void line(int x0, int y0, int x1, int y1) {
        double deltaX = x1 - x0;
        double deltaY = y1 - y0;
        double deltaError = Math.abs(deltaY / deltaX);
        double error = 0;
        int y = y0;
        for (int x = x0; x < x1; x++) {
            setPixel(x, y);
            error += deltaError;
            if (error >= 0.5) {
                y += Math.signum(deltaY);
                error -= 1;
            }
        }
    }
    */

    public void line(int x0, int y0, int x1, int y1) {
        int dx = Math.abs(x1 - x0);
        int sx = x0 < x1 ? 1 : -1;
        int dy = -Math.abs(y1 - y0);
        int sy = y0 < y1 ? 1 : -1;
        int err = dx + dy;
        while (true) {
            setPixel(x0, y0);
            if (x0 == x1 && y0 == y1) 
                break;
            
            int e2 = 2 * err;
            if (e2 >= dy) {
                err += dy;
                x0 += sx;
            }
            if (e2 <= dx) {
                err += dx;
                y0 += sy;
            }
        }
    }

    /*
    plotLine(int x0, int y0, int x1, int y1)
    dx =  abs(x1-x0);
    sx = x0<x1 ? 1 : -1;
    dy = -abs(y1-y0);
    sy = y0<y1 ? 1 : -1;
    err = dx+dy;   
    while (true)   
        plot(x0, y0);
        if (x0 == x1 && y0 == y1) break;
        e2 = 2*err;
        if (e2 >= dy)  
            err += dy;
            x0 += sx;
        end if
        if (e2 <= dx) 
            err += dx;
            y0 += sy;
        end if
    end while
    */
}
