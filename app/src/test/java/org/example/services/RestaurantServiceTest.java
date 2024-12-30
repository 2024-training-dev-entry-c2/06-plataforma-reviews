package org.example.services;

import org.example.models.RestaurantModel;
import org.example.repositories.RestaurantRepository;
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
  private RestaurantRepository mockRepository;

  @BeforeEach
  void setup() throws Exception {
    mockRepository = mock(RestaurantRepository.class);
    setMockInstance(mockRepository);
    restaurantService = new RestaurantService();
  }

  private void setMockInstance(RestaurantRepository mockRepository) throws Exception {
    Field instanceField = RestaurantRepository.class.getDeclaredField("instance");
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

  @Test
  @DisplayName("Test Create Existing Restaurant")
  void testCreateExistingRestaurant() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    when(mockRepository.getRestaurant("Restaurante 1")).thenReturn(restaurant);
    assertThrows(IllegalArgumentException.class, () -> restaurantService.createRestaurant("Restaurante 1", "Calle Ficticia 123", true));
  }

  @Test
  @DisplayName("Test Update Nonexistent Restaurant")
  void testUpdateNonexistentRestaurant() {
    when(mockRepository.getRestaurant("Nonexistent Restaurant")).thenReturn(null);
    assertThrows(IllegalArgumentException.class, () -> restaurantService.updateRestaurant("Nonexistent Restaurant", "Calle Nueva 789", false));
  }

  @Test
  @DisplayName("Test Delete Nonexistent Restaurant")
  void testDeleteNonexistentRestaurant() {
    when(mockRepository.getRestaurant("Nonexistent Restaurant")).thenReturn(null);
    assertThrows(IllegalArgumentException.class, () -> restaurantService.deleteRestaurant("Nonexistent Restaurant"));
  }

  @Test
  @DisplayName("Test Get Average Rating Of Nonexistent Restaurant")
  void testGetAverageRatingOfNonexistentRestaurant() {
    when(mockRepository.getRestaurant("Nonexistent Restaurant")).thenReturn(null);
    assertThrows(IllegalArgumentException.class, () -> restaurantService.getAverageRatingOfRestaurant("Nonexistent Restaurant"));
  }

  @Test
  @DisplayName("Test Observer Update With Non-Restaurant Message")
  void testObserverUpdateWithNonRestaurantMessage() {
    restaurantService.update("Some other message");

  }
}