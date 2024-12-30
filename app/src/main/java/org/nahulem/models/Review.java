package org.nahulem.models;

public abstract class Review {
    private static Integer idCounter = 0;
    private Integer reviewId;
    private Float averageRating;
    private String comment;

    public Review(String comment) {
        this.reviewId = generateId();
        this.averageRating = 0f;
        this.comment = comment;
    }

    public Review() {
    }


    @Override
    public String toString() {
        return "Comentario: " + comment +
                "\n      Calificación promedio: " + String.format("%.1f", averageRating) + "\n";
    }



    public abstract void calculateRating();

    private static Integer generateId() {
        return idCounter++;
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

    public static Integer getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(Integer idCounter) {
        Review.idCounter = idCounter;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }
}