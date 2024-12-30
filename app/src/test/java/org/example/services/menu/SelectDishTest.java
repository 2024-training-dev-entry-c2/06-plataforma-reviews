package org.example.services.menu;

import org.example.models.Dish;
import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.services.restaurant.SelectRestaurant;
import org.example.utils.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SelectDishTest {
	private Validator mockValidator;
	private SelectRestaurant mockSelectRestaurant;
	private SelectDish selectDish;


	@BeforeEach
	void setUp() {
		mockValidator = mock(Validator.class);
		mockSelectRestaurant = mock(SelectRestaurant.class);
		selectDish = new SelectDish(mockValidator, mockSelectRestaurant);
	}

	@Test
	void executeWithNullRestaurant() {
		when(mockSelectRestaurant.execute()).thenReturn(null);

		Dish result = selectDish.execute();

		assertNull(result);
	}

	@Test
	void executeWithDishesIsEmpty() {
		Menu menu = mock(Menu.class);
		Restaurant restaurant = new Restaurant(
			"Restaurante Test",
			"Descripcion Test",
			"Ubicacion Test",
			menu
		);

		when(mockSelectRestaurant.execute()).thenReturn(restaurant);
		when(restaurant.getMenu().getDishes()).thenReturn(new HashSet<>());

		Dish result = selectDish.execute();

		assertNull(result);
	}

	@Test
	void executeWithRestaurant() {
		Set<Dish> dishes = new LinkedHashSet<>();
		dishes.add(new Dish("Bandeja paisa", "Bandeja paisa description", 10.0f));
		dishes.add(new Dish("Bandeja paisa 2", "Bandeja paisa description 2", 15.0f));
		Menu menu = new Menu("Menu Test", "Descripcion Test", dishes);
		Restaurant restaurant = new Restaurant("Restaurante Test", "Descripcion Test", "Ubicacion Test", menu);

		when(mockSelectRestaurant.execute()).thenReturn(restaurant);
		when(mockValidator.readInteger(anyString())).thenReturn(1);

		Dish result = selectDish.execute();

		assertNotNull(result);
		assertEquals("Bandeja paisa", result.getName());
		assertEquals("Bandeja paisa description", result.getDescription());
		assertEquals(10.0f, result.getPrice());

		verify(mockSelectRestaurant).execute();
		verify(mockValidator).readInteger(anyString());
	}

	@Test
	void testExecuteInvalidIndexBelowRange() {
		Dish dish1 = new Dish("Bandeja paisa", "Bandeja paisa description", 10.0f);
		Dish dish2 = new Dish("Bandeja paisa 2", "Bandeja paisa description 2", 15.0f);
		Set<Dish> dishes = new LinkedHashSet<>();
		dishes.add(dish1);
		dishes.add(dish2);
		Menu menu = new Menu("Menu Test", "Descripcion Test", dishes);
		Restaurant restaurant = new Restaurant("Restaurante Test", "Descripcion Test", "Ubicacion Test", menu);

		when(mockSelectRestaurant.execute()).thenReturn(restaurant);
		when(mockValidator.readInteger(anyString())).thenReturn(0, 1);

		Dish result = selectDish.execute();

		assertNotNull(result);
		assertEquals("Bandeja paisa", result.getName());
		assertEquals("Bandeja paisa description", result.getDescription());
		assertEquals(10.0f, result.getPrice());

		verify(mockSelectRestaurant).execute();
		verify(mockValidator, times(2)).readInteger(anyString());
	}

	@Test
	void testExecuteInvalidIndexAboveRange() {
		Dish dish1 = new Dish("Bandeja paisa", "Bandeja paisa description", 10.0f);
		Dish dish2 = new Dish("Bandeja paisa 2", "Bandeja paisa description 2", 15.0f);
		Set<Dish> dishes = new LinkedHashSet<>();
		dishes.add(dish1);
		dishes.add(dish2);
		Menu menu = new Menu("Menu Test", "Descripcion Test", dishes);
		Restaurant restaurant = new Restaurant("Restaurante Test", "Descripcion Test", "Ubicacion Test", menu);

		when(mockSelectRestaurant.execute()).thenReturn(restaurant);
		when(mockValidator.readInteger(anyString())).thenReturn(3, 1);

		Dish result = selectDish.execute();

		assertNotNull(result);
		assertEquals("Bandeja paisa", result.getName());
		assertEquals("Bandeja paisa description", result.getDescription());
		assertEquals(10.0f, result.getPrice());

		verify(mockSelectRestaurant).execute();
		verify(mockValidator, times(2)).readInteger(anyString());
	}
}