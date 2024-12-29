package org.example.command.review;

import org.example.interfaces.ICommand;
import org.example.interfaces.IHandler;
import org.example.services.ReviewService;

public class AddReview implements ICommand {
  private ReviewService service;
  private final IHandler handler;

  public AddReview(ReviewService service, IHandler handler) {
    this.service = service;
    this.handler = handler;
  }

  @Override
  public void execute() {
    handler.writeLine("¿Deseas reseñar un restaurante o un plato? (Restaurante/Plato): ");
    String type = handler.readLine().toLowerCase();

    if (type.equalsIgnoreCase("restaurante")) {
      addRestaurantReview();
    } else if (type.equalsIgnoreCase("plato")) {
      addDishReview();
    } else {
      handler.writeLine("Opción no válida.");
    }
  }

  private void addRestaurantReview() {
    handler.writeLine("Indica el nombre del restaurante: ");
    String restaurantName = handler.readLine();

    handler.writeLine("Indica tu comentario: ");
    String comment = handler.readLine();
    handler.writeLine("Indica tu calificación (0.0 - 5.0): ");
    Float rating = Float.parseFloat(handler.readLine());

    service.addRestaurantReview(comment, rating, restaurantName);
  }

  private void addDishReview() {
    handler.writeLine("Indica el nombre del restaurante donde está el plato: ");
    String restaurantName = handler.readLine();

    handler.writeLine("Indica el nombre del plato: ");
    String dishName = handler.readLine();

    handler.writeLine("Indica tu comentario: ");
    String comment = handler.readLine();
    handler.writeLine("Indica tu calificación de sabor (0.0 - 5.0): ");
    Float taste = Float.parseFloat(handler.readLine());
    handler.writeLine("Indica tu calificación de presentación (0.0 - 5.0): ");
    Float presentation = Float.parseFloat(handler.readLine());

    service.addDishReview(comment, taste, presentation, restaurantName, dishName);
  }

}

