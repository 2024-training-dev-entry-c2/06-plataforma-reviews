package org.example.services.restaurant;

import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommandParameterized;
import org.example.utils.Validator;

public class UpdateRestaurant implements ICommandParameterized<Boolean, Restaurant> {
	private final Validator validator;
	private final RestaurantRepository restaurantRepository;

	public UpdateRestaurant(Validator validator, RestaurantRepository restaurantRepository) {
		this.validator = validator;
		this.restaurantRepository = restaurantRepository;
	}

	@Override
	public Boolean execute(Restaurant updatedRestaurant) {
		Restaurant existingRestaurant = restaurantRepository.getRestaurant(updatedRestaurant.getRestaurantId());

		if (existingRestaurant == null) {
			return false;
		}

		updateRestaurantDetails(existingRestaurant);

		return restaurantRepository.updateRestaurant(existingRestaurant);
	}

	private void updateRestaurantDetails(Restaurant existingRestaurant) {
		String newName = validator.readString("Ingrese el nuevo nombre del restaurante (deje vacío para no cambiar): ");
		if (!newName.isEmpty()) {
			existingRestaurant.setName(newName);
		}

		String newDescription = validator.readString("Ingrese la nueva descripción del restaurante (deje vacío para no cambiar): ");
		if (!newDescription.isEmpty()) {
			existingRestaurant.setDescription(newDescription);
		}

		String newLocation = validator.readString("Ingrese la nueva ubicación del restaurante (deje vacío para no cambiar): ");
		if (!newLocation.isEmpty()) {
			existingRestaurant.setLocation(newLocation);
		}
	}
}