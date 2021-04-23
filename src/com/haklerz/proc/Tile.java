package com.haklerz.proc;

import com.haklerz.truss.Texture;

public class Tile {

    public static final int SIZE = 8;
    
    private Texture texture;

    public Tile(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }
}
