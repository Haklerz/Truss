package com.haklerz.multiplayer.net;

import java.io.Serializable;

public abstract class Message implements Serializable {

    private static final long serialVersionUID = 8053471668696128224L;

    public final int playerID;

    public Message(int playerID) {
        this.playerID = playerID;
    }
}
