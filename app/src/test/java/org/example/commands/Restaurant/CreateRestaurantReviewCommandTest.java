package org.example.commands.Restaurant;

import org.example.commands.Restaurants.CreateRestaurantReviewCommand;
import org.example.models.RestaurantModel;
import org.example.models.RestaurantReviewModel;
import org.example.controllers.RestaurantReviewController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CreateRestaurantReviewCommandTest {

  private RestaurantModel mockRestaurant;
  private RestaurantReviewController mockReviewController;

  @BeforeEach
  void setup() {
    mockRestaurant = mock(RestaurantModel.class);
    mockReviewController = mock(RestaurantReviewController.class);
    when(mockRestaurant.getName()).thenReturn("Restaurante 1");
    doNothing().when(mockReviewController).addReviewToRestaurant(anyString(), anyString(), anyDouble(), anyString());
    // Ensure the restaurant exists in the mock controller
    doAnswer(invocation -> {
      String restaurantName = invocation.getArgument(0);
      if (!"Restaurante 1".equals(restaurantName)) {
        throw new IllegalArgumentException("Restaurante no encontrado: " + restaurantName);
      }
      return null;
    }).when(mockReviewController).addReviewToRestaurant(anyString(), anyString(), anyDouble(), anyString());
  }

  @Test
  @DisplayName("Test Create Restaurant Review Command")
  void testCreateRestaurantReview() {
    String reviewerName = "Juan Pérez";
    Double rating = 4.5;
    String comment = "Excelente comida y servicio.";

    CreateRestaurantReviewCommand command = new CreateRestaurantReviewCommand(mockRestaurant, reviewerName, rating, comment);

    try {
      java.lang.reflect.Field field = CreateRestaurantReviewCommand.class.getDeclaredField("restaurantReviewController");
      field.setAccessible(true);
      field.set(command, mockReviewController);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }

    command.execute();

    ArgumentCaptor<RestaurantReviewModel> reviewCaptor = ArgumentCaptor.forClass(RestaurantReviewModel.class);
    verify(mockRestaurant).addReview(reviewCaptor.capture());
    RestaurantReviewModel capturedReview = reviewCaptor.getValue();

    assertEquals(reviewerName, capturedReview.getReviewerName());
    assertEquals(rating, capturedReview.getRating());
    assertEquals(comment, capturedReview.getComment());
    assertEquals(mockRestaurant, capturedReview.getRestaurant());

    verify(mockReviewController).addReviewToRestaurant(
      mockRestaurant.getName(),
      capturedReview.getReviewerName(),
      capturedReview.getRating(),
      capturedReview.getComment()
    );
  }
}