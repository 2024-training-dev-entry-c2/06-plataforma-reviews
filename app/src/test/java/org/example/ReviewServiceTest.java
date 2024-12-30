package org.example;

import org.example.models.Dish;
import org.example.models.Restaurant;
import org.example.repositories.ReviewRepository;
import org.example.services.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.anyFloat;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ReviewServiceTest {

  private ReviewRepository reviewRepositoryMock;
  private ReviewService reviewService;

  @BeforeEach
  void setUp() {
    reviewRepositoryMock = mock(ReviewRepository.class);
    reviewService = new ReviewService(reviewRepositoryMock);
  }

  @Test
  void testCreateRestaurantReview_RestaurantExists() {
    Long restaurantId = 1L;
    Float score = 4.5F;
    String comment = "Excelente servicio";

    when(reviewRepositoryMock.getRestaurant(restaurantId))
      .thenReturn(new Restaurant(restaurantId, "Pollos Mario", "123456789", "Descripción de Pollos Mario"));

    reviewService.createRestaurantReview(score, comment, restaurantId);

    verify(reviewRepositoryMock).createReview("Restaurant", score, comment, restaurantId);
    verify(reviewRepositoryMock).notifyObservers("Restaurant", restaurantId);
  }

  @Test
  void testCreateRestaurantReview_RestaurantDoesNotExist() {
    Long restaurantId = 1L;
    Float score = 4.5F;
    String comment = "Excelente servicio";

    when(reviewRepositoryMock.getRestaurant(restaurantId)).thenReturn(null);

    reviewService.createRestaurantReview(score, comment, restaurantId);

    verify(reviewRepositoryMock, never()).createReview(anyString(), anyFloat(), anyString(), anyLong());
    verify(reviewRepositoryMock, never()).notifyObservers(anyString(), anyLong());
  }

  @Test
  void testCreateDishReview_DishExists() {
    Long dishId = 1L;
    Float score = 4.0F;
    String comment = "Delicioso";

    when(reviewRepositoryMock.getDish(dishId)).thenReturn(new Dish(dishId, 2L,"Pollo", "Plato original", 15000L));

    reviewService.createDishReview(score, comment, dishId);

    verify(reviewRepositoryMock).createReview("Dish", score, comment, dishId);
    verify(reviewRepositoryMock).notifyObservers("Dish", dishId);
  }

  @Test
  void testCreateDishReview_DishDoesNotExist() {
    Long dishId = 1L;
    Float score = 4.0F;
    String comment = "Delicioso";

    when(reviewRepositoryMock.getDish(dishId)).thenReturn(null);

    reviewService.createDishReview(score, comment, dishId);

    verify(reviewRepositoryMock, never()).createReview(anyString(), anyFloat(), anyString(), anyLong());
    verify(reviewRepositoryMock, never()).notifyObservers(anyString(), anyLong());
  }

  @Test
  void testDisplayRestaurants() {
    reviewService.displayRestaurants();
    verify(reviewRepositoryMock).displayRestaurants();
  }

  @Test
  void testDisplayMenu_RestaurantExists() {
    Long restaurantId = 1L;
    when(reviewRepositoryMock.getRestaurant(restaurantId)).thenReturn(new Restaurant(restaurantId, "Pollos Mario", "123456789", "Descripción de Pollos Mario"));

    reviewService.displayMenu(restaurantId);
    verify(reviewRepositoryMock).getDishes(restaurantId);
  }

  @Test
  void testDisplayMenu_RestaurantDoesNotExist() {
    Long restaurantId = 1L;

    when(reviewRepositoryMock.getRestaurant(restaurantId)).thenReturn(null);

    reviewService.displayMenu(restaurantId);
    verify(reviewRepositoryMock, never()).getDishes(anyLong());
  }

  @Test
  void testDisplayReviews_ReviewsExist() {
    String typeOfReview = "Restaurant";
    Long reviewedId = 1L;

    when(reviewRepositoryMock.getReviewsById(typeOfReview, reviewedId)).thenReturn(List.of());

    reviewService.displayReviews(typeOfReview, reviewedId);
    verify(reviewRepositoryMock).getReviewList(anyList());
    verify(reviewRepositoryMock).getAverageScore(anyList());
  }

  @Test
  void testDisplayReviews_NoReviews() {
    String typeOfReview = "Restaurant";
    Long reviewedId = 1L;

    when(reviewRepositoryMock.getReviewsById(typeOfReview, reviewedId)).thenReturn(null);

    reviewService.displayReviews(typeOfReview, reviewedId);
    verify(reviewRepositoryMock, never()).getReviewList(anyList());
    verify(reviewRepositoryMock, never()).getAverageScore(anyList());
  }
}

