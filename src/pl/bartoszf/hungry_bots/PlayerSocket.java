package pl.bartoszf.hungry_bots;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

public class PlayerSocket {

    Socket socket;
    String userName;
    CharacterEntity character;
    int id = 0;
    GameManager gameManager;

    GameState gameState;

    public PlayerSocket(Socket s, int id) {
        this.socket = s;
        this.id = id;
        GameManager.players.add(this);
    }

    public Socket getSocket() {
        return socket;
    }

    public void setUserName(String username) {
        this.userName = username;

    }

    public String getUsername() {
        return userName;
    }

    public CharacterEntity getCharacter() {
        return character;
    }

    public void setCharacter(CharacterEntity c) {
        this.character = c;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void close() throws IOException {
        socket.close();
        GameManager.players.remove(this);
        Logger.getLogger("PlayerSocket[" + id + "]").info(userName + "[" + id + "] disconnected");
    }
}