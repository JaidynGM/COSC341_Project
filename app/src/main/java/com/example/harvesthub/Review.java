package com.example.harvesthub;


public class Review {
    private String name;
    private float rating;
    private String comment;

    public Review(String name, int rating, String comment) {
        this.name = name;
        this.rating = rating;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return (int) rating;
    }

    public String getComment() {
        return comment;
    }
}