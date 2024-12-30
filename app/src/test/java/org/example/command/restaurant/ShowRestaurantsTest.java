package org.example.command.restaurant;


import org.example.models.Restaurant;
import org.example.services.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ShowRestaurantsTest {
  private RestaurantService mockRestaurantService;
  private ShowRestaurants showRestaurantsCommand;

  @BeforeEach
  void setup(){
    mockRestaurantService = mock(RestaurantService.class);
    showRestaurantsCommand = new ShowRestaurants(mockRestaurantService);
  }

  @Test
  @DisplayName("Prueba para ver los restaurantes disponibles")
  void testCreateRestaurant(){
    Restaurant mockRestaurant1 = mock(Restaurant.class);
    Restaurant mockRestaurant2 = mock(Restaurant.class);
    List<Restaurant> mockRestaurants = Arrays.asList(mockRestaurant1, mockRestaurant2);

    when(mockRestaurantService.getAvailableRestaurants()).thenReturn(mockRestaurants);


    showRestaurantsCommand.execute();

    verify(mockRestaurantService).getAvailableRestaurants();

    verify(mockRestaurant1).displayRestaurant();
    verify(mockRestaurant2).displayRestaurant();
  }


}