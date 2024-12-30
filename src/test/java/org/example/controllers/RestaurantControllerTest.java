package org.example.controllers;

import org.example.models.Restaurant;
import org.example.services.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class RestaurantControllerTest {
  private RestaurantController controller;
  private RestaurantService service;

  @BeforeEach
  void setUp() {
    service = mock(RestaurantService.class);
    controller = new RestaurantController();
    controller.setRestaurantService(service); // Usar el setter para inyectar el mock
  }

  @Test
  void testAddRestaurant() {
    controller.addRestaurant("Restaurante Test", "Dirección Test");
    verify(service).addRestaurant("Restaurante Test", "Dirección Test"); // Verificar que el servicio fue llamado correctamente
  }

  @Test
  void testGetRestaurantsEmpty() {
    when(service.getRestaurants()).thenReturn(List.of()); // Simular lista vacía
    controller.getRestaurants();
    verify(service).getRestaurants(); // Verificar que se llamó a getRestaurants
  }

  @Test
  void testGetRestaurantsWithData() {
    when(service.getRestaurants()).thenReturn(List.of(
            new Restaurant("Restaurante 1", "Dirección 1"),
            new Restaurant("Restaurante 2", "Dirección 2")
    )); // Simular lista con datos
    controller.getRestaurants();
    verify(service).getRestaurants(); // Verificar que se llamó a getRestaurants
  }

  @Test
  void testRemoveRestaurant() {
    controller.removeRestaurant("Restaurante Test");
    verify(service).removeRestaurant("Restaurante Test"); // Verificar que se llamó a removeRestaurant
  }
}
