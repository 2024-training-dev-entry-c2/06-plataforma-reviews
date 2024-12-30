package org.example.services;

import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;

import java.util.List;

public class RestaurantService {
  private RestaurantRepository repository;

  public RestaurantService() {
    this.repository = RestaurantRepository.getInstance();
  }

  //Para pruebas
  public RestaurantService(RestaurantRepository repository) {
    this.repository = repository;
  }

  public Boolean createRestaurant(Restaurant restaurant){
    return repository.addRestaurant(restaurant);
  }

  public Boolean editRestaurant(Restaurant restaurant, String restaurantAddress, Boolean restaurantAvailable){
    return repository.editRestaurant(restaurant, restaurantAddress, restaurantAvailable);
  }

  public Boolean deleteRestaurant(Restaurant restaurant){
    return repository.removeRestaurant(restaurant);
  }

  public List<Restaurant> getAvailableRestaurants(){
    return repository.getAvailableRestaurants();
  }

  public Restaurant getRestaurantByName(String restaurantName){
    return repository.getRestaurantByName(restaurantName);
  }



}
