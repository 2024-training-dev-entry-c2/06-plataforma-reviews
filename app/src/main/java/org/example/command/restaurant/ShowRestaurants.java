package org.example.command.restaurant;

import org.example.interfaces.ICommand;
import org.example.models.Restaurant;
import org.example.services.RestaurantService;

public class ShowRestaurants implements ICommand {
  private RestaurantService service;

  public ShowRestaurants(RestaurantService service){
    this.service = service;
  }


  @Override
  public void execute() {
    System.out.println("\nLista de restaurantes");
    service.getAvailableRestaurants().forEach(restaurant -> {
      System.out.print("- ");
      restaurant.displayRestaurant();
    });
  }
}
