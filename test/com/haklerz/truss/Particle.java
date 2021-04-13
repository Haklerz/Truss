package com.haklerz.truss;

public class Particle {
    public float px, py, vx, vy;

    public Particle(float px, float py, float vx, float vy) {
        this.px = px;
        this.py = py;
        this.vx = vx;
        this.vy = vy;
    }

    public void update() {
        px += vx;
        py += vy;

        if (px < 0) {
            px = 640 - 1;
        }
        if (px >= 640) {
            px = 0;
        }
        if (py < 0) {
            py = 360 - 1;
        }
        if (py >= 360) {
            py = 0;
        }
    }
}
