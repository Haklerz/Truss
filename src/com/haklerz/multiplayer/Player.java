package com.haklerz.multiplayer;

public class Player {
    
    public final Vec pos, vel;
    public final int playerID;

    public Player(double x, double y, int playerID) {
        this.pos = new Vec(x, y);
        this.vel = new Vec(null);
        this.playerID = playerID;
    }

}
