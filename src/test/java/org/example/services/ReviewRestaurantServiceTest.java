package org.example.services;

import org.example.models.Restaurant;
import org.example.models.ReviewFactory;
import org.example.models.ReviewRestaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReviewRestaurantServiceTest {
  private ReviewRestaurantService reviewRestaurantService;
  private Restaurant mockRestaurant;

  @BeforeEach
  void setUp() {
    reviewRestaurantService = new ReviewRestaurantService();
    mockRestaurant = mock(Restaurant.class);
  }

  @Test
  void testAddReview() {
    // Mock de ReviewRestaurant
    ReviewRestaurant mockReview = mock(ReviewRestaurant.class);

    // Mock de ReviewFactory
    try (var mockStatic = mockStatic(ReviewFactory.class)) {
      when(ReviewFactory.createReview(ReviewFactory.ReviewType.RESTAURANT, 4, "Amazing experience"))
              .thenReturn(mockReview);

      // Llama al método a probar
      reviewRestaurantService.addReview(mockRestaurant, 4, "Amazing experience");

      // Verifica que se creó una ReviewRestaurant
      mockStatic.verify(() -> ReviewFactory.createReview(ReviewFactory.ReviewType.RESTAURANT, 4, "Amazing experience"), times(1));

      // Verifica que se agregó la reseña al restaurante
      verify(mockRestaurant, times(1)).addReview(mockReview);
    }
  }

  @Test
  void testGetReviews() {
    // Mock de la lista de reseñas
    ReviewRestaurant mockReview1 = mock(ReviewRestaurant.class);
    ReviewRestaurant mockReview2 = mock(ReviewRestaurant.class);
    when(mockRestaurant.getReviews()).thenReturn(List.of(mockReview1, mockReview2));

    // Llama al método a probar
    List<ReviewRestaurant> reviews = reviewRestaurantService.getReviews(mockRestaurant);

    // Verifica el resultado
    assertEquals(2, reviews.size());
    assertSame(mockReview1, reviews.get(0));
    assertSame(mockReview2, reviews.get(1));

    // Verifica que se llamó al método getReviews
    verify(mockRestaurant, times(1)).getReviews();
  }
}
