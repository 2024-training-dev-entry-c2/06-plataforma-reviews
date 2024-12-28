package org.example.models;

public abstract class Review {
  private static int idCounter = 1;
  private Integer reviewId;
  private Float ratingAverage;
  private String comment;

  protected Review() {
  }

  protected Review(String comment) {
    this.reviewId = generateId();
    this.ratingAverage = 0f;
    this.comment = comment;
  }

  private static int generateId() {
    return idCounter++;
  }

  public Integer getReviewId() {
    return reviewId;
  }

  public void setReviewId(Integer reviewId) {
    this.reviewId = reviewId;
  }

  public Float getRatingAverage() {
    return ratingAverage;
  }

  public void setRatingAverage(Float ratingAverage) {
    this.ratingAverage = ratingAverage;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public abstract void calculateRating();
}