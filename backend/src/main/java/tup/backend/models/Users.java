package tup.backend.models;

// ----- Imports ----- //
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class Users {

    // ----- Tabla users perteneciente a la base de datos ----- //

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user")
    private String user;

    @Column(name = "password")
    private String password;

    @Column(name = "points")
    private int points;


    // ----- Getters & Setters ----- //
    
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getUser(){
        return user;
    }

    public void setUser(String user){
        this.user = user;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public int getPoints(){
        return points;
    }

    public void setPoints(int points){
        this.points = points;
    }

}
