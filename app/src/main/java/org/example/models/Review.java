package org.example.models;

public abstract class Review {
  private Float ratingAverage;
  private String comment;

  protected Review(String comment) {
    this.ratingAverage = 0f;
    this.comment = comment;
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

  public abstract void calculateRating();
}