package org.example.models;

public class Review {
    private String id;
    private Float averageRating;
    private String comment;

    public Review(String id, Float averageRating, String comment) {
        this.id = id;
        this.averageRating = averageRating;
        this.comment = comment;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}