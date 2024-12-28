package org.example.services.restaurant;

import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.menu.AddDish;
import org.example.utils.Validator;

public class CreateRestaurant implements ICommand<Restaurant> {
	private final Validator validator;
	private final AddDish addDish;
	private final RestaurantRepository restaurantRepository;

	public CreateRestaurant(Validator validator, AddDish addDish, RestaurantRepository restaurantRepository) {
		this.validator = validator;
		this.addDish = addDish;
		this.restaurantRepository = restaurantRepository;
	}

	@Override
	public Restaurant execute() {
		String name = validator.readString("Ingrese el nombre del restaurante: ");
		String description = validator.readString("Ingrese la descripción del restaurante: ");
		String location = validator.readString("Ingrese la ubicación del restaurante: ");

		Restaurant restaurant = new Restaurant(name, description, location, null, null);

		Menu menu = addDish.execute();
		restaurant.setMenu(menu);

		restaurantRepository.addRestaurant(restaurant);
		return restaurant;
	}
}