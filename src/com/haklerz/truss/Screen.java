package com.haklerz.truss;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Screen extends JPanel {

    private static final long serialVersionUID = 201933871359374759L;

    private final Game game;

    public Screen(Game game) {
        this.game = game;

        setDoubleBuffered(true);
        setIgnoreRepaint(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        game.draw((Graphics2D) g);
    }
}
