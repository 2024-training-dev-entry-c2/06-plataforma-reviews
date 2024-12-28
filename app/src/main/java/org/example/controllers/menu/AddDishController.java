package org.example.controllers.menu;

import org.example.controllers.interfaces.ICommandController;
import org.example.models.Menu;
import org.example.services.menu.AddDish;

public class AddDishController implements ICommandController {
	private final AddDish addDish;

	public AddDishController(AddDish addDish) {
		this.addDish = addDish;
	}

	@Override
	public void execute() {
		Menu menu = addDish.execute();
		System.out.println("""
			    \033[1;32m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
			    \033[1;32m☆ ¡PLATO AGREGADO CON ÉXITO AL MENU!  ☆
			    \033[1;32m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
			    \033[0m
			""");

		System.out.println(menu.toString());
	}
}