package pl.bartoszf.hungry_bots.States;

import pl.bartoszf.hungry_bots.ClientWorker;
import pl.bartoszf.hungry_bots.PlayerSocket;

import java.io.IOException;

public class ConnectedState extends ClientState {

    public ConnectedState(PlayerSocket socket, ClientWorker worker) {
        super(socket, worker);

        socket.getOut().println("{state: 'Connected'}");
    }

    @Override
    public void handle() {
        try {
            String command = getSocket().getReader().readLine();

            if (command.equals("REGISTER")) {
                getSocket().setUserName("" + getSocket().getId());
                getSocket().getGameManager().register(getSocket());
                getWorker().changeState(new RegisteredState(getSocket(), getWorker()));
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
