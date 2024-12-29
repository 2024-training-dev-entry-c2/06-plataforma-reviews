package org.example.services.restaurant;

import org.example.models.Dish;
import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;
import org.example.services.menu.AddDish;
import org.example.utils.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateRestaurantTest {
	private Validator mockValidator;
	private AddDish mockAddDish;
	private RestaurantRepository mockRestaurantRepository;
	private CreateRestaurant createRestaurant;

	@BeforeEach
	public void setUp() {
		mockValidator = mock(Validator.class);
		mockAddDish = mock(AddDish.class);
		mockRestaurantRepository = mock(RestaurantRepository.class);

		createRestaurant = new CreateRestaurant(mockValidator, mockAddDish, mockRestaurantRepository);
	}

	@Test
	void testExecute() {
		String name = "Restaurante Test";
		String description = "Descripción Test";
		String location = "Ubicación Test";

		Set<Dish> dishes = new HashSet<>();
		dishes.add(new Dish("Dish Test", "Descripción Test", 10.0f));
		Menu menu = new Menu("Menu Test", "Descripción Test", dishes);

		when(mockValidator.readString(anyString())).thenReturn(name, description, location);
		when(mockAddDish.execute()).thenReturn(menu);

		Restaurant result = createRestaurant.execute();

		assertNotNull(result);
		assertEquals(name, result.getName());
		assertEquals(description, result.getDescription());
		assertEquals(location, result.getLocation());
		assertEquals(menu.getName(), result.getMenu().getName());
		assertEquals(menu.getDescription(), result.getMenu().getDescription());
		assertEquals(menu.getDishes().size(), result.getMenu().getDishes().size());

		verify(mockValidator, times(3)).readString(anyString());
		verify(mockRestaurantRepository).addRestaurant(any(Restaurant.class));
		verify(mockAddDish).execute();
	}
}