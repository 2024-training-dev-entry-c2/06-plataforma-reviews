package org.example.services.restaurant;

import org.example.models.Restaurant;
import org.example.models.UserObserver;
import org.example.services.interfaces.ICommandParameterized;
import org.example.utils.Validator;

public class AddRestaurantObserver implements ICommandParameterized<Boolean, Restaurant> {
	private final Validator validator;

	public AddRestaurantObserver(Validator validator) {
		this.validator = validator;
	}

	@Override
	public Boolean execute(Restaurant restaurant) {
		if (restaurant == null) {
			return false;
		}

		UserObserver userObserver = userForObserver();
		if (userObserver == null) {
			return false;
		}

		subscribeToRestaurantAndDishes(restaurant, userObserver);
		return true;
	}

	private UserObserver userForObserver() {
		String response = validator.readString("¿Desea recibir notificaciones de este restaurante y sus platos? (S/N): ");
		if (!response.equalsIgnoreCase("s")) {
			return null;
		}

		String userName = validator.readString("Ingrese su nombre de usuario para recibir notificaciones: ");
		return new UserObserver(userName);
	}

	private void subscribeToRestaurantAndDishes(Restaurant restaurant, UserObserver userObserver) {
		subscribeToRestaurant(restaurant, userObserver);
		subscribeToDishes(restaurant, userObserver);
	}

	private void subscribeToRestaurant(Restaurant restaurant, UserObserver userObserver) {
		restaurant.addObserver(userObserver);
	}

	private void subscribeToDishes(Restaurant restaurant, UserObserver userObserver) {
		if (restaurant.getMenu() != null && restaurant.getMenu().getDishes() != null) {
			restaurant.getMenu().getDishes().forEach(dish -> dish.addObserver(userObserver));
		}
	}
}