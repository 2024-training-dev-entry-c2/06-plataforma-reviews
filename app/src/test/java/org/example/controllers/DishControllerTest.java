package org.example.controllers;

import org.example.services.DishService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DishControllerTest {

  private DishController dishController;
  private DishService mockDishService;

  @BeforeEach
  void setup() throws Exception {
    mockDishService = mock(DishService.class);
    dishController = new DishController();

    Field dishServiceField = DishController.class.getDeclaredField("dishService");
    dishServiceField.setAccessible(true);
    dishServiceField.set(dishController, mockDishService);
  }

  @Test
  @DisplayName("Test Create Dish")
  void testCreateDish() {
    dishController.createDish("Dish 1", "Description", 10.0);
    verify(mockDishService).createDish("Dish 1", "Description", 10.0);
  }

  @Test
  @DisplayName("Test Get Average Rating Of Dish")
  void testGetAverageRatingOfDish() {
    when(mockDishService.getAverageRatingOfDish("Dish 1")).thenReturn(4.5);
    Double rating = dishController.getAverageRatingOfDish("Dish 1");
    assertEquals(4.5, rating);
  }
}