package org.example.models;

import org.example.models.RestaurantModel;
import org.example.models.RestaurantReviewModel;
import org.example.models.MenuModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantModelTest {

  private RestaurantModel restaurant;

  @BeforeEach
  void setUp() {
    restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
  }

  @Test
  @DisplayName("Test Get Name")
  void testGetName() {
    assertEquals("Restaurante 1", restaurant.getName());
  }

  @Test
  @DisplayName("Test Set Name")
  void testSetName() {
    restaurant.setName("Nuevo Restaurante");
    assertEquals("Nuevo Restaurante", restaurant.getName());
  }

  @Test
  @DisplayName("Test Get Address")
  void testGetAddress() {
    assertEquals("Calle Ficticia 123", restaurant.getAddress());
  }

  @Test
  @DisplayName("Test Set Address")
  void testSetAddress() {
    restaurant.setAddress("Nueva Dirección 456");
    assertEquals("Nueva Dirección 456", restaurant.getAddress());
  }

  @Test
  @DisplayName("Test Is Available")
  void testIsAvailable() {
    assertTrue(restaurant.isAvailable());
  }

  @Test
  @DisplayName("Test Set Available")
  void testSetAvailable() {
    restaurant.setAvailable(false);
    assertFalse(restaurant.isAvailable());
  }

  @Test
  @DisplayName("Test Get Menu")
  void testGetMenu() {
    MenuModel menu = new MenuModel(restaurant, "Menú Principal");
    restaurant.setMenu(menu);
    assertEquals(menu, restaurant.getMenu());
  }

  @Test
  @DisplayName("Test Set Menu")
  void testSetMenu() {
    MenuModel menu = new MenuModel(restaurant, "Menú Principal");
    restaurant.setMenu(menu);
    assertEquals(menu, restaurant.getMenu());
  }

  @Test
  @DisplayName("Test Add Review")
  void testAddReview() {
    RestaurantReviewModel review = new RestaurantReviewModel("Cliente 1", 5.0, "Excelente", restaurant);
    restaurant.addReview(review);
    assertEquals(1, restaurant.getReviews().size());
    assertEquals(review, restaurant.getReviews().get(0));
  }

  @Test
  @DisplayName("Test Get Reviews")
  void testGetReviews() {
    RestaurantReviewModel review = new RestaurantReviewModel("Cliente 1", 5.0, "Excelente", restaurant);
    restaurant.addReview(review);
    assertEquals(1, restaurant.getReviews().size());
    assertEquals(review, restaurant.getReviews().get(0));
  }

  @Test
  @DisplayName("Test Get Average Rating")
  void testGetAverageRating() {
    RestaurantReviewModel review1 = new RestaurantReviewModel("Cliente 1", 5.0, "Excelente", restaurant);
    RestaurantReviewModel review2 = new RestaurantReviewModel("Cliente 2", 3.0, "Bueno", restaurant);
    restaurant.addReview(review1);
    restaurant.addReview(review2);
    assertEquals(4.0, restaurant.getAverageRating());
  }
}