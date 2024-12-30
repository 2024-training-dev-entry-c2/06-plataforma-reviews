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

class EditDishInMenuCommandTest {

  private IConsoleHandler mockHandler;
  private MenuController mockMenuController;

  @BeforeEach
  void setup() {
    mockHandler = mock(IConsoleHandler.class);
    mockMenuController = mock(MenuController.class);
  }

  @Test
  @DisplayName("Test Edit Dish In Menu Command")
  void testEditDishInMenu() {
    EditDishInMenuCommand command = new EditDishInMenuCommand(mockMenuController, mockHandler);
    when(mockHandler.readLine()).thenReturn("Restaurante 1", "Plato 1", "Nuevo Plato 1", "Nueva Descripción", "10.0");

    command.execute();

    verify(mockHandler).writeLine("Ingresa el nombre del restaurante:");
    verify(mockHandler).writeLine("Ingresa el nombre del plato:");
    verify(mockHandler).writeLine("Ingresa el nuevo nombre del plato:");
    verify(mockHandler).writeLine("Ingresa la nueva descripción del plato:");
    verify(mockHandler).writeLine("Ingresa el nuevo precio del plato:");

    ArgumentCaptor<String> restaurantNameCaptor = ArgumentCaptor.forClass(String.class);
    ArgumentCaptor<String> dishNameCaptor = ArgumentCaptor.forClass(String.class);
    ArgumentCaptor<DishModel> dishModelCaptor = ArgumentCaptor.forClass(DishModel.class);

    verify(mockMenuController).editDishInMenu(restaurantNameCaptor.capture(), dishNameCaptor.capture(), dishModelCaptor.capture());

    assertEquals("Restaurante 1", restaurantNameCaptor.getValue());
    assertEquals("Plato 1", dishNameCaptor.getValue());
    assertEquals("Nuevo Plato 1", dishModelCaptor.getValue().getName());
    assertEquals("Nueva Descripción", dishModelCaptor.getValue().getDescription());
    assertEquals(10.0, dishModelCaptor.getValue().getPrice());
  }
}