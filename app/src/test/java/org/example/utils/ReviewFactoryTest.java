package org.example.utils;

import org.example.Interface.ICommand;
import org.example.commands.Menu.CreateDishReviewCommand;
import org.example.commands.Restaurants.CreateRestaurantReviewCommand;
import org.example.models.DishModel;
import org.example.models.RestaurantModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReviewFactoryTest {

  private ReviewFactory reviewFactory;
  private ReviewInvoker mockInvoker;

  @BeforeEach
  void setup() {
    mockInvoker = mock(ReviewInvoker.class);
    reviewFactory = new ReviewFactory();
    reviewFactory.invoker = mockInvoker;
  }

  @Test
  @DisplayName("Test Create Restaurant Review")
  void testCreateRestaurantReview() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    String reviewerName = "Juan Pérez";
    Double rating = 4.5;
    String comment = "Excelente comida y servicio.";

    reviewFactory.createReview("Restaurant", reviewerName, rating, comment, restaurant);

    ArgumentCaptor<ICommand> commandCaptor = ArgumentCaptor.forClass(ICommand.class);
    verify(mockInvoker).setCommand(commandCaptor.capture());
    verify(mockInvoker).executeCommand();

    ICommand capturedCommand = commandCaptor.getValue();
    assertTrue(capturedCommand instanceof CreateRestaurantReviewCommand);
  }

  @Test
  @DisplayName("Test Create Dish Review")
  void testCreateDishReview() {
    DishModel dish = new DishModel("Plato 1", "Descripción del plato", 10.0);
    String reviewerName = "Juan Pérez";
    Double rating = 4.5;
    String comment = "Excelente comida y servicio.";

    reviewFactory.createReview("Dish", reviewerName, rating, comment, dish);

    ArgumentCaptor<ICommand> commandCaptor = ArgumentCaptor.forClass(ICommand.class);
    verify(mockInvoker).setCommand(commandCaptor.capture());
    verify(mockInvoker).executeCommand();

    ICommand capturedCommand = commandCaptor.getValue();
    assertTrue(capturedCommand instanceof CreateDishReviewCommand);
  }

  @Test
  @DisplayName("Test Create Review with Invalid Type")
  void testCreateReviewWithInvalidType() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      reviewFactory.createReview("InvalidType", "Juan Pérez", 4.5, "Comentario", new Object());
    });

    assertEquals("Error en la creación de la review", exception.getMessage());
  }

  @Test
  @DisplayName("Test Create Review with Null Target")
  void testCreateReviewWithNullTarget() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      reviewFactory.createReview("Restaurant", "Juan Pérez", 4.5, "Comentario", null);
    });

    assertEquals("Error en la creación de la review", exception.getMessage());
  }

  @Test
  @DisplayName("Test Create Review with Null Review Type")
  void testCreateReviewWithNullReviewType() {
    DishModel dish = new DishModel("Plato 1", "Descripción del plato", 10.0);
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      reviewFactory.createReview(null, "Juan Pérez", 4.5, "Comentario", dish);
    });

    assertEquals("Error en la creación de la review", exception.getMessage());
  }

  @Test
  @DisplayName("Test Create Review with Null Reviewer Name")
  void testCreateReviewWithNullReviewerName() {
    DishModel dish = new DishModel("Plato 1", "Descripción del plato", 10.0);
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      reviewFactory.createReview("Dish", null, 4.5, "Comentario", dish);
    });

    assertEquals("Error en la creación de la review", exception.getMessage());
  }

  @Test
  @DisplayName("Test Create Review with Null Rating")
  void testCreateReviewWithNullRating() {
    DishModel dish = new DishModel("Plato 1", "Descripción del plato", 10.0);
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      reviewFactory.createReview("Dish", "Juan Pérez", null, "Comentario", dish);
    });

    assertEquals("Error en la creación de la review", exception.getMessage());
  }

  @Test
  @DisplayName("Test Create Review with Null Comment")
  void testCreateReviewWithNullComment() {
    DishModel dish = new DishModel("Plato 1", "Descripción del plato", 10.0);
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      reviewFactory.createReview("Dish", "Juan Pérez", 4.5, null, dish);
    });

    assertEquals("Error en la creación de la review", exception.getMessage());
  }

  @Test
  @DisplayName("Test Create Review with Invalid Target Type")
  void testCreateReviewWithInvalidTargetType() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      reviewFactory.createReview("Restaurant", "Juan Pérez", 4.5, "Comentario", new Object());
    });

    assertEquals("Error en la creación de la review", exception.getMessage());
  }
}