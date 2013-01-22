/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 20/12/12
 * Time: 14:51
 * To change this template use File | Settings | File Templates.
 */

import java.io.Serializable;

class Player implements Serializable{
    protected String username;
    protected String password;
    protected int highscore;

    private String getUsername() {return username;}
    private void setUsername(String username) {this.username = username;}
    private void setPassword(String password) {this.password = password;}
    private String getPassword() {return password;}
    private int getHighscore() {return highscore;}
    private void setHighscore(int highscore) {this.highscore = highscore;}
}
