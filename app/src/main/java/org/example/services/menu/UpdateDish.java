package org.example.services.menu;

import org.example.models.Dish;
import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.services.interfaces.ICommandParameterized;
import org.example.utils.Validator;
import java.util.Set;
import java.util.stream.Collectors;

public class UpdateDish implements ICommandParameterized<Boolean, Restaurant> {
	private final Validator validator;

	public UpdateDish(Validator validator) {
		this.validator = validator;
	}

	@Override
	public Boolean execute(Restaurant restaurant) {
		Menu menu = restaurant.getMenu();
		if (menu == null) {
			return false;
		}

		Dish dish = findDish(menu);
		if (dish == null) {
			return false;
		}

		updateDishDetails(dish);
		return true;
	}

	private Dish findDish(Menu menu) {
		Set<Dish> dishes = menu.getDishes();

		String dishesNames = dishes.stream()
			.map(dish -> dish.getDishId() + ". " + dish.getName())
			.collect(Collectors.joining("\n"));

		String dishName = validator.readString("\nListado de nombres de platos:\n" + dishesNames +
			"\nIngrese el nombre del plato que desea actualizar: ");

		return dishes.stream()
			.filter(dish -> dish.getName().equalsIgnoreCase(dishName))
			.findFirst()
			.orElse(null);
	}

	private void updateDishDetails(Dish dish) {
		String newName = validator.readString("Ingrese el nuevo nombre del plato (deje vacío para no cambiar): ");
		if (!newName.isEmpty()) {
			dish.setName(newName);
		}

		String newDescription = validator.readString("Ingrese la nueva descripción del plato (deje vacío para no cambiar): ");
		if (!newDescription.isEmpty()) {
			dish.setDescription(newDescription);
		}

		Float newPrice = validator.readFloat("Ingrese el nuevo precio del plato (deje vacío para no cambiar): ");
		if (newPrice != null) {
			dish.setPrice(newPrice);
		}
	}
}