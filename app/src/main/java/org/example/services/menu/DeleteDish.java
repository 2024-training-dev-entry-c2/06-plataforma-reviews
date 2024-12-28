package org.example.services.menu;

import org.example.models.Dish;
import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.services.interfaces.ICommandParameterized;
import org.example.utils.Validator;

import java.util.stream.Collectors;

public class DeleteDish implements ICommandParameterized<Boolean, Restaurant> {
	private final Validator validator;

	public DeleteDish(Validator validator) {
		this.validator = validator;
	}

	@Override
	public Boolean execute(Restaurant restaurant) {
		Menu menu = restaurant.getMenu();
		if (menu == null) {
			return false;
		}

		Dish dishToDelete = findDish(menu);
		if (dishToDelete == null) {
			return false;
		}

		menu.getDishes().remove(dishToDelete);
		return true;
	}

	private Dish findDish(Menu menu) {
		String dishesNames = menu.getDishes().stream()
			.map(dish -> dish.getDishId() + ". " + dish.getName())
			.collect(Collectors.joining("\n"));

		String dishId = validator.readString("\nListado de platos:\n" + dishesNames +
			"\nIngrese el ID del plato que desea eliminar: ");

		return menu.getDishes().stream()
			.filter(dish -> dish.getDishId() == Integer.parseInt(dishId))
			.findFirst()
			.orElse(null);
	}
}