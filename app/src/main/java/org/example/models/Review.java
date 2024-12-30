package org.example.models;

public class Review {
    private String comment;
    private Integer score;
    private Object reviewedItem; // Can be either Dish or Restaurant

    public Review() {
    }

    public Review(String comment, Integer score, Object reviewedItem) {
        this.comment = comment;
        this.score = score;
        this.reviewedItem = reviewedItem;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Object getReviewedItem() {
        return reviewedItem;
    }

    public void setReviewedItem(Object reviewedItem) {
        this.reviewedItem = reviewedItem;
    }
}