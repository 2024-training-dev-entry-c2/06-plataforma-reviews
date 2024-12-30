package org.example.commands.Restaurant;

import org.example.commands.Restaurants.GetAverageRatingOfRestaurantCommand;
import org.example.controllers.RestaurantController;
import org.example.Interface.IConsoleHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class GetAverageRatingOfRestaurantCommandTest {

  private IConsoleHandler mockHandler;
  private RestaurantController mockRestaurantController;

  @BeforeEach
  void setup() {
    mockHandler = mock(IConsoleHandler.class);
    mockRestaurantController = mock(RestaurantController.class);
  }

  @Test
  @DisplayName("Test Get Average Rating Of Restaurant Command")
  void testGetAverageRatingOfRestaurant() {
    GetAverageRatingOfRestaurantCommand command = new GetAverageRatingOfRestaurantCommand(mockRestaurantController, mockHandler);

    when(mockHandler.readLine()).thenReturn("Restaurante 1");
    when(mockRestaurantController.getAverageRatingOfRestaurant("Restaurante 1")).thenReturn(4.5);

    command.execute();

    verify(mockHandler).writeLine("Ingrese el nombre del restaurante:");
    verify(mockHandler).readLine();
    verify(mockRestaurantController).getAverageRatingOfRestaurant("Restaurante 1");
    verify(mockHandler).writeLine("Valoración promedio del restaurante Restaurante 1: 4.5");
  }
}