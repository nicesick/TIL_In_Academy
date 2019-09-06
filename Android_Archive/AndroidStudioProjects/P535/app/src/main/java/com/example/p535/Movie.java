package com.example.p535;

public class Movie {
    String title;
    String actor;
    String img;
    int rating;

    public Movie(String title, String actor, String img, int rating) {
        this.title = title;
        this.actor = actor;
        this.img = img;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
