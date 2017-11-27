package pl.bartoszf.hungry_bots;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameManager {

    public static List<PlayerSocket> players = new ArrayList<PlayerSocket>();

    public int minPlayers = 2;
    public int maxPlayers = 10;
    public boolean waitingForPlayers = true;

    private List<PlayerSocket> registeredPlayers = new ArrayList<>();

    public GameManager() {

    }

    public void addPlayer(PlayerSocket psock) {
        if (waitingForPlayers) {
            players.add(psock);
            broadcast("New bot joined! " + psock.getId());
            psock.setGameManager(this);
            if (players.size() == maxPlayers) {
                waitingForPlayers = false;
            }


        }
    }

    public void register(PlayerSocket psock) {
        registeredPlayers.add(psock);
        broadcast(psock.getUsername() + " registered to game!");
    }

    public void tick() {

    }

    public void broadcast(String message) {
        for (PlayerSocket so : GameManager.players) {
            try {
                so.getSocket().getOutputStream().write(message.getBytes());
            } catch (IOException ex) {

            }
        }
    }
}
