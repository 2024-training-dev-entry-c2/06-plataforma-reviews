package org.example.command.menu;

import org.example.interfaces.IHandler;
import org.example.services.DishService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AddDishTest {
  private IHandler mockHandler;
  private DishService mockDishService;
  private AddDish addDishCommand;

  @BeforeEach
  void setup(){
    mockHandler = mock(IHandler.class);
    mockDishService = mock(DishService.class);
    addDishCommand = new AddDish(mockDishService, mockHandler);
  }

  @Test
  @DisplayName("Prueba exitosa para agregar plato")
  void testAddDish(){
    when(mockHandler.readLine()).thenReturn("Crepes & Waffles","Sombrero volteao", "Nidea, la descripción acá", "50000");
    when(mockDishService.addDish("Crepes & Waffles", "Sombrero volteao", "Nidea, la descripción acá", 50000f)).thenReturn(true);
    addDishCommand.execute();


    verify(mockHandler).writeLine("Indica el nombre del restaurante");
    verify(mockHandler).writeLine("Indica el nombre del plato: ");
    verify(mockHandler).writeLine("Indica la descripción del plato: ");
    verify(mockHandler).writeLine("Indica el precio del plato: ");

    verify(mockDishService).addDish("Crepes & Waffles", "Sombrero volteao", "Nidea, la descripción acá", 50000f);
  }

  @Test
  @DisplayName("Prueba fallida para agregar plato")
  void testAddFailDish(){
    when(mockHandler.readLine()).thenReturn("Crepes & Waffles","Sombrero volteao", "Nidea, la descripción acá", "50000");
    when(mockDishService.addDish("Crepes & Waffles", "Sombrero volteao", "Nidea, la descripción acá", 50000f)).thenReturn(false);

    addDishCommand.execute();


    verify(mockHandler).writeLine("Indica el nombre del restaurante");
    verify(mockHandler).writeLine("Indica el nombre del plato: ");
    verify(mockHandler).writeLine("Indica la descripción del plato: ");
    verify(mockHandler).writeLine("Indica el precio del plato: ");

    verify(mockDishService).addDish("Crepes & Waffles", "Sombrero volteao", "Nidea, la descripción acá", 50000f);
  }
}