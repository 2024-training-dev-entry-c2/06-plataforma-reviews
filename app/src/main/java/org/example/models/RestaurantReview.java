package org.example.models;

public class RestaurantReview extends Review {
  private Long restaurantId;

  public RestaurantReview(Float score, String comment, Long restaurantId) {
    super(score, comment);
    this.restaurantId = restaurantId;
  }

  @Override
  public Long getReviewedId() {
    return restaurantId;
  }
}
