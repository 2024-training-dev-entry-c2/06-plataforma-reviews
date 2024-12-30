package org.example.services.menu;

import org.example.models.Dish;
import org.example.utils.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateDishTest {
	private Validator mockValidator;
	private SelectDish mockSelectDish;
	private UpdateDish updateDish;

	@BeforeEach
	void setUp() {
		mockValidator = mock(Validator.class);
		mockSelectDish = mock(SelectDish.class);
		updateDish = new UpdateDish(mockValidator, mockSelectDish);
	}

	@Test
	void executeWithNullDish() {
		when(mockSelectDish.execute()).thenReturn(null);

		Boolean result = updateDish.execute();

		assertFalse(result);
	}

	@Test
	void executeWithDish() {
		Dish dish = new Dish("Bandeja paisa", "Bandeja paisa description", 10.0f);

		when(mockSelectDish.execute()).thenReturn(dish);
		when(mockValidator.readString(anyString())).thenReturn("Bandeja paisa 2", "Bandeja paisa description 2");
		when(mockValidator.readFloat(anyString())).thenReturn(15.0f);

		Boolean result = updateDish.execute();

		assertNotNull(result);
		assertTrue(result);

		assertEquals("Bandeja paisa 2", dish.getName());
		assertEquals("Bandeja paisa description 2", dish.getDescription());
		assertEquals(15.0f, dish.getPrice());

		verify(mockSelectDish).execute();
		verify(mockValidator, times(2)).readString(anyString());
		verify(mockValidator).readFloat(anyString());
	}

	@Test
	void executeDishDetailsWithEmptyFields() {
		Dish dish = new Dish("Bandeja paisa", "Bandeja paisa description", 10.0f);

		when(mockSelectDish.execute()).thenReturn(dish);
		when(mockValidator.readString(anyString())).thenReturn("", "");
		when(mockValidator.readFloat(anyString())).thenReturn(0.0f);

		Boolean result = updateDish.execute();

		assertNotNull(result);
		assertTrue(result);

		assertEquals("Bandeja paisa", dish.getName());
		assertEquals("Bandeja paisa description", dish.getDescription());
		assertEquals(10.0f, dish.getPrice());

		verify(mockSelectDish).execute();
		verify(mockValidator, times(2)).readString(anyString());
		verify(mockValidator).readFloat(anyString());
	}
}