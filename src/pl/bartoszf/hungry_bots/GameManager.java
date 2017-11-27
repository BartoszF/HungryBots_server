package pl.bartoszf.hungry_bots;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    public static List<PlayerSocket> players = new ArrayList<PlayerSocket>();

    public int minPlayers = 2;
    public int maxPlayers = 10;
    public boolean waitingForPlayers = true;

    private List<PlayerSocket> registeredPlayers = new ArrayList<>();

    private GameState state;
    private int countdown = -1;

    public GameManager() {
        state = GameState.CONNECTED;
    }

    public void addPlayer(PlayerSocket psock) {
        if (waitingForPlayers) {
            players.add(psock);
            psock.setGameState(GameState.CONNECTED);
            broadcast("New bot joined! " + psock.getId());
            psock.setGameManager(this);
            if (players.size() == maxPlayers) {
                waitingForPlayers = false;
            }


        }
    }

    public void register(PlayerSocket psock) {
        registeredPlayers.add(psock);
        psock.setGameState(GameState.REGISTERED);
        broadcast(psock.getUsername() + " registered to game!");
    }

    public void tick() {
        if (state == GameState.CONNECTED) {
            if (registeredPlayers.size() >= minPlayers) {
                if (countdown == -1) {
                    countdown = 10;
                    broadcast("Time " + countdown);
                } else if (countdown == 0) {
                    state = GameState.REGISTERED;
                    broadcast("REGISTERED");
                } else {
                    countdown--;
                    broadcast("Time " + countdown);
                }
            }
        }

        if (state == GameState.REGISTERED) {
            state = GameState.WAIT_FOR_COMMANDS;
            //Send player statuses
            //Send state changed
            broadcast("WAIT_FOR_COMMANDS");
        }
    }

    public void broadcast(String message) {
        for (PlayerSocket so : GameManager.players) {
            so.getOut().print(message);
            so.getOut().flush();
        }
    }

    public void closed(PlayerSocket psock) {
        registeredPlayers.remove(psock);
        players.remove(this);
    }
}
