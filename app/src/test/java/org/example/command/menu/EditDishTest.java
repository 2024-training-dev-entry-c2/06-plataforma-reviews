package org.example.command.menu;

import org.example.interfaces.IHandler;
import org.example.models.Dish;
import org.example.services.DishService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EditDishTest {
  private IHandler mockHandler;
  private DishService mockDishService;
  private EditDish editDishCommand;

  @BeforeEach
  void setup(){
    mockHandler = mock(IHandler.class);
    mockDishService = mock(DishService.class);
    editDishCommand = new EditDish(mockDishService, mockHandler);
  }

  @Test
  @DisplayName("Prueba exitosa para editar plato")
  void testEditDish(){
    when(mockHandler.readLine()).thenReturn("Crepes & Waffles","Sombrero volteao", "Crepe Arequipe", "Crepe dulce con bola de helado.", "35000");
    Dish mockDish = new Dish("Sombrero volteao","Descripción acá", 50000f);
    when(mockDishService.getDishByName("Crepes & Waffles", "Sombrero volteao")).thenReturn(mockDish);
    when(mockDishService.editDish("Crepes & Waffles", mockDish,  "Crepe Arequipe", "Crepe dulce con bola de helado.", 35000f)).thenReturn(true);
    Dish updatedDish = mock(Dish.class);
    when(mockDishService.getDishByName("Crepes & Waffles", "Crepe Arequipe")).thenReturn(updatedDish);

    doNothing().when(updatedDish).displayDish();

    editDishCommand.execute();


    verify(mockHandler).writeLine("Indica el nombre del restaurante");
    verify(mockHandler).writeLine("Indica el nombre del plato a modificar: ");
    verify(mockHandler).writeLine("Indica el nuevo nombre del plato: ");
    verify(mockHandler).writeLine("Indica la nueva descripción del plato: ");
    verify(mockHandler).writeLine("Indica el nuevo precio del plato: ");
    verify(mockDishService).getDishByName("Crepes & Waffles", "Sombrero volteao");

    verify(mockDishService).editDish("Crepes & Waffles", mockDish,  "Crepe Arequipe", "Crepe dulce con bola de helado.", 35000f);
    verify(updatedDish).displayDish();

  }

  @Test
  @DisplayName("Prueba exitosa para editar plato")
  void testFailEditDish(){
    when(mockHandler.readLine()).thenReturn("Crepes & Waffles","Sombrero volteao", "Crepe Arequipe", "Crepe dulce con bola de helado.", "35000");

    Dish mockDish = mock(Dish.class);
    when(mockDishService.getDishByName("Crepes & Waffles", "Sombrero volteao")).thenReturn(mockDish);
    when(mockDishService.editDish("Crepes & Waffles", mockDish,  "Crepe Arequipe", "Crepe dulce con bola de helado.", 35000f)).thenReturn(false);


    editDishCommand.execute();


    verify(mockHandler).writeLine("Indica el nombre del restaurante");
    verify(mockHandler).writeLine("Indica el nombre del plato a modificar: ");
    verify(mockHandler).writeLine("Indica el nuevo nombre del plato: ");
    verify(mockHandler).writeLine("Indica la nueva descripción del plato: ");
    verify(mockHandler).writeLine("Indica el nuevo precio del plato: ");
    verify(mockDishService).getDishByName("Crepes & Waffles", "Sombrero volteao");

    verify(mockDishService).editDish("Crepes & Waffles", mockDish,  "Crepe Arequipe", "Crepe dulce con bola de helado.", 35000f);
  }

}