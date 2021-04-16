package com.haklerz.proc;

import com.haklerz.truss.Texture;

public class Tile {

    public static final Tile WATER = new Tile(new Texture("res/water.png"));
    public static final Tile LAND = new Tile(new Texture("res/land.png"));

    public static final int SIZE = 8;
    
    private Texture texture;

    private Tile(Texture texture) {
        this.texture = texture;
    }
}
