package org.example;

import org.example.controllers.MenuMenuController;
import org.example.controllers.RestaurantMenuController;
import org.example.controllers.ReviewMenuController;
import org.example.interfaces.IHandler;
import org.example.utils.ReviewerMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ReviewerMenuTest {
  private IHandler mockHandler;
  private RestaurantMenuController mockRestaurantMenuController;
  private MenuMenuController mockMenuMenuController;
  private ReviewMenuController mockReviewMenuController;
  private ReviewerMenu menu;

  @BeforeEach
  void setup(){
    mockHandler = mock( IHandler.class);
    mockRestaurantMenuController = mock(RestaurantMenuController.class);
    mockMenuMenuController = mock(MenuMenuController.class);
    mockReviewMenuController = mock(ReviewMenuController.class);
    menu = new ReviewerMenu(mockHandler, mockRestaurantMenuController, mockMenuMenuController, mockReviewMenuController);
  }

  @Test
  @DisplayName("Caso 1 del menú, ver opciones de restaurante")
  void testViewRestaurantOptions(){
    when(mockHandler.readLine())
      .thenReturn("1")
      .thenReturn("0");

    menu.displayMenu();

    verify(mockHandler).writeLine("\n¡Bienvenido al reseñador de Restaurantes!");
    verify(mockHandler, times(2)).writeLine("¿Qué deseas hacer?\n1. Ver opciones de restaurante.\n2. Ver opciones de menú.\n3. Ver opciones de reseña.\n0. Salir");

    verify(mockRestaurantMenuController).start();
    verify(mockHandler).writeLine("\n¡Gracias por usar nuestros servicios!");
  }

  @Test
  @DisplayName("Caso 2 del menú, ver opciones de menú de restaurante")
  void testViewMenuOptions(){
    when(mockHandler.readLine())
      .thenReturn("2")
      .thenReturn("0");

    menu.displayMenu();

    verify(mockHandler).writeLine("\n¡Bienvenido al reseñador de Restaurantes!");
    verify(mockHandler, times(2)).writeLine("¿Qué deseas hacer?\n1. Ver opciones de restaurante.\n2. Ver opciones de menú.\n3. Ver opciones de reseña.\n0. Salir");

    verify(mockMenuMenuController).start();
    verify(mockHandler).writeLine("\n¡Gracias por usar nuestros servicios!");
  }

  @Test
  @DisplayName("Caso 3 del menú, ver opciones de las reseñas de restaurante")
  void testViewReviewOptions(){
    when(mockHandler.readLine())
      .thenReturn("3")
      .thenReturn("0");

    menu.displayMenu();

    verify(mockHandler).writeLine("\n¡Bienvenido al reseñador de Restaurantes!");
    verify(mockHandler, times(2)).writeLine("¿Qué deseas hacer?\n1. Ver opciones de restaurante.\n2. Ver opciones de menú.\n3. Ver opciones de reseña.\n0. Salir");

    verify(mockReviewMenuController).start();
    verify(mockHandler).writeLine("\n¡Gracias por usar nuestros servicios!");
  }

}