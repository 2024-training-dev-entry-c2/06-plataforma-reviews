package org.example.commands.Menu;

import org.example.controllers.DishReviewController;
import org.example.Interface.IConsoleHandler;
import org.example.models.DishReviewModel;
import org.example.models.DishModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class ListDishReviewsCommandTest {

  private IConsoleHandler mockHandler;
  private DishReviewController mockReviewDishController;

  @BeforeEach
  void setup() {
    mockHandler = mock(IConsoleHandler.class);
    mockReviewDishController = mock(DishReviewController.class);
  }

  @Test
  @DisplayName("Test List Dish Reviews Command")
  void testListDishReviews() {
    DishReviewModel review = new DishReviewModel("Cliente 1", 5.0, "Excelente plato", new DishModel("Plato 1", "", 0.0));
    List<DishReviewModel> reviews = List.of(review);

    when(mockReviewDishController.getReviewsOfDish("Plato 1")).thenReturn(reviews);

    ViewDishReviewsCommand command = new ViewDishReviewsCommand(mockReviewDishController, mockHandler);
    when(mockHandler.readLine()).thenReturn("Plato 1");

    command.execute();

    verify(mockHandler).writeLine("Ingresa el nombre del plato:");
    verify(mockReviewDishController).getReviewsOfDish("Plato 1");
    verify(mockHandler).writeLine("Cliente: Cliente 1, Valoración: 5.0, Comentario: Excelente plato");
  }
}