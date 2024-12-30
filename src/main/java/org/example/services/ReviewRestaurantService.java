package org.example.services;

import org.example.models.Restaurant;
import org.example.models.ReviewFactory;
import org.example.models.ReviewFactory.ReviewType;
import org.example.models.ReviewRestaurant;

import java.util.List;

public class ReviewRestaurantService {
  public void addReview(Restaurant restaurant, Integer rating, String comment){
    ReviewRestaurant review = (ReviewRestaurant) ReviewFactory.createReview(ReviewType.RESTAURANT, rating, comment);
    restaurant.addReview(review);
  }

  public List<ReviewRestaurant> getReviews(Restaurant restaurant){
    return restaurant.getReviews();
  }
}
