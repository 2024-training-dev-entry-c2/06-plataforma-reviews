package org.example.models;

public class DishReview extends Review {
  private Long dishId;

  public DishReview(Float score, String comment, Long dishId) {
    super(score, comment);
    this.dishId = dishId;
  }

  @Override
  public Long getReviewedId() {
    return dishId;
  }
}
