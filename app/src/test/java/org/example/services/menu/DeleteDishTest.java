package org.example.services.menu;

import org.example.models.Dish;
import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeleteDishTest {
	private SelectDish selectDish;
	private RestaurantRepository restaurantRepository;
	private DeleteDish deleteDish;

	@BeforeEach
	void setUp() {
		selectDish = mock(SelectDish.class);
		restaurantRepository = mock(RestaurantRepository.class);
		deleteDish = new DeleteDish(selectDish, restaurantRepository);
	}

	@Test
	void executeWithNullDish() {
		when(selectDish.execute()).thenReturn(null);

		Boolean result = deleteDish.execute();

		assertFalse(result);
	}

	@Test
	void executeWithDish() {
		Dish dish = new Dish("Bandeja paisa", "Bandeja paisa description", 10.0f);
		Set<Dish> dishes = new HashSet<>();
		dishes.add(dish);

		Menu menu = new Menu("Menu Test", "Descripcion Test", dishes);
		Restaurant restaurant = new Restaurant("Restaurante Test", "Descripcion Test", "Ubicacion Test", menu);

		when(selectDish.execute()).thenReturn(dish);
		when(restaurantRepository.getAllRestaurants()).thenReturn(Map.of(1, restaurant));

		Boolean result = deleteDish.execute();

		assertTrue(result);
		assertEquals(0, restaurant.getMenu().getDishes().size());

		verify(selectDish).execute();
		verify(restaurantRepository).getAllRestaurants();
	}
}