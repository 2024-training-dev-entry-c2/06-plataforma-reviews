package org.example.services;

import org.example.models.RestaurantModel;
import org.example.Interface.observable.Observer;
import org.example.repositories.RestaurantRepository;


import java.util.List;

public class RestaurantService implements Observer {
  private final RestaurantRepository repositoryRestaurant;


  public RestaurantService() {
    this.repositoryRestaurant = RestaurantRepository.getInstance();
    repositoryRestaurant.addObserver(this);
  }

  public void createRestaurant(String name, String address, boolean isAvailable) {
    if (repositoryRestaurant.getRestaurant(name) != null) {
      throw new IllegalArgumentException("Restaurante ya existente.");
    }
    RestaurantModel restaurant = new RestaurantModel(name, address, isAvailable);
    repositoryRestaurant.addRestaurant(restaurant);
  }

  public List<RestaurantModel> getAllRestaurants() {
    return repositoryRestaurant.getAllRestaurants();
  }

  public void updateRestaurant(String name, String newAddress, boolean newAvailability) {
    RestaurantModel restaurant = repositoryRestaurant.getRestaurant(name);
    if (restaurant == null) {
      throw new IllegalArgumentException("Restaurante no encontrado.");
    }
    restaurant.setAddress(newAddress);
    restaurant.setAvailable(newAvailability);
    repositoryRestaurant.updateRestaurant(restaurant);
  }

  public void deleteRestaurant(String name) {
    if (repositoryRestaurant.getRestaurant(name) == null) {
      throw new IllegalArgumentException("Restaurante no encontrado.");
    }
    repositoryRestaurant.removeRestaurant(name);
    repositoryRestaurant.removeObserver(this);
  }

  public Double getAverageRatingOfRestaurant(String name) {
    if (repositoryRestaurant.getRestaurant(name) == null) {
      throw new IllegalArgumentException("Restaurant not found.");
    }
    return repositoryRestaurant.calculateAverageRatingRestaurant(name);
  }


  @Override
  public void update(String message) {
    if (message.toLowerCase().contains("restaurant")) {
      System.out.println("Servicio de restaurantes revisado: " + message);
    }
  }
}
