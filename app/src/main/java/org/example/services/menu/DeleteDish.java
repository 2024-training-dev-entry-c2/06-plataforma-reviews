package org.example.services.menu;

import org.example.models.Dish;
import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.restaurant.SelectRestaurant;
import org.example.utils.Validator;

import java.util.Objects;
import java.util.stream.Collectors;

public class DeleteDish implements ICommand<Boolean> {
	private final Validator validator;
	private final SelectDish selectDish;
	private final RestaurantRepository restaurantRepository;

	public DeleteDish(Validator validator, SelectDish selectDish, RestaurantRepository restaurantRepository) {
		this.validator = validator;
		this.selectDish = selectDish;
		this.restaurantRepository = restaurantRepository;
	}

	@Override
	public Boolean execute() {
		Dish dishToDelete = selectDish.execute();

		if (dishToDelete == null) {
			return false;
		}

		return restaurantRepository.getAllRestaurants().values().stream()
			.map(Restaurant::getMenu)
			.filter(Objects::nonNull)
			.anyMatch(menu -> menu.getDishes().remove(dishToDelete));
	}
}