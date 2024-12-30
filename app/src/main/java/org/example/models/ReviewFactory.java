package org.example.models;

public class ReviewFactory {
  public Review createReview(String typeOfReview,Float score, String comment, Long reviewedId) {
    if(typeOfReview.equalsIgnoreCase("Restaurant")) {
      return new RestaurantReview(score, comment, reviewedId);
    }
    else if(typeOfReview.equalsIgnoreCase("Dish")) {
      return new DishReview(score, comment, reviewedId);
    }
    return null;
  }
}
