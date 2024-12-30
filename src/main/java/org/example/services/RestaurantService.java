package org.example.services;

import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;

import java.util.List;

public class RestaurantService {
  private RestaurantRepository restaurantRepository;

  public RestaurantService() {
    this.restaurantRepository = RestaurantRepository.getInstance();
  }

  public void addRestaurant(String name, String address) {
    Restaurant restaurant = new Restaurant(name, address);
    restaurantRepository.addRestaurant(restaurant);
  }

  public List<Restaurant> getRestaurants() {
    return restaurantRepository.getRestaurants();
  }

  public void removeRestaurant(String name) {
    restaurantRepository.removeRestaurant(name);
  }
}
