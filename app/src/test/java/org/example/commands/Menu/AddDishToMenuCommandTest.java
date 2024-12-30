package org.example.commands.Menu;

import org.example.controllers.MenuController;
import org.example.Interface.IConsoleHandler;
import org.example.models.DishModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AddDishToMenuCommandTest {

  private IConsoleHandler mockHandler;
  private MenuController mockMenuController;

  @BeforeEach
  void setup() {
    mockHandler = mock(IConsoleHandler.class);
    mockMenuController = mock(MenuController.class);
  }

  @Test
  @DisplayName("Test Add Dish To Menu Command")
  void testAddDishToMenu() {
    AddDishToMenuCommand command = new AddDishToMenuCommand(mockMenuController, mockHandler);
    when(mockHandler.readLine()).thenReturn("Restaurante 1", "Plato 1");

    command.execute();

    verify(mockHandler).writeLine("Ingresa el nombre del restaurante:");
    verify(mockHandler).writeLine("Ingresa el nombre del plato:");

    ArgumentCaptor<String> restaurantNameCaptor = ArgumentCaptor.forClass(String.class);
    ArgumentCaptor<DishModel> dishModelCaptor = ArgumentCaptor.forClass(DishModel.class);

    verify(mockMenuController).addDishToMenu(restaurantNameCaptor.capture(), dishModelCaptor.capture());

    assertEquals("Restaurante 1", restaurantNameCaptor.getValue());
    assertEquals("Plato 1", dishModelCaptor.getValue().getName());
    assertEquals("", dishModelCaptor.getValue().getDescription());
    assertEquals(0.0, dishModelCaptor.getValue().getPrice());

    verify(mockHandler).writeLine("Plato agregado con éxito al menú.");
  }
}