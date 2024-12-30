package org.example.command.menu;

import org.example.interfaces.IHandler;
import org.example.models.Dish;
import org.example.services.DishService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeleteDishTest {
  private IHandler mockHandler;
  private DishService mockDishService;
  private DeleteDish deleteDishCommand;

  @BeforeEach
  void setup(){
    mockHandler = mock(IHandler.class);
    mockDishService = mock(DishService.class);
    deleteDishCommand = new DeleteDish(mockDishService, mockHandler);
  }

  @Test
  @DisplayName("Prueba exitosa para eliminar plato")
  void testDeleteMenu(){
    when(mockHandler.readLine()).thenReturn("Crepes & Waffles","Sombrero volteao");
    Dish mockDish = new Dish("Sombrero volteao","Descripción acá", 50000f);
    when(mockDishService.getDishByName("Crepes & Waffles", "Sombrero volteao")).thenReturn(mockDish);
    when(mockDishService.removeDish("Crepes & Waffles", mockDish)).thenReturn(true);

    deleteDishCommand.execute();


    verify(mockHandler).writeLine("Indica el nombre del restaurante");
    verify(mockHandler).writeLine("Indica el nombre del plato a eliminar: ");
    verify(mockDishService).getDishByName("Crepes & Waffles", "Sombrero volteao");

    verify(mockDishService).removeDish("Crepes & Waffles", mockDish);

  }

  @Test
  @DisplayName("Prueba fallida para eliminar plato")
  void testDeleteFailMenu(){
    when(mockHandler.readLine()).thenReturn("Crepes & Waffles","Sombrero volteao");
    Dish mockDish = new Dish("Sombrero volteao","Descripción acá", 50000f);
    when(mockDishService.getDishByName("Crepes & Waffles", "Sombrero volteao")).thenReturn(mockDish);
    when(mockDishService.removeDish("Crepes & Waffles", mockDish)).thenReturn(false);

    deleteDishCommand.execute();


    verify(mockHandler).writeLine("Indica el nombre del restaurante");
    verify(mockHandler).writeLine("Indica el nombre del plato a eliminar: ");
    verify(mockDishService).getDishByName("Crepes & Waffles", "Sombrero volteao");

    verify(mockDishService).removeDish("Crepes & Waffles", mockDish);

  }

}