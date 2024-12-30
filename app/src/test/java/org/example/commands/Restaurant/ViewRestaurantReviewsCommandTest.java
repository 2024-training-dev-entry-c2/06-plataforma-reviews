package org.example.commands.Restaurant;

import org.example.commands.Restaurants.ViewRestaurantReviewsCommand;
import org.example.controllers.RestaurantReviewController;
import org.example.Interface.IConsoleHandler;
import org.example.models.RestaurantReviewModel;
import org.example.models.RestaurantModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class ViewRestaurantReviewsCommandTest {

  private IConsoleHandler mockHandler;
  private RestaurantReviewController mockReviewController;

  @BeforeEach
  void setup() {
    mockHandler = mock(IConsoleHandler.class);
    mockReviewController = mock(RestaurantReviewController.class);
  }

  @Test
  @DisplayName("Test View Restaurant Reviews Command")
  void testViewRestaurantReviews() {
    ViewRestaurantReviewsCommand command = new ViewRestaurantReviewsCommand(mockReviewController, mockHandler);

    when(mockHandler.readLine()).thenReturn("Restaurante 1");
    List<RestaurantReviewModel> reviews = List.of(
      new RestaurantReviewModel("Cliente 1", 5.0, "Excelente", new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true)),
      new RestaurantReviewModel("Cliente 2", 4.0, "Muy bueno", new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true))
    );
    when(mockReviewController.getReviewsOfRestaurant("Restaurante 1")).thenReturn(reviews);

    command.execute();

    verify(mockHandler).writeLine("Ingrese el nombre del restaurante:");
    verify(mockHandler).readLine();
    verify(mockReviewController).getReviewsOfRestaurant("Restaurante 1");
    verify(mockHandler).writeLine("Cliente: Cliente 1, Valoración: 5.0, Comentario: Excelente");
    verify(mockHandler).writeLine("Cliente: Cliente 2, Valoración: 4.0, Comentario: Muy bueno");
  }}