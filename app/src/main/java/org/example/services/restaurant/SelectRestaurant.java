package org.example.services.restaurant;

import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.utils.Validator;
import java.util.Map;

public class SelectRestaurant implements ICommand<Restaurant> {
	private final Validator validator;
	private final RestaurantRepository restaurantRepository;

	public SelectRestaurant(Validator validator, RestaurantRepository restaurantRepository) {
		this.validator = validator;
		this.restaurantRepository = restaurantRepository;
	}

	@Override
	public Restaurant execute() {
		Map<Integer, Restaurant> restaurants = restaurantRepository.getAllRestaurants();

		if (restaurants.isEmpty()) {
			return null;
		}

		displayRestaurantList(restaurants);

		int selectedIndex = getSelectedIndex(restaurants.size());

		return restaurants.get(selectedIndex);
	}

	private void displayRestaurantList(Map<Integer, Restaurant> restaurants) {
		validator.printMessage("Listado de restaurantes:");
		restaurants.forEach((key, value) -> validator.printMessage(key + ". " + value.getName()));
	}

	private int getSelectedIndex(int size) {
		int selectedIndex = validator.readInteger("Ingrese el número de la opción que desea: ");

		if (selectedIndex < 1 || selectedIndex > size) {
			validator.printMessage("Selección inválida.");
			return getSelectedIndex(size);
		}

		return selectedIndex;
	}
}