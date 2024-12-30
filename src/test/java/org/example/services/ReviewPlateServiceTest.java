package org.example.services;

import org.example.models.Plate;
import org.example.models.ReviewFactory;
import org.example.models.ReviewPlate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReviewPlateServiceTest {
  private ReviewPlateService reviewPlateService;
  private Plate mockPlate;

  @BeforeEach
  void setUp() {
    reviewPlateService = new ReviewPlateService();
    mockPlate = mock(Plate.class);
  }

  @Test
  void testAddReview() {
    // Mock de ReviewPlate
    ReviewPlate mockReview = mock(ReviewPlate.class);

    // Mock de ReviewFactory
    try (var mockStatic = mockStatic(ReviewFactory.class)) {
      when(ReviewFactory.createReview(ReviewFactory.ReviewType.PLATE, 5, "Great Plate"))
              .thenReturn(mockReview);

      // Llama al método a probar
      reviewPlateService.addReview(mockPlate, 5, "Great Plate");

      // Verifica que se creó una ReviewPlate
      mockStatic.verify(() -> ReviewFactory.createReview(ReviewFactory.ReviewType.PLATE, 5, "Great Plate"), times(1));

      // Verifica que se agregó la reseña al plato
      verify(mockPlate, times(1)).addReview(mockReview);
    }
  }

  @Test
  void testGetReviews() {
    // Mock de la lista de reseñas
    ReviewPlate mockReview1 = mock(ReviewPlate.class);
    ReviewPlate mockReview2 = mock(ReviewPlate.class);
    when(mockPlate.getReviews()).thenReturn(List.of(mockReview1, mockReview2));

    // Llama al método a probar
    List<ReviewPlate> reviews = reviewPlateService.getReviews(mockPlate);

    // Verifica el resultado
    assertEquals(2, reviews.size());
    assertSame(mockReview1, reviews.get(0));
    assertSame(mockReview2, reviews.get(1));

    // Verifica que se llamó al método getReviews
    verify(mockPlate, times(1)).getReviews();
  }
}
