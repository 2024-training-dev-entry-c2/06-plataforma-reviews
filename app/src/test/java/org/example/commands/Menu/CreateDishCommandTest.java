package org.example.commands.Menu;

import org.example.controllers.DishController;
import org.example.Interface.IConsoleHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CreateDishCommandTest {

  private IConsoleHandler mockHandler;
  private DishController mockDishController;

  @BeforeEach
  void setup() {
    mockHandler = mock(IConsoleHandler.class);
    mockDishController = mock(DishController.class);
  }

  @Test
  @DisplayName("Test Create Dish Command")
  void testCreateDish() {
    CreateDishCommand command = new CreateDishCommand(mockDishController, mockHandler);
    when(mockHandler.readLine()).thenReturn("Plato 1", "Nueva Descripción", "10.0");

    command.execute();

    verify(mockHandler).writeLine("Ingresa el nombre del plato:");
    verify(mockHandler).writeLine("Ingresa la descripción del plato:");
    verify(mockHandler).writeLine("Ingresa el precio del plato:");

    ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
    ArgumentCaptor<String> descriptionCaptor = ArgumentCaptor.forClass(String.class);
    ArgumentCaptor<Double> priceCaptor = ArgumentCaptor.forClass(Double.class);

    verify(mockDishController).createDish(nameCaptor.capture(), descriptionCaptor.capture(), priceCaptor.capture());

    assertEquals("Plato 1", nameCaptor.getValue());
    assertEquals("Nueva Descripción", descriptionCaptor.getValue());
    assertEquals(10.0, priceCaptor.getValue(), 0.01);

    verify(mockHandler).writeLine("Plato creado con éxito.");
  }
}