package org.example.controllers;

import org.example.models.DishReviewModel;
import org.example.services.ReviewService;

import java.util.List;

public class DishReviewController {
  private final ReviewService reviewService;

  public DishReviewController() {
    this.reviewService = new ReviewService();
  }

  public void addReviewToDish(String dishName, String reviewerName, Double rating, String comment) {
    reviewService.addReviewToDish(dishName, reviewerName, rating, comment);
  }

  public List<DishReviewModel> getReviewsOfDish(String dishName) {
    return reviewService.getReviewsForDish(dishName);
  }
}