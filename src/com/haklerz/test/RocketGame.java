package com.haklerz.test;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.haklerz.truss.Game;
import com.haklerz.truss.Input;
import com.haklerz.truss.Draw;
import com.haklerz.truss.Window;

public class RocketGame implements Game {

    private Rocket rocket;
    private ArrayList<Shot> shots;
    private int frame;

    public static void main(String[] args) {
        new Window("Rocket", 640, 360, new RocketGame()).run();
    }

    public RocketGame() {
        this.rocket = new Rocket(50, 50);
        this.shots = new ArrayList<>();
    }

    @Override
    public void loop(Draw renderer, Input input) {
        renderer.setColor(0, 0, 0);
        renderer.fill();
        rocket.update(renderer, input);
        rocket.draw(renderer);

        if (input.isKeyboardKeyDown(KeyEvent.VK_SPACE) && frame % 10 == 0) {
            shots.add(new Shot(rocket.px, rocket.py, rocket.a));
        }

        for (Shot shot : shots) {
            shot.update(renderer);
            shot.draw(renderer);
        }

        frame++;
    }

    private class Rocket {
        private double px, py, vx, vy, a, b;

        public Rocket(double x, double y) {
            this.px = x;
            this.py = y;
        }

        public void update(Draw renderer, Input input) {
            if (input.isKeyboardKeyDown(KeyEvent.VK_UP)) {
                vx += Math.cos(a) * 0.0002;
                vy += Math.sin(a) * 0.0002;
            }

            if (input.isKeyboardKeyDown(KeyEvent.VK_LEFT)) {
                b -= 0.00002;
            }

            if (input.isKeyboardKeyDown(KeyEvent.VK_RIGHT)) {
                b += 0.00002;
            }

            b *= 1 - 0.001;
            vx *= 1 - 0.0001;
            vy *= 1 - 0.0001;

            a += b;
            px += vx;
            py += vy;

            if (px < 0) {
                px = renderer.WIDTH - 1;
            }

            if (px >= renderer.WIDTH) {
                px = 0;
            }

            if (py < 0) {
                py = renderer.HEIGHT - 1;
            }

            if (py >= renderer.HEIGHT) {
                py = 0;
            }
        }

        public void draw(Draw renderer) {

            double size = 10;

            double frontX = size * Math.cos(a);
            double frontY = size * Math.sin(a);

            double leftX = size * 0.8 * Math.cos(a - Math.PI * 0.7);
            double leftY = size * 0.8 * Math.sin(a - Math.PI * 0.7);

            double rightX = size * 0.8 * Math.cos(a + Math.PI * 0.7);
            double rightY = size * 0.8 * Math.sin(a + Math.PI * 0.7);

            renderer.setColor(255, 0, 255);
            renderer.line(round(px + frontX), round(py + frontY), round(px + rightX), round(py + rightY));
            renderer.line(round(px + frontX), round(py + frontY), round(px + leftX), round(py + leftY));
            renderer.line(round(px + rightX * 0.7), round(py + rightY * 0.7), round(px + leftX * 0.7), round(py + leftY * 0.7));

            renderer.setColor(0, 255, 255);
            renderer.circle(round(px), round(py), round(size * 1.3));
        }

    }

    private static int round(double x) {
        return (x >= 0) ? (int) (x + 0.5d) : (int) (x - 0.5d);
    }

    private class Shot {

        private double px, py, vx, vy;

        public Shot(double x, double y, double a) {
            this.px = x;
            this.py = y;
            this.vx = Math.cos(a);
            this.vy = Math.sin(a);
        }

        public void update(Draw renderer) {
            px += vx;
            py += vy;
        }

        public void draw(Draw renderer) {
            renderer.setColor(255, 0, 0);
            renderer.fill((int) px, (int) py);
        }
    }
}
