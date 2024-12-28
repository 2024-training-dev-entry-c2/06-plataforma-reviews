package org.example.controllers.restaurant;

import org.example.controllers.interfaces.ICommandController;
import org.example.models.Restaurant;
import org.example.services.restaurant.ListRestaurant;

import java.util.List;

public class ListRestaurantController implements ICommandController {
	private final ListRestaurant listRestaurant;

	public ListRestaurantController(ListRestaurant listRestaurant) {
		this.listRestaurant = listRestaurant;
	}

	@Override
	public void execute() {
		List<Restaurant> restaurants = listRestaurant.execute();
		if (restaurants.isEmpty()) {
			System.out.println("""
				    \033[1;31m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
				    \033[1;31m☆NO HAY RESTAURANTES EN EL REPOSITORIO☆
				    \033[1;31m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
				    \033[0m
				""");
			return;
		}

		System.out.println("""
			    \033[1;32m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
			    \033[1;32m☆       LISTADO DE RESTAURANTES       ☆
			    \033[1;32m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
			    \033[0m
			""");

		restaurants.forEach(restaurant -> System.out.println(restaurant.toString()));
	}
}