package org.example.controllers.restaurant;

import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.services.restaurant.ListRestaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ListRestaurantControllerTest {
	private ListRestaurant mockListRestaurant;
	private ListRestaurantController listRestaurantController;

	@BeforeEach
	void setUp() {
		mockListRestaurant = mock(ListRestaurant.class);
		listRestaurantController = new ListRestaurantController(mockListRestaurant);
	}

	@Test
	void execute() {
		Menu mockMenu = mock(Menu.class);
		List<Restaurant> restaurants = List.of(new Restaurant("Restaurante Test", "Descripcion Test", "Ubicacion Test", mockMenu));

		when(mockListRestaurant.execute()).thenReturn(restaurants);

		listRestaurantController.execute();

		verify(mockListRestaurant).execute();
	}

	@Test
	void executeWithEmptyList() {
		when(mockListRestaurant.execute()).thenReturn(List.of());

		listRestaurantController.execute();

		verify(mockListRestaurant).execute();
	}
}