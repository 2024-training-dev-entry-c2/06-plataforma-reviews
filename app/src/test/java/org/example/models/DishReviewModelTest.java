package org.example.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DishReviewModelTest {

  private DishReviewModel review;
  private DishModel dish;

  @BeforeEach
  void setUp() {
    dish = new DishModel("Plato 1", "Descripción", 10.0);
    review = new DishReviewModel("Cliente 1", 5.0, "Excelente plato", dish);
  }

  @Test
  @DisplayName("Test Get Dish")
  void testGetDish() {
    assertEquals(dish, review.getDish());
  }

  @Test
  @DisplayName("Test Set Dish")
  void testSetDish() {
    DishModel newDish = new DishModel("Plato 2", "Nueva Descripción", 15.0);
    review.setDish(newDish);
    assertEquals(newDish, review.getDish());
  }

  @Test
  @DisplayName("Test Get Review Type")
  void testGetReviewType() {
    assertEquals("Dish", review.getReviewType());
  }
}