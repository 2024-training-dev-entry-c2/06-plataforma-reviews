package org.example.commands.Restaurants;

import org.example.Interface.ICommand;
import org.example.Interface.IConsoleHandler;
import org.example.controllers.RestaurantController;

public class DeleteRestaurantCommand implements ICommand {
  private final RestaurantController restaurantController;
  private final IConsoleHandler consoleHandler;

  public DeleteRestaurantCommand(RestaurantController restaurantController, IConsoleHandler consoleHandler) {
    this.restaurantController = restaurantController;
    this.consoleHandler = consoleHandler;
  }

  @Override
  public void execute() {
    consoleHandler.writeLine("Ingrese el nombre del restaurante:");
    String name = consoleHandler.readLine();
    restaurantController.deleteRestaurant(name);
    consoleHandler.writeLine("Restaurante eliminado.");
  }
}