package org.example.command.review;

import org.example.interfaces.IHandler;
import org.example.models.review.Review;
import org.example.services.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ShowReviewsTest {
  private IHandler mockHandler;
  private ReviewService mockReviewService;
  private ShowReviews showReviewsCommand;

  @BeforeEach
  void setup(){
    mockHandler = mock(IHandler.class);
    mockReviewService = mock(ReviewService.class);
    showReviewsCommand = new ShowReviews(mockReviewService, mockHandler);
  }

  @Test
  @DisplayName("Prueba exitosa para ver review de restaurante")
  void testDisplayRestaurantReview(){
    when(mockHandler.readLine()).thenReturn("restaurante", "Crepes & Waffles");
    List<Review> mockReviews = Arrays.asList(mock(Review.class), mock(Review.class));
    when(mockReviewService.getRestaurantReviews("Crepes & Waffles")).thenReturn(mockReviews);
    when(mockReviewService.calculateAverageRestaurantRating("Crepes & Waffles")).thenReturn(4.5f);

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    showReviewsCommand.execute();

    verify(mockHandler).writeLine("¿Deseas ver las reseñas de un restaurante o un plato? (Restaurante/Plato): ");
    verify(mockHandler).writeLine("Indica el nombre del restaurante: ");
    verify(mockReviewService).getRestaurantReviews("Crepes & Waffles");
    verify(mockReviewService).calculateAverageRestaurantRating("Crepes & Waffles");
    mockReviews.forEach(review -> verify(review).displayReview());

    String output = outContent.toString();
    assertTrue(output.contains("Lista de reseñas de 'Crepes & Waffles'"));
    assertTrue(output.contains("Calificación promedio: 4.5"));

    System.setOut(System.out);
  }

  @Test
  @DisplayName("Prueba exitosa para ver lista vacía de review de restaurante")
  void testDisplayEmptyRestaurantReview(){
    when(mockHandler.readLine()).thenReturn("restaurante", "Crepes & Waffles");
    when(mockReviewService.getRestaurantReviews("Crepes & Waffles")).thenReturn(Collections.emptyList());
    when(mockReviewService.calculateAverageRestaurantRating("Crepes & Waffles")).thenReturn(4.5f);

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    showReviewsCommand.execute();

    verify(mockHandler).writeLine("¿Deseas ver las reseñas de un restaurante o un plato? (Restaurante/Plato): ");
    verify(mockHandler).writeLine("Indica el nombre del restaurante: ");
    verify(mockReviewService).getRestaurantReviews("Crepes & Waffles");

    String output = outContent.toString();
    assertTrue(output.contains("No se pueden obtener las reseñas."));

    System.setOut(System.out);
  }

  @Test
  @DisplayName("Prueba exitosa para ver review de plato")
  void testDisplayDishReview(){
    when(mockHandler.readLine()).thenReturn("plato", "Crepes & Waffles", "Crepe arequipe");
    List<Review> mockReviews = Arrays.asList(mock(Review.class), mock(Review.class));
    when(mockReviewService.getDishReviews("Crepes & Waffles", "Crepe arequipe")).thenReturn(mockReviews);
    when(mockReviewService.calculateAverageDishRating("Crepes & Waffles", "Crepe arequipe")).thenReturn(4.1f);

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    showReviewsCommand.execute();

    verify(mockHandler).writeLine("¿Deseas ver las reseñas de un restaurante o un plato? (Restaurante/Plato): ");
    verify(mockHandler).writeLine("Indica el nombre del restaurante donde está el plato: ");
    verify(mockHandler).writeLine("Indica el nombre del plato: ");
    verify(mockReviewService).getDishReviews("Crepes & Waffles", "Crepe arequipe");
    verify(mockReviewService).calculateAverageDishRating("Crepes & Waffles", "Crepe arequipe");
    mockReviews.forEach(review -> verify(review).displayReview());

    String output = outContent.toString();
    assertTrue(output.contains("Lista de reseñas de 'Crepe arequipe'"));
    assertTrue(output.contains("Calificación promedio: 4.1"));

    System.setOut(System.out);
  }

  @Test
  @DisplayName("Prueba exitosa para ver lista vacía de review de plato")
  void testDisplayEmptyDishReview(){
    when(mockHandler.readLine()).thenReturn("plato", "Crepes & Waffles", "Crepe arequipe");
    when(mockReviewService.getDishReviews("Crepes & Waffles", "Crepe arequipe")).thenReturn(Collections.emptyList());

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    showReviewsCommand.execute();

    verify(mockHandler).writeLine("¿Deseas ver las reseñas de un restaurante o un plato? (Restaurante/Plato): ");
    verify(mockHandler).writeLine("Indica el nombre del restaurante donde está el plato: ");
    verify(mockHandler).writeLine("Indica el nombre del plato: ");
    verify(mockReviewService).getDishReviews("Crepes & Waffles", "Crepe arequipe");

    String output = outContent.toString();
    assertTrue(output.contains("No se pueden obtener las reseñas."));

    System.setOut(System.out);
  }

  @Test
  @DisplayName("Prueba fail para ver reviews de categoria incorrecta")
  void testFailDisplayReview(){
    when(mockHandler.readLine()).thenReturn("other");

    showReviewsCommand.execute();

    verify(mockHandler).writeLine("¿Deseas ver las reseñas de un restaurante o un plato? (Restaurante/Plato): ");
    verify(mockHandler).writeLine("Opción no válida.");
  }

}