package com.haklerz.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.haklerz.truss.*;

public class NBody implements Game {
    private List<Body> bodies;
    private Random rng;

    public static void main(String[] args) {
        new Runner(new NBody()).run();
    }

    @Override
    public void setup(Settings settings) {
        settings.setTitle("NBody Simulation");
        settings.setResolution(640, 360);

        rng = new Random();
        bodies = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            bodies.add(new Body(rng.nextDouble() * 640, rng.nextDouble() * 360));
        }
    }

    @Override
    public void update(Time time) {
        Body a, b;
        for (int i = 0; i < bodies.size(); i++) {
            a = bodies.get(i);
            for (int j = i + 1; j < bodies.size(); j++) {
                b = bodies.get(j);

                Vec ab = Vec.sub(b.pos, a.pos);
                double d = ab.mag() * 0.05;

                ab.div(d * d * d);

                double rep = Math.sin(0.5 * time.getElapsed()) + 2;
                // System.out.println(rep);
                if (d < rep) {
                    ab.mul(-1);
                }

                a.acc(time, ab);
                b.acc(time, ab.mul(-1));
            }
        }

        for (Body body : bodies) {
            body.update(time);
        }
    }

    @Override
    public void draw(Renderer renderer) {
        renderer.clear();
        for (Body body : bodies) {
            body.draw(renderer);
        }
    }
}