package com.haklerz.truss;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Transparency;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JFrame;

public class Window implements Runnable {

    private final int WIDTH;
    private final int HEIGHT;

    private final Game game;
    private final Canvas canvas;
    private final JFrame frame;

    private final Input input;

    private int xOffset;
    private int yOffset;
    private int scalingFactor = 1;

    public Window(String title, int width, int height, Game game) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.game = game;

        this.input = new Input();

        KeyAdapter keyAdapter = new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                input.setKeyboardKeyDown(e.getKeyCode(), true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                input.setKeyboardKeyDown(e.getKeyCode(), false);
            }
        };

        MouseAdapter mouseAdapter = new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);

                int mouseX = Math.floorDiv(e.getX() - xOffset, scalingFactor);
                int mouseY = Math.floorDiv(e.getY() - yOffset, scalingFactor);

                if (mouseX == input.getMouseX() && mouseY == input.getMouseY())
                    return;

                if (mouseX < 0 || mouseX >= WIDTH || mouseY < 0 || mouseY >= HEIGHT)
                    return;

                input.setMouseX(mouseX);
                input.setMouseY(mouseY);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);

                int mouseX = Math.floorDiv(e.getX() - xOffset, scalingFactor);
                int mouseY = Math.floorDiv(e.getY() - yOffset, scalingFactor);

                if (mouseX == input.getMouseX() && mouseY == input.getMouseY())
                    return;

                if (mouseX < 0 || mouseX >= WIDTH || mouseY < 0 || mouseY >= HEIGHT)
                    return;

                input.setMouseX(mouseX);
                input.setMouseY(mouseY);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                input.setMouseButtonDown(e.getButton(), true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                input.setMouseButtonDown(e.getButton(), false);
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);

                input.addMouseWheel(e.getWheelRotation());
            }

        };

        this.canvas = new Canvas();
        canvas.setIgnoreRepaint(true);
        canvas.setSize(width, height);
        canvas.setBackground(Color.decode("0x181d26"));
        canvas.addKeyListener(keyAdapter);
        canvas.addMouseMotionListener(mouseAdapter);
        canvas.addMouseWheelListener(mouseAdapter);
        canvas.addMouseListener(mouseAdapter);

        this.frame = new JFrame(title);
        frame.setIgnoreRepaint(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.add(canvas);
        frame.pack();
        frame.setMinimumSize(frame.getSize());
    }

    @Override
    public void run() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice().getDefaultConfiguration();
        BufferedImage screen = graphicsConfiguration.createCompatibleImage(WIDTH, HEIGHT, Transparency.OPAQUE);
        int[] pixels = ((DataBufferInt) screen.getRaster().getDataBuffer()).getData();

        canvas.createBufferStrategy(2);
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();

        Renderer renderer = new Renderer(pixels, WIDTH, HEIGHT);

        while (true) {

            game.loop(renderer, input);

            Graphics graphics = bufferStrategy.getDrawGraphics();

            scalingFactor = max(min(canvas.getWidth() / WIDTH, canvas.getHeight() / HEIGHT), 1);
            xOffset = (canvas.getWidth() - WIDTH * scalingFactor) >> 1;
            yOffset = (canvas.getHeight() - HEIGHT * scalingFactor) >> 1;
            graphics.drawImage(screen, xOffset, yOffset, WIDTH * scalingFactor, HEIGHT * scalingFactor, null);

            graphics.dispose();
            if (!bufferStrategy.contentsLost()) {
                bufferStrategy.show();
            }
        }
    }

    private static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private static int min(int a, int b) {
        return (a < b) ? a : b;
    }
}
