package org.example.commands.Restaurant;


import org.example.commands.Restaurants.EditRestaurantCommand;
import org.example.controllers.RestaurantController;
import org.example.Interface.IConsoleHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class EditRestaurantCommandTest {

  private IConsoleHandler mockHandler;
  private RestaurantController mockRestaurantController;

  @BeforeEach
  void setup() {
    mockHandler = mock(IConsoleHandler.class);
    mockRestaurantController = mock(RestaurantController.class);
  }

  @Test
  @DisplayName("Test Edit Restaurant Command")
  void testEditRestaurant() {
    EditRestaurantCommand command = new EditRestaurantCommand(mockRestaurantController, mockHandler);
    when(mockHandler.readLine()).thenReturn("Restaurante 1", "Nueva Dirección 456", "false");

    command.execute();

    verify(mockHandler).writeLine("Ingrese el nombre del restaurante al editar:");
    verify(mockHandler).writeLine("Ingrese la nueva dirección del restaurante:");
    verify(mockHandler).writeLine("Ingrese la nueva disponibilidad del restaurante (Si/No):");
    verify(mockRestaurantController).updateRestaurant("Restaurante 1", "Nueva Dirección 456", false);
  }
}