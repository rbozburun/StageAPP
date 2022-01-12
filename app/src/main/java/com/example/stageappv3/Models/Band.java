package com.example.stageappv3.Models;

// Coded by Nesim Özgün
public class Band {
    String name;
    String email;
    String location;
    String genre;
    String imageURL;

    public Band() {
    }

    public Band(String name, String email, String genre, String location, String imageURL) {
        this.name = name;
        this.email = email;
        this.location = location;
        this.genre = genre;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGenre() {return genre;}

    public void setGenre(String genre) {this.location = location;}

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }


}
