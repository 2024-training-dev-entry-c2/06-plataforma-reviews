package org.example.command.restaurant;

import org.example.interfaces.IHandler;
import org.example.models.Restaurant;
import org.example.services.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeleteRestaurantTest {
  private IHandler mockHandler;
  private RestaurantService mockRestaurantService;
  private DeleteRestaurant deleteRestaurantCommand;

  @BeforeEach
  void setup(){
    mockHandler = mock(IHandler.class);
    mockRestaurantService = mock(RestaurantService.class);
    deleteRestaurantCommand = new DeleteRestaurant(mockRestaurantService, mockHandler);
  }

  @Test
  @DisplayName("Prueba exitosa para eliminar restaurante")
  void testDeleteRestaurant(){
    when(mockHandler.readLine()).thenReturn("Crepes & Waffles");
    Restaurant mockRestaurant = new Restaurant("Crepes & Waffles", "Carrera 111 # 432-123",true);
    when(mockRestaurantService.getRestaurantByName("Crepes & Waffles")).thenReturn(mockRestaurant);
    when(mockRestaurantService.deleteRestaurant(any(Restaurant.class))).thenReturn(true);

    deleteRestaurantCommand.execute();


    verify(mockHandler).writeLine("Indica el nombre del restaurante: ");

    verify(mockRestaurantService).deleteRestaurant(argThat(restaurant ->
      "Crepes & Waffles".equals(restaurant.getName())));
  }

  @Test
  @DisplayName("Prueba fallida para eliminar restaurante")
  void testFailDeleteRestaurant(){
    when(mockHandler.readLine()).thenReturn("Crepes & Waffles");
    Restaurant mockRestaurant = new Restaurant("Crepes & Waffles", "Carrera 111 # 432-123",true);
    when(mockRestaurantService.getRestaurantByName("Crepes & Waffles")).thenReturn(mockRestaurant);
    when(mockRestaurantService.deleteRestaurant(any(Restaurant.class))).thenReturn(false);

    deleteRestaurantCommand.execute();


    verify(mockHandler).writeLine("Indica el nombre del restaurante: ");

    verify(mockRestaurantService).deleteRestaurant(argThat(restaurant ->
      "Crepes & Waffles".equals(restaurant.getName())));
  }

}