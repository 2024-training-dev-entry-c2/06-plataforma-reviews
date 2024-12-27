package org.example.services;

import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;

import java.util.List;

public class RestaurantService {
  private RestaurantRepository repository;

  public RestaurantService() {
    this.repository = RestaurantRepository.getInstance();
  }

  public Boolean createRestaurant(Restaurant restaurant){
    return repository.addRestaurant(restaurant);
  }

  public Boolean editRestaurant(Restaurant restaurant, String restaurantName, String restaurantAddress){
    return repository.editRestaurant(restaurant, restaurantName, restaurantAddress);
  }

  public Boolean deleteRestaurant(Restaurant restaurant){
    return repository.removeRestaurant(restaurant);
  }

  public List<Restaurant> getRestaurants(){
    return repository.getRestaurants();
  }

  public Restaurant getRestaurantByName(String restaurantName){
    return repository.getRestaurantByName(restaurantName);
  }


}
