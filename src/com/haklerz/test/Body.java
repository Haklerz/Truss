package com.haklerz.test;

import com.haklerz.truss.Renderer;
import com.haklerz.truss.Time;

public class Body {
    public Vec pos, vel;
    private static final double R = 2;
    private static final double F = 0.001;

    public Body(double x, double y) {
        this.pos = new Vec(x, y);
        this.vel = new Vec();
    }

    public void acc(Time time, Vec a) {
        vel.add(Vec.mul(a, time.getDelta()));
    }

    public void update(Time time) {
        //vel.mul(1-F);
        pos.add(Vec.mul(vel, time.getDelta()));
        if (pos.x - R < 0) {
            pos.x = 0 + R;
        }
        if (pos.x + R > 1280) {
            pos.x = 1280 - R;
        }
        if (pos.y - R < 0) {
            pos.y = 0 + R;
        }
        if (pos.y + R > 720) {
            pos.y = 720 - R;
        }
    }

    public void draw(Renderer renderer) {
        renderer.setColor(1, 0, 1);
        renderer.circle(pos.x, pos.y, R);
    } 
}