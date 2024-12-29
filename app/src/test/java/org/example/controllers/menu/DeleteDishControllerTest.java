package org.example.controllers.menu;

import org.example.services.menu.DeleteDish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeleteDishControllerTest {
	private DeleteDish mockDeleteDish;
	private DeleteDishController deleteDishController;

	@BeforeEach
	void setUp() {
		mockDeleteDish = mock(DeleteDish.class);
		deleteDishController = new DeleteDishController(mockDeleteDish);
	}

	@Test
	void execute() {
		when(mockDeleteDish.execute()).thenReturn(true);

		deleteDishController.execute();
		verify(mockDeleteDish).execute();
	}

	@Test
	void executeWithFalse() {
		when(mockDeleteDish.execute()).thenReturn(false);

		deleteDishController.execute();
		verify(mockDeleteDish).execute();
	}
}