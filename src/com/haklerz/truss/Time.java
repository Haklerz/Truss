package com.haklerz.truss;

public class Time {

    private long previousNanos;

    private double delta;
    private double elapsed;

    public Time() {
        this.previousNanos = System.nanoTime();
    }

    public void step() {
        long currentNanos = System.nanoTime();

        delta = (currentNanos - previousNanos) * 1e-9d;
        previousNanos = currentNanos;

        elapsed += delta;
    }

    public double getDelta() {
        return delta;
    }

    public double getElapsed() {
        return elapsed;
    }
}
