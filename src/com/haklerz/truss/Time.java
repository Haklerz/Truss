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

    public double getFramerate() {
        return 1 / deltaSmooth;
    }

    public double getDelta() {
        return delta;
    }

    public double getElapsed() {
        return elapsed;
    }
}