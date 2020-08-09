package com.haklerz.truss;

/**
 * Interface which all Truss games must implement.
 */
public interface Game {

    /**
     * Called once at the start of the game.
     */
    public abstract void setup();

    /**
     * Called once every frame before draw.
     * @param dt
     */
    public abstract void update(double dt);

    /**
     * Called once every frame after update.
     */
    public abstract void draw();
}