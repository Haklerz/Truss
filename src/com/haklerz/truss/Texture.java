package com.haklerz.truss;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {
    

    private BufferedImage compatibleImage;

    public Texture(String path) throws IOException {
        BufferedImage image = ImageIO.read(new File(path));

        Graphics2D graphics = compatibleImage.createGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
    }
}
