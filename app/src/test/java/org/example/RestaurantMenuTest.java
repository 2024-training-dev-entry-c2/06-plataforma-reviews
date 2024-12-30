package org.example;

import org.example.services.RestaurantService;
import org.example.utils.IHandler;
import org.example.utils.RestaurantMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class RestaurantMenuTest {
  private IHandler mockHandler;
  private RestaurantService mockService;
  private RestaurantMenu restaurantMenu;


  @BeforeEach
  void setUp() {
    mockHandler = mock(IHandler.class);
    mockService = mock(RestaurantService.class);

    restaurantMenu = new RestaurantMenu(mockService, mockHandler);
  }

  @Test
  @DisplayName("Caso 1 del menu")
  void testAddRestaurant() {
    when(mockHandler.readLine()).thenReturn("1","Frisby", "320-123-4567", "Pollo apanado","5");
    restaurantMenu.displayMenu();
    verify(mockHandler).writeLine("Ingrese el nombre del restaurante");
    verify(mockHandler).writeLine("Ingrese el teléfono");
    verify(mockHandler).writeLine("Ingrese la descripción");
    verify(mockService).addRestaurant("Frisby", "320-123-4567", "Pollo apanado");
  }

  @Test
  @DisplayName("Caso 2 del menu")
  void testUpdateRestaurant() {
    when(mockHandler.readLine()).thenReturn("2","2","Frisby", "320-123-4567", "Pollo apanado","5");
    restaurantMenu.displayMenu();
    verify(mockHandler).writeLine("Ingrese el ID del restaurante");
    verify(mockHandler).writeLine("Ingrese el nombre del restaurante");
    verify(mockHandler).writeLine("Ingrese el teléfono");
    verify(mockHandler).writeLine("Ingrese la descripción");
    verify(mockService).updateRestaurant(2L,"Frisby", "320-123-4567", "Pollo apanado");
  }

  @Test
  @DisplayName("Caso 3 del menu")
  void testDisplayRestaurants() {
    when(mockHandler.readLine()).thenReturn("3","5");
    restaurantMenu.displayMenu();
    verify(mockService).displayRestaurants();
  }

  @Test
  @DisplayName("Caso 4 del menu")
  void testRemoveRestaurant() {
    when(mockHandler.readLine()).thenReturn("4","2","5");
    restaurantMenu.displayMenu();
    verify(mockHandler).writeLine("Ingrese el ID del restaurante");
    verify(mockService).removeRestaurant(2L);
  }

  @Test
  @DisplayName("Caso 5 del menu")
  void testExitRestaurantMenu() {
    when(mockHandler.readLine()).thenReturn("5");
    restaurantMenu.displayMenu();
    verify(mockHandler).writeLine("Saliendo de opciones de restaurante");
  }

  @Test
  @DisplayName("Caso inválido del menu")
  void testInvalidOption() {
    when(mockHandler.readLine()).thenReturn("7","5");
    restaurantMenu.displayMenu();
    verify(mockHandler).writeLine("Opción inválida");
  }

}
