package org.example.commands.Menu;

import org.example.controllers.MenuController;
import org.example.Interface.IConsoleHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class AssociateMenuToRestaurantCommandTest {

  private IConsoleHandler mockHandler;
  private MenuController mockMenuController;

  @BeforeEach
  void setup() {
    mockHandler = mock(IConsoleHandler.class);
    mockMenuController = mock(MenuController.class);
  }

  @Test
  @DisplayName("Test Associate Menu To Restaurant Command")
  void testAssociateMenuToRestaurant() {
    AssociateMenuToRestaurantCommand command = new AssociateMenuToRestaurantCommand(mockMenuController, mockHandler);
    when(mockHandler.readLine()).thenReturn("Restaurante 1", "Menú 1");

    command.execute();

    verify(mockHandler).writeLine("Ingresa el nombre del restaurante:");
    verify(mockHandler).writeLine("Ingrese el nombre del menú:");
    verify(mockMenuController).associateMenuToRestaurant("Restaurante 1", "Menú 1");
    verify(mockHandler).writeLine("Menú asociado con éxito al restaurante.");
  }
}