package com.haklerz.truss;

/**
 * Interface which all Truss games must implement.
 */
public interface Game {

    /**
     * Called once at the start of the game.
     * @param config
     */
    public abstract void setup(Config config);

    /**
     * Called once every frame before draw.
     * @param time
     */
    public abstract void update(Config config, Time time);

    /**
     * Called once every frame after update.
     * @param renderer
     */
    public abstract void draw(Config config, Renderer renderer);
}