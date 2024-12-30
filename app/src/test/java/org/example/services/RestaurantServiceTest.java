package org.example.services;

import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RestaurantServiceTest {
  private RestaurantRepository mockRepository;
  private RestaurantService restaurantService;

  @BeforeEach
  void setup(){
    mockRepository = mock(RestaurantRepository.class);
    restaurantService = new RestaurantService(mockRepository);
  }

  @Test
  @DisplayName("Crear restaurante")
  void testCreateRestaurant(){
    Restaurant restaurant = new Restaurant("Restaurante de testeo", "Dirección de testeo", true);
    when(mockRepository.addRestaurant(restaurant)).thenReturn(true);
    Boolean result = restaurantService.createRestaurant(restaurant);

    assertTrue(result);
    verify(mockRepository).addRestaurant(restaurant);
  }

  @Test
  @DisplayName("Editar restaurante")
  void testEditRestaurant(){
    Restaurant restaurant = new Restaurant("Restaurante de testeo", "Dirección antigua", true);
    String newAddress = "New Address";
    Boolean newAvailability = false;

    when(mockRepository.editRestaurant(restaurant, newAddress, newAvailability)).thenReturn(true);
    Boolean result = restaurantService.editRestaurant(restaurant, newAddress, newAvailability);

    assertTrue(result);
    verify(mockRepository).editRestaurant(restaurant, newAddress, newAvailability);
  }

  @Test
  @DisplayName("Eliminar restaurante")
  void testDeleteRestaurant(){
    Restaurant restaurant = new Restaurant("Restaurante de testeo", "Dirección de testeo", true);

    when(mockRepository.removeRestaurant(restaurant)).thenReturn(true);
    Boolean result = restaurantService.deleteRestaurant(restaurant);

    assertTrue(result);
    verify(mockRepository).removeRestaurant(restaurant);
  }

  @Test
  @DisplayName("Obtener restaurantes disponibles")
  void testDGetRestaurants(){
    List<Restaurant> availableRestaurants = new ArrayList<>();
    availableRestaurants.add(new Restaurant("Restaurante de testeo 1", "Dirección 1", true));
    availableRestaurants.add(new Restaurant("Restaurante de testeo 2", "Dirección 2", true));

    when(mockRepository.getAvailableRestaurants()).thenReturn(availableRestaurants);

    List<Restaurant> result = restaurantService.getAvailableRestaurants();

    assertNotNull(result);
    assertEquals(availableRestaurants.size(), result.size());
    assertEquals(availableRestaurants, result);
    verify(mockRepository).getAvailableRestaurants();
  }

  @Test
  @DisplayName("Obtener restaurante por nombre")
  void testGetRestaurantByName() {
    String restaurantName = "Restaurante de testeo";
    Restaurant restaurant = new Restaurant(restaurantName, "Dirección de testeo", true);

    when(mockRepository.getRestaurantByName(restaurantName)).thenReturn(restaurant);

    Restaurant result = restaurantService.getRestaurantByName(restaurantName);

    assertNotNull(result);
    assertEquals(restaurant, result);
    verify(mockRepository).getRestaurantByName(restaurantName);
  }
}