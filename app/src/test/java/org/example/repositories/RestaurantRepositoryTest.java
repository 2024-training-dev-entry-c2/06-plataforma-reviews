package org.example.repositories;

import org.example.models.Menu;
import org.example.models.Restaurant;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class RestaurantRepositoryTest {
	private RestaurantRepository restaurantRepository = RestaurantRepository.getInstance();

	@Test
	void updateRestaurant() {
		restaurantRepository = RestaurantRepository.getInstance();
		Restaurant restaurant = mock(Restaurant.class);
		restaurantRepository.addRestaurant(restaurant);

		Restaurant updatedRestaurant = new Restaurant("Restaurante Test", "Descripcion Test", "Ubicacion Test", null);

		assertFalse(restaurantRepository.updateRestaurant(updatedRestaurant));

		updatedRestaurant.setName("Restaurante Test 2");
		updatedRestaurant.setDescription("Descripcion Test 2");
		updatedRestaurant.setLocation("Ubicacion Test 2");

		assertTrue(restaurantRepository.updateRestaurant(restaurant));
	}

	@Test
	void deleteRestaurant() {
		Menu mockMenu = mock(Menu.class);
		Restaurant restaurant = new Restaurant("Restaurante Test", "Descripcion Test", "Ubicacion Test", mockMenu);
		restaurantRepository.addRestaurant(restaurant);

		assertTrue(restaurantRepository.deleteRestaurant(restaurant.getRestaurantId()));
		assertFalse(restaurantRepository.deleteRestaurant(restaurant.getRestaurantId()));
	}

	@Test
	void getAllRestaurants() {
		Menu mockMenu = mock(Menu.class);
		Restaurant restaurant = new Restaurant("Restaurante Test", "Descripcion Test", "Ubicacion Test", mockMenu);
		restaurantRepository.addRestaurant(restaurant);

		assertEquals(restaurant, restaurantRepository.getAllRestaurants().get(restaurant.getRestaurantId()));
	}
}