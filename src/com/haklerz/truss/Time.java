package com.haklerz.truss;

public class Time {
    private static final double SMOOTHING = 100;

    private long previousNano;
    private double delta;
    private double deltaSmooth;
    private double elapsed;

    public Time(long startNano) {
        this.previousNano = startNano;
        this.delta = 0;
        this.deltaSmooth = 0;
        this.elapsed = 0;
    }

    void update(long currentNano) {
        delta = (currentNano - previousNano) * 1e-9;
        deltaSmooth = (deltaSmooth * SMOOTHING + delta) / (1 + SMOOTHING);
        elapsed += delta;
        previousNano = currentNano;
    }

    /**
     * Returns the framerate in frames per second.
     * 
     * @return The framerate
     */
    public double getFramerate() {
        return 1 / deltaSmooth;
    }

    /**
     * Returns the time between frames in seconds.
     * 
     * @return The delta time
     */
    public double getDelta() {
        return delta;
    }

    /**
     * Returns the amount of time simce the start of the game.
     * 
     * @return The elapsed time
     */
    public double getElapsed() {
        return elapsed;
    }
}