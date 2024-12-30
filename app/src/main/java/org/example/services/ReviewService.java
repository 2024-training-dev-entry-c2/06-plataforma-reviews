package org.example.services;

import org.example.models.DishModel;
import org.example.models.DishReviewModel;
import org.example.models.RestaurantModel;
import org.example.models.RestaurantReviewModel;
import org.example.Interface.observable.Observer;
import org.example.repositories.DishRepository;
import org.example.repositories.RestaurantRepository;

import java.util.List;

public class ReviewService implements Observer {
  private final RestaurantRepository repositoryRestaurant;
  private final DishRepository repositoryDish;

  public ReviewService() {
    this.repositoryDish = DishRepository.getInstance();
    this.repositoryRestaurant = RestaurantRepository.getInstance();
    repositoryDish.addObserver(this);
    repositoryRestaurant.addObserver(this);
  }

  public void addReviewToRestaurant(String restaurantName, String reviewerName, Double rating, String comment) {
    RestaurantModel restaurant = repositoryRestaurant.getRestaurant(restaurantName);
    if (restaurant == null) {
      throw new IllegalArgumentException("Restaurante no encontrado: " + restaurantName);
    }
    repositoryRestaurant.addReviewToRestaurant(new RestaurantReviewModel(reviewerName, rating, comment, restaurant));
  }

  public void addReviewToDish(String dishName, String reviewerName, Double rating, String comment) {
    DishModel dish = repositoryDish.getDish(dishName);
    if (dish == null) {
      throw new IllegalArgumentException("Plato no encontrado: " + dishName);
    }
    repositoryDish.addReviewToDish(new DishReviewModel(reviewerName, rating, comment, dish));
  }

  public List<RestaurantReviewModel> getReviewsForRestaurant(String restaurantName) {
    RestaurantModel restaurant = repositoryRestaurant.getRestaurant(restaurantName);
    if (restaurant == null) {
      throw new IllegalArgumentException("Restaurante no encontrado: " + restaurantName);
    }
    return restaurant.getReviews();
  }

  public List<DishReviewModel> getReviewsForDish(String dishName) {
    DishModel dish = repositoryDish.getDish(dishName);
    if (dish == null) {
      throw new IllegalArgumentException("Plato no encontrado: " + dishName);
    }
    return dish.getReviews();
  }


  @Override
  public void update(String message) {
    if (message.toLowerCase().contains("review")) {
      System.out.println("Servicio de reseñas revisado: " + message);
    }
  }
}
