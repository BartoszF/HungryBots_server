package pl.bartoszf.hungry_bots;

import pl.bartoszf.hungry_bots.Configuration.ConfigurationManager;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    private static final ExecutorService executor = Executors
            .newFixedThreadPool(ConfigurationManager.INSTANCE.getMaxThreads());
    private static final String workingDir = System.getProperty("user.dir");
    private static final Logger logger = Logger.getLogger(App.class.getName());
    private static final int GIVEN_PORT = 0;
    private static ServerSocket server = null;
    private static int connNum = 0;

    private static Timer timer;
    private static SecondTicker secondTicker;
    private static GameManager gameManager;

    public static void main(String[] args)
    {
        try {
            // establish a connection
            server = new ServerSocket(GIVEN_PORT == 0 ? ConfigurationManager.INSTANCE.getPort() : GIVEN_PORT);
            logger.info("Server running and listening on port : "
                    + (GIVEN_PORT == 0 ? ConfigurationManager.INSTANCE.getPort() : GIVEN_PORT));

            gameManager = new GameManager();
            secondTicker = new SecondTicker(gameManager);
            timer = new Timer();
            timer.scheduleAtFixedRate(secondTicker, 0, 1000);


            while (true) {
                Socket sock = server.accept();
                /*for (PlayerSocket so : GameManager.players) {
                    if (so.getSocket().getInetAddress().equals(sock.getInetAddress())) {
                        PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
                        out.write("Someone is already connected on this IP Address");
                        out.flush();
                        sock.close();
                        return;
                    }
                }*/
                PlayerSocket s = new PlayerSocket(sock, connNum++, gameManager);
                executor.execute(new ClientWorker(s, workingDir));
            }

        } catch (Exception e) {
            logger.log(Level.WARNING, e.getClass().getName() + ": " + e.getMessage());
            logger.log(Level.WARNING, "Shutting down the server..");
        } finally {
            executor.shutdown();
        }
    }
}
