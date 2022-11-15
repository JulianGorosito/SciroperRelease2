package tup.backend.models;

public class User_Datos {
    
    // --- Variables de USER_DATOS --- //

    public String player_name;
    public String player_password;
    public int player_points;
    public Boolean player_logged_on;
    

    // --- Getters & Setters --- //

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getPlayer_password() {
        return player_password;
    }

    public void setPlayer_password(String player_password) {
        this.player_password = player_password;
    }

    public int getPlayer_points() {
        return player_points;
    }

    public void setPlayer_points(int player_points) {
        this.player_points = player_points;
    }

    public Boolean getPlayer_logged_on() {
        return player_logged_on;
    }
    
    public void setPlayer_logged_on(Boolean player_logged_on) {
        this.player_logged_on = player_logged_on;
    }
}
