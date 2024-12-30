package org.example.commands.Restaurants;

import org.example.Interface.ICommand;
import org.example.Interface.IConsoleHandler;
import org.example.controllers.RestaurantController;

public class CreateRestaurantCommand implements ICommand {
  private final RestaurantController restaurantController;
  private final IConsoleHandler consoleHandler;

  public CreateRestaurantCommand(RestaurantController restaurantController, IConsoleHandler consoleHandler) {
    this.restaurantController = restaurantController;
    this.consoleHandler = consoleHandler;
  }

  @Override
  public void execute() {
    consoleHandler.writeLine("Ingrese el nombre del restaurante:");
    String name = consoleHandler.readLine();
    consoleHandler.writeLine("Ingrese la dirección del restaurante:");
    String address = consoleHandler.readLine();
    consoleHandler.writeLine("Ingrese la disponibilidad del restaurante (Si/No):");
    Boolean isAvailable = Boolean.parseBoolean(consoleHandler.readLine());
    restaurantController.createRestaurant(name, address, isAvailable);
    consoleHandler.writeLine("Restaurante creado con éxito.");
  }
}
