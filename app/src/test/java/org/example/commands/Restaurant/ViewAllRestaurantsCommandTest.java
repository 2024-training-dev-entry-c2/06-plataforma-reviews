package org.example.commands.Restaurant;

import org.example.commands.Restaurants.ViewAllRestaurantsCommand;
import org.example.controllers.RestaurantController;
import org.example.Interface.IConsoleHandler;
import org.example.models.RestaurantModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class ViewAllRestaurantsCommandTest {

  private IConsoleHandler mockHandler;
  private RestaurantController mockRestaurantController;

  @BeforeEach
  void setup() {
    mockHandler = mock(IConsoleHandler.class);
    mockRestaurantController = mock(RestaurantController.class);
  }

  @Test
  @DisplayName("Test View All Restaurants Command - No Restaurants")
  void testViewAllRestaurantsNoRestaurants() {
    when(mockRestaurantController.getAllRestaurants()).thenReturn(Collections.emptyList());
    ViewAllRestaurantsCommand command = new ViewAllRestaurantsCommand(mockRestaurantController, mockHandler);

    command.execute();

    verify(mockHandler).writeLine("No se encontraron restaurantes.");
  }

  @Test
  @DisplayName("Test View All Restaurants Command - With Restaurants")
  void testViewAllRestaurantsWithRestaurants() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    when(mockRestaurantController.getAllRestaurants()).thenReturn(List.of(restaurant));
    ViewAllRestaurantsCommand command = new ViewAllRestaurantsCommand(mockRestaurantController, mockHandler);

    command.execute();

    verify(mockHandler).writeLine("Nombre: Restaurante 1, Dirección: Calle Ficticia 123, Disponibilidad: true, Rating: 0.0");
  }
  @Test
  @DisplayName("Test View All Restaurants Command - Multiple Restaurants")
  void testViewAllRestaurantsMultipleRestaurants() {
    RestaurantModel restaurant1 = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    RestaurantModel restaurant2 = new RestaurantModel("Restaurante 2", "Avenida Imaginaria 456", false);
    when(mockRestaurantController.getAllRestaurants()).thenReturn(List.of(restaurant1, restaurant2));
    ViewAllRestaurantsCommand command = new ViewAllRestaurantsCommand(mockRestaurantController, mockHandler);

    command.execute();

    verify(mockHandler).writeLine("Nombre: Restaurante 1, Dirección: Calle Ficticia 123, Disponibilidad: true, Rating: 0.0");
    verify(mockHandler).writeLine("Nombre: Restaurante 2, Dirección: Avenida Imaginaria 456, Disponibilidad: false, Rating: 0.0");
  }

  @Test
  @DisplayName("Test View All Restaurants Command - Null Restaurant List")
  void testViewAllRestaurantsNullList() {
    when(mockRestaurantController.getAllRestaurants()).thenReturn(null);
    ViewAllRestaurantsCommand command = new ViewAllRestaurantsCommand(mockRestaurantController, mockHandler);

    command.execute();

    verify(mockHandler).writeLine("No se encontraron restaurantes.");
  }
}