package org.example.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewFactoryTest {

  @Test
  void testCreateReviewRestaurant() {
    Review review = ReviewFactory.createReview(ReviewFactory.ReviewType.RESTAURANT, 5, "Excelente comida");

    assertNotNull(review);
    assertTrue(review instanceof ReviewRestaurant);
    assertEquals(5, review.getRating());
    assertEquals("Excelente comida", review.getComment());
  }

  @Test
  void testCreateReviewPlate() {
    Review review = ReviewFactory.createReview(ReviewFactory.ReviewType.PLATE, 4, "Muy buen plato");

    assertNotNull(review);
    assertTrue(review instanceof ReviewPlate);
    assertEquals(4, review.getRating());
    assertEquals("Muy buen plato", review.getComment());
  }

  @Test
  void testCreateReviewInvalidType() {
    Exception exception = assertThrows(RuntimeException.class, () ->
            ReviewFactory.createReview(null, 3, "Comentario inválido")
    );

    assertEquals("Tipo de comentario no reconocido", exception.getMessage());
  }
}
