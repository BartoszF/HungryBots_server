package pl.bartoszf.hungry_bots;

import pl.bartoszf.hungry_bots.States.ClientState;
import pl.bartoszf.hungry_bots.States.ConnectedState;

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
        this.state = new ConnectedState(socket, this);
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
                //manager.handle();
                // TODO: Handle messages
                //
                //
                /*String command = socket.getReader().readLine();
                if(command.equals("REGISTER"))
                {
                    socket.setUserName("" + socket.getId());
                    socket.gameManager.register(socket);
                }*/
                state.handle();
                if (socket.getSocket().isClosed()) cancel = true;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        } finally {
            try {
                socket.close();

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


}
