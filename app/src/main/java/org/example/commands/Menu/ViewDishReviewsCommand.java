package org.example.commands.Menu;

import org.example.Interface.ICommand;
import org.example.Interface.IConsoleHandler;
import org.example.controllers.DishReviewController;
import org.example.models.DishReviewModel;

import java.util.List;

public class ViewDishReviewsCommand implements ICommand {
  private final DishReviewController dishReviewController;
  private final IConsoleHandler consoleHandler;

  public ViewDishReviewsCommand(DishReviewController dishReviewController, IConsoleHandler consoleHandler) {
    this.dishReviewController = dishReviewController;
    this.consoleHandler = consoleHandler;
  }

  @Override
  public void execute() {
    consoleHandler.writeLine("Ingresa el nombre del plato:");
    String dishName = consoleHandler.readLine();
    List<DishReviewModel> reviews = dishReviewController.getReviewsOfDish(dishName);
    if (reviews == null || reviews.isEmpty()) {
      consoleHandler.writeLine("No hay reseñas para este plato.");
    } else {
      for (DishReviewModel review : reviews) {
        consoleHandler.writeLine("Cliente: " + review.getReviewerName() + ", Valoración: " + review.getRating() + ", Comentario: " + review.getComment());
      }
    }
  }
}