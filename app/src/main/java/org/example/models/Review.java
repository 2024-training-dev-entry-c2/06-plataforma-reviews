package org.example.models;

public abstract class Review {
  private Float score;
  private String comment;

  public Review(Float score, String comment) {
    this.score = score;
    this.comment = comment;
  }

  @Override
  public String toString() {
    return "\nReseña " +"\nCalificación: " + score + "\nComentario: " + comment;
  }

  public Float getScore() {
    return score;
  }

  public void setScore(Float score) {
    this.score = score;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public abstract Long getReviewedId();
}
