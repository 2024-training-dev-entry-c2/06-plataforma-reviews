package org.example.repositories;

import org.example.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantRepository {
  private static RestaurantRepository instance;
  private List<Restaurant> restaurants;

  private RestaurantRepository() {
    this.restaurants = new ArrayList<>();
  }

  public static RestaurantRepository getInstance() {
    if (instance == null) {
      instance = new RestaurantRepository();
    }
    return instance;
  }

  public void addRestaurant(Restaurant restaurant) {
    restaurants.add(restaurant);
  }

  public List<Restaurant> getRestaurants() {
    return new ArrayList<>(restaurants);
  }

  public void removeRestaurant(String name) {
    restaurants.removeIf(restaurant -> restaurant.getName().equalsIgnoreCase(name));
  }
}
