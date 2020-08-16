package com.haklerz.truss;

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
     * @param time
     */
    public abstract void update(Time time);

    /**
     * Called once every frame after update.
     * @param renderer
     */
    public abstract void draw(Renderer renderer);
}