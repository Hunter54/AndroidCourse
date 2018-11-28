package com.example.ionut.androidcourse;

public class RelatedMovie {
    private float rating;
    private String photoBase64;
    private String nume;

    public RelatedMovie(float rating, String photoBase64, String nume) {
        this.rating = rating;
        this.photoBase64 = photoBase64;
        this.nume = nume;
    }
    //region-Getter,Setter
    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPhotoBase64() {
        return photoBase64;
    }

    public void setPhotoBase64(String photoBase64) {
        this.photoBase64 = photoBase64;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
    //endregion
}
