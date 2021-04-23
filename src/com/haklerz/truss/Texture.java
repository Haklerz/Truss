package com.haklerz.truss;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

public final class Texture {

    private BufferedImage image;

    public Texture(String path) {
        try {
            image = ImageIO.read(new File(path));

            // TODO: Create compatible image
        } catch (IOException e) {
            e.printStackTrace();

            // TODO: Gracefully handle exception
        }
    }

    public Texture() {
        // TODO: Assign empty compatible image
    }

    protected Image getImage() {
        return image;
    }

    public void draw(Texture texture, float x, float y) {
        Graphics2D graphics = image.createGraphics();
        graphics.drawImage(texture.getImage(), x, y, null);
    }
}
