package org.example.controllers.restaurant;

import org.example.controllers.IControllerCommand;
import org.example.services.RestaurantService;

public class DisplayRestaurantsControllerCommand implements IControllerCommand {
  private final RestaurantService restaurantService;

  public DisplayRestaurantsControllerCommand(RestaurantService restaurantService) {
    this.restaurantService = restaurantService;
  }
  @Override
  public void execute() {
    this.restaurantService.displayRestaurants();
  }
}
