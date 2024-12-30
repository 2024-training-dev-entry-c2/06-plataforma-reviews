package org.example.controllers.restaurant;

import org.example.controllers.IControllerCommand;
import org.example.services.RestaurantService;
import org.example.utils.IHandler;

public class UpdateRestaurantControllerCommand implements IControllerCommand {
  private final IHandler handler;
  private final RestaurantService restaurantService;

  public UpdateRestaurantControllerCommand(IHandler handler, RestaurantService restaurantService) {
    this.handler = handler;
    this.restaurantService = restaurantService;
  }

  @Override
  public void execute() {
    handler.writeLine("Ingrese el ID del restaurante");
    Long id = Long.parseLong(handler.readLine());
    handler.writeLine("Ingrese el nombre del restaurante");
    String name = handler.readLine();
    handler.writeLine("Ingrese el teléfono");
    String phone = handler.readLine();
    handler.writeLine("Ingrese la descripción");
    String description = handler.readLine();
    this.restaurantService.updateRestaurant(id, name, phone, description);
  }
}
