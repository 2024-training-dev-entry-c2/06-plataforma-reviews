package org.example.controllers.menu;

import org.example.controllers.interfaces.ICommandController;
import org.example.services.menu.UpdateDish;

public class UpdateDishController implements ICommandController {
	private final UpdateDish updateDish;

	public UpdateDishController(UpdateDish updateDish) {
		this.updateDish = updateDish;
	}

	@Override
	public void execute() {
		Boolean dish = updateDish.execute();
		if (!dish) {
			System.out.println("""
				    \033[1;31m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
				    \033[1;31m☆   ¡ERROR AL ACTUALIZAR EL PLATO!    ☆
				    \033[1;31m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
				    \033[0m
				""");
			return;
		}

		System.out.println("""
			    \033[1;32m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
			    \033[1;32m☆    ¡PLATO ACTUALIZADO CON ÉXITO!    ☆
			    \033[1;32m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
			    \033[0m
			""");
	}
}