package org.example.models;

public abstract class Review {
  private String reviewerName;
  private Double rating;
  private String comment;

  public Review(String reviewerName, Double rating, String comment) {
    this.reviewerName = reviewerName;
    this.rating = rating;
    this.comment = comment;
  }

  public String getReviewerName() {
    return reviewerName;
  }

  public void setReviewerName(String reviewerName) {
    this.reviewerName = reviewerName;
  }

  public Double getRating() {
    return rating;
  }

  public void setRating(Double rating) {
    this.rating = rating;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public abstract String getReviewType();
}