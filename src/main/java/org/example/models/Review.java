package org.example.models;

public abstract class Review {
  private Integer rating;
  private String comment;

  public Review(Integer rating, String comment) {
    this.rating = rating;
    this.comment = comment;
  }

  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  @Override
  public String toString() {
    return "Review{" +
            "rating=" + rating +
            ", comment='" + comment + '\'' +
            '}';
  }
}
