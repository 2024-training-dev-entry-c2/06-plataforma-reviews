package org.example.services;

import org.example.models.*;
import org.example.repositories.DataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReviewServiceTest {

  private ReviewService reviewService;
  private DataRepository mockRepository;

  @BeforeEach
  void setup() throws Exception {
    mockRepository = mock(DataRepository.class);
    setMockInstance(mockRepository);
    reviewService = new ReviewService();
  }

  private void setMockInstance(DataRepository mockRepository) throws Exception {
    Field instanceField = DataRepository.class.getDeclaredField("instance");
    instanceField.setAccessible(true);
    instanceField.set(null, mockRepository);
  }

  @Test
  @DisplayName("Test Add Review To Restaurant")
  void testAddReviewToRestaurant() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    when(mockRepository.getRestaurant("Restaurante 1")).thenReturn(restaurant);

    reviewService.addReviewToRestaurant("Restaurante 1", "Cliente 1", 5.0, "Excelente");

    verify(mockRepository).addReviewToRestaurant(argThat(review ->
      "Cliente 1".equals(review.getReviewerName()) &&
        5.0 == review.getRating() &&
        "Excelente".equals(review.getComment()) &&
        restaurant.equals(review.getRestaurant())
    ));
  }

  @Test
  @DisplayName("Test Add Review To Dish")
  void testAddReviewToDish() {
    DishModel dish = new DishModel("Plato 1", "Descripción", 10.0);
    when(mockRepository.getDish("Plato 1")).thenReturn(dish);

    reviewService.addReviewToDish("Plato 1", "Cliente 1", 5.0, "Excelente plato");

    verify(mockRepository).addReviewToDish(argThat(review ->
      "Cliente 1".equals(review.getReviewerName()) &&
        5.0 == review.getRating() &&
        "Excelente plato".equals(review.getComment()) &&
        dish.equals(review.getDish())
    ));
  }

  @Test
  @DisplayName("Test Get Reviews For Restaurant")
  void testGetReviewsForRestaurant() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    RestaurantReviewModel review = new RestaurantReviewModel("Cliente 1", 5.0, "Excelente", restaurant);
    restaurant.addReview(review);
    when(mockRepository.getRestaurant("Restaurante 1")).thenReturn(restaurant);

    List<RestaurantReviewModel> reviews = reviewService.getReviewsForRestaurant("Restaurante 1");

    assertEquals(1, reviews.size());
    assertTrue(reviews.contains(review));
  }

  @Test
  @DisplayName("Test Get Reviews For Dish")
  void testGetReviewsForDish() {
    DishModel dish = new DishModel("Plato 1", "Descripción", 10.0);
    DishReviewModel review = new DishReviewModel("Cliente 1", 5.0, "Excelente plato", dish);
    dish.addReview(review);
    when(mockRepository.getDish("Plato 1")).thenReturn(dish);

    List<DishReviewModel> reviews = reviewService.getReviewsForDish("Plato 1");

    assertEquals(1, reviews.size());
    assertTrue(reviews.contains(review));
  }

  @Test
  @DisplayName("Test Observer Update")
  void testObserverUpdate() {
    reviewService.update("Nueva review agregada");
  }
  @Test
  @DisplayName("Test Add Review To Nonexistent Restaurant")
  void testAddReviewToNonexistentRestaurant() {
    when(mockRepository.getRestaurant("Nonexistent Restaurant")).thenReturn(null);
    assertThrows(IllegalArgumentException.class, () -> reviewService.addReviewToRestaurant("Nonexistent Restaurant", "Cliente 1", 5.0, "Excelente"));
  }

  @Test
  @DisplayName("Test Add Review To Nonexistent Dish")
  void testAddReviewToNonexistentDish() {
    when(mockRepository.getDish("Nonexistent Dish")).thenReturn(null);
    assertThrows(IllegalArgumentException.class, () -> reviewService.addReviewToDish("Nonexistent Dish", "Cliente 1", 5.0, "Excelente plato"));
  }

  @Test
  @DisplayName("Test Get Reviews For Nonexistent Restaurant")
  void testGetReviewsForNonexistentRestaurant() {
    when(mockRepository.getRestaurant("Nonexistent Restaurant")).thenReturn(null);
    assertThrows(IllegalArgumentException.class, () -> reviewService.getReviewsForRestaurant("Nonexistent Restaurant"));
  }

  @Test
  @DisplayName("Test Get Reviews For Nonexistent Dish")
  void testGetReviewsForNonexistentDish() {
    when(mockRepository.getDish("Nonexistent Dish")).thenReturn(null);
    assertThrows(IllegalArgumentException.class, () -> reviewService.getReviewsForDish("Nonexistent Dish"));
  }

  @Test
  @DisplayName("Test Observer Update With Non-Review Message")
  void testObserverUpdateWithNonReviewMessage() {
    reviewService.update("Some other message");

  }
}