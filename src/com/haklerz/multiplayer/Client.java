package com.haklerz.multiplayer;

import java.awt.event.KeyEvent;

import com.haklerz.truss.Game;
import com.haklerz.truss.Input;
import com.haklerz.truss.Renderer;
import com.haklerz.truss.Time;
import com.haklerz.truss.Window;

public class Client implements Game {

    private static final double ROOT_2_OVER_2 = Math.sqrt(2) / 2;
    private static final int MOVE_SPEED = 250;

    private Players players;
    private int id;

    public static void main(String[] args) {
        new Window("Client", 640, 360, new Client()).run();
    }

    public Client() {
        this.players = new Players();
        this.id = 0;
        players.spawnPlayer(new Player(100, 100, 0));
    }

    @Override
    public void loop(Renderer renderer, Input input, Time time) {
        players.forEach(player -> updatePlayer(player, input, time));

        renderer.setColor(0, 0, 0);
        renderer.fill();
        players.forEach(player -> renderPlayer(player, renderer));
    }

    private void updatePlayer(Player player, Input input, Time time) {
        if (player.playerID == id) {
            player.vel.x = 0;
            player.vel.y = 0;
            if (input.isKeyboardKeyDown(KeyEvent.VK_W))
                player.vel.y = -MOVE_SPEED;

            if (input.isKeyboardKeyDown(KeyEvent.VK_A))
                player.vel.x = -MOVE_SPEED;

            if (input.isKeyboardKeyDown(KeyEvent.VK_S))
                player.vel.y = MOVE_SPEED;

            if (input.isKeyboardKeyDown(KeyEvent.VK_D))
                player.vel.x = MOVE_SPEED;

            if (Math.abs(player.vel.x) + Math.abs(player.vel.y) > MOVE_SPEED) {
                player.vel.mul(ROOT_2_OVER_2);
            }
        }

        player.pos.add(Vec.mul(player.vel, time.getDelta()));
    }

    private void renderPlayer(Player player, Renderer renderer) {
        renderer.setColor(255, 0, 0);
        renderer.circle(player.pos.x, player.pos.y, 10);
    }
}
