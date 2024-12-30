package org.example.command.review;

import org.example.interfaces.IHandler;
import org.example.services.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AddReviewTest {
  private IHandler mockHandler;
  private ReviewService mockReviewService;
  private AddReview addReviewCommand;

  @BeforeEach
  void setup(){
    mockHandler = mock(IHandler.class);
    mockReviewService = mock(ReviewService.class);
    addReviewCommand = new AddReview(mockReviewService, mockHandler);
  }

  @Test
  @DisplayName("Prueba exitosa para agregar review de restaurante")
  void testAddRestaurantReview(){
    when(mockHandler.readLine()).thenReturn("restaurante", "Crepes & Waffles", "Todo ok.", "4.0");
    when(mockReviewService.addRestaurantReview("Todo ok.", 4.0f, "Crepes & Waffles")).thenReturn(true);

    addReviewCommand.execute();

    verify(mockHandler).writeLine("¿Deseas reseñar un restaurante o un plato? (Restaurante/Plato): ");
    verify(mockHandler).writeLine("Indica el nombre del restaurante: ");
    verify(mockHandler).writeLine("Indica tu comentario: ");
    verify(mockHandler).writeLine("Indica tu calificación (0.0 - 5.0): ");
    verify(mockReviewService).addRestaurantReview("Todo ok.", 4.0f, "Crepes & Waffles");
  }

  @Test
  @DisplayName("Prueba exitosa para agregar review de plato")
  void testAddDishReview(){
    when(mockHandler.readLine()).thenReturn("plato", "Crepes & Waffles", "Crepe arequipe", "Estaba deli.", "4.4", "4.0");
    when(mockReviewService.addDishReview("Estaba deli.", 4.4f, 4.0f, "Crepes & Waffles", "Crepe arequipe")).thenReturn(true);

    addReviewCommand.execute();

    verify(mockHandler).writeLine("¿Deseas reseñar un restaurante o un plato? (Restaurante/Plato): ");
    verify(mockHandler).writeLine("Indica el nombre del restaurante donde está el plato: ");
    verify(mockHandler).writeLine("Indica el nombre del plato: ");
    verify(mockHandler).writeLine("Indica tu comentario: ");
    verify(mockHandler).writeLine("Indica tu calificación de sabor (0.0 - 5.0): ");
    verify(mockHandler).writeLine("Indica tu calificación de presentación (0.0 - 5.0): ");
    verify(mockReviewService).addDishReview("Estaba deli.", 4.4f, 4.0f, "Crepes & Waffles", "Crepe arequipe");
  }

  @Test
  @DisplayName("Prueba fail para agregar review de categoria incorrecta")
  void testFailAddReview(){
    when(mockHandler.readLine()).thenReturn("other");

    addReviewCommand.execute();

    verify(mockHandler).writeLine("¿Deseas reseñar un restaurante o un plato? (Restaurante/Plato): ");
    verify(mockHandler).writeLine("Opción no válida.");
  }

}