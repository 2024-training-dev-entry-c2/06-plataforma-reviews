package org.example.models;

public abstract class Review {
    private String idReview;
    private Float averageRating;
    private String comment;

    public Review(String idReview, String comment) {
        this.idReview = idReview;
        this.comment = comment;
    }

    public abstract void calculateRating();

    public Float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Float averageRating) {
        this.averageRating = averageRating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getIdReview() {
        return idReview;
    }

    public void setIdReview(String idReview) {
        this.idReview = idReview;
    }
}