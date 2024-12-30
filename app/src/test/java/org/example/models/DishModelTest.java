package org.example.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DishModelTest {

  @Test
  @DisplayName("Test DishModel Get Description")
  void testDishModelGetDescription() {
    DishModel dish = new DishModel("Plato 1", "Descripción", 10.0);
    assertEquals("Descripción", dish.getDescription());
  }

  @Test
  @DisplayName("Test DishModel Get Price")
  void testDishModelGetPrice() {
    DishModel dish = new DishModel("Plato 1", "Descripción", 10.0);
    assertEquals(10.0, dish.getPrice());
  }

  @Test
  @DisplayName("Test DishModel Get Name")
  void testDishModelGetName() {
    DishModel dish = new DishModel("Plato 1", "Descripción", 10.0);
    assertEquals("Plato 1", dish.getName());
  }

  @Test
  @DisplayName("Test DishModel Set Name")
  void testDishModelSetName() {
    DishModel dish = new DishModel("Plato 1", "Descripción", 10.0);
    dish.setName("Nuevo Plato");
    assertEquals("Nuevo Plato", dish.getName());
  }

  @Test
  @DisplayName("Test DishModel Set Description")
  void testDishModelSetDescription() {
    DishModel dish = new DishModel("Plato 1", "Descripción", 10.0);
    dish.setDescription("Nueva Descripción");
    assertEquals("Nueva Descripción", dish.getDescription());
  }

  @Test
  @DisplayName("Test DishModel Set Price")
  void testDishModelSetPrice() {
    DishModel dish = new DishModel("Plato 1", "Descripción", 10.0);
    dish.setPrice(15.0);
    assertEquals(15.0, dish.getPrice());
  }

  @Test
  @DisplayName("Test DishModel Add Review")
  void testDishModelAddReview() {
    DishModel dish = new DishModel("Plato 1", "Descripción", 10.0);
    DishReviewModel review = new DishReviewModel("Cliente 1", 5.0, "Excelente plato", dish);
    dish.addReview(review);
    assertEquals(1, dish.getReviews().size());
    assertEquals(review, dish.getReviews().get(0));
  }

  @Test
  @DisplayName("Test DishModel Get Reviews")
  void testDishModelGetReviews() {
    DishModel dish = new DishModel("Plato 1", "Descripción", 10.0);
    DishReviewModel review = new DishReviewModel("Cliente 1", 5.0, "Excelente plato", dish);
    dish.addReview(review);
    assertEquals(1, dish.getReviews().size());
    assertEquals(review, dish.getReviews().get(0));
  }

  @Test
  @DisplayName("Test DishModel Get Average Rating")
  void testDishModelGetAverageRating() {
    DishModel dish = new DishModel("Plato 1", "Descripción", 10.0);
    DishReviewModel review1 = new DishReviewModel("Cliente 1", 5.0, "Excelente plato", dish);
    DishReviewModel review2 = new DishReviewModel("Cliente 2", 3.0, "Buen plato", dish);
    dish.addReview(review1);
    dish.addReview(review2);
    assertEquals(4.0, dish.getAverageRating());
  }
}