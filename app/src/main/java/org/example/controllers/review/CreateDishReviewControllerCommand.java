package org.example.controllers.review;

import org.example.controllers.IControllerCommand;
import org.example.services.ReviewService;
import org.example.utils.IHandler;

public class CreateDishReviewControllerCommand implements IControllerCommand {
  private final IHandler handler;
  private final ReviewService reviewService;

  public CreateDishReviewControllerCommand(IHandler handler, ReviewService reviewService){
    this.handler = handler;
    this.reviewService = reviewService;
  }

  @Override
  public void execute() {
    this.reviewService.displayRestaurants();
    handler.writeLine("Ingrese el ID del restaurante");
    Long restaurantId = Long.parseLong(handler.readLine());
    this.reviewService.displayMenu(restaurantId);
    handler.writeLine("Ingrese el ID del plato");
    Long dishId = Long.parseLong(handler.readLine());
    handler.writeLine("Ingrese el comentario");
    String comment = handler.readLine();
    handler.writeLine("Ingrese la calificación");
    Float score = Float.parseFloat(handler.readLine());
    this.reviewService.createDishReview(score, comment, dishId );
  }
}
