package com.haklerz.multiplayer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Players implements Iterable<Player> {
    private Map<Integer, Player> playersByPlayerID;

    public Players() {
        this.playersByPlayerID = new HashMap<>();
    }

    public void spawnPlayer(Player player) {
        playersByPlayerID.put(player.playerID, player);
    }

    public Player getPlayer(int playerID) {
        return playersByPlayerID.get(playerID);
    }

    public void despawnPlayer(int playerID) {
        playersByPlayerID.remove(playerID);
    }

    public void updatePlayer(int playerID, double posX, double posY, double velX, double velY) {
        Player player = getPlayer(playerID);
        if (player != null) {
            player.pos.x = posX;
            player.pos.y = posY;
            player.vel.x = velX;
            player.vel.y = velY;
        }

    }

    @Override
    public Iterator<Player> iterator() {
        return playersByPlayerID.values().iterator();
    }
}
