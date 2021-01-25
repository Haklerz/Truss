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

    private final Game game;
    private final Canvas canvas;
    private final JFrame frame;
    private final int WIDTH;
    private final int HEIGHT;
    private boolean[] keyFrontBuffer = new boolean[65536];
    private boolean[] keyBackBuffer = new boolean[65536];
    private int scale = 1;
    private int mouseX;
    private int mouseY;
    private int xOffset;
    private int yOffset;

    public Window(String title, int width, int height, Game game) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.game = game;

        MouseAdapter mouseAdapter = new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);

                int newMouseX = Math.floorDiv(e.getX() - xOffset, scale);
                int newMouseY = Math.floorDiv(e.getY() - yOffset, scale);

                if (newMouseX >= 0 && newMouseX < WIDTH && newMouseY >= 0 && newMouseY < HEIGHT) {
                    mouseX = newMouseX;
                    mouseY = newMouseY;
                    System.out.println("[" + mouseX + ", " + mouseY + "] ");
                }

                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                super.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                super.mouseReleased(e);
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                // TODO Auto-generated method stub
                super.mouseWheelMoved(e);
            }
        };
        KeyAdapter keyAdapter = new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                keyBackBuffer[e.getKeyCode()] = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                keyBackBuffer[e.getKeyCode()] = false;
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
            keyFrontBuffer = keyBackBuffer.clone();

            game.loop(renderer);

            Graphics graphics = bufferStrategy.getDrawGraphics();

            scale = max(min(canvas.getWidth() / WIDTH, canvas.getHeight() / HEIGHT), 1);
            xOffset = (canvas.getWidth() - WIDTH * scale) >> 1;
            yOffset = (canvas.getHeight() - HEIGHT * scale) >> 1;
            graphics.drawImage(screen, xOffset, yOffset, WIDTH * scale, HEIGHT * scale, null);

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
