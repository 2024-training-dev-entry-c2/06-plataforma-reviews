package org.example.models;

public class RestaurantReviewModel extends Review {
  private RestaurantModel restaurant;

  public RestaurantReviewModel(String reviewerName, Double rating, String comment, RestaurantModel restaurant) {
    super(reviewerName, rating, comment);
    this.restaurant = restaurant;
  }

  public RestaurantModel getRestaurant() {
    return restaurant;
  }

  public void setRestaurant(RestaurantModel restaurant) {
    this.restaurant = restaurant;
  }

  @Override
  public String getReviewType() {
    return "Restaurant";
  }
}