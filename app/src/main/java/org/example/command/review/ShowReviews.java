package org.example.command.review;

import org.example.interfaces.ICommand;
import org.example.interfaces.IHandler;
import org.example.models.review.Review;
import org.example.services.ReviewService;

import java.util.List;

public class ShowReviews implements ICommand {
  private final ReviewService service;
  private final IHandler handler;

  public ShowReviews(ReviewService service, IHandler handler) {
    this.service = service;
    this.handler = handler;
  }

  @Override
  public void execute() {
    handler.writeLine("¿Deseas ver las reseñas de un restaurante o un plato? (Restaurante/Plato): ");
    String type = handler.readLine().toLowerCase();

    if (type.equalsIgnoreCase("restaurante")) {
      displayRestaurantReview();
    } else if (type.equalsIgnoreCase("plato")) {
      displayDishReview();
    } else {
      handler.writeLine("Opción no válida.");
    }
  }

  private void displayRestaurantReview(){
    handler.writeLine("Indica el nombre del restaurante: ");
    String restaurantName = handler.readLine();
    List<Review> reviews= service.getRestaurantReviews(restaurantName);
    if (reviews == null || reviews.isEmpty()){
      System.out.println("No se pueden obtener las reseñas.");
    }else{
      System.out.println("\n Lista de reseñas de '" + restaurantName + "'");
      reviews.forEach(Review::displayReview);
      float rating = service.calculateAverageRestaurantRating(restaurantName);
      System.out.println("Calificación promedio: " + rating);
    }
  }

  private void displayDishReview(){
    handler.writeLine("Indica el nombre del restaurante donde está el plato: ");
    String restaurantName = handler.readLine();
    handler.writeLine("Indica el nombre del plato: ");
    String dishName = handler.readLine();

    List<Review> reviews= service.getDishReviews(restaurantName, dishName);
    if (reviews == null || reviews.isEmpty()){
      System.out.println("No se pueden obtener las reseñas.");
    }else{
      System.out.println("\n Lista de reseñas de '" + dishName + "'");
      reviews.forEach(Review::displayReview);
      float rating = service.calculateAverageDishRating(restaurantName, dishName);
      System.out.println("Calificación promedio: " + rating);
    }
  }

}
