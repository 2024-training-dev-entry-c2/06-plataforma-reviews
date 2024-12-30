package org.example.controllers.restaurant;

import org.example.controllers.IControllerCommand;
import org.example.services.RestaurantService;
import org.example.utils.IHandler;

public class RemoveRestaurantControllerCommand implements IControllerCommand {
  private final IHandler handler;
  private final RestaurantService restaurantService;

  public RemoveRestaurantControllerCommand(IHandler handler, RestaurantService restaurantService) {
    this.handler = handler;
    this.restaurantService = restaurantService;
  }

  public void execute() {
    handler.writeLine("Ingrese el ID del restaurante");
    Long id = Long.parseLong(handler.readLine());
    this.restaurantService.removeRestaurant(id);
  }
}
