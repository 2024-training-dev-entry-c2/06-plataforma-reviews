package org.example;

import org.example.models.DishReview;
import org.example.models.Review;
import org.example.repositories.IObserver;
import org.example.repositories.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ReviewRepositoryTest {

  private ReviewRepository reviewRepository;

  @Mock
  private IObserver observer;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    reviewRepository = ReviewRepository.getInstance();
    reviewRepository.setRestaurantRepository(); // Set the restaurant repository
    reviewRepository.setMenuRepository(); // Set the menu repository

    reviewRepository.setRestaurantRepository();
    reviewRepository.setMenuRepository();
    reviewRepository.addObserver(observer); // Adding the observer
  }

  @Test
  void testCreateDishReview() {
    Long dishId = 1L;
    Float score = 3.5f;
    String comment = "Tasty dish!";

    reviewRepository.createReview("Dish", score, comment, dishId);

    List<Review> reviews = reviewRepository.getReviewsById("Dish", dishId);
    assertNotNull(reviews);
    assertEquals(1, reviews.size());
    assertTrue(reviews.get(0) instanceof DishReview);
    assertEquals(score, reviews.get(0).getScore());
    assertEquals(comment, reviews.get(0).getComment());
  }

  @Test
  void testGetAverageScore() {
    Long restaurantId = 1L;
    reviewRepository.createReview("Restaurant", 4.5f, "Great!", restaurantId);
    reviewRepository.createReview("Restaurant", 3.5f, "Good!", restaurantId);

    List<Review> reviews = reviewRepository.getReviewsById("Restaurant", restaurantId);
    Float averageScore = reviewRepository.getAverageScore(reviews);

    assertEquals(4.0f, averageScore);
  }

}

