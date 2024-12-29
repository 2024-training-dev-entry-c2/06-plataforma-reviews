package org.example.services.restaurant;

import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListRestaurant implements ICommand<List<Restaurant>> {
	private final RestaurantRepository restaurantRepository;

	public ListRestaurant(RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
	}

	@Override
	public List<Restaurant> execute() {
		Map<Integer, Restaurant> allRestaurants = restaurantRepository.getAllRestaurants();

		return new ArrayList<>(allRestaurants.values());
	}
}