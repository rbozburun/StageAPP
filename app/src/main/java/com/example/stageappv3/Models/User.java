package com.example.stageappv3.Models;

// Coded by Resul Bozburun
public class User {
    String nameSurname;
    String email;
    String role;
    String imageURL;
    String profileType = null;


    public User() {
    }


    public User(String nameSurname, String email, String role, String imageURL) {
        this.nameSurname = nameSurname;
        this.email = email;
        this.role = role;
        this.imageURL = imageURL;
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

    public String getProfileType() {
        return profileType;
    }

    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }
    

}




