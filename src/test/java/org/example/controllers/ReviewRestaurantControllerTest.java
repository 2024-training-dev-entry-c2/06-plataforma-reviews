package org.example.controllers;

import org.example.models.Restaurant;
import org.example.models.ReviewRestaurant;
import org.example.services.ReviewRestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class ReviewRestaurantControllerTest {
  private ReviewRestaurantController controller;
  private ReviewRestaurantService service;

  @BeforeEach
  void setUp() {
    service = mock(ReviewRestaurantService.class); // Crear un mock de ReviewRestaurantService
    controller = new ReviewRestaurantController(); // Crear instancia del controlador
    controller.setReviewRestaurantService(service);// Reemplazar el servicio real con el mock
  }

  @Test
  void testAddReview() {
    Restaurant restaurant = new Restaurant("Restaurante Test", "Dirección Test");
    controller.addReview(restaurant, 5, "Excelente servicio");
    verify(service).addReview(restaurant, 5, "Excelente servicio"); // Verificar que el servicio fue llamado correctamente
  }

  @Test
  void testGetReviews() {
    Restaurant restaurant = new Restaurant("Restaurante Test", "Dirección Test");
    ReviewRestaurant review1 = new ReviewRestaurant(5, "Excelente");
    ReviewRestaurant review2 = new ReviewRestaurant(4, "Muy bueno");

    when(service.getReviews(restaurant)).thenReturn(List.of(review1, review2)); // Simular lista de reseñas
    controller.getReviews(restaurant);
    verify(service).getReviews(restaurant); // Verificar que se llamó a getReviews
  }
}
