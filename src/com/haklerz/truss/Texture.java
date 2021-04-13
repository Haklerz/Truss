package com.haklerz.truss;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {
    private BufferedImage image;

    public Texture(String path) throws IOException {
        BufferedImage readImage = ImageIO.read(new File(path));

        this.image = DefaultGraphicsConfiguration.getInstance()
            .createCompatibleImage(readImage.getWidth(), readImage.getHeight(), readImage.getTransparency());

        Graphics2D graphics = image.createGraphics();
        graphics.drawImage(readImage, 0, 0, null);
        graphics.dispose();
    }

    BufferedImage getImage() {
        return image;
    }
}
