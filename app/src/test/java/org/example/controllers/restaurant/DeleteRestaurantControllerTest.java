package org.example.controllers.restaurant;

import org.example.services.restaurant.DeleteRestaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeleteRestaurantControllerTest {
	private DeleteRestaurant mockDeleteRestaurant;
	private DeleteRestaurantController deleteRestaurantController;

	@BeforeEach
	void setUp() {
		mockDeleteRestaurant = mock(DeleteRestaurant.class);
		deleteRestaurantController = new DeleteRestaurantController(mockDeleteRestaurant);
	}

	@Test
	void execute() {
		when(mockDeleteRestaurant.execute()).thenReturn(true);

		deleteRestaurantController.execute();
		verify(mockDeleteRestaurant).execute();
	}

	@Test
	void executeWithFalse() {
		when(mockDeleteRestaurant.execute()).thenReturn(false);

		deleteRestaurantController.execute();
		verify(mockDeleteRestaurant).execute();
	}
}