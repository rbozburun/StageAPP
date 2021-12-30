package com.example.stageappv3.Models;

public class User {
    String nameSurname;
    String email;
    String role;
    String getImageURL;



    public User(String nameSurname, String email, String role, String getImageURL) {
        this.nameSurname = nameSurname;
        this.email = email;
        this.role = role;
        this.imageURL = getImageURL;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    String imageURL;

}




