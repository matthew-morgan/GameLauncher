package launcher;

import java.io.Serializable;

class Player implements Serializable{
    protected String username;
    protected String password;
    protected int highscore;

    public Player(String username, String password){this.username = username; this.password = password;}

    protected String getUsername() {return username;}
    //In a real application only a hash of the password would be returned.
    //Dans une application réelle seulement un hash du mot de passe serait renvoyé.
    private String getPassword() {return password;}
    protected Boolean passwordMatches(Player p){return p.getPassword().equals(this.getPassword());}
    private void setUsername(String username) {this.username = username;}
    private void setPassword(String password) {this.password = password;}
    private int getHighscore() {return highscore;}
    private void setHighscore(int highscore) {this.highscore = highscore;}
}
