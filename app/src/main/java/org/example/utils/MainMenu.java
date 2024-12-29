package org.example.utils;

import org.example.controllers.interfaces.ICommandController;
import org.example.controllers.menu.AddDishController;
import org.example.controllers.menu.DeleteDishController;
import org.example.controllers.menu.UpdateDishController;
import org.example.controllers.restaurant.CreateRestaurantController;
import org.example.controllers.restaurant.DeleteRestaurantController;
import org.example.controllers.restaurant.ListRestaurantController;
import org.example.controllers.restaurant.UpdateRestaurantController;
import org.example.controllers.review.CreateDishReviewController;
import org.example.controllers.review.CreateRestaurantReviewController;
import org.example.controllers.review.ListDishReviewController;
import org.example.controllers.review.ListRestaurantReviewController;
import org.example.repositories.RestaurantRepository;
import org.example.services.menu.AddDish;
import org.example.services.menu.DeleteDish;
import org.example.services.menu.SelectDish;
import org.example.services.menu.UpdateDish;
import org.example.services.restaurant.AddRestaurantObserver;
import org.example.services.restaurant.CreateRestaurant;
import org.example.services.restaurant.DeleteRestaurant;
import org.example.services.restaurant.ListRestaurant;
import org.example.services.restaurant.SelectRestaurant;
import org.example.services.restaurant.UpdateRestaurant;
import org.example.services.review.CreateDishReview;
import org.example.services.review.CreateRestaurantReview;
import org.example.services.review.ListDishReview;
import org.example.services.review.ListRestaurantReview;
import org.example.utils.interfaces.IMenu;

import java.util.HashMap;
import java.util.Map;

public class MainMenu implements IMenu<Integer> {
	private final Validator validator;
	private final CreateRestaurantController createRestaurantController;
	private final UpdateRestaurantController updateRestaurantController;
	private final DeleteRestaurantController deleteRestaurantController;
	private final ListRestaurantController listRestaurantController;
	private final AddDishController addDishController;
	private final UpdateDishController updateDishController;
	private final DeleteDishController deleteDishController;
	private final CreateDishReviewController createDishReviewController;
	private final ListDishReviewController listDishReviewController;
	private final CreateRestaurantReviewController createRestaurantReviewController;
	private final ListRestaurantReviewController listRestaurantReviewController;

	public MainMenu(Validator validator,  CreateRestaurantController createRestaurantController, UpdateRestaurantController updateRestaurantController, DeleteRestaurantController deleteRestaurantController, ListRestaurantController listRestaurantController, AddDishController addDishController, UpdateDishController updateDishController, DeleteDishController deleteDishController, CreateDishReviewController createDishReviewController, ListDishReviewController listDishReviewController, CreateRestaurantReviewController createRestaurantReviewController, ListRestaurantReviewController listRestaurantReviewController) {
		this.validator = validator;
		this.createRestaurantController = createRestaurantController;
		this.updateRestaurantController = updateRestaurantController;
		this.deleteRestaurantController = deleteRestaurantController;
		this.listRestaurantController = listRestaurantController;
		this.addDishController = addDishController;
		this.updateDishController = updateDishController;
		this.deleteDishController = deleteDishController;
		this.createDishReviewController = createDishReviewController;
		this.listDishReviewController = listDishReviewController;
		this.createRestaurantReviewController = createRestaurantReviewController;
		this.listRestaurantReviewController = listRestaurantReviewController;
	}

	@Override
	public Integer execute() {
		Map<Integer, ICommandController> controllers = new HashMap<>();
		controllers.put(1, createRestaurantController);
		controllers.put(2, updateRestaurantController);
		controllers.put(3, deleteRestaurantController);
		controllers.put(4, listRestaurantController);
		controllers.put(5, addDishController);
		controllers.put(6, updateDishController);
		controllers.put(7, deleteDishController);
		controllers.put(8, createDishReviewController);
		controllers.put(9, listDishReviewController);
		controllers.put(10, createRestaurantReviewController);
		controllers.put(11, listRestaurantReviewController);

		Integer option;

		while (true) {
			option = showMenu();

			if (option == 12) {
				System.out.println("""
					    \033[1;32m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
					    \033[1;32m☆   ¡GRACIAS POR USAR NUESTRA APLICACIÓN!     ☆
					    \033[1;32m☆     Esperamos verte nuevamente pronto.      ☆
					    \033[1;32m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
					    \033[0m
					""");
				break;
			}

			if (controllers.containsKey(option)) {
				controllers.get(option).execute();
			} else {
				validator.printMessage("Opción no válida. Por favor, elige una opción entre 1 y 11.");
			}
		}

		return option;
	}

	private Integer showMenu() {
		return validator.readInteger("""
			====================================================================
			☆ ☆ ☆ BIENVENIDO A LA APLICACIÓN DE GESTIÓN DE RESTAURANTES ☆ ☆ ☆
			====================================================================
			                         ¿QUÉ DESEAS HACER?                       \s
			────────────────────────────────────────────────────────────────────
			    1. Crear un restaurante
			    2. Actualizar un restaurante
			    3. Eliminar un restaurante
			    4. Listar restaurantes
			    5. Añadir un plato
			    6. Actualizar un plato
			    7. Eliminar un plato
			    8. Crear una reseña de un plato
			    9. Listar reseñas de platos
			    10. Crear una reseña de un restaurante
			    11. Listar reseñas de restaurantes
			    12. Salir
			────────────────────────────────────────────────────────────────────
			Introduce el número de la opción que deseas realizar:\s"""
		);
	}
}