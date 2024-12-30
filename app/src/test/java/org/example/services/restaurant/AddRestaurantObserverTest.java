package org.example.services.restaurant;

import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.models.UserObserver;
import org.example.utils.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AddRestaurantObserverTest {
	private Validator mockValidator;
	private AddRestaurantObserver addRestaurantObserver;

	@BeforeEach
	void setUp() {
		mockValidator = mock(Validator.class);
		addRestaurantObserver = new AddRestaurantObserver(mockValidator);
	}

	@Test
	void execute() {
		Restaurant restaurant = new Restaurant();
		when(mockValidator.readString(anyString())).thenReturn("S", "Usuario Test");

		Boolean result = addRestaurantObserver.execute(restaurant);

		assertTrue(result);
		assertEquals( 1, restaurant.getObservers().size());

		verify(mockValidator, times(2)).readString(anyString());
	}

	@Test
	void executeWithNullRestaurant() {
		Boolean result = addRestaurantObserver.execute(null);

		assertFalse(result);
	}

	@Test
	void executeWithNoUserObserver() {
		Restaurant restaurant = new Restaurant("Restaurante Test", "Descripcion Test", "Ubicacion Test", null);
		when(mockValidator.readString(anyString())).thenReturn("N");

		Boolean result = addRestaurantObserver.execute(restaurant);

		assertFalse(result);
	}

	@Test
	void executeWithNullMenu() {
		Restaurant restaurant = new Restaurant("Restaurante Test", "Descripcion Test", "Ubicacion Test", null);
		when(mockValidator.readString(anyString())).thenReturn("S", "Usuario Test");

		Boolean result = addRestaurantObserver.execute(restaurant);

		assertTrue(result);
		assertEquals( 1, restaurant.getObservers().size());
		assertNull(restaurant.getMenu());
		verify(mockValidator, times(2)).readString(anyString());
	}

	@Test
	void executeWithEmptyDishes() {
		Restaurant restaurant = mock(Restaurant.class);
		when(mockValidator.readString(anyString())).thenReturn("S", "Usuario Test");
		when(restaurant.getMenu()).thenReturn(mock(Menu.class));
		when(restaurant.getMenu().getDishes()).thenReturn(new HashSet<>());

		Boolean result = addRestaurantObserver.execute(restaurant);

		assertTrue(result);
		verify(restaurant).addObserver(any(UserObserver.class));
	}

	@Test
	void executeWithMenuButNoDishes() {
		Restaurant restaurant = mock(Restaurant.class);
		Menu menu = mock(Menu.class);
		when(mockValidator.readString(anyString())).thenReturn("S", "Usuario Test");
		when(restaurant.getMenu()).thenReturn(menu);
		when(menu.getDishes()).thenReturn(null);

		Boolean result = addRestaurantObserver.execute(restaurant);

		assertTrue(result);
		verify(restaurant).addObserver(any(UserObserver.class));
	}
}