package pl.bartoszf.hungry_bots;

import pl.bartoszf.hungry_bots.States.ConnectedState;
import pl.bartoszf.hungry_bots.States.RegisteredState;
import pl.bartoszf.hungry_bots.States.WaitForCommandsState;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    public static List<ClientWorker> players = new ArrayList<>();

    public int minPlayers = 2;
    public int maxPlayers = 10;
    public boolean waitingForPlayers = true;

    private List<ClientWorker> registeredPlayers = new ArrayList<>();

    private GameState state;
    private int countdown = -1;

    public GameManager() {
        state = GameState.CONNECTED;
    }

    public void addPlayer(ClientWorker clientWorker) {
        if (waitingForPlayers) {
            players.add(clientWorker);
            clientWorker.changeState(new ConnectedState(clientWorker.getSocket(), clientWorker));
            broadcast("{players: " + players.size() + "}");
            clientWorker.getSocket().setGameManager(this);
            if (players.size() == maxPlayers) {
                waitingForPlayers = false;
            }


        }
    }

    public void register(ClientWorker clientWorker) {
        registeredPlayers.add(clientWorker);
        clientWorker.changeState(new RegisteredState(clientWorker.getSocket(), clientWorker));
        broadcast(clientWorker.getSocket().getUsername() + " registered to game!");
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
            for (ClientWorker so : GameManager.players) {
                so.changeState(new WaitForCommandsState(so.getSocket(), so));
            }
            //broadcast("WAIT_FOR_COMMANDS");
        }
    }

    public void broadcast(String message) {
        for (ClientWorker so : GameManager.players) {
            so.getSocket().getOut().print(message);
            so.getSocket().getOut().flush();
        }
    }

    public void closed(ClientWorker psock) {
        registeredPlayers.remove(psock);
        players.remove(this);
    }
}
