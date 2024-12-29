package org.example.services.restaurant;

import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ListRestaurantTest {
	private RestaurantRepository mockRestaurantRepository;
	private ListRestaurant listRestaurant;

	@BeforeEach
	void setUp() {
		mockRestaurantRepository = mock(RestaurantRepository.class);
		listRestaurant = new ListRestaurant(mockRestaurantRepository);
	}

	@Test
	void testExecute() {
		Map<Integer, Restaurant> restaurants = new HashMap<>();
		restaurants.put(1, new Restaurant( "Restaurante 1", "Descripción 1", "Ubicación 1", null));
		restaurants.put(2, new Restaurant( "Restaurante 2", "Descripción 2", "Ubicación 2", null));
		restaurants.put(3, new Restaurant( "Restaurante 3", "Descripción 3", "Ubicación 3", null));

		when(mockRestaurantRepository.getAllRestaurants()).thenReturn(restaurants);

		List<Restaurant> result = listRestaurant.execute();

		assertNotNull(result);
		assertEquals(restaurants.size(), result.size());
		assertEquals(restaurants.get(1).getName(), result.get(0).getName());
		assertEquals(restaurants.get(2).getName(), result.get(1).getName());
		assertEquals(restaurants.get(3).getName(), result.get(2).getName());

		verify(mockRestaurantRepository).getAllRestaurants();
	}
}