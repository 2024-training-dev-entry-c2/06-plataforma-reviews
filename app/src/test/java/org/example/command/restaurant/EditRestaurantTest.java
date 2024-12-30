package org.example.command.restaurant;

import org.example.interfaces.IHandler;
import org.example.models.Restaurant;
import org.example.services.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EditRestaurantTest {
  private IHandler mockHandler;
  private RestaurantService mockRestaurantService;
  private EditRestaurant editRestaurantCommand;

  @BeforeEach
  void setup(){
    mockHandler = mock(IHandler.class);
    mockRestaurantService = mock(RestaurantService.class);
    editRestaurantCommand = new EditRestaurant(mockRestaurantService, mockHandler);
  }

  @Test
  @DisplayName("Prueba exitosa para editar restaurante")
  void testEditRestaurant(){
    when(mockHandler.readLine()).thenReturn("Crepes & Waffles", "Carrera 123 # 456-321", "no");
    Restaurant mockRestaurant = new Restaurant("Crepes & Waffles", "Carrera 111 # 432-123",true);
    when(mockRestaurantService.getRestaurantByName("Crepes & Waffles")).thenReturn(mockRestaurant);
    when(mockRestaurantService.editRestaurant(mockRestaurant, "Carrera 123 # 456-321", false)).thenReturn(true);


    editRestaurantCommand.execute();


    verify(mockHandler).writeLine("Indica el nombre del restaurante: ");
    verify(mockHandler).writeLine("Nueva dirección del restaurante: ");
    verify(mockHandler).writeLine("¿El restaurante está disponible? (Si/No)");
    verify(mockRestaurantService,times(2)).getRestaurantByName("Crepes & Waffles");
    verify(mockRestaurantService).editRestaurant(mockRestaurant, "Carrera 123 # 456-321", false);
  }

  @Test
  @DisplayName("Prueba fallida para editar restaurante")
  void testFailEditRestaurant(){
    when(mockHandler.readLine()).thenReturn("Crepes & Waffles", "Carrera 123 # 456-321", "no");
    Restaurant mockRestaurant = new Restaurant("Crepes & Waffles", "Carrera 111 # 432-123",true);
    when(mockRestaurantService.getRestaurantByName("Crepes & Waffles")).thenReturn(mockRestaurant);
    when(mockRestaurantService.editRestaurant(mockRestaurant, "Carrera 123 # 456-321", false)).thenReturn(false);


    editRestaurantCommand.execute();


    verify(mockHandler).writeLine("Indica el nombre del restaurante: ");
    verify(mockHandler).writeLine("Nueva dirección del restaurante: ");
    verify(mockHandler).writeLine("¿El restaurante está disponible? (Si/No)");
    verify(mockRestaurantService).getRestaurantByName("Crepes & Waffles");
    verify(mockRestaurantService).editRestaurant(mockRestaurant, "Carrera 123 # 456-321", false);

  }
}