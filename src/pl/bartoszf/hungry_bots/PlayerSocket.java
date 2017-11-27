package pl.bartoszf.hungry_bots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

public class PlayerSocket {

    Socket socket;
    String userName;
    CharacterEntity character;
    int id = 0;

    BufferedReader reader;
    PrintWriter out;

    GameManager gameManager;

    GameState gameState;

    public PlayerSocket(Socket s, int id, GameManager gameManager) {
        this.socket = s;
        this.id = id;

        try {
            reader = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
            out = new PrintWriter(getSocket().getOutputStream(), true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        this.gameManager = gameManager;
        gameManager.addPlayer(this);
        //GameManager.players.add(this);
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

    public GameManager getGameManager() {
        return gameManager;
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

    public BufferedReader getReader() {
        return reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public void close() throws IOException {
        socket.close();
        gameManager.closed(this);
        Logger.getLogger("PlayerSocket[" + id + "]").info(userName + "[" + id + "] disconnected");
    }
}