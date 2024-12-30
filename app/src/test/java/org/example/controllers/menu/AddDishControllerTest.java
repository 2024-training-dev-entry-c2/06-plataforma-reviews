package org.example.controllers.menu;

import org.example.models.Dish;
import org.example.models.Menu;
import org.example.services.menu.AddDish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AddDishControllerTest {
	private AddDish mockAddDish;
	private AddDishController addDishController;

	@BeforeEach
	void setUp() {
		mockAddDish = mock(AddDish.class);
		addDishController = new AddDishController(mockAddDish);
	}

	@Test
	void execute() {
		Set<Dish> dishes = new HashSet<>();
		dishes.add(new Dish("Bandeja paisa", "Bandeja paisa description", 10.0f));
		Menu menu = new Menu("Menu Test", "Descripcion Test", dishes);

		when(mockAddDish.execute()).thenReturn(menu);

		addDishController.execute();

		verify(mockAddDish).execute();
	}

	@Test
	void executeWithNullMenu() {
		when(mockAddDish.execute()).thenReturn(null);

		addDishController.execute();

		verify(mockAddDish).execute();
	}
}