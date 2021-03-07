package com.haklerz.truss;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window implements Runnable {

    private final JFrame frame;

    public Window(String title, int width, int height) {
        this.frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setIgnoreRepaint(true);
        // frame.setLocationRelativeTo(null);
        JPanel yeet = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.BLACK);
                g2.fillRect(0, 0, getWidth(), getHeight());

                //BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);

                //g2.drawImage(img, 0, 0, null);
                g2.setColor(Color.BLUE);
                g2.drawLine(0, 0, getWidth(), getHeight());
            }

        };

        frame.add(yeet);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Window("Test", 640, 360).run();
    }

    @Override
    public void run() {

        while (true) {

        }
    }

}
