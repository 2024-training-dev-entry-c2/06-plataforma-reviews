package org.example.commands.Restaurants;

import org.example.Interface.ICommand;
import org.example.Interface.IConsoleHandler;
import org.example.controllers.RestaurantController;

public class GetAverageRatingOfRestaurantCommand implements ICommand {
  private final IConsoleHandler consoleHandler;
  private final RestaurantController restaurantController;

  public GetAverageRatingOfRestaurantCommand(RestaurantController restaurantController, IConsoleHandler consoleHandler) {
    this.restaurantController = restaurantController;
    this.consoleHandler = consoleHandler;
  }

  @Override
  public void execute() {
    consoleHandler.writeLine("Ingrese el nombre del restaurante:");
    String name = consoleHandler.readLine();
    double averageRating = restaurantController.getAverageRatingOfRestaurant(name);
    consoleHandler.writeLine("Valoración promedio del restaurante " + name + ": " + averageRating);
}
}
