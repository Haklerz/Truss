package com.haklerz.multiplayer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

import com.haklerz.multiplayer.net.Connect;
import com.haklerz.multiplayer.net.Message;
import com.haklerz.multiplayer.net.SpawnPlayer;
import com.haklerz.multiplayer.net.UpdatePlayer;

public class Server implements Runnable {

    private static final double TICKS_PER_SECOND = 20;
    private ServerSocket acceptingSocket;
    private Set<ClientHandler> clients;
    private final int port;
    private Players players;
    private int currentPlayerID;

    public Server(int port) throws IOException {
        this.port = port;
        this.clients = new HashSet<>();
        this.players = new Players();
        this.currentPlayerID = 0;
    }

    @Override
    public void run() {
        try {
            acceptingSocket = new ServerSocket(port);
            System.out.println("Server is running at port " + port + "...");
        } catch (IOException e) {
            System.err.println("Server could not open at port " + port);
        }

        new Thread(this::acceptClients).start();

        long previousMillis = System.currentTimeMillis();
        while (true) {
            long currentMillis = System.currentTimeMillis();
            if (currentMillis - previousMillis > 1000 / TICKS_PER_SECOND) {
                tick();
                previousMillis = currentMillis;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void tick() {
        players.forEach(player -> updatePlayer(player));
    }

    private void updatePlayer(Player player) {
        player.pos.add(Vec.mul(player.vel, 1 / TICKS_PER_SECOND));
    }

    private void acceptClients() {
        while (true) {
            try {
                Socket client = acceptingSocket.accept();
                System.out.println("Client connected.");
                ClientHandler handler = new ClientHandler(this, client);
                clients.add(handler);
                new Thread(handler).start();

            } catch (IOException e) {
                System.err.println("Client could not connect.");
            }
        }
    }

    public int getPlayerID() {
        return currentPlayerID++;
    }

    private class ClientHandler implements Runnable {

        private final Server server;
        private final Socket client;
        private final ObjectInputStream input;
        private final ObjectOutputStream output;
        private final int playerID;

        public ClientHandler(Server server, Socket client) throws IOException {
            this.server = server;
            this.client = client;
            this.input = new ObjectInputStream(client.getInputStream());
            this.output = new ObjectOutputStream(client.getOutputStream());
            this.playerID = server.getPlayerID();
        }

        @Override
        public void run() {
            
            while (!client.isClosed()) {
                Message message = receive();

                if (message instanceof UpdatePlayer) {
                    handle((UpdatePlayer) message);
                }
                else if (message instanceof Connect) {
                    handle((Connect) message);
                }
            }
        }

        private void handle(Connect message) {
            send(new Connect(playerID));
            // TODO: Create player on server.
            // TODO: Send client all players.
        }

        private void handle(UpdatePlayer message) {
            if (message.playerID == playerID) {
                server.players.updatePlayer(playerID, message.posX, message.posY, message.velX, message.velY);
            }
        }

        public void send(Message message) {
            try {
                output.writeObject(message);
            } catch (IOException e) {
                disconnect();
            }
        }

        public Message receive() {
            Message message = null;
            try {
                message = (Message) input.readObject();
            } catch (ClassNotFoundException | IOException e) {
                disconnect();
            }
            return message;
        }

        public void disconnect() {
            try {
                input.close();
                output.close();
                client.close();
                System.out.println("Client disconnected.");
            } catch (IOException e) {
                System.out.println("Client lost connection.");
            }
        }
    }

}
