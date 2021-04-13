package com.haklerz.truss;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestGame implements Game {

    private Texture glow;
    private long startTime;
    private int frameCount;
    private List<Particle> particles;
    private Random random;

    public TestGame() throws IOException {
        Window window = new Window.Builder().setTitle("Test Game").setResolution(640, 360).setGame(this).build();

        random = new Random();
        particles = new ArrayList<>();
        glow = new Texture("res/glow.png");

        window.start();
    }

    @Override
    public void update() {
        long currentTime = System.nanoTime();
        if (currentTime - startTime >= 1e9) {
            startTime = currentTime;
            System.out.println("n = " + particles.size() + " | FPS = " + frameCount);
            frameCount = 0;
        }
        frameCount++;

        float vx = random.nextFloat() - 0.5f;
        float vy = random.nextFloat() - 0.5f;
        particles.add(new Particle(640/2, 360/2, vx, vy));

        for (int i = 0; i < particles.size(); i++) {
            Particle particle = particles.get(i);
            particle.update();
        }
    }

    @Override
    public void draw(Renderer renderer) {
        renderer.clear();
        for (int i = 0; i < particles.size(); i++) {
            Particle particle = particles.get(i);
            renderer.blit(glow, particle.px - 8, particle.py - 8);
        }
    }

    public static void main(String[] args) throws IOException {
        new TestGame();
    }
}
