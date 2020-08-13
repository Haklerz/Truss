package com.haklerz.truss;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Runner extends JPanel implements Runnable {
    private static final long serialVersionUID = 7696523570424558811L;

    private final Game game;

    public Runner(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        initialize();

        long startTime = System.nanoTime();
        long previousTime = startTime;

        while (true) {
            long currentTime = System.nanoTime();
            game.update((currentTime - previousTime) * 1e-9);
            repaint();
            previousTime = currentTime;
        }
    }

    private void initialize() {
        Settings settings = new Settings();
        game.setup(settings);

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(settings.getWidth(), settings.getHeight()));

        JFrame frame = new JFrame(settings.getTitle());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        frame.add(this, BorderLayout.CENTER);
        frame.pack();

        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.draw((Graphics2D) g);
    }
}