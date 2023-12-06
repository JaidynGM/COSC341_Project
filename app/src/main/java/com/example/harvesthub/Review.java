package com.example.harvesthub;


public class Review {
    private String vendor, name;
    private float rating;
    private String comment;

    public Review(String vendor, String name, float rating, String comment) {
        this.vendor = vendor;
        this.name = name;
        this.rating = rating;
        this.comment = comment;
    }

    public String getVendor() {return vendor;}
    public String getName() {return name;}

    public float getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}