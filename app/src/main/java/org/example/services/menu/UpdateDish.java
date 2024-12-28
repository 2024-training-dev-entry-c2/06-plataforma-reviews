package org.example.services.menu;

import org.example.models.Dish;
import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.services.interfaces.ICommand;
import org.example.services.restaurant.SelectRestaurant;
import org.example.utils.Validator;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class UpdateDish implements ICommand<Boolean> {
	private final Validator validator;
	private final SelectRestaurant selectRestaurant;

	public UpdateDish(Validator validator, SelectRestaurant selectRestaurant) {
		this.validator = validator;
		this.selectRestaurant = selectRestaurant;
	}

	@Override
	public Boolean execute() {
		return Optional.ofNullable(selectRestaurant.execute())
			.map(Restaurant::getMenu)
			.map(this::findDish)
			.map(dish -> {
				updateDishDetails(dish);
				return true;
			})
			.orElse(false);
	}

	private Dish findDish(Menu menu) {
		Set<Dish> dishes = menu.getDishes();

		String dishesNames = dishes.stream()
			.map(dish -> dish.getDishId() + ". " + dish.getName())
			.collect(Collectors.joining("\n"));

		Integer dishId= validator.readInteger("\nListado de nombres de platos:\n" + dishesNames +
			"\nIngrese el numero del plato que desea actualizar: ");

		return dishes.stream()
			.filter(dish -> dish.getDishId().equals(dishId))
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

		Float newPrice = validator.readFloat("Ingrese el nuevo precio del plato (Escriba 0 para no cambiar): ");
		if (newPrice != null && newPrice != 0f) {
			dish.setPrice(newPrice);
		}
	}
}