package org.example.command.review;

import constants.TypeReview;
import org.example.factory.ReviewFactory;
import org.example.interfaces.ICommand;
import org.example.models.Dish;
import org.example.models.Restaurant;
import org.example.models.review.DishReview;
import org.example.models.review.RestaurantReview;
import org.example.models.review.Review;
import org.example.repositories.RestaurantRepository;
import org.example.utils.ConsoleHandler;

public class AddReview implements ICommand {
  private final RestaurantRepository repository;
  private final ConsoleHandler console;

  public AddReview(ConsoleHandler console) {
    this.console = console;
    this.repository = RestaurantRepository.getInstance();
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
    Restaurant restaurant = repository.searchRestaurant(restaurantName);

    if (restaurant != null) {
      console.writeLine("Indica tu comentario: ");
      String comment = console.readLine();
      console.writeLine("Indica tu calificación (0.0 - 5.0): ");
      Float rating = Float.parseFloat(console.readLine());

      Review review = ReviewFactory.createReview(TypeReview.RESTAURANT, comment, rating, restaurant, null, null);
      if (review != null) {
        restaurant.addReview((RestaurantReview) review);
        console.writeLine("Reseña añadida exitosamente.");
      }
    } else {
      console.writeLine("Restaurante no encontrado.");
    }
  }

  private void addDishReview() {
    console.writeLine("Indica el nombre del restaurante donde está el plato: ");
    String restaurantName = console.readLine();
    Restaurant restaurant = repository.searchRestaurant(restaurantName);

    if (restaurant != null) {
      console.writeLine("Indica el nombre del plato: ");
      String dishName = console.readLine();
      Dish dish = restaurant.getMenu().searchDish(dishName);

      if (dish != null) {
        console.writeLine("Indica tu comentario: ");
        String comment = console.readLine();
        console.writeLine("Indica tu calificación de sabor (0.0 - 5.0): ");
        Float taste = Float.parseFloat(console.readLine());
        console.writeLine("Indica tu calificación de presentación (0.0 - 5.0): ");
        Float presentation = Float.parseFloat(console.readLine());

        Review review = ReviewFactory.createReview(TypeReview.DISH, comment, null, dish, taste, presentation);
        if (review != null) {
          dish.addReview((DishReview) review);
          console.writeLine("Reseña añadida exitosamente.");
        }
      } else {
        console.writeLine("Plato no encontrado.");
      }
    } else {
      console.writeLine("Restaurante no encontrado.");
    }
  }
}

