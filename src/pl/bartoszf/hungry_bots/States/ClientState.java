package pl.bartoszf.hungry_bots.States;

import pl.bartoszf.hungry_bots.ClientWorker;
import pl.bartoszf.hungry_bots.PlayerSocket;

public class ClientState {

    private PlayerSocket socket;
    private ClientWorker worker;

    public ClientState(PlayerSocket socket, ClientWorker worker) {
        this.socket = socket;
        this.worker = worker;
    }

    public void handle() {

    }

    public PlayerSocket getSocket() {
        return socket;
    }

    public void setSocket(PlayerSocket socket) {
        this.socket = socket;
    }

    public ClientWorker getWorker() {
        return worker;
    }

    public void setWorker(ClientWorker worker) {
        this.worker = worker;
    }

}
