package com.haklerz.truss;

import java.awt.Graphics2D;

/**
 * Interface which all Truss games must implement.
 */
public interface Game {

    /**
     * Called once at the start of the game.
     * @param settings
     */
    public abstract void setup(Settings settings);

    /**
     * Called once every frame before draw.
     * @param dt
     */
    public abstract void update(double dt);

    /**
     * Called once every frame after update.
     * @param g
     */
    public abstract void draw(Graphics2D g);
}