package com.haklerz.multiplayer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Players implements Iterable<Player> {
    private Map<Integer, Player> playersByID;

    public Players() {
        this.playersByID = new HashMap<>();
    }

    public void addPlayer(Player player) {
        playersByID.put(player.ID, player);
    }

    public Player getPlayer(int id) {
        return playersByID.get(id);
    }

    public void removePlayer(int id) {
        playersByID.remove(id);
    }

    @Override
    public Iterator<Player> iterator() {
        return playersByID.values().iterator();
    }
}
