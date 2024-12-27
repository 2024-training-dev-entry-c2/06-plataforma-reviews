package org.example.repositories;

import org.example.models.Restaurant;

import java.util.HashMap;
import java.util.Map;

public class RestaurantRepository {
  private static RestaurantRepository instance;
  private final Map<String,Restaurant> restaurants;

  public RestaurantRepository() {
    this.restaurants = new HashMap<>();
  }

  public static synchronized RestaurantRepository getInstance(){
    if(instance == null){
      instance = new RestaurantRepository();
    }
    return instance;
  }

  public void addRestaurant(Restaurant restaurant){
    restaurants.put(restaurant.getName(), restaurant);
  }
}
