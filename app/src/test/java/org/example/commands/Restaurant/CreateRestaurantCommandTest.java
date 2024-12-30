package org.example.commands.Restaurant;

import org.example.commands.Restaurants.CreateRestaurantCommand;
import org.example.controllers.RestaurantController;
import org.example.Interface.IConsoleHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CreateRestaurantCommandTest {

  private IConsoleHandler mockHandler;
  private RestaurantController mockRestaurantController;

  @BeforeEach
  void setup() {
    mockHandler = mock(IConsoleHandler.class);
    mockRestaurantController = mock(RestaurantController.class);
  }

  @Test
  @DisplayName("Test Create Restaurant Command")
  void testCreateRestaurant() {
    CreateRestaurantCommand command = new CreateRestaurantCommand(mockRestaurantController, mockHandler);
    when(mockHandler.readLine()).thenReturn("Restaurante 1", "Calle Ficticia 123", "true");

    command.execute();

    verify(mockHandler).writeLine("Ingrese el nombre del restaurante:");
    verify(mockHandler).writeLine("Ingrese la dirección del restaurante:");
    verify(mockHandler).writeLine("Ingrese la disponibilidad del restaurante (Si/No):");
    verify(mockRestaurantController).createRestaurant("Restaurante 1", "Calle Ficticia 123", true);
  }
}