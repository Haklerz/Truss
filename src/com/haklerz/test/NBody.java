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
    public void setup(Config settings) {
        settings.setTitle("NBody Simulation");
        settings.setResolution(1280, 720);

        rng = new Random();
        bodies = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            bodies.add(new Body(rng.nextDouble() * 1280, rng.nextDouble() * 720));
        }
    }

    @Override
    public void update(Config config, Time time) {
        Body a, b;
        for (int i = 0; i < bodies.size(); i++) {
            a = bodies.get(i);
            for (int j = i + 1; j < bodies.size(); j++) {
                b = bodies.get(j);

                Vec ab = Vec.sub(b.pos, a.pos);
                double d = ab.mag() * 0.05;

                if (d > 0.5) {
                    ab.div(d * d * d);
                    ab.mul(0.5);

                    a.acc(time, ab);
                    b.acc(time, ab.mul(-1));
                }
            }
        }

        bodies.forEach(body -> body.update(time));
        System.out.println("FPS: " + time.getFramerate());
    }

    @Override
    public void draw(Config config, Renderer renderer) {
        bodies.forEach(body -> body.draw(renderer));
    }
}