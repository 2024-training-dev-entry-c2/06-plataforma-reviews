package org.example.controllers.restaurant;

import org.example.controllers.interfaces.ICommandController;
import org.example.models.Restaurant;
import org.example.services.restaurant.CreateRestaurant;

public class CreateRestaurantController implements ICommandController {
	private final CreateRestaurant createRestaurant;

	public CreateRestaurantController(CreateRestaurant createRestaurant) {
		this.createRestaurant = createRestaurant;
	}

	@Override
	public void execute() {
		Restaurant restaurant = createRestaurant.execute();
		System.out.println("""
			    \033[1;32m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
			    \033[1;32m☆   ¡RESTAURANTE CREADO CON ÉXITO!    ☆
			    \033[1;32m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
			    \033[0m
			""");

		System.out.println(restaurant.toString());
	}
}
