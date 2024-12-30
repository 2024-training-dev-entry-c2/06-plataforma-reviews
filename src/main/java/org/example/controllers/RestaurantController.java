package org.example.controllers;

import org.example.models.Restaurant;
import org.example.services.RestaurantService;

import java.util.List;

public class RestaurantController {
  private RestaurantService restaurantService;

  public RestaurantController(){
    this.restaurantService = new RestaurantService();
  }

  public void addRestaurant(String name, String address) {
    restaurantService.addRestaurant(name, address);
    System.out.println("Restaurante agregado exitosamente.");
  }

  public void getRestaurants(){
    List<Restaurant> restaurants = restaurantService.getRestaurants();
    if (restaurants.isEmpty()){
      System.out.println("No hay restaurantes registrados.");
    } else {
      System.out.println("Restaurantes registrados:");
      restaurants.forEach(System.out::println);
    }
  }

  public void setRestaurantService(RestaurantService restaurantService) {
    this.restaurantService = restaurantService;
  }


  public void removeRestaurant(String name){
    restaurantService.removeRestaurant(name);
    System.out.println("Restaurante eliminado exitosamente.");
  }
}
