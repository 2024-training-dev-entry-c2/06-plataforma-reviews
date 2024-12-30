package org.example;

import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;
import org.example.services.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RestaurantServiceTest {
  private RestaurantRepository mockRepository;
  private RestaurantService restaurantService;

  @BeforeEach
  void setUp() {
    mockRepository = mock(RestaurantRepository.class);
    restaurantService = new RestaurantService(mockRepository);
  }

  @Test
  @DisplayName("Agregar un restaurante")
  void testAddRestaurant() {
    restaurantService.addRestaurant("Frisby", "320-123-4567", "Pollo apanado");
    verify(mockRepository).addRestaurant("Frisby", "320-123-4567", "Pollo apanado");
  }

  @Test
  @DisplayName("Actualizar un restaurante existente")
  void testUpdateRestaurant() {
    Long id = 1L;
    Restaurant mockRestaurant = mock(Restaurant.class);
    when(mockRepository.getRestaurant(id)).thenReturn(mockRestaurant);

    restaurantService.updateRestaurant(id, "Frisby Actualizado", "321-987-6543", "Pollo gourmet");
    verify(mockRepository).updateRestaurant(mockRestaurant, "Frisby Actualizado", "321-987-6543", "Pollo gourmet");
  }

  @Test
  @DisplayName("Intentar actualizar un restaurante inexistente")
  void testUpdateNonExistentRestaurant() {
    Long id = 999L;
    when(mockRepository.getRestaurant(id)).thenReturn(null);

    restaurantService.updateRestaurant(id, "Frisby", "320-123-4567", "Pollo apanado");
    verify(mockRepository, never()).updateRestaurant(any(Restaurant.class), anyString(), anyString(), anyString());
  }

  @Test
  @DisplayName("Eliminar un restaurante existente")
  void testRemoveRestaurant() {
    Long id = 1L;
    when(mockRepository.removeRestaurant(id)).thenReturn(true);

    restaurantService.removeRestaurant(id);
    verify(mockRepository).removeRestaurant(id);
  }

  @Test
  @DisplayName("Intentar eliminar un restaurante inexistente")
  void testRemoveNonExistentRestaurant() {
    Long id = 999L;
    when(mockRepository.removeRestaurant(id)).thenReturn(false);

    restaurantService.removeRestaurant(id);
    verify(mockRepository).removeRestaurant(id);
  }

  @Test
  @DisplayName("Mostrar restaurantes")
  void testDisplayRestaurants() {
    restaurantService.displayRestaurants();
    verify(mockRepository).getRestaurants();
  }
}
