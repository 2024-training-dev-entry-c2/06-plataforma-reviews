package org.example.services;

import org.example.constants.TypeReview;
import org.example.factory.ReviewFactory;
import org.example.interfaces.IObservable;
import org.example.interfaces.IObserver;
import org.example.models.Dish;
import org.example.models.Restaurant;
import org.example.models.review.DishReview;
import org.example.models.review.Review;
import org.example.repositories.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;

public class ReviewService implements IObservable {
  private RestaurantRepository repository;
  private final List<IObserver> observers = new ArrayList<>();
  private final ReviewFactory reviewFactory;

  public ReviewService() {
    this.repository = RestaurantRepository.getInstance();
    this.reviewFactory = new ReviewFactory();
  }

  //Para pruebas
  public ReviewService(RestaurantRepository repository, ReviewFactory reviewFactory) {
    this.repository = repository;
    this.reviewFactory = reviewFactory;
  }

  @Override
  public void addObserver(IObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(IObserver observer) {
    observers.remove(observer);
  }

  @Override
  public void notifyObservers(String message) {
    for(IObserver observer : observers){
      observer.update(message);
    }
  }

  public Boolean addDishReview(String comment, Float taste, Float presentation, String restaurantName, String dishName) {
    Dish dish = repository.getRestaurantByName(restaurantName).getMenu().searchDish(dishName);

    Review review = reviewFactory.createReview(TypeReview.DISH, comment, null, dish, taste, presentation );

    if (review != null) {
      repository.getRestaurantByName(restaurantName).getMenu().searchDish(dishName).addReview(review);
      notifyObservers("Se ha agregado una reseña al plato " + dishName + " del restaurante " + restaurantName + ".");
      return true;
    }

    return false;
  }

  public Boolean addRestaurantReview(String comment, Float rating, String restaurantName) {
    Restaurant restaurant = repository.getRestaurantByName(restaurantName);

    Review review = reviewFactory.createReview(TypeReview.RESTAURANT, comment, rating, restaurant, null, null);

    if (review != null) {
      repository.getRestaurantByName(restaurantName).addReview(review);
      notifyObservers("Se ha agregado una reseña al restaurante " + restaurantName + ".");
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

  public List<IObserver> getObservers() {
    return observers;
  }
}
