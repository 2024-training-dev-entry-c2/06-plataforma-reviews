package org.example.controllers;

import org.example.models.Plate;
import org.example.models.ReviewPlate;
import org.example.services.ReviewPlateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class ReviewPlateControllerTest {
  private ReviewPlateController controller;
  private ReviewPlateService service;

  @BeforeEach
  void setUp() {
    service = mock(ReviewPlateService.class);
    controller = new ReviewPlateController();
    controller.setReviewPlateService(service); // Inyectar el mock usando el setter
  }

  @Test
  void testAddReview() {
    Plate plate = new Plate("Plato Test", "Descripción", 50.0);
    controller.addReview(plate, 5, "Excelente");
    verify(service).addReview(plate, 5, "Excelente"); // Verificar que el servicio fue llamado correctamente
  }

  @Test
  void testGetReviews() {
    Plate plate = new Plate("Plato Test", "Descripción", 50.0);
    ReviewPlate review1 = new ReviewPlate(5, "Muy bueno");
    ReviewPlate review2 = new ReviewPlate(4, "Delicioso");

    when(service.getReviews(plate)).thenReturn(List.of(review1, review2)); // Simular lista de reseñas
    controller.getReviews(plate);
    verify(service).getReviews(plate); // Verificar que se llamó a getReviews
  }
}
