package org.example.command.review;

import org.example.interfaces.ICommand;
import org.example.services.ReviewService;
import org.example.utils.ConsoleHandler;

public class AddReview implements ICommand {
  private ReviewService service;
  private final ConsoleHandler console;

  public AddReview(ReviewService service, ConsoleHandler console) {
    this.service = service;
    this.console = console;
  }

  @Override
  public void execute() {
    console.writeLine("¿Deseas reseñar un restaurante o un plato? (restaurante/plato): ");
    String type = console.readLine().toLowerCase();

    if (type.equals("restaurante")) {
      addRestaurantReview();
    } else if (type.equals("plato")) {
      addDishReview();
    } else {
      console.writeLine("Opción no válida.");
    }
  }

  private void addRestaurantReview() {
    console.writeLine("Indica el nombre del restaurante: ");
    String restaurantName = console.readLine();

    console.writeLine("Indica tu comentario: ");
    String comment = console.readLine();
    console.writeLine("Indica tu calificación (0.0 - 5.0): ");
    Float rating = Float.parseFloat(console.readLine());

    service.addRestaurantReview(comment, rating, restaurantName);
  }

  private void addDishReview() {
    console.writeLine("Indica el nombre del restaurante donde está el plato: ");
    String restaurantName = console.readLine();

    console.writeLine("Indica el nombre del plato: ");
    String dishName = console.readLine();

    console.writeLine("Indica tu comentario: ");
    String comment = console.readLine();
    console.writeLine("Indica tu calificación de sabor (0.0 - 5.0): ");
    Float taste = Float.parseFloat(console.readLine());
    console.writeLine("Indica tu calificación de presentación (0.0 - 5.0): ");
    Float presentation = Float.parseFloat(console.readLine());

    service.addDishReview(comment, taste, presentation, restaurantName, dishName);
  }

}

