package com.haklerz.multiplayer;

public class Player {
    
    public final Vec pos, vel;
    public final int ID;

    public Player(double x, double y, int id) {
        this.pos = new Vec(x, y);
        this.vel = new Vec(null);
        this.ID = id;
    }
}
