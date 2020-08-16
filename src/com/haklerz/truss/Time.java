package com.haklerz.truss;

public class Time {
    private long previousNano;
    private double delta;
    private double elapsed;

    public Time(long startNano) {
        this.previousNano = startNano;
        this.delta = 0;
        this.elapsed = 0;
    }

    void update(long currentNano) {
        delta = (currentNano - previousNano) * 1e-9;
        elapsed += delta;
        previousNano = currentNano;
    }

    public double getDelta() {
        return delta;
    }

    public double getElapsed() {
        return elapsed;
    }
}