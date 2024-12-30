package org.example.commands.Restaurants;

import org.example.Interface.ICommand;
import org.example.Interface.IConsoleHandler;
import org.example.controllers.RestaurantController;

public class EditRestaurantCommand implements ICommand {
  private final RestaurantController restaurantController;
  private final IConsoleHandler consoleHandler;

  public EditRestaurantCommand(RestaurantController restaurantController, IConsoleHandler consoleHandler) {
    this.restaurantController = restaurantController;
    this.consoleHandler = consoleHandler;
  }

  @Override
  public void execute() {
    consoleHandler.writeLine("Ingrese el nombre del restaurante al editar:");
    String name = consoleHandler.readLine();
    consoleHandler.writeLine("Ingrese la nueva dirección del restaurante:");
    String newAddress = consoleHandler.readLine();
    consoleHandler.writeLine("Ingrese la nueva disponibilidad del restaurante (Si/No):");
    Boolean newAvailability = Boolean.parseBoolean(consoleHandler.readLine());
    restaurantController.updateRestaurant(name, newAddress, newAvailability);
    consoleHandler.writeLine("Restaurante editado con éxito.");
  }
}