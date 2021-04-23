package com.haklerz.proc;

public class Chunk {

    public static final int SIZE = 16;

    private Tile[] tiles;

    public Chunk() {
        tiles = new Tile[SIZE * SIZE];
    }

    public Tile get(int x, int y) {
        return tiles[x + y * SIZE];
    }

    public static Chunk generate(int seed, int chunkX, int chunkY) {
        Chunk chunk = new Chunk();

        for (int i = 0; i < SIZE * SIZE; i++) {
            int x = i % SIZE + chunkX * SIZE;
            int y = i / SIZE + chunkY * SIZE;

            float v = valueNoise(seed, x * 0.2f, y * 0.2f);

            Tile tile;
            if (v < 0.5) {
                tile = WATER;
            } else {
                tile = LAND;
            }
            
            chunk.tiles[i] = tile;
        }
        return null;
    }

    public static float valueNoise(int seed, float x, float y) {
        int cellX = floor(x);
        int cellY = floor(y);
        float deltaX = x - cellX;
        float deltaY = y - cellY;

        deltaX = qubicHermite(deltaX);
        deltaY = qubicHermite(deltaY);

        return lerp(lerp(hash(seed, cellX, cellY), hash(seed, cellX + 1, cellY), deltaX),
                lerp(hash(seed, cellX, cellY + 1), hash(seed, cellX + 1, cellY + 1), deltaX), deltaY);
    }

    private static float qubicHermite(float x) {
        return x * x * (3 - 2 * x);
    }

    private static float lerp(float a, float b, float t) {
        return a + t * (b - a);
    }

    private static int floor(float x) {
        return x >= 0 ? (int) x : (int) x - 1;
    }

    public static float hash(int seed, int x, int y) {
        int v = seed;
        v ^= x * 0xdb738e61;
        v ^= y * 0xccf19057;

        v ^= v << 17;
        v ^= v >> 5;
        v ^= v << 9;

        v = v * v * v;

        v &= 0x7FFFFFFF;

        return v / (float) Integer.MAX_VALUE;
    }
}
