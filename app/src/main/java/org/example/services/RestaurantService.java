package org.example.services;

import org.example.models.RestaurantModel;
import org.example.Interface.observable.Observer;
import org.example.repositories.DataRepository;

import java.util.List;

public class RestaurantService implements Observer {
  private final DataRepository repository;

  public RestaurantService() {
    this.repository = DataRepository.getInstance();
    repository.addObserver(this);
  }

  public void createRestaurant(String name, String address, boolean isAvailable) {
    if (repository.getRestaurant(name) != null) {
      throw new IllegalArgumentException("Restaurante ya existente.");
    }
    RestaurantModel restaurant = new RestaurantModel(name, address, isAvailable);
    repository.addRestaurant(restaurant);
  }

  public List<RestaurantModel> getAllRestaurants() {
    return repository.getAllRestaurants();
  }

  public void updateRestaurant(String name, String newAddress, boolean newAvailability) {
    RestaurantModel restaurant = repository.getRestaurant(name);
    if (restaurant == null) {
      throw new IllegalArgumentException("Restaurante no encontrado.");
    }
    restaurant.setAddress(newAddress);
    restaurant.setAvailable(newAvailability);
    repository.updateRestaurant(restaurant);
  }

  public void deleteRestaurant(String name) {
    if (repository.getRestaurant(name) == null) {
      throw new IllegalArgumentException("Restaurante no encontrado.");
    }
    repository.removeRestaurant(name);
    repository.removeObserver(this);
  }

  public Double getAverageRatingOfRestaurant(String name) {
    if (repository.getRestaurant(name) == null) {
      throw new IllegalArgumentException("Restaurant not found.");
    }
    return repository.calculateAverageRatingRestaurant(name);
  }


  @Override
  public void update(String message) {
    if (message.toLowerCase().contains("restaurant")) {
      System.out.println("Servicio de restaurantes revisado: " + message);
    }
  }
}
