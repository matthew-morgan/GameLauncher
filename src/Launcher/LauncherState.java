package launcher;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

class LauncherState implements Serializable {
    protected final ArrayList<Player> playerList;
    protected Player currentPlayer;
    protected final ArrayList<URLAndClassName> storedGames;

    public LauncherState(){
        storedGames = new ArrayList<URLAndClassName>();
        playerList = new ArrayList<Player>();
        currentPlayer = null;
    }

    protected void addPlayer(Player player){this.playerList.add(player);}
    protected void setCurrentPlayer(Player player){this.currentPlayer = player;}
    protected void addGame(URL url, String className){storedGames.add(new URLAndClassName(url, className));}

    protected ArrayList<Player> getPlayerList(){return this.playerList;}
    protected Player getCurrentPlayer(){return this.currentPlayer;}
    protected ArrayList<URLAndClassName> getStoredGames(){return this.storedGames;}
    protected Boolean playerExists(Player player){
        for (Player p : playerList){if(player.getUsername().equals(p.getUsername())) return true;}
        return false;
    }

    protected Boolean validPassword(Player player){
        for (Player p : playerList){
            if(player.getUsername().equals(p.getUsername()) && player.passwordMatches(p)) return true;}
        return false;
    }

}
