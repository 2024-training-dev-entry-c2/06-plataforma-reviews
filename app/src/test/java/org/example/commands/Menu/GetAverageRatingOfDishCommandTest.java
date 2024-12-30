package org.example.commands.Menu;

import org.example.controllers.DishController;
import org.example.Interface.IConsoleHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class GetAverageRatingOfDishCommandTest {

  private IConsoleHandler mockHandler;
  private DishController mockDishController;

  @BeforeEach
  void setup() {
    mockHandler = mock(IConsoleHandler.class);
    mockDishController = mock(DishController.class);
  }

  @Test
  @DisplayName("Test Get Average Rating Of Dish Command")
  void testGetAverageRatingOfDish() {
    GetAverageRatingOfDishCommand command = new GetAverageRatingOfDishCommand(mockDishController, mockHandler);
    when(mockHandler.readLine()).thenReturn("Plato 1");
    when(mockDishController.getAverageRatingOfDish("Plato 1")).thenReturn(4.5);

    command.execute();

    verify(mockHandler).writeLine("Ingresa el nombre del plato:");
    verify(mockDishController).getAverageRatingOfDish("Plato 1");
    verify(mockHandler).writeLine("Promedio de calificaciones del plato Plato 1: 4.5");
  }
}