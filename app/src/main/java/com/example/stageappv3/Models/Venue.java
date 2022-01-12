package com.example.stageappv3.Models;

// Coded by Nesim Özgün
public class Venue {
    String name;
    String email;
    String location;
    String imageURL;

    public Venue() {
    }

    public Venue(String name, String email, String location, String imageURL) {
        this.name = name;
        this.email = email;
        this.location = location;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String nameSurname) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String role) {
        this.location = location;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }


}
