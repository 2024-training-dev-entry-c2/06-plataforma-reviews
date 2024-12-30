package org.example.services.menu;

import org.example.models.Dish;
import org.example.services.interfaces.ICommand;
import org.example.utils.Validator;

public class UpdateDish implements ICommand<Boolean> {
	private final Validator validator;
	private final SelectDish selectDish;

	public UpdateDish(Validator validator, SelectDish selectDish) {
		this.validator = validator;
		this.selectDish = selectDish;
	}

	@Override
	public Boolean execute() {
		Dish selectedDish = selectDish.execute();

		if (selectedDish == null) {
			return false;
		}

		updateDishDetails(selectedDish);

		return true;
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
		if (newPrice != 0f) {
			dish.setPrice(newPrice);
		}
	}
}