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

class CreateRestaurantTest {
  private IHandler mockHandler;
  private RestaurantService mockRestaurantService;
  private CreateRestaurant createRestaurantCommand;

  @BeforeEach
  void setup(){
    mockHandler = mock(IHandler.class);
    mockRestaurantService = mock(RestaurantService.class);
    createRestaurantCommand = new CreateRestaurant(mockRestaurantService, mockHandler);
  }

  @Test
  @DisplayName("Prueba exitosa para crear restaurante")
  void testCreateRestaurant(){
    when(mockHandler.readLine()).thenReturn("Crepes & Waffles","Carrera 111 #456-321", "si");
    when(mockRestaurantService.createRestaurant(any(Restaurant.class))).thenReturn(true);

    createRestaurantCommand.execute();


    verify(mockHandler).writeLine("Indica el nombre del restaurante: ");
    verify(mockHandler).writeLine("Indica la dirección del restaurante: ");
    verify(mockHandler).writeLine("¿El restaurante está disponible?(Si/No)");

    verify(mockRestaurantService).createRestaurant(argThat(restaurant ->
      "Crepes & Waffles".equals(restaurant.getName()) &&
        "Carrera 111 #456-321".equals(restaurant.getAddress()) &&
        restaurant.isAvailable()
    ));
  }

  @Test
  @DisplayName("Prueba fallida para crear restaurante")
  void testFailCreateRestaurant(){
    when(mockHandler.readLine()).thenReturn("Crepes & Waffles","Carrera 111 #456-321", "si");
    when(mockRestaurantService.createRestaurant(new Restaurant("Crepes & Waffles","Carrera 111 #456-321", true))).thenReturn(false);

    createRestaurantCommand.execute();


    verify(mockHandler).writeLine("Indica el nombre del restaurante: ");
    verify(mockHandler).writeLine("Indica la dirección del restaurante: ");
    verify(mockHandler).writeLine("¿El restaurante está disponible?(Si/No)");

    verify(mockRestaurantService).createRestaurant(argThat(restaurant ->
      "Crepes & Waffles".equals(restaurant.getName()) &&
        "Carrera 111 #456-321".equals(restaurant.getAddress()) &&
        restaurant.isAvailable()
    ));
  }
}