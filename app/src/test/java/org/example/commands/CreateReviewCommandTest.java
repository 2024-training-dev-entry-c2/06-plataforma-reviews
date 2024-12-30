package org.example.commands;

import org.example.Interface.IConsoleHandler;
import org.example.utils.ReviewFactory;
import org.example.constants.MenuOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CreateReviewCommandTest {

  private IConsoleHandler mockHandler;
  private ReviewFactory mockReviewFactory;
  private MenuOption mockMenuOption;

  @BeforeEach
  void setup() {
    mockHandler = mock(IConsoleHandler.class);
    mockReviewFactory = mock(ReviewFactory.class);
    mockMenuOption = MenuOption.ADD_REVIEW_TO_RESTAURANT;
  }

  @Test
  @DisplayName("Test Create Review Command for Restaurant")
  void testCreateReviewCommandForRestaurant() {
    CreateReviewCommand command = new CreateReviewCommand(mockHandler, mockReviewFactory, mockMenuOption);

    when(mockHandler.readLine()).thenReturn("Cliente 1", "5", "Excelente servicio", "Restaurante 1");

    command.execute();

    verify(mockHandler).writeLine("Ingrese el nombre del cliente:");
    verify(mockHandler).writeLine("Ingrese la calificación:");
    verify(mockHandler).writeLine("Ingrese el comentario:");
    verify(mockHandler).writeLine("Ingrese el nombre del restaurante:");
    verify(mockReviewFactory).createReview(eq("Restaurant"), eq("Cliente 1"), eq(5.0), eq("Excelente servicio"), any());
  }

  @Test
  @DisplayName("Test Create Review Command for Dish")
  void testCreateReviewCommandForDish() {
    mockMenuOption = MenuOption.ADD_REVIEW_TO_DISH;
    CreateReviewCommand command = new CreateReviewCommand(mockHandler, mockReviewFactory, mockMenuOption);

    when(mockHandler.readLine()).thenReturn("Cliente 1", "5", "Excelente plato", "Plato 1");

    command.execute();

    verify(mockHandler).writeLine("Ingrese el nombre del cliente:");
    verify(mockHandler).writeLine("Ingrese la calificación:");
    verify(mockHandler).writeLine("Ingrese el comentario:");
    verify(mockHandler).writeLine("Ingrese el nombre del plato:");
    verify(mockReviewFactory).createReview(eq("Dish"), eq("Cliente 1"), eq(5.0), eq("Excelente plato"), any());
  }
}