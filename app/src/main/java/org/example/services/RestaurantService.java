package org.example.services;

import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;

public class RestaurantService {
  private final RestaurantRepository restaurantRepository;


  public RestaurantService() {
    this.restaurantRepository = RestaurantRepository.getInstance();
  }

  public void addRestaurant(String name, String phone, String description) {
    this.restaurantRepository.addRestaurant(name, phone, description);
    System.out.println("Se ha añadido el restaurante " + name);
  }

  public void updateRestaurant(Long id, String name, String phone, String description) {
    Restaurant restaurant = this.restaurantRepository.getRestaurants().get(id);
    if(restaurant != null) {
      this.restaurantRepository.updateRestaurant(restaurant, name, phone, description);
      System.out.println("Se ha actualizado el restaurante " + name);
    } else {
      System.out.println("El restaurante con ID " + id + " no existe.");
    }
  }

  public void removeRestaurant(Long id) {
    if(this.restaurantRepository.removeRestaurant(id)) {
      System.out.println("Se ha eliminado el restaurante con ID " + id);
    } else {
      System.out.println("El restaurante con ID " + id + " no existe.");
    }
  }

  public void displayRestaurants() {
    System.out.println("\nRestaurantes disponibles:");
    this.restaurantRepository.getRestaurants().forEach((id, restaurant) -> System.out.println("\n"+id + ". " + restaurant.toString()));
  }

}
