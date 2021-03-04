package com.haklerz.multiplayer.net;

public class DespawnPlayer extends Message {

    private static final long serialVersionUID = 243395268351590635L;

    public DespawnPlayer(int playerID) {
        super(playerID);
    }
    
}
