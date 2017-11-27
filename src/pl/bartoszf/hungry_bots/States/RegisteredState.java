package pl.bartoszf.hungry_bots.States;

import pl.bartoszf.hungry_bots.ClientWorker;
import pl.bartoszf.hungry_bots.PlayerSocket;

public class RegisteredState extends ClientState {

    public RegisteredState(PlayerSocket socket, ClientWorker worker) {
        super(socket, worker);

        socket.getOut().println("{state: 'Registered'}");
    }
}
