package org.example.controllers;

import org.example.models.DishReviewModel;
import org.example.services.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DishReviewControllerTest {

  private DishReviewController dishReviewController;
  private ReviewService mockReviewService;

  @BeforeEach
  void setup() throws Exception {
    mockReviewService = mock(ReviewService.class);
    dishReviewController = new DishReviewController();

    Field reviewServiceField = DishReviewController.class.getDeclaredField("reviewService");
    reviewServiceField.setAccessible(true);
    reviewServiceField.set(dishReviewController, mockReviewService);
  }

  @Test
  @DisplayName("Test Add Review To Dish")
  void testAddReviewToDish() {
    dishReviewController.addReviewToDish("Dish 1", "Reviewer 1", 5.0, "Excellent");
    verify(mockReviewService).addReviewToDish("Dish 1", "Reviewer 1", 5.0, "Excellent");
  }

  @Test
  @DisplayName("Test Get Reviews Of Dish")
  void testGetReviewsOfDish() {
    List<DishReviewModel> reviews = List.of(new DishReviewModel("Reviewer 1", 5.0, "Excellent", null));
    when(mockReviewService.getReviewsForDish("Dish 1")).thenReturn(reviews);
    List<DishReviewModel> result = dishReviewController.getReviewsOfDish("Dish 1");
    assertEquals(reviews, result);
  }
}