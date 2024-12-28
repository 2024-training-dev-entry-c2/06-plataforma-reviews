package org.example.models;

public abstract class Review {
    private String comment;
    public Review(String comment) {
        this.comment = comment;
    }
    abstract void showDetails();
}
