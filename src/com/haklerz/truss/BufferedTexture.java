package com.haklerz.truss;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Transparency;

public final class BufferedTexture implements Texture {
    protected BufferedImage compatibleImage;

    public BufferedTexture(int width, int height, boolean isTranslucent) {
        this.compatibleImage = Texture.defaultGraphicsConfiguration.createCompatibleImage(width, height,
                isTranslucent ? Transparency.TRANSLUCENT : Transparency.OPAQUE);
    }

    protected BufferedTexture(BufferedImage image) {
        this.compatibleImage = Texture.defaultGraphicsConfiguration.createCompatibleImage(image.getWidth(), image.getHeight(),
                image.getTransparency());

        Graphics2D graphics = compatibleImage.createGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        graphics = null;
    }

    @Override
    public void draw(Texture texture, float x, float y) {
        
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void color(float r, float g, float b) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void rect(float x, float y, float width, float height) {
        // TODO Auto-generated method stub
        
    }
}
