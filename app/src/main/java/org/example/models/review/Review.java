package org.example.models.review;

public abstract class Review {
  private String comment;
  private Float rating;

  public Review(String comment, Float rating) {
    this.comment = comment;
    this.rating = rating;
  }

  public Review() {
  }

  public abstract void displayReview();

  public String getComment() {
    return comment;
  }

  public Float getRating() {
    return rating;
  }

}
