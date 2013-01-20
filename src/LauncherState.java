/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 20/12/12
 * Time: 15:10
 * To change this template use File | Settings | File Templates.
 */

import java.io.*;
import java.net.URL;
import java.util.*;


public class LauncherState implements Serializable {
    protected ArrayList<Player> playerList;
    protected Player currentPlayer;
    protected ArrayList<URLAndClassName> storedGames;

    public LauncherState(){
        storedGames = new ArrayList<URLAndClassName>();
        playerList = new ArrayList<Player>();
        currentPlayer = null;
    }

    public void addPlayer(Player player){this.playerList.add(player);}
    public void setCurrentPlayer(Player player){this.currentPlayer = player;}
    public void addGame(URL url, String className){storedGames.add(new URLAndClassName(url, className));}

    public ArrayList<Player> getPlayerList(){return this.playerList;}
    public Player getCurrentPlayer(){return this.currentPlayer;}
    public ArrayList<URLAndClassName> getStoredGames(){return this.storedGames;}

}
