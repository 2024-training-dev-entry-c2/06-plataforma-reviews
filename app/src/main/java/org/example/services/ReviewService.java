package org.example.services;

import constants.TypeReview;
import org.example.factory.ReviewFactory;
import org.example.models.Dish;
import org.example.models.Restaurant;
import org.example.models.review.DishReview;
import org.example.models.review.Review;
import org.example.repositories.RestaurantRepository;

import java.util.List;

public class ReviewService {
  private RestaurantRepository repository;

  public ReviewService() {
    this.repository = RestaurantRepository.getInstance();
  }

  public Boolean addDishReview(String comment, Float taste, Float presentation, String restaurantName, String dishName) {
    Dish dish = repository.getRestaurantByName(restaurantName).getMenu().searchDish(dishName);

    Review review = ReviewFactory.createReview(TypeReview.DISH, comment, null, dish, taste, presentation );

    if (review != null) {
      repository.getRestaurantByName(restaurantName).getMenu().searchDish(dishName).addReview(review);
      return true;
    }

    return false;
  }

  public Boolean addRestaurantReview(String comment, Float rating, String restaurantName) {
    Restaurant restaurant = repository.getRestaurantByName(restaurantName);

    Review review = ReviewFactory.createReview(TypeReview.RESTAURANT, comment, rating, restaurant, null, null);

    if (review != null) {
      repository.getRestaurantByName(restaurantName).addReview(review);
      return true;
    }
    return false;
  }


  public List<Review> getRestaurantReviews(String restaurantName) {
    return repository.getRestaurantByName(restaurantName).getReviews();
  }

  public List<Review> getDishReviews(String restaurantName, String dishName) {
    return repository.getRestaurantByName(restaurantName).getMenu().searchDish(dishName).getReviews();
  }

  public float calculateAverageRestaurantRating(String restaurantName) {
    List<Review> reviews = repository.getRestaurantByName(restaurantName).getReviews();
    return (float) reviews.stream()
      .mapToDouble(review -> review.getRating() != null ? review.getRating() : 0.0)
      .average()
      .orElse(0.0);
  }

  public float calculateAverageDishRating(String restaurantName, String dishName) {
    List<Review> reviews = repository.getRestaurantByName(restaurantName).getMenu().searchDish(dishName).getReviews();
    return (float) reviews.stream()
      .mapToDouble(review -> {
        DishReview dishReview = (DishReview) review;
        return (dishReview.getTaste() + dishReview.getPresentation()) / 2;
      }).average().orElse(0.0);
  }
}
