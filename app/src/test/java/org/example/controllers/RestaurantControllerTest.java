package org.example.controllers;

import org.example.models.RestaurantModel;
import org.example.services.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantControllerTest {

  private RestaurantController restaurantController;
  private RestaurantService mockRestaurantService;

  @BeforeEach
  void setup() throws Exception {
    mockRestaurantService = mock(RestaurantService.class);
    restaurantController = new RestaurantController(); // Constructor without arguments


    Field restaurantServiceField = RestaurantController.class.getDeclaredField("restaurantService");
    restaurantServiceField.setAccessible(true);
    restaurantServiceField.set(restaurantController, mockRestaurantService);
  }

  @Test
  @DisplayName("Test Create Restaurant")
  void testCreateRestaurant() {
    restaurantController.createRestaurant("Restaurant 1", "Address", true);
    verify(mockRestaurantService).createRestaurant("Restaurant 1", "Address", true);
  }

  @Test
  @DisplayName("Test Get All Restaurants")
  void testGetAllRestaurants() {
    List<RestaurantModel> restaurants = List.of(new RestaurantModel("Restaurant 1", "Address", true));
    when(mockRestaurantService.getAllRestaurants()).thenReturn(restaurants);
    List<RestaurantModel> result = restaurantController.getAllRestaurants();
    assertEquals(restaurants, result);
  }

  @Test
  @DisplayName("Test Update Restaurant")
  void testUpdateRestaurant() {
    restaurantController.updateRestaurant("Restaurant 1", "New Address", false);
    verify(mockRestaurantService).updateRestaurant("Restaurant 1", "New Address", false);
  }

  @Test
  @DisplayName("Test Delete Restaurant")
  void testDeleteRestaurant() {
    restaurantController.deleteRestaurant("Restaurant 1");
    verify(mockRestaurantService).deleteRestaurant("Restaurant 1");
  }

  @Test
  @DisplayName("Test Get Average Rating Of Restaurant")
  void testGetAverageRatingOfRestaurant() {
    when(mockRestaurantService.getAverageRatingOfRestaurant("Restaurant 1")).thenReturn(4.5);
    Double rating = restaurantController.getAverageRatingOfRestaurant("Restaurant 1");
    assertEquals(4.5, rating);
  }
}