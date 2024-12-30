package org.example.controllers;

import org.example.models.RestaurantReviewModel;
import org.example.services.ReviewService;

import java.util.List;

public class RestaurantReviewController {
  private final ReviewService reviewService;

  public RestaurantReviewController() {
    this.reviewService = new ReviewService();
  }

  public void addReviewToRestaurant(String restaurantName, String reviewerName, Double rating, String comment) {
    reviewService.addReviewToRestaurant(restaurantName, reviewerName, rating, comment);
  }

  public List<RestaurantReviewModel> getReviewsOfRestaurant(String restaurantName) {
    return reviewService.getReviewsForRestaurant(restaurantName);
  }
}