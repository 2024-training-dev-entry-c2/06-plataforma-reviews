package org.example.controllers.restaurant;

import org.example.services.restaurant.UpdateRestaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateRestaurantControllerTest {
	private UpdateRestaurant mockUpdateRestaurant;
	private UpdateRestaurantController updateRestaurantController;

	@BeforeEach
	void setUp() {
		mockUpdateRestaurant = mock(UpdateRestaurant.class);
		updateRestaurantController = new UpdateRestaurantController(mockUpdateRestaurant);
	}

	@Test
	void execute() {
		when(mockUpdateRestaurant.execute()).thenReturn(true);

		updateRestaurantController.execute();
		verify(mockUpdateRestaurant).execute();
	}

	@Test
	void executeWithFalse() {
		when(mockUpdateRestaurant.execute()).thenReturn(false);

		updateRestaurantController.execute();
		verify(mockUpdateRestaurant).execute();
	}
}