package org.example.controllers;

import org.example.models.Restaurant;
import org.example.services.ReviewRestaurantService;

public class ReviewRestaurantController {
  private ReviewRestaurantService reviewRestaurantService;

  public ReviewRestaurantController(){
    this.reviewRestaurantService = new ReviewRestaurantService();
  }

  public void addReview(Restaurant restaurant, Integer rating, String comment){
    reviewRestaurantService.addReview(restaurant, rating, comment);
    System.out.println("Reseña agregada exitosamente.");
  }

  public void getReviews(Restaurant restaurant){
    System.out.println("Reseñas de este restaurante: " + restaurant.getName());
    reviewRestaurantService.getReviews(restaurant).forEach(System.out::println);
  }

  public void setReviewRestaurantService(ReviewRestaurantService reviewRestaurantService) {
    this.reviewRestaurantService = reviewRestaurantService;
  }
}
