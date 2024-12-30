package org.example.models;

import org.example.models.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewTest {

  private Review review;

  @BeforeEach
  void setUp() {
    review = new Review("Cliente 1", 5.0, "Excelente") {
      @Override
      public String getReviewType() {
        return "Generic";
      }
    };
  }

  @Test
  @DisplayName("Test Get Reviewer Name")
  void testGetReviewerName() {
    assertEquals("Cliente 1", review.getReviewerName());
  }

  @Test
  @DisplayName("Test Set Reviewer Name")
  void testSetReviewerName() {
    review.setReviewerName("Cliente 2");
    assertEquals("Cliente 2", review.getReviewerName());
  }

  @Test
  @DisplayName("Test Get Rating")
  void testGetRating() {
    assertEquals(5.0, review.getRating());
  }

  @Test
  @DisplayName("Test Set Rating")
  void testSetRating() {
    review.setRating(4.0);
    assertEquals(4.0, review.getRating());
  }

  @Test
  @DisplayName("Test Get Comment")
  void testGetComment() {
    assertEquals("Excelente", review.getComment());
  }

  @Test
  @DisplayName("Test Set Comment")
  void testSetComment() {
    review.setComment("Muy bueno");
    assertEquals("Muy bueno", review.getComment());
  }

  @Test
  @DisplayName("Test Get Review Type")
  void testGetReviewType() {
    assertEquals("Generic", review.getReviewType());
  }
}