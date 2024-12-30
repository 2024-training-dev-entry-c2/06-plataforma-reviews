package org.example.repositories;

import org.example.models.Restaurant;

import java.util.HashMap;
import java.util.List;
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

  public Boolean addRestaurant(Restaurant restaurant){
    if( restaurants.get(restaurant.getName()) != null){
      return false;
    }
    restaurants.put(restaurant.getName(), restaurant);
    return  true;
  }

  public Boolean editRestaurant(Restaurant restaurant, String restaurantAddress, Boolean restaurantAvailable){
    if( restaurants.get(restaurant.getName()) != null){
      restaurants.get(restaurant.getName()).setAvailable(restaurantAvailable);
      restaurants.get(restaurant.getName()).setAddress(restaurantAddress);
      return true;
    }
    return false;
  }

  public Boolean removeRestaurant(Restaurant restaurant){
    if( restaurants.get(restaurant.getName()) != null){
      restaurants.remove(restaurant.getName());
      return true;
    }
    return  false;
  }

  public Restaurant getRestaurantByName(String restaurantName){
    return restaurants.get(restaurantName);
  }

  public List<Restaurant> getAvailableRestaurants() {
    return restaurants.values().stream().filter(Restaurant::getAvailable).toList();
  }

  //Para pruebas
  public void clear() {
    restaurants.clear();
  }
}
