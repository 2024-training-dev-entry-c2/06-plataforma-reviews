package org.example.controllers.restaurant;

import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.services.restaurant.CreateRestaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateRestaurantControllerTest {
	private CreateRestaurant mockCreateRestaurant;
	private CreateRestaurantController createRestaurantController;

	@BeforeEach
	void setUp() {
		mockCreateRestaurant = mock(CreateRestaurant.class);
		createRestaurantController = new CreateRestaurantController(mockCreateRestaurant);
	}

	@Test
	void execute() {
		Menu mockMenu = mock(Menu.class);
		Restaurant restaurant = new Restaurant("Restaurante Test", "Descripcion Test", "Ubicacion Test", mockMenu);
		when(mockCreateRestaurant.execute()).thenReturn(restaurant);

		createRestaurantController.execute();

		verify(mockCreateRestaurant).execute();
	}
}