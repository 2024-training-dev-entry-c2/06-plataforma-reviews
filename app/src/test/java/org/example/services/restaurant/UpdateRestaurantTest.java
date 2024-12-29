package org.example.services.restaurant;

import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;
import org.example.utils.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateRestaurantTest {
	private Validator mockValidator;
	private RestaurantRepository mockRestaurantRepository;
	private SelectRestaurant mockSelectRestaurant;
	private UpdateRestaurant updateRestaurant;

	@BeforeEach
	void setUp() {
		mockValidator = mock(Validator.class);
		mockRestaurantRepository = mock(RestaurantRepository.class);
		mockSelectRestaurant = mock(SelectRestaurant.class);
		updateRestaurant = new UpdateRestaurant(mockValidator, mockRestaurantRepository, mockSelectRestaurant);
	}

	@Test
	void testExecute() {
		Menu mockMenu = mock(Menu.class);
		Restaurant restaurant = new Restaurant("Restaurante Test", "Descripcion Test", "Ubicacion Test", mockMenu);

		when(mockSelectRestaurant.execute()).thenReturn(restaurant);
		when(mockRestaurantRepository.getRestaurant(restaurant.getRestaurantId())).thenReturn(restaurant);
		when(mockValidator.readString(anyString())).thenReturn("Nuevo Nombre Test", "Nueva Descripcion Test", "Nueva Ubicacion Test");
		when(mockRestaurantRepository.updateRestaurant(restaurant)).thenReturn(true);

		Boolean result = updateRestaurant.execute();

		assertNotNull(result);
		assertTrue(result);

		assertEquals("Nuevo Nombre Test", restaurant.getName());
		assertEquals("Nueva Descripcion Test", restaurant.getDescription());
		assertEquals("Nueva Ubicacion Test", restaurant.getLocation());

		verify(mockSelectRestaurant).execute();
		verify(mockRestaurantRepository).getRestaurant(restaurant.getRestaurantId());
		verify(mockRestaurantRepository).updateRestaurant(restaurant);
		verify(mockValidator, times(3)).readString(anyString());
	}

	@Test
	void testSelectedRestaurantIsNull() {
		when(mockSelectRestaurant.execute()).thenReturn(null);

		Boolean result = updateRestaurant.execute();

		assertNotNull(result);
		assertFalse(result);

		verify(mockSelectRestaurant).execute();
	}

	@Test
	void testExistingRestaurantIsNull() {
		Menu mockMenu = mock(Menu.class);
		Restaurant restaurant = new Restaurant("Restaurante Test", "Descripcion Test", "Ubicacion Test", mockMenu);

		when(mockSelectRestaurant.execute()).thenReturn(restaurant);
		when(mockRestaurantRepository.getRestaurant(restaurant.getRestaurantId())).thenReturn(null);

		Boolean result = updateRestaurant.execute();

		assertNotNull(result);
		assertFalse(result);

		verify(mockRestaurantRepository).getRestaurant(restaurant.getRestaurantId());
	}

	@Test
	void testUpdateRestaurantDetailsWithEmptyFields() {
		Menu mockMenu = mock(Menu.class);
		Restaurant restaurant = new Restaurant("Restaurante Test", "Descripcion Test", "Ubicacion Test", mockMenu);

		when(mockSelectRestaurant.execute()).thenReturn(restaurant);
		when(mockRestaurantRepository.getRestaurant(restaurant.getRestaurantId())).thenReturn(restaurant);
		when(mockValidator.readString(anyString())).thenReturn("", "", "");
		when(mockRestaurantRepository.updateRestaurant(restaurant)).thenReturn(true);

		Boolean result = updateRestaurant.execute();

		assertNotNull(result);
		assertTrue(result);

		assertEquals("Restaurante Test", restaurant.getName());
		assertEquals("Descripcion Test", restaurant.getDescription());
		assertEquals("Ubicacion Test", restaurant.getLocation());

		verify(mockSelectRestaurant).execute();
		verify(mockRestaurantRepository).getRestaurant(restaurant.getRestaurantId());
		verify(mockRestaurantRepository).updateRestaurant(restaurant);
		verify(mockValidator, times(3)).readString(anyString());
	}
}