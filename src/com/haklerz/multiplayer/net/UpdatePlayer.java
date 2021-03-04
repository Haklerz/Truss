package com.haklerz.multiplayer.net;

public class UpdatePlayer extends Message {

    private static final long serialVersionUID = -8316023854408144251L;

    public final double posX, posY, velX, velY;

    public UpdatePlayer(int playerID, double posX, double posY, double velX, double velY) {
        super(playerID);
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY;
    }
}
