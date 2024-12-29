package org.example.services.menu;

import org.example.models.Dish;
import org.example.models.Restaurant;
import org.example.services.interfaces.ICommand;
import org.example.services.restaurant.SelectRestaurant;
import org.example.utils.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SelectDish implements ICommand<Dish> {
	private final Validator validator;
	private final SelectRestaurant selectRestaurant;

	public SelectDish(Validator validator, SelectRestaurant selectRestaurant) {
		this.validator = validator;
		this.selectRestaurant = selectRestaurant;
	}

	@Override
	public Dish execute() {
		Restaurant restaurant = selectRestaurant.execute();

		if (restaurant == null) {
			return null;
		}

		Set<Dish> dishes = restaurant.getMenu().getDishes();

		if (dishes.isEmpty()) {
			validator.printMessage("No hay platos disponibles.");
			return null;
		}

		List<Dish> dishList = new ArrayList<>(dishes);

		displayDishList(dishList);
		int selectedDishIndex = getSelectedIndex(dishList.size());

		return dishList.get(selectedDishIndex - 1);
	}

	private void displayDishList(List<Dish> dishes) {
		dishes.forEach(dish -> validator.printMessage((dishes.indexOf(dish) + 1) + ". " + dish.getName()));
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