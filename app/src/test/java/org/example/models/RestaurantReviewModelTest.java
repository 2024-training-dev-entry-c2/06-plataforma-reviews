package org.example.models;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantReviewModelTest {

  private RestaurantReviewModel review;
  private RestaurantModel restaurant;

  @BeforeEach
  void setUp() {
    restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    review = new RestaurantReviewModel("Cliente 1", 5.0, "Excelente", restaurant);
  }

  @Test
  @DisplayName("Test Get Restaurant")
  void testGetRestaurant() {
    assertEquals(restaurant, review.getRestaurant());
  }

  @Test
  @DisplayName("Test Set Restaurant")
  void testSetRestaurant() {
    RestaurantModel newRestaurant = new RestaurantModel("Restaurante 2", "Nueva Dirección 456", false);
    review.setRestaurant(newRestaurant);
    assertEquals(newRestaurant, review.getRestaurant());
  }

  @Test
  @DisplayName("Test Get Review Type")
  void testGetReviewType() {
    assertEquals("Restaurant", review.getReviewType());
  }
}