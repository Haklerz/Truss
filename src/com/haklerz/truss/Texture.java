package com.haklerz.truss;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public interface Texture {
    public static final GraphicsConfiguration defaultGraphicsConfiguration = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

    public void draw(Texture texture, float x, float y);

    public void clear();

    public void color(float r, float g, float b);

    public void rect(float x, float y, float width, float height);

    public static Texture load(String path) throws IOException {
        return new BufferedTexture(ImageIO.read(new File(path)));
    }

}
