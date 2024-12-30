package org.example.commands.Menu;

import org.example.controllers.MenuController;
import org.example.Interface.IConsoleHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ViewDishByRestaurantCommandTest {

  private IConsoleHandler mockHandler;
  private MenuController mockMenuController;

  @BeforeEach
  void setup() {
    mockHandler = mock(IConsoleHandler.class);
    mockMenuController = mock(MenuController.class);
  }

  @Test
  @DisplayName("View Dish By Restaurant Command")
  void testViewDishByRestaurant() {
    ViewDishByRestaurantCommand command = new ViewDishByRestaurantCommand(mockMenuController, mockHandler);
    when(mockHandler.readLine()).thenReturn("Restaurante 1");

    command.execute();

    verify(mockHandler).writeLine("Ingresa el nombre del restaurante:");
    verify(mockMenuController).getDishesInMenu("Restaurante 1");
  }
}