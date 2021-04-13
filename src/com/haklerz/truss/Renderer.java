package com.haklerz.truss;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Renderer {
    private final int WIDTH, HEIGHT;

    private Canvas canvas;
    private BufferedImage screen;
    private Graphics2D graphics;

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
        //Toolkit.getDefaultToolkit().sync(); // Maybe not needed?
    }

    private static int round(float x) {
        return x >= 0 ? (int) (x + 0.5f) : (int) (x - 0.5f);
    }

    public void setColor(float r, float g, float b) {
        graphics.setColor(new Color(r, g, b));
    }

    public void line(float x0, float y0, float x1, float y1) {
        graphics.drawLine(round(x0), round(y0), round(x1), round(y1));
    }

    public void blit(Texture texture, float x, float y) {
        if (texture == null)
            return;

        graphics.drawImage(texture.getImage(), round(x), round(y), null);
    }

    public void clear() {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
    }
}
