package pl.bartoszf.hungry_bots;

import pl.bartoszf.hungry_bots.States.ClientState;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Użytkownik on 2016-12-29.
 */
public class ClientWorker implements Runnable {

    private final PlayerSocket socket;
    private final Logger logger = Logger.getLogger(ClientWorker.class.getName());
    private String workingDir;
    private int id = -1;

    private ClientState state;
    //private ScreenManager manager;

    /**
     * @param socket
     */
    public ClientWorker(final PlayerSocket socket, String homeDir) {
        this.socket = socket;
        this.workingDir = homeDir;
        //this.state = new ConnectedState(socket, this);
        socket.gameManager.addPlayer(this);
        //this.manager = new ScreenManager(socket,this);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */
    public void run() {
        try {
            boolean cancel = false;
            while (!cancel) {
                state.handle();
                if (socket.getSocket().isClosed()) cancel = true;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        } finally {
            try {
                socket.close();
                socket.gameManager.closed(this);

            } catch (Exception e) {
                logger.log(Level.SEVERE, "Failed to close the socket", e);

            }
        }
    }

    public void changeState(ClientState state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public PlayerSocket getSocket() {
        return socket;
    }
}
