package org.example.repositories;

import org.example.models.Restaurant;

import java.util.HashMap;
import java.util.Map;

public class RestaurantRepository {
	private static RestaurantRepository instance;
	private Map<Integer, Restaurant> restaurants;

	private RestaurantRepository() {
		this.restaurants = new HashMap<>();
	}

	public static synchronized RestaurantRepository getInstance() {
		if (instance == null) {
			instance = new RestaurantRepository();
		}
		return instance;
	}

	public void addRestaurant(Restaurant restaurant) {
		restaurants.put(restaurant.getRestaurantId(), restaurant);
	}

	public boolean updateRestaurant(Restaurant updatedRestaurant) {
		Integer restaurantId = updatedRestaurant.getRestaurantId();
		if (restaurants.containsKey(restaurantId)) {
			Restaurant existingRestaurant = restaurants.get(restaurantId);
			existingRestaurant.setName(updatedRestaurant.getName());
			existingRestaurant.setDescription(updatedRestaurant.getDescription());
			existingRestaurant.setLocation(updatedRestaurant.getLocation());
			return true;
		}
		return false;
	}

	public boolean deleteRestaurant(Integer restaurantId) {
		return restaurants.remove(restaurantId) != null;
	}

	public Map<Integer, Restaurant> getAllRestaurants() {
		return new HashMap<>(restaurants);
	}
}