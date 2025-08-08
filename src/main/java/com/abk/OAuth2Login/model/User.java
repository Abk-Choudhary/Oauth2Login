package com.abk.OAuth2Login.model;

import jakarta.persistence.*;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String password, String name, String userEmail, String profilePicture) {
        this.password = password;
        this.name = name;
        this.userEmail = userEmail;
        this.profilePicture = profilePicture;
    }

    private String name;

    private String userEmail;

    private String profilePicture;

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public User() {
    }

    public User(int id, String name, String userEmail, String profilePicture) {
        this.id = id;
        this.name = name;
        this.userEmail = userEmail;
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public User(String userEmail, String name) {
        this.userEmail = userEmail;
        this.name = name;
    }
}
