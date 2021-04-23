package com.haklerz.proc;

import com.haklerz.truss.*;

public class ProcGame implements Game {

    private Chunk chunk;

    public ProcGame() {

        chunk = Chunk.generate(0, 0, 0);
        
        new Window.Builder().setTitle("Proc").setResolution(128 * 2, 128 * 2).setGame(this).build().start();
    }

    public static void main(String[] args) {
        new ProcGame();
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Renderer renderer) {
        if (chunk != null) {
            for (int y = 0; y < Chunk.SIZE; y++) {
                for (int x = 0; x < Chunk.SIZE; x++) {
                    Tile tile = chunk.get(x, y);
                    renderer.blit(tile.getTexture(), x * Tile.SIZE, y * Tile.SIZE);
                }
            }
        }
    }
}