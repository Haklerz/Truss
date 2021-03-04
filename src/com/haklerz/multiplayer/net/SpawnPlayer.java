package com.haklerz.multiplayer.net;

public class SpawnPlayer extends Message {

    private static final long serialVersionUID = -470733546274431536L;

    public final double posX, posY;

    public SpawnPlayer(int playerID, double posX, double posY) {
        super(playerID);
        this.posX = posX;
        this.posY = posY;
    }
}
