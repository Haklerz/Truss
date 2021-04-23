package com.haklerz.truss;

import javax.swing.JFrame;

/*
Create a frame
Create a canvas
Add the frame to the canvas
Create a screen buffer from compatibleImage
Create a buffer strategy on the canvas
Create a Graphics2D from the screen buffer


loop:
	Let game update
	Let game draw using screen buffer's Graphics2D
	
	Create a Graphics2D from the canvas's buffer strategy
	Draw screen buffer
	Dispose of Graphics2D
	Show the buffer strategy
	Sync

Dispose of Frame



-More optimizations-
Enable hardware acceleration  System.setProperty("sun.java2d.opengl", "true");

Manage your own VolatileImage

Convert loaded textures to compatible buffered image

https://docs.oracle.com/javase/7/docs/api/java/awt/image/VolatileImage.html
https://stackoverflow.com/questions/1963494/java-2d-game-graphics
https://stackoverflow.com/questions/4627320/java-hardware-acceleration#13832805
*/

public class Window implements Runnable {
    private Game game;
    private JFrame frame;
    private Renderer renderer;

    public Window(Game game, String title, int width, int height) {
        this.game = game;

        this.frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setIgnoreRepaint(true);

        this.renderer = new Renderer(width, height);
        frame.add(renderer.getCanvas());
        frame.pack();
        frame.setMinimumSize(frame.getSize());
        renderer.initGraphics();
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void run() {
        while (frame.isDisplayable()) {
            game.update();
            game.draw(renderer);
            renderer.flip();
        }
        frame.dispose();
    }

    public void start() {
        new Thread(this).start();
    }

    public static class Builder {
        private Game game;
        private String title;
        private int width, height;

        public Builder setGame(Game game) {
            this.game = game;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setResolution(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }
    
        public Window build() {
            return new Window(game, title, width, height);
        }
    }
}
