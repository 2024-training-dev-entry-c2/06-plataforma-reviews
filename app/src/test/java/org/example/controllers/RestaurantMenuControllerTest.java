package org.example.controllers;

import org.example.command.CommandInvoker;
import org.example.interfaces.IHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RestaurantMenuControllerTest {
  private IHandler mockHandler;
  private RestaurantMenuController restaurantMenuController;
  private CommandInvoker mockInvoker;

  @BeforeEach
  void setup(){
    mockHandler = mock(IHandler.class);
    mockInvoker = mock(CommandInvoker.class);
    restaurantMenuController = new RestaurantMenuController(mockHandler, mockInvoker);
  }

  @Test
  @DisplayName("Caso 1 del menú de restaurante")
  void testAddRestaurant(){
    when(mockHandler.readLine()).thenReturn("1","0");

    restaurantMenuController.start();

    verify(mockInvoker, times(2)).printMenu();
    verify(mockHandler, times(2)).writeLine("Selecciona una opción (o ingresa 0 para regresar): ");

    verify(mockInvoker).executeCommand(1);
  }

  @Test
  @DisplayName("Caso 2 del menú de restaurante")
  void testModifyRestaurant(){
    when(mockHandler.readLine()).thenReturn("2","0");

    restaurantMenuController.start();

    verify(mockInvoker, times(2)).printMenu();
    verify(mockHandler, times(2)).writeLine("Selecciona una opción (o ingresa 0 para regresar): ");

    verify(mockInvoker).executeCommand(2);
  }

  @Test
  @DisplayName("Caso 3 del menú de restaurante")
  void testDeleteRestaurant(){
    when(mockHandler.readLine()).thenReturn("3","0");

    restaurantMenuController.start();

    verify(mockInvoker, times(2)).printMenu();
    verify(mockHandler, times(2)).writeLine("Selecciona una opción (o ingresa 0 para regresar): ");

    verify(mockInvoker).executeCommand(3);
  }

  @Test
  @DisplayName("Caso 4 del menú de restaurante")
  void testDisplayRestaurants(){
    when(mockHandler.readLine()).thenReturn("4","0");

    restaurantMenuController.start();

    verify(mockInvoker, times(2)).printMenu();
    verify(mockHandler, times(2)).writeLine("Selecciona una opción (o ingresa 0 para regresar): ");

    verify(mockInvoker).executeCommand(4);
  }
}