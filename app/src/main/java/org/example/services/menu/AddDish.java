package org.example.services.menu;

import org.example.models.Dish;
import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.services.interfaces.ICommand;
import org.example.services.restaurant.SelectRestaurant;
import org.example.utils.Validator;
import java.util.LinkedHashSet;

public class AddDish implements ICommand<Menu> {
	private final Validator validator;
	private final SelectRestaurant selectRestaurant;

	public AddDish(Validator validator, SelectRestaurant selectRestaurant) {
		this.validator = validator;
		this.selectRestaurant = selectRestaurant;
	}

	@Override
	public Menu execute() {
		Restaurant restaurant = selectRestaurant.execute();
		if (restaurant == null) {
			return null;
		}

		Menu menu = restaurant.getMenu();

		if (menu == null) {
			menu = createMenu();
		}

		validator.printMessage("\n☆ ☆ ☆ AÑADIENDO PLATOS AL MENU ☆ ☆ ☆");
		addDishesToMenu(menu);

		return menu;
	}

	private void addDishesToMenu(Menu menu) {
		boolean addingDishes = true;

		while (addingDishes) {
			String dishName = validator.readString("Ingrese el nombre del plato: ");
			String dishDescription = validator.readString("Ingrese la descripción del plato: ");
			Float dishPrice = validator.readFloat("Ingrese el precio del plato: ");

			Dish dish = new Dish(dishName, dishDescription, dishPrice);

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
		return new Menu(menuName, menuDescription, new LinkedHashSet<>());
	}
}