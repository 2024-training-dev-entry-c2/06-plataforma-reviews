package org.example.commands.Menu;

import org.example.Interface.ICommand;
import org.example.Interface.IConsoleHandler;
import org.example.controllers.DishController;

public class GetAverageRatingOfDishCommand implements ICommand {
  private final IConsoleHandler consoleHandler;
  private final DishController dishController;

  public GetAverageRatingOfDishCommand(DishController dishController, IConsoleHandler consoleHandler) {
    this.dishController = dishController;
    this.consoleHandler = consoleHandler;
  }

  @Override
  public void execute() {
    consoleHandler.writeLine("Ingresa el nombre del plato:");
    String name = consoleHandler.readLine();
    Double averageRating = dishController.getAverageRatingOfDish(name);
    consoleHandler.writeLine("Promedio de calificaciones del plato " + name + ": " + averageRating);
  }
}
