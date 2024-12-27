package org.example.models;

public abstract class Review {
  private Integer reviewId;
  private Float ratingAverage;
  private String comment;

  protected Review() {
  }

  protected Review(Integer reviewId, Float ratingAverage, String comment) {
    this.reviewId = reviewId;
    this.ratingAverage = ratingAverage;
    this.comment = comment;
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