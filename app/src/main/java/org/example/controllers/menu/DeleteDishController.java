package org.example.controllers.menu;

import org.example.controllers.interfaces.ICommandController;
import org.example.services.menu.DeleteDish;

public class DeleteDishController implements ICommandController {
	private final DeleteDish deleteDish;

	public DeleteDishController(DeleteDish deleteDish) {
		this.deleteDish = deleteDish;
	}

	@Override
	public void execute() {
		Boolean dish = deleteDish.execute();
		if (!dish) {
			System.out.println("""
				    \033[1;31m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
				    \033[1;31m☆ ¡ERROR AL ELIMINAR EL PLATO!  ☆
				    \033[1;31m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
				    \033[0m
				""");
			return;
		}

		System.out.println("""
			    \033[1;32m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
			    \033[1;32m☆  ¡PLATO ELIMINADO CON ÉXITO!  ☆
			    \033[1;32m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
			    \033[0m
			""");
	}
}