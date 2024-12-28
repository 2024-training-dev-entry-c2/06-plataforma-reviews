package org.example.services.restaurant;

import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;

public class DeleteRestaurant implements ICommand<Boolean> {
	private final RestaurantRepository restaurantRepository;
	private final SelectRestaurant selectRestaurant;

	public DeleteRestaurant(RestaurantRepository restaurantRepository, SelectRestaurant selectRestaurant) {
		this.restaurantRepository = restaurantRepository;
		this.selectRestaurant = selectRestaurant;
	}

	@Override
	public Boolean execute() {
		Restaurant selectedRestaurant = selectRestaurant.execute();
		return restaurantRepository.deleteRestaurant(selectedRestaurant.getRestaurantId());
	}
}