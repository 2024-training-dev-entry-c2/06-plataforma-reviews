package org.example.commands.Restaurant;

import org.example.commands.Restaurants.DeleteRestaurantCommand;
import org.example.controllers.RestaurantController;
import org.example.Interface.IConsoleHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DeleteRestaurantCommandTest {

  private IConsoleHandler mockHandler;
  private RestaurantController mockRestaurantController;

  @BeforeEach
  void setup() {
    mockHandler = mock(IConsoleHandler.class);
    mockRestaurantController = mock(RestaurantController.class);
  }

  @Test
  @DisplayName("Test Delete Restaurant Command")
  void testDeleteRestaurant() {
    DeleteRestaurantCommand command = new DeleteRestaurantCommand(mockRestaurantController, mockHandler);
    when(mockHandler.readLine()).thenReturn("Restaurante 1");

    command.execute();

    verify(mockHandler).writeLine("Ingrese el nombre del restaurante:");
    verify(mockRestaurantController).deleteRestaurant("Restaurante 1");
  }
}