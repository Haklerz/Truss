package com.haklerz.multiplayer.net;

public class Connect extends Message {

    private static final long serialVersionUID = -8283289490243106376L;

    public Connect(int playerID) {
        super(playerID);
    }
}
