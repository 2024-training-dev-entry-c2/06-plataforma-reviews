package org.example.commands;

import org.example.Interface.ICommand;
import org.example.Interface.IConsoleHandler;
import org.example.constants.MenuOption;
import org.example.models.DishModel;
import org.example.models.RestaurantModel;
import org.example.utils.ReviewFactory;

public class CreateReviewCommand implements ICommand {
  private final IConsoleHandler consoleHandler;
  private final ReviewFactory reviewFactory;
  private final MenuOption menuOption;

  public CreateReviewCommand(IConsoleHandler consoleHandler, ReviewFactory reviewFactory, MenuOption menuOption) {
    this.consoleHandler = consoleHandler;
    this.reviewFactory = reviewFactory;
    this.menuOption = menuOption;
  }

  @Override
  public void execute() {
    consoleHandler.writeLine("Ingrese el nombre del cliente:");
    String reviewerName = consoleHandler.readLine();
    consoleHandler.writeLine("Ingrese la calificación:");
    Double rating = Double.parseDouble(consoleHandler.readLine());
    consoleHandler.writeLine("Ingrese el comentario:");
    String comment = consoleHandler.readLine();

    if (menuOption == MenuOption.ADD_REVIEW_TO_DISH) {
      consoleHandler.writeLine("Ingrese el nombre del plato:");
      String dishName = consoleHandler.readLine();
      DishModel dish = new DishModel(dishName, "", 0.0); // Assuming a simple constructor for DishModel
      reviewFactory.createReview("Dish", reviewerName, rating, comment, dish);
    } else if (menuOption == MenuOption.ADD_REVIEW_TO_RESTAURANT) {
      consoleHandler.writeLine("Ingrese el nombre del restaurante:");
      String restaurantName = consoleHandler.readLine();
      RestaurantModel restaurant = new RestaurantModel(restaurantName, "", true); // Assuming a simple constructor for RestaurantModel
      reviewFactory.createReview("Restaurant", reviewerName, rating, comment, restaurant);
    }
  }
}