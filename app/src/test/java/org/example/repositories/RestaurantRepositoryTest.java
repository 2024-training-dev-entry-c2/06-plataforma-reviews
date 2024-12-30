package org.example.repositories;

import org.example.models.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RestaurantRepositoryTest {
  private RestaurantRepository restaurantRepository;
  private Restaurant restaurant1;
  private Restaurant restaurant2;

  @BeforeEach
  public void setup() {
    restaurantRepository = RestaurantRepository.getInstance();
    restaurantRepository.clear();
    restaurant1 = new Restaurant("La Pizzeria", "Calle 123", true);
    restaurant2 = new Restaurant("El Sushi", "Avenida 456", false);
  }

  @Test
  public void testAddRestaurant() {
    assertTrue(restaurantRepository.addRestaurant(restaurant1));
    assertNotNull(restaurantRepository.getRestaurantByName("La Pizzeria"));

    assertFalse(restaurantRepository.addRestaurant(restaurant1));
  }

  @Test
  public void testEditRestaurant() {
    restaurantRepository.addRestaurant(restaurant1);

    assertTrue(restaurantRepository.editRestaurant(restaurant1, "Calle 789", false));
    assertEquals("Calle 789", restaurantRepository.getRestaurantByName("La Pizzeria").getAddress());
    assertFalse(restaurantRepository.getRestaurantByName("La Pizzeria").getAvailable());

    assertFalse(restaurantRepository.editRestaurant(restaurant2, "Avenida 100", true));
  }

  @Test
  public void testRemoveRestaurant() {
    restaurantRepository.addRestaurant(restaurant1);

    assertTrue(restaurantRepository.removeRestaurant(restaurant1));
    assertNull(restaurantRepository.getRestaurantByName("La Pizzeria"));

    assertFalse(restaurantRepository.removeRestaurant(restaurant2));
  }

  @Test
  public void testGetAvailableRestaurants() {
    restaurantRepository.addRestaurant(restaurant1);
    restaurantRepository.addRestaurant(restaurant2);

    assertEquals(1, restaurantRepository.getAvailableRestaurants().size());
    assertTrue(restaurantRepository.getAvailableRestaurants().contains(restaurant1));
    assertFalse(restaurantRepository.getAvailableRestaurants().contains(restaurant2));
  }

  @Test
  public void testSingletonInstance() {
    RestaurantRepository anotherRepository = RestaurantRepository.getInstance();
    assertSame(restaurantRepository, anotherRepository);
  }
}