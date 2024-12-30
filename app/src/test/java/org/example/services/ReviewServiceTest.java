package org.example.services;

import org.example.constants.TypeReview;
import org.example.factory.ReviewFactory;
import org.example.interfaces.IObserver;
import org.example.models.Dish;
import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.models.review.DishReview;
import org.example.models.review.RestaurantReview;
import org.example.models.review.Review;
import org.example.repositories.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ReviewServiceTest {
  private RestaurantRepository mockRepository;
  private ReviewFactory mockReviewFactory;
  private IObserver mockObserver;
  private ReviewService reviewService;

  private Restaurant mockRestaurant;
  private Dish mockDish;
  private Menu mockMenu;

  @BeforeEach
  void setup() {
    mockRepository = mock(RestaurantRepository.class);
    mockObserver = mock(IObserver.class);
    mockReviewFactory = mock(ReviewFactory.class);
    reviewService = new ReviewService(mockRepository, mockReviewFactory);

    mockRestaurant = mock(Restaurant.class);
    mockDish = mock(Dish.class);
    mockMenu = mock(Menu.class);

    reviewService.addObserver(mockObserver);
  }

  @Test
  @DisplayName("Agregar reseña a un plato")
  void testAddDishReview() {
    String restaurantName = "Restaurante de testeo";
    String dishName = "Plato de testeo";
    String comment = "Delicioso!";
    Float taste = 4.5f;
    Float presentation = 4.0f;

    when(mockRepository.getRestaurantByName(restaurantName)).thenReturn(mockRestaurant);
    when(mockRestaurant.getMenu()).thenReturn(mockMenu);
    when(mockMenu.searchDish(dishName)).thenReturn(mockDish);

    Review mockReview = mock(DishReview.class);
    when(mockReviewFactory.createReview(TypeReview.DISH, comment, null, mockDish, taste, presentation))
      .thenReturn(mockReview);
    when(mockRepository.getRestaurantByName(restaurantName)).thenReturn(mockRestaurant);
    when(mockRestaurant.getMenu()).thenReturn(mockMenu);
    when(mockMenu.searchDish(dishName)).thenReturn(mockDish);

    boolean result = reviewService.addDishReview(comment, taste, presentation, restaurantName, dishName);

    assertTrue(result);
    verify(mockDish).addReview(mockReview);
    verify(mockObserver).update("Se ha agregado una reseña al plato " + dishName + " del restaurante " + restaurantName + ".");
  }

  @Test
  @DisplayName("Agregar reseña null a un plato")
  void testAddNullDishReview() {
    String restaurantName = "Restaurante de testeo";
    String dishName = "Plato de testeo";

    when(mockRepository.getRestaurantByName(restaurantName)).thenReturn(mockRestaurant);
    when(mockRestaurant.getMenu()).thenReturn(mockMenu);
    when(mockMenu.searchDish(dishName)).thenReturn(mockDish);

    when(mockReviewFactory.createReview(TypeReview.DISH, null, null, mockDish, null, null))
      .thenReturn(null);

    boolean result = reviewService.addDishReview(null, null, null, restaurantName, dishName);

    assertFalse(result);

    verify(mockDish, never()).addReview(any());
  }

  @Test
  @DisplayName("Agregar reseña a un restaurante")
  void testAddRestaurantReview() {
    String restaurantName = "Restaurante de testeo";
    String comment = "Buen lugar!";
    Float rating = 4.7f;

    when(mockRepository.getRestaurantByName(restaurantName)).thenReturn(mockRestaurant);

    Review mockReview = mock(RestaurantReview.class);
    when(mockReviewFactory.createReview(TypeReview.RESTAURANT, comment, rating, mockRestaurant, null, null))
      .thenReturn(mockReview);

    boolean result = reviewService.addRestaurantReview(comment, rating, restaurantName);

    assertTrue(result);
    verify(mockRestaurant).addReview(mockReview);
    verify(mockObserver).update("Se ha agregado una reseña al restaurante " + restaurantName + ".");
  }

  @Test
  @DisplayName("Agregar reseña null a un restaurante")
  void testAddNullRestaurantReview() {
    String restaurantName = "Restaurante de testeo";

    when(mockRepository.getRestaurantByName(restaurantName)).thenReturn(mockRestaurant);

    when(mockReviewFactory.createReview(TypeReview.RESTAURANT, null, null, mockRestaurant, null, null))
      .thenReturn(null);

    boolean result = reviewService.addRestaurantReview(null, null, restaurantName);

    assertFalse(result);

    verify(mockRestaurant, never()).addReview(any());
  }

  @Test
  @DisplayName("Obtener reseñas de un restaurante")
  void testGetRestaurantReviews() {
    String restaurantName = "Restaurante de testeo";
    List<Review> mockReviews = Arrays.asList(mock(Review.class), mock(Review.class));

    when(mockRepository.getRestaurantByName(restaurantName)).thenReturn(mockRestaurant);
    when(mockRestaurant.getReviews()).thenReturn(mockReviews);

    List<Review> reviews = reviewService.getRestaurantReviews(restaurantName);

    assertNotNull(reviews);
    assertEquals(mockReviews.size(), reviews.size());
    assertEquals(mockReviews, reviews);
    verify(mockRepository).getRestaurantByName(restaurantName);
    verify(mockRestaurant).getReviews();
  }

  @Test
  @DisplayName("Obtener reseñas de un plato")
  void testGetDishReviews() {
    String restaurantName = "Restaurante de testeo";
    String dishName = "Plato de testeo";
    List<Review> mockReviews = Arrays.asList(mock(Review.class), mock(Review.class));

    when(mockRepository.getRestaurantByName(restaurantName)).thenReturn(mockRestaurant);
    when(mockRestaurant.getMenu()).thenReturn(mockMenu);
    when(mockMenu.searchDish(dishName)).thenReturn(mockDish);
    when(mockDish.getReviews()).thenReturn(mockReviews);

    List<Review> reviews = reviewService.getDishReviews(restaurantName, dishName);

    assertNotNull(reviews);
    assertEquals(mockReviews.size(), reviews.size());
    assertEquals(mockReviews, reviews);
    verify(mockRepository).getRestaurantByName(restaurantName);
    verify(mockDish).getReviews();
  }

  @Test
  @DisplayName("Calcular promedio de calificaciones de un restaurante")
  void testCalculateAverageRestaurantRating() {
    String restaurantName = "Restaurante de testeo";
    List<Review> mockReviews = Arrays.asList(
      createMockReview(4.0f),
      createMockReview(5.0f),
      createMockReview(3.5f)
    );

    when(mockRepository.getRestaurantByName(restaurantName)).thenReturn(mockRestaurant);
    when(mockRestaurant.getReviews()).thenReturn(mockReviews);

    float averageRating = reviewService.calculateAverageRestaurantRating(restaurantName);

    assertEquals(4.17f, averageRating, 0.01f);
    verify(mockRepository).getRestaurantByName(restaurantName);
    verify(mockRestaurant).getReviews();
  }

  private Review createMockReview(Float rating) {
    Review mockReview = mock(Review.class);
    when(mockReview.getRating()).thenReturn(rating);
    return mockReview;
  }

  @Test
  @DisplayName("Calcular promedio de calificaciones de un plato")
  void testCalculateAverageDishRating() {
    String restaurantName = "Restaurante de testeo";
    String dishName = "Plato de testeo";
    List<Review> mockReviews = Arrays.asList(
      createMockDishReview(4.0f, 5.0f),
      createMockDishReview(3.5f, 4.5f)
    );

    when(mockRepository.getRestaurantByName(restaurantName)).thenReturn(mockRestaurant);
    when(mockRestaurant.getMenu()).thenReturn(mockMenu);
    when(mockMenu.searchDish(dishName)).thenReturn(mockDish);
    when(mockDish.getReviews()).thenReturn(mockReviews);

    float averageRating = reviewService.calculateAverageDishRating(restaurantName, dishName);

    assertEquals(4.25f, averageRating, 0.01f);
    verify(mockRepository).getRestaurantByName(restaurantName);
    verify(mockDish).getReviews();
  }

  private DishReview createMockDishReview(Float taste, Float presentation) {
    DishReview mockReview = mock(DishReview.class);
    when(mockReview.getTaste()).thenReturn(taste);
    when(mockReview.getPresentation()).thenReturn(presentation);
    return mockReview;
  }

  @Test
  @DisplayName("Remover un observador")
  void testRemoveObserver() {
    assertTrue(reviewService.getObservers().contains(mockObserver));

    reviewService.removeObserver(mockObserver);

    assertFalse(reviewService.getObservers().contains(mockObserver));
  }
}