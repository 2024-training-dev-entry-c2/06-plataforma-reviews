package org.example.commands.Restaurants;

import org.example.Interface.ICommand;
import org.example.models.RestaurantModel;
import org.example.models.RestaurantReviewModel;
import org.example.controllers.RestaurantReviewController;

public class CreateRestaurantReviewCommand implements ICommand {
  private final RestaurantModel restaurant;
  private final String reviewerName;
  private final Double rating;
  private final String comment;
  private final RestaurantReviewController restaurantReviewController = new RestaurantReviewController();

  public CreateRestaurantReviewCommand(RestaurantModel restaurant, String reviewerName, Double rating, String comment) {
    this.restaurant = restaurant;
    this.reviewerName = reviewerName;
    this.rating = rating;
    this.comment = comment;
  }

  @Override
  public void execute() {
    RestaurantReviewModel review = new RestaurantReviewModel(reviewerName, rating, comment, restaurant);
    restaurant.addReview(review);
    restaurantReviewController.addReviewToRestaurant(restaurant.getName(), review.getReviewerName(), review.getRating(), review.getComment());
  }
}