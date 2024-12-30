package org.example;

import org.example.services.ReviewService;
import org.example.utils.DishReviewMenu;
import org.example.utils.IHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DishMenuReviewTest {
  private IHandler mockHandler;
  private ReviewService mockService;
  private DishReviewMenu dishReviewMenu;

  @BeforeEach
  void setUp() {
    mockHandler = mock(IHandler.class);
    mockService = mock(ReviewService.class);
    dishReviewMenu = new DishReviewMenu(mockService, mockHandler);
  }

  @Test
  @DisplayName("Caso 1 del menu")
  void testCreateDishReview() {
    when(mockHandler.readLine()).thenReturn("1","5", "2", "Muy buen plato", "4.6", "3");
    dishReviewMenu.displayMenu();
    verify(mockHandler).writeLine("Ingrese el ID del restaurante");
    verify(mockHandler).writeLine("Ingrese el ID del plato");
    verify(mockHandler).writeLine("Ingrese el comentario");
    verify(mockHandler).writeLine("Ingrese la calificación");
    verify(mockService).createDishReview(4.6F, "Muy buen plato", 2L);
  }

  @Test
  @DisplayName("Caso 2 del menu")
  void testListDishReviews() {
    when(mockHandler.readLine()).thenReturn("2","5", "1", "3");
    dishReviewMenu.displayMenu();
    verify(mockHandler).writeLine("Ingrese el ID del restaurante");
    verify(mockHandler).writeLine("Ingrese el ID del plato");
    verify(mockService).displayReviews("Dish", 1L);
  }

  @Test
  @DisplayName("Caso 3 del menu")
  void testExitDishReviewMenu() {
    when(mockHandler.readLine()).thenReturn("3");
    dishReviewMenu.displayMenu();
    verify(mockHandler).writeLine("Saliendo de opciones de reseñas de plato");
  }

  @Test
  @DisplayName("Caso inválido del menu")
  void testInvalidOption() {
    when(mockHandler.readLine()).thenReturn("7","3");
    dishReviewMenu.displayMenu();
    verify(mockHandler).writeLine("Opción inválida");
  }
}
