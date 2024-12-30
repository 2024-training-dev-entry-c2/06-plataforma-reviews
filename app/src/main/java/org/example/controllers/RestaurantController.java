package org.example.controllers;

import org.example.models.RestaurantModel;
import org.example.services.RestaurantService;

import java.util.List;

public class RestaurantController {
  private final RestaurantService restaurantService;


  public RestaurantController() {
    this.restaurantService = new RestaurantService();
  }

  public void createRestaurant(String name, String address, Boolean isAvailable) {
    restaurantService.createRestaurant(name, address, isAvailable);
  }

  public List<RestaurantModel> getAllRestaurants() {
    return restaurantService.getAllRestaurants();
  }

  public void updateRestaurant(String name, String newAddress, Boolean newAvailability) {
    restaurantService.updateRestaurant(name, newAddress, newAvailability);
  }

  public void deleteRestaurant(String name) {
    restaurantService.deleteRestaurant(name);
  }

  public Double getAverageRatingOfRestaurant(String restaurantName) {
    return restaurantService.getAverageRatingOfRestaurant(restaurantName);
  }
}