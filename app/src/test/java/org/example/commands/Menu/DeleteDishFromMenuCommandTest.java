package org.example.commands.Menu;

import org.example.controllers.MenuController;
import org.example.Interface.IConsoleHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DeleteDishFromMenuCommandTest {

  private IConsoleHandler mockHandler;
  private MenuController mockMenuController;

  @BeforeEach
  void setup() {
    mockHandler = mock(IConsoleHandler.class);
    mockMenuController = mock(MenuController.class);
  }

  @Test
  @DisplayName("Test Delete Dish From Menu Command")
  void testDeleteDishFromMenu() {
    DeleteDishFromMenuCommand command = new DeleteDishFromMenuCommand(mockMenuController, mockHandler);
    when(mockHandler.readLine()).thenReturn("Restaurante 1", "Plato 1");

    command.execute();

    verify(mockHandler).writeLine("Ingresa el nombre del restaurante:");
    verify(mockHandler).writeLine("Ingresa el nombre del plato:");
    verify(mockMenuController).removeDishFromMenu("Restaurante 1", "Plato 1");
    verify(mockHandler).writeLine("Plato eliminado con éxito del menú.");
  }
}