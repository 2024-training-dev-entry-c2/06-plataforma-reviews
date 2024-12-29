package org.example.services.menu;

import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.services.restaurant.SelectRestaurant;
import org.example.utils.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AddDishTest {
	private Validator mockValidator;
	private SelectRestaurant mockSelectRestaurant;
	private AddDish addDish;

	@BeforeEach
	void setUp() {
		this.mockValidator = mock(Validator.class);
		this.mockSelectRestaurant = mock(SelectRestaurant.class);
		this.addDish = new AddDish(mockValidator, mockSelectRestaurant);
	}

	@Test
	void executeWithNullRestaurant() {
		when(mockSelectRestaurant.execute()).thenReturn(null);

		Menu result = addDish.execute();

		assertNull(result);
		verify(mockSelectRestaurant).execute();
	}

	@Test
	void executeWithNullMenu() {
		Restaurant restaurant = new Restaurant("Name", "Description", "Location", null);
		when(mockSelectRestaurant.execute()).thenReturn(restaurant);
		when(mockValidator.readString(anyString()))
			.thenReturn(
				"Menu test",
				"Description test",
				"Bandeja paisa",
				"Bandeja paisa description",
				"n"
			);
		when(mockValidator.readFloat(anyString())).thenReturn(10.0f);

		Menu result = addDish.execute();

		assertNotNull(result);
		assertEquals("Menu test", result.getName());
		assertEquals("Description test", result.getDescription());
		assertEquals(1, result.getDishes().size());
		assertTrue(result.getDishes().stream().anyMatch(dish -> dish.getName().equals("Bandeja paisa")));
		assertTrue(result.getDishes().stream().anyMatch(dish -> dish.getDescription().equals("Bandeja paisa description")));
		assertTrue(result.getDishes().stream().anyMatch(dish -> dish.getPrice() == 10.0f));

		verify(mockSelectRestaurant).execute();
		verify(mockValidator, times(5)).readString(anyString());
		verify(mockValidator).readFloat(anyString());
	}

	@Test
	void executeWithMenu() {
		Restaurant restaurant = new Restaurant(
			"Restaurante Test",
			"Descripcion Test",
			"Ubicacion Test",
			new Menu("Menu Test", "Descripcion Test", new HashSet<>())
		);

		when(mockSelectRestaurant.execute()).thenReturn(restaurant);
		when(mockValidator.readString(anyString()))
			.thenReturn(
				"Bandeja paisa",
				"Bandeja paisa description",
				"s",
				"Bandeja paisa 2",
				"Bandeja paisa description 2",
				"n"
			);

		when(mockValidator.readFloat(anyString())).thenReturn(10.0f);
		when(mockValidator.readFloat(anyString())).thenReturn(11.0f);

		Menu result = addDish.execute();

		assertNotNull(result);
		assertEquals("Menu Test", result.getName());
		assertEquals("Descripcion Test", result.getDescription());
		assertEquals(2, result.getDishes().size());
		assertTrue(result.getDishes().stream().anyMatch(dish -> dish.getName().equals("Bandeja paisa 2")));
		assertTrue(result.getDishes().stream().anyMatch(dish -> dish.getDescription().equals("Bandeja paisa description 2")));
		assertTrue(result.getDishes().stream().anyMatch(dish -> dish.getPrice() == 11.0f));

		verify(mockSelectRestaurant).execute();
		verify(mockValidator, times(6)).readString(anyString());
		verify(mockValidator, times(2)).readFloat(anyString());
	}
}