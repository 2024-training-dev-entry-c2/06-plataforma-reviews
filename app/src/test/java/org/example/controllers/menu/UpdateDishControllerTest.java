package org.example.controllers.menu;

import org.example.services.menu.UpdateDish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateDishControllerTest {
	private UpdateDish mockUpdateDish;
	private UpdateDishController updateDishController;

	@BeforeEach
	void setUp() {
		mockUpdateDish = mock(UpdateDish.class);
		updateDishController = new UpdateDishController(mockUpdateDish);
	}

	@Test
	void execute() {
		when(mockUpdateDish.execute()).thenReturn(true);

		updateDishController.execute();
		verify(mockUpdateDish).execute();
	}

	@Test
	void executeWithFalse() {
		when(mockUpdateDish.execute()).thenReturn(false);

		updateDishController.execute();
		verify(mockUpdateDish).execute();
	}
}