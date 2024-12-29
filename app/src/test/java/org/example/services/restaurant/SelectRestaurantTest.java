package org.example.services.restaurant;

import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;
import org.example.utils.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SelectRestaurantTest {
	private Validator mockValidator;
	private RestaurantRepository mockRestaurantRepository;
	private SelectRestaurant selectRestaurant;

	@BeforeEach
	void setUp() {
		mockValidator = mock(Validator.class);
		mockRestaurantRepository = mock(RestaurantRepository.class);
		selectRestaurant = new SelectRestaurant(mockValidator, mockRestaurantRepository);
	}

	@Test
	void testExecuteValidSelection() {
		Menu menu = mock(Menu.class);

		Restaurant restaurant1 = new Restaurant("Restaurante1", "Descripción1", "Ubicación1", menu);
		Restaurant restaurant2 = new Restaurant("Restaurante2", "Descripción2", "Ubicación2", menu);
		Restaurant restaurant3 = new Restaurant("Restaurante3", "Descripción3", "Ubicación3", menu);

		when(mockRestaurantRepository.getAllRestaurants()).thenReturn(Map.of(1, restaurant1, 2, restaurant2, 3, restaurant3));
		when(mockValidator.readInteger(anyString())).thenReturn(1);

		Restaurant result = selectRestaurant.execute();

		assertNotNull(result);
		assertEquals(restaurant1.getName(), result.getName());
		assertEquals(restaurant1.getDescription(), result.getDescription());
		assertEquals(restaurant1.getLocation(), result.getLocation());

		verify(mockValidator).readInteger(anyString());
		verify(mockRestaurantRepository).getAllRestaurants();
	}

	@Test
	void testExecuteNoRestaurants() {
		when(mockRestaurantRepository.getAllRestaurants()).thenReturn(Map.of());

		Restaurant result = selectRestaurant.execute();

		assertNull(result);

		verify(mockRestaurantRepository).getAllRestaurants();
		verify(mockValidator, never()).readInteger(anyString());
	}

	@Test
	void testExecuteInvalidIndexBelowRange() {
		Menu menu = mock(Menu.class);

		Restaurant restaurant1 = new Restaurant("Restaurante1", "Descripción1", "Ubicación1", menu);
		Restaurant restaurant2 = new Restaurant("Restaurante2", "Descripción2", "Ubicación2", menu);

		when(mockRestaurantRepository.getAllRestaurants()).thenReturn(Map.of(1, restaurant1, 2, restaurant2));
		when(mockValidator.readInteger(anyString())).thenReturn(0, 1);

		Restaurant result = selectRestaurant.execute();

		assertNotNull(result);
		assertEquals(restaurant1.getName(), result.getName());

		verify(mockValidator, times(2)).readInteger(anyString());
		verify(mockRestaurantRepository).getAllRestaurants();
	}

	@Test
	void testExecuteInvalidIndexAboveRange() {
		Menu menu = mock(Menu.class);

		Restaurant restaurant1 = new Restaurant("Restaurante1", "Descripción1", "Ubicación1", menu);
		Restaurant restaurant2 = new Restaurant("Restaurante2", "Descripción2", "Ubicación2", menu);

		when(mockRestaurantRepository.getAllRestaurants()).thenReturn(Map.of(1, restaurant1, 2, restaurant2));
		when(mockValidator.readInteger(anyString())).thenReturn(3, 2);

		Restaurant result = selectRestaurant.execute();

		assertNotNull(result);
		assertEquals(restaurant2.getName(), result.getName());

		verify(mockValidator, times(2)).readInteger(anyString());
		verify(mockRestaurantRepository).getAllRestaurants();
	}
}