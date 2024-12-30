package org.example.controllers.restaurant;

import org.example.controllers.interfaces.ICommandController;
import org.example.services.restaurant.UpdateRestaurant;

public class UpdateRestaurantController implements ICommandController {
	private final UpdateRestaurant updateRestaurant;

	public UpdateRestaurantController(UpdateRestaurant updateRestaurant) {
		this.updateRestaurant = updateRestaurant;
	}

	@Override
	public void execute() {
		Boolean restaurant = updateRestaurant.execute();
		if (Boolean.FALSE.equals(restaurant)) {
			System.out.println("""
				    \033[1;31m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
				    \033[1;31m☆ ¡ERROR AL ACTUALIZAR EL RESTAURANTE! ☆
				    \033[1;31m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
				    \033[0m
				""");
			return;
		}

		System.out.println("""
			    \033[1;32m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
			    \033[1;32m☆ ¡RESTAURANTE ACTUALIZADO CON ÉXITO!  ☆
			    \033[1;32m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
			    \033[0m
			""");
	}
}