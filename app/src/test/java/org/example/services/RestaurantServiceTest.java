package org.example.services;

import org.example.models.RestaurantModel;
import org.example.repositories.DataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantServiceTest {

  private RestaurantService restaurantService;
  private DataRepository mockRepository;

  @BeforeEach
  void setup() throws Exception {
    mockRepository = mock(DataRepository.class);
    setMockInstance(mockRepository);
    restaurantService = new RestaurantService();
  }

  private void setMockInstance(DataRepository mockRepository) throws Exception {
    Field instanceField = DataRepository.class.getDeclaredField("instance");
    instanceField.setAccessible(true);
    instanceField.set(null, mockRepository);
  }

  @Test
  @DisplayName("Test Create Restaurant")
  void testCreateRestaurant() {
    when(mockRepository.getRestaurant("Restaurante 1")).thenReturn(null);

    restaurantService.createRestaurant("Restaurante 1", "Calle Ficticia 123", true);

    verify(mockRepository).addRestaurant(argThat(restaurant ->
      "Restaurante 1".equals(restaurant.getName()) &&
        "Calle Ficticia 123".equals(restaurant.getAddress()) &&
        restaurant.isAvailable()
    ));
  }

  @Test
  @DisplayName("Test Get All Restaurants")
  void testGetAllRestaurants() {
    RestaurantModel restaurant1 = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    RestaurantModel restaurant2 = new RestaurantModel("Restaurante 2", "Calle Real 456", false);
    List<RestaurantModel> restaurants = Arrays.asList(restaurant1, restaurant2);

    when(mockRepository.getAllRestaurants()).thenReturn(restaurants);

    List<RestaurantModel> result = restaurantService.getAllRestaurants();

    assertEquals(2, result.size());
    assertTrue(result.contains(restaurant1));
    assertTrue(result.contains(restaurant2));
  }

  @Test
  @DisplayName("Test Update Restaurant")
  void testUpdateRestaurant() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);

    when(mockRepository.getRestaurant("Restaurante 1")).thenReturn(restaurant);

    restaurantService.updateRestaurant("Restaurante 1", "Calle Nueva 789", false);

    assertEquals("Calle Nueva 789", restaurant.getAddress());
    assertFalse(restaurant.isAvailable());
    verify(mockRepository).updateRestaurant(restaurant);
  }

  @Test
  @DisplayName("Test Delete Restaurant")
  void testDeleteRestaurant() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);

    when(mockRepository.getRestaurant("Restaurante 1")).thenReturn(restaurant);

    restaurantService.deleteRestaurant("Restaurante 1");

    verify(mockRepository).removeRestaurant("Restaurante 1");
  }

  @Test
  @DisplayName("Test Get Average Rating Of Restaurant")
  void testGetAverageRatingOfRestaurant() {
    when(mockRepository.getRestaurant("Restaurante 1")).thenReturn(new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true));
    when(mockRepository.calculateAverageRatingRestaurant("Restaurante 1")).thenReturn(4.5);

    Double averageRating = restaurantService.getAverageRatingOfRestaurant("Restaurante 1");

    assertEquals(4.5, averageRating);
  }

  @Test
  @DisplayName("Test Observer Update")
  void testObserverUpdate() {
    restaurantService.update("Nuevo restaurante agregado: Restaurante 1");
  }
}