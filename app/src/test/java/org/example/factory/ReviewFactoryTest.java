package org.example.factory;

import org.example.constants.TypeReview;
import org.example.models.Dish;
import org.example.models.Restaurant;
import org.example.models.review.DishReview;
import org.example.models.review.RestaurantReview;
import org.example.models.review.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ReviewFactoryTest {
  private ReviewFactory reviewFactory;

  @BeforeEach
  void setup(){
    reviewFactory = new ReviewFactory();
  }

  @Test
  void testCreateDishReview() {
    Dish mockDish = mock(Dish.class);
    String comment = "Buen plato";
    Float taste = 4.5f;
    Float presentation = 4.0f;

    Review review = reviewFactory.createReview(TypeReview.DISH, comment, null, mockDish, taste, presentation);

    assertNotNull(review);
    assertTrue(review instanceof DishReview);
    DishReview dishReview = (DishReview) review;
    assertEquals(comment, dishReview.getComment());
    assertEquals(taste, dishReview.getTaste());
    assertEquals(presentation, dishReview.getPresentation());
    assertEquals(mockDish, dishReview.getDish());
  }

  @Test
  void testCreateRestaurantReview() {
    Restaurant mockRestaurant = mock(Restaurant.class);
    String comment = "Excelente servicio";
    Float rating = 4.7f;

    Review review = reviewFactory.createReview(TypeReview.RESTAURANT, comment, rating, mockRestaurant, null, null);

    assertNotNull(review);
    assertTrue(review instanceof RestaurantReview);
    RestaurantReview restaurantReview = (RestaurantReview) review;
    assertEquals(comment, restaurantReview.getComment());
    assertEquals(rating, restaurantReview.getRating());
    assertEquals(mockRestaurant, restaurantReview.getRestaurant());
  }

  @Test
  void testCreateDishReviewWithInvalidEntity() {
    Restaurant mockRestaurant = mock(Restaurant.class);
    String comment = "Invalid dish";
    Float taste = 4.5f;
    Float presentation = 4.0f;

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setErr(new PrintStream(outContent));

    Review review = reviewFactory.createReview(TypeReview.DISH, comment, null, mockRestaurant, taste, presentation);

    assertNull(review);
    String output = outContent.toString();
    assertTrue(output.contains("Invalid review type"));

    System.setErr(System.err);
  }
}