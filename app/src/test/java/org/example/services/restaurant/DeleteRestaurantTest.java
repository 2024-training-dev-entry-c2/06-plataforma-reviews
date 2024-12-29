package org.example.services.restaurant;

import org.example.models.Dish;
import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeleteRestaurantTest {
	private RestaurantRepository mockRestaurantRepository;
	private SelectRestaurant mockSelectRestaurant;
	private DeleteRestaurant deleteRestaurant;

	@BeforeEach
	void setUp() {
		mockRestaurantRepository = mock(RestaurantRepository.class);
		mockSelectRestaurant = mock(SelectRestaurant.class);
		deleteRestaurant = new DeleteRestaurant(mockRestaurantRepository, mockSelectRestaurant);
	}

	@Test
	void testReturnsTrueOnSuccess() {
		Set<Dish> dishes = new HashSet<>();
		dishes.add(new Dish("Dish Test", "Descripción Test", 10.0f));
		Menu menu = new Menu("Menu Test", "Descripción Test", dishes);
		Restaurant restaurant = new Restaurant("Restaurante Test", "Descripción Test", "Ubicación Test", menu);

		when(mockSelectRestaurant.execute()).thenReturn(restaurant);
		when(mockRestaurantRepository.deleteRestaurant(restaurant.getRestaurantId())).thenReturn(true);

		Boolean result = deleteRestaurant.execute();

		assertNotNull(result);
		assertTrue(result);

		verify(mockRestaurantRepository).deleteRestaurant(restaurant.getRestaurantId());
		verify(mockSelectRestaurant).execute();
	}

	@Test
	void testReturnsFalseOnFailure() {
		Set<Dish> dishes = new HashSet<>();
		dishes.add(new Dish("Dish Test", "Descripción Test", 10.0f));
		Menu menu = new Menu("Menu Test", "Descripción Test", dishes);
		Restaurant restaurant = new Restaurant("Restaurante Test", "Descripción Test", "Ubicación Test", menu);

		when(mockSelectRestaurant.execute()).thenReturn(restaurant);
		when(mockRestaurantRepository.deleteRestaurant(restaurant.getRestaurantId())).thenReturn(false);

		Boolean result = deleteRestaurant.execute();

		assertNotNull(result);
		assertFalse(result);

		verify(mockRestaurantRepository).deleteRestaurant(restaurant.getRestaurantId());
		verify(mockSelectRestaurant).execute();
	}

	@Test
	void testReturnsFalseIfNoneSelected() {
		when(mockSelectRestaurant.execute()).thenReturn(null);

		Boolean result = deleteRestaurant.execute();

		assertNotNull(result);
		assertFalse(result);
		verify(mockSelectRestaurant).execute();
		verify(mockRestaurantRepository, never()).deleteRestaurant(anyInt());
	}
}