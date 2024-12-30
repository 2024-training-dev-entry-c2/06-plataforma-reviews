package org.example;

import org.example.services.ReviewService;
import org.example.utils.IHandler;
import org.example.utils.RestaurantReviewMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RestaurantReviewMenuTest {
  private IHandler mockHandler;
  private ReviewService mockService;
  private RestaurantReviewMenu restaurantReviewMenu;

  @BeforeEach
  void setUp() {
    mockHandler = mock(IHandler.class);
    mockService = mock(ReviewService.class);
    restaurantReviewMenu = new RestaurantReviewMenu(mockService, mockHandler);
  }

  @Test
  @DisplayName("Caso 1 del menu")
  void testCreateRestaurantReview() {
    when(mockHandler.readLine()).thenReturn("1","2", "Excelente servicio", "4.8","3");
    restaurantReviewMenu.displayMenu();
    verify(mockHandler).writeLine("Ingrese el ID del restaurante");
    verify(mockHandler).writeLine("Ingrese el comentario");
    verify(mockHandler).writeLine("Ingrese la calificación");
    verify(mockService).createRestaurantReview(4.8F, "Excelente servicio", 2L);
  }

  @Test
  @DisplayName("Caso 2 del menu")
  void testListRestaurantReviews() {
    when(mockHandler.readLine()).thenReturn("2","1","3");
    restaurantReviewMenu.displayMenu();
    verify(mockHandler).writeLine("Ingrese el ID del restaurante");
    verify(mockService).displayReviews("Restaurant", 1L);
  }

  @Test
  @DisplayName("Caso 3 del menu")
  void testExitRestaurantReviewMenu() {
    when(mockHandler.readLine()).thenReturn("3");
    restaurantReviewMenu.displayMenu();
    verify(mockHandler).writeLine("Saliendo de opciones de reseñas de restaurante");
  }

  @Test
  @DisplayName("Caso inválido del menu")
  void testInvalidOption() {
    when(mockHandler.readLine()).thenReturn("7","3");
    restaurantReviewMenu.displayMenu();
    verify(mockHandler).writeLine("Opción inválida");
  }

}
