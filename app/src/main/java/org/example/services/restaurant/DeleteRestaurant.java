package org.example.services.restaurant;

import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommandParameterized;

public class DeleteRestaurant implements ICommandParameterized<Boolean, Integer> {
	private final RestaurantRepository restaurantRepository;

	public DeleteRestaurant(RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
	}

	@Override
	public Boolean execute(Integer restaurantId) {
		return restaurantRepository.deleteRestaurant(restaurantId);
	}
}