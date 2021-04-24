package com.haklerz.truss;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public final class CanvasTexture extends Canvas implements Texture {
    private final int WIDTH;
    private final int HEIGHT;
    
    private BufferedImage screen;
    private Graphics2D graphics;

    protected CanvasTexture(int width, int height) {
        super(Texture.defaultGraphicsConfiguration);
        setIgnoreRepaint(true);
        setBackground(Color.decode("0x373f47")); // TODO: This is hardcoded
        setSize(width, height);

        this.WIDTH = width;
        this.HEIGHT = height;
        this.screen = Texture.defaultGraphicsConfiguration.createCompatibleImage(width, height, Transparency.OPAQUE);
        this.graphics = screen.createGraphics();
    }

    protected void flip() {
        if (!isShowing())
            return;

        BufferStrategy strategy = getBufferStrategy();
        Graphics2D canvasGraphics = (Graphics2D) strategy.getDrawGraphics();

        // Does not need to be recalculated every frame
        // TODO: Move this to a window event
        int scale = Math.max(Math.min(getWidth() / WIDTH, getHeight() / HEIGHT), 1);
        int x = (getWidth() - WIDTH * scale) / 2;
        int y = (getHeight() - HEIGHT * scale) / 2;

        canvasGraphics.drawImage(screen, x, y, WIDTH * scale, HEIGHT * scale, null);
        canvasGraphics.dispose();

        strategy.show();
        Toolkit.getDefaultToolkit().sync(); // Maybe not needed?
    }

    @Override
    public void draw(Texture texture, float x, float y) {
        graphics.drawImage(((BufferedTexture) texture).compatibleImage, round(x), round(y), null);
    }

    @Override
    public void clear() {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
    }

    @Override
    public void color(float r, float g, float b) {
        graphics.setColor(new Color(r, g, b));
    }

    @Override
    public void rect(float x, float y, float width, float height) {
        graphics.drawRect(round(x), round(y), round(width), round(height));
    }

    private static int round(float x) {
        return x >= 0 ? (int) (x + 0.5f) : (int) (x - 0.5f);
    }
}