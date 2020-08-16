package com.haklerz.test;

import com.haklerz.truss.Time;

public class Body {
    private Vec pos, vel;
    private static final double R = 5;

    public Body(double x, double y) {
        this.pos = new Vec(x, y);
        this.vel = new Vec();
    }

    public void update(Time time) {
        pos.add(Vec.mul(vel, time.getDelta()));
    }
}