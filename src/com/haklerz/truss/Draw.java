package com.haklerz.truss;

public class Draw {
    private final int[] pixels;
    public final int WIDTH;
    public final int HEIGHT;

    private int currentColor;

    /**
     * Creates a Draw component.
     * 
     * @param pixels
     * @param width
     * @param height
     */
    Draw(int[] pixels, int width, int height) {
        this.pixels = pixels;
        this.WIDTH = width;
        this.HEIGHT = height;
    }

    /**
     * Clamps a value x to a range [min, max]
     * 
     * @param x   the value to be clamped
     * @param min the minimum value
     * @param max the maximum value
     * @return the clamped value
     */
    private static int clamp(int x, int min, int max) {
        return (x < min) ? min : (x > max) ? max : x;
    }

    /**
     * Sets the current color.
     * 
     * @param r the red component
     * @param g the green component
     * @param b the blue component
     */
    public void setColor(int r, int g, int b) {
        currentColor = clamp(r, 0, 255) << 16 | clamp(g, 0, 255) << 8 | clamp(b, 0, 255);
    }

    /**
     * Fills a pixel with the current color.
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void fill(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT)
            return;

        pixels[x + y * WIDTH] = currentColor;
    }

    /**
     * Fills a rectangle with the current color.
     * 
     * @param x      the top left x coordinate
     * @param y      the top left y coordinate
     * @param width  the width
     * @param height the height
     */
    public void fill(int x, int y, int width, int height) {
        // TODO: More bounds checking
        // TODO: Handle negative width and height

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                fill(x + i, y + j);
    }

    /**
     * Fills the whole screen with the current color.
     */
    public void fill() {
        for (int i = 0; i < pixels.length; i++)
            pixels[i] = currentColor;
    }

    /**
     * Draws a line from (x0, y0) to (x1, y1) with the current color.
     * 
     * @param x0
     * @param y0
     * @param x1
     * @param y1
     */
    public void line(int x0, int y0, int x1, int y1) {
        int deltaX = Math.abs(x1 - x0);
        int sx = x0 < x1 ? 1 : -1;
        int deltaY = -Math.abs(y1 - y0);
        int sy = y0 < y1 ? 1 : -1;
        int error = deltaX + deltaY;

        while (true) {
            fill(x0, y0);
            if (x0 == x1 && y0 == y1) 
                break;
            
            int error2 = error * 2;
            if (error2 >= deltaY) {
                error += deltaY;
                x0 += sx;
            }
            if (error2 <= deltaX) {
                error += deltaX;
                y0 += sy;
            }
        }
    }

    public void circle(int x, int y, int r) {
        int currentX = r;
        int currentY = 0;

        int errorSum = 3 - 2 * r;

        while (currentY <= currentX) {
            fill(x + currentX, y + currentY);
            fill(x + currentY, y + currentX);
            fill(x - currentX, y + currentY);
            fill(x - currentY, y + currentX);
            fill(x - currentX, y - currentY);
            fill(x - currentY, y - currentX);
            fill(x + currentX, y - currentY);
            fill(x + currentY, y - currentX);

            if (errorSum <= 0) {
                errorSum += 4 * currentY + 6;
                currentY++;
            } else {
                errorSum += 4 * (currentY - currentX) + 10;
                currentX--;
                currentY++;
            }
        }
    }
}
