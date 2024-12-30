package org.example.utils;

import org.example.controllers.IControllerCommand;
import org.example.controllers.review.CreateRestaurantReviewControllerCommand;
import org.example.controllers.review.ListRestaurantReviewsControllerCommand;
import org.example.services.ReviewService;

import java.util.Map;

public class RestaurantReviewMenu implements IMenuCommand{
  private final IHandler handler;
  private final ReviewService reviewService;
  private Map<Integer, IControllerCommand> controllers ;
  private Boolean exit = false;

  public RestaurantReviewMenu(ReviewService reviewService, IHandler handler) {
    this.reviewService = reviewService;
    this.handler = handler;
    this.controllers = Map.of(
      1, new CreateRestaurantReviewControllerCommand(handler, reviewService),
      2, new ListRestaurantReviewsControllerCommand(handler, reviewService),
      3, ()-> {
        handler.writeLine("Saliendo de opciones de reseñas de restaurante");
        exit = true;
      }
    );
  }

  public void displayMenu() {
    String message = "\nSeleccione una opción:\n1. Crear reseña de restaurante\n2. Listar reseñas de restaurante\n3. Menú principal";
    while (!exit) {
      handler.writeLine(message);
      int choice = Integer.parseInt(handler.readLine());
      if (choice >= 1 && choice <= 3) {
        controllers.get(choice).execute();      }
      else {
        handler.writeLine("Opción inválida");
      }
    }
    exit = false;
  }
}
