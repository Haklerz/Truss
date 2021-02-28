package com.haklerz.test;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.haklerz.truss.Game;
import com.haklerz.truss.Input;
import com.haklerz.truss.Renderer;
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
    public void loop(Renderer renderer, Input input) {
        renderer.fade();
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

        public void update(Renderer renderer, Input input) {
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
                px = renderer.getWidth() - 1;
            }

            if (px >= renderer.getWidth()) {
                px = 0;
            }

            if (py < 0) {
                py = renderer.getHeight() - 1;
            }

            if (py >= renderer.getHeight()) {
                py = 0;
            }
        }

        public void draw(Renderer renderer) {
            renderer.setColor(0, 255, 0);

            renderer.setPixel((int) (px + 5 * Math.cos(a)), (int) (py + 5 * Math.sin(a)));
            renderer.setPixel((int) (px + 5 * Math.cos(a + 2.6)), (int) (py + 5 * Math.sin(a + 2.6)));
            renderer.setPixel((int) (px + 5 * Math.cos(a - 2.6)), (int) (py + 5 * Math.sin(a - 2.6)));
        }
    }

    private class Shot {
    
        private double px, py, vx, vy;

        public Shot(double x, double y, double a) {
            this.px = x;
            this.py = y;
            this.vx = Math.cos(a);
            this.vy = Math.sin(a);
        }

        public void update(Renderer renderer) {
            px += vx;
            py += vy;
        }

        public void draw(Renderer renderer) {
            renderer.setColor(255, 0, 0);
            renderer.setPixel((int) px, (int) py);
        }
    }
}
