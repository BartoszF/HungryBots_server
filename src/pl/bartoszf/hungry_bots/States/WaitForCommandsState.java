package pl.bartoszf.hungry_bots.States;

import pl.bartoszf.hungry_bots.ClientWorker;
import pl.bartoszf.hungry_bots.PlayerSocket;

public class WaitForCommandsState extends ClientState {
    public WaitForCommandsState(PlayerSocket socket, ClientWorker worker) {
        super(socket, worker);

        //return state of player
        socket.getOut().println("{state: 'commands'}");
    }

    @Override
    public void handle() {

    }
}
