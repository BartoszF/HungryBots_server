package pl.bartoszf.hungry_bots;

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
    //private ScreenManager manager;

    /**
     * @param socket
     */
    public ClientWorker(final PlayerSocket socket, String homeDir) {
        this.socket = socket;
        this.workingDir = homeDir;
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

    public int getId() {
        return id;
    }


}
