package org.example.models;

public abstract class Review {
    private String reviewId;
    private Float averageRating;
    private String comment;

    public Review(String reviewId, String comment) {
        this.reviewId = reviewId;
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

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }
}