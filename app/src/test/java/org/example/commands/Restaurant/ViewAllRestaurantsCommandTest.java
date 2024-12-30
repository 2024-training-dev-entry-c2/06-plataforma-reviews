package org.example.commands.Restaurant;

import org.example.commands.Restaurants.ViewAllRestaurantsCommand;
import org.example.controllers.RestaurantController;
import org.example.Interface.IConsoleHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
  @DisplayName("Test View All Restaurants Command")
  void testViewAllRestaurants() {
    ViewAllRestaurantsCommand command = new ViewAllRestaurantsCommand(mockRestaurantController, mockHandler);

    command.execute();

    verify(mockRestaurantController).getAllRestaurants();
  }
}