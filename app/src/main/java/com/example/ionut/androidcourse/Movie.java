package com.example.ionut.androidcourse;

import java.util.ArrayList;

public class Movie {
    private String name;
    private String shortDescription;
    private String genre;
    private String photoBase64;
    private float rating;
    private ArrayList<RelatedMovie> relatedMovieArrayList;
    private String link;



    public Movie(String name, String shortDescription,
                 String genre, float rating, String photoBase64,
                 ArrayList<RelatedMovie> relatedMovieArrayList, String link) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.genre = genre;
        this.photoBase64 = photoBase64;
        this.rating = rating;
        this.relatedMovieArrayList = relatedMovieArrayList;
        this.link=link;


    }
    //region-Getter, Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPhotoBase64() {
        return photoBase64;
    }

    public void setPhotoBase64(String photoBase64) {
        this.photoBase64 = photoBase64;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public ArrayList<RelatedMovie> getRelatedMovieArrayList() {
        return relatedMovieArrayList;
    }

    public void setRelatedMovieArrayList(ArrayList<RelatedMovie> relatedMovieArrayList) {
        this.relatedMovieArrayList = relatedMovieArrayList;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    //endregion
}
