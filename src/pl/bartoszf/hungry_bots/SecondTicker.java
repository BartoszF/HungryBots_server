package pl.bartoszf.hungry_bots;

import java.util.TimerTask;

public class SecondTicker extends TimerTask {

    private GameManager gameManager;

    public SecondTicker(GameManager gmanager) {
        gameManager = gmanager;
    }

    @Override
    public void run() {
        gameManager.tick();
    }
}
