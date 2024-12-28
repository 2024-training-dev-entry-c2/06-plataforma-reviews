package org.example.controllers.restaurant;

import org.example.controllers.interfaces.ICommandController;
import org.example.services.restaurant.DeleteRestaurant;

public class DeleteRestaurantController implements ICommandController {
	private final DeleteRestaurant deleteRestaurant;

	public DeleteRestaurantController(DeleteRestaurant deleteRestaurant) {
		this.deleteRestaurant = deleteRestaurant;
	}

	@Override
	public void execute() {
		Boolean restaurant = deleteRestaurant.execute();
		if (!restaurant) {
			System.out.println("""
				    \033[1;31m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
				    \033[1;31m☆ ¡ERROR AL ELIMINAR EL RESTAURANTE!  ☆
				    \033[1;31m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
				    \033[0m
				""");
			return;
		}

		System.out.println("""
			    \033[1;32m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
			    \033[1;32m☆  ¡RESTAURANTE ELIMINADO CON ÉXITO!  ☆
			    \033[1;32m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
			    \033[0m
			""");
	}
}