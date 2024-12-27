package org.example.services.menu;

import org.example.models.Dish;
import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.services.interfaces.ICommandParameterized;
import org.example.utils.Validator;
import java.util.HashSet;

public class AddDish implements ICommandParameterized<Menu, Restaurant> {
	private final Validator validator;

	public AddDish(Validator validator) {
		this.validator = validator;
	}

	@Override
	public Menu execute(Restaurant restaurant) {
		Menu menu = restaurant.getMenu();

		if (menu == null) {
			menu = createMenu();
		}

		addDishesToMenu(menu);

		return menu;
	}

	private void addDishesToMenu(Menu menu) {
		boolean addingDishes = true;

		while (addingDishes) {
			String dishName = validator.readString("Ingrese el nombre del plato: ");
			String dishDescription = validator.readString("Ingrese la descripción del plato: ");
			Float dishPrice = validator.readFloat("Ingrese el precio del plato: ");

			Dish dish = new Dish(dishName, dishDescription, dishPrice, null);

			menu.addDish(dish);

			String response = validator.readString("¿Desea agregar otro plato? (s/n): ");
			if (!response.equalsIgnoreCase("s")) {
				addingDishes = false;
			}
		}
	}

	private Menu createMenu() {
		String menuName = validator.readString("Ingrese el nombre del menú: ");
		String menuDescription = validator.readString("Ingrese la descripción del menú: ");
		return new Menu(menuName, menuDescription, new HashSet<>());
	}
}