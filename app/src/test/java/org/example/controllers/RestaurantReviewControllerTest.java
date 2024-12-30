package org.example.controllers;

import org.example.models.RestaurantReviewModel;
import org.example.services.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantReviewControllerTest {

  private RestaurantReviewController restaurantReviewController;
  private ReviewService mockReviewService;

  @BeforeEach
  void setup() throws Exception {
    mockReviewService = mock(ReviewService.class);
    restaurantReviewController = new RestaurantReviewController();

    Field reviewServiceField = RestaurantReviewController.class.getDeclaredField("reviewService");
    reviewServiceField.setAccessible(true);
    reviewServiceField.set(restaurantReviewController, mockReviewService);
  }

  @Test
  @DisplayName("Test Add Review To Restaurant")
  void testAddReviewToRestaurant() {
    restaurantReviewController.addReviewToRestaurant("Restaurant 1", "Reviewer 1", 5.0, "Excellent");
    verify(mockReviewService).addReviewToRestaurant("Restaurant 1", "Reviewer 1", 5.0, "Excellent");
  }

  @Test
  @DisplayName("Test Get Reviews Of Restaurant")
  void testGetReviewsOfRestaurant() {
    List<RestaurantReviewModel> reviews = List.of(new RestaurantReviewModel("Reviewer 1", 5.0, "Excellent", null));
    when(mockReviewService.getReviewsForRestaurant("Restaurant 1")).thenReturn(reviews);
    List<RestaurantReviewModel> result = restaurantReviewController.getReviewsOfRestaurant("Restaurant 1");
    assertEquals(reviews, result);
  }
}