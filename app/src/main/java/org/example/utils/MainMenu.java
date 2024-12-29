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
import java.util.Scanner;

public class MainMenu implements IMenu<Integer> {
	RestaurantRepository restaurantRepository = RestaurantRepository.getInstance();
	Validator validator = new Validator(new Scanner(System.in));

	SelectRestaurant selectRestaurant = new SelectRestaurant(validator, restaurantRepository);
	AddDish addDish = new AddDish(validator, selectRestaurant);
	CreateRestaurant createRestaurant = new CreateRestaurant(validator, addDish, restaurantRepository);

	UpdateRestaurant updateRestaurant = new UpdateRestaurant(validator, restaurantRepository, selectRestaurant);
	DeleteRestaurant deleteRestaurant = new DeleteRestaurant(restaurantRepository, selectRestaurant);
	ListRestaurant listRestaurant = new ListRestaurant(restaurantRepository);

	SelectDish selectDish = new SelectDish(validator, selectRestaurant);
	UpdateDish updateDish = new UpdateDish(validator, selectDish);
	DeleteDish deleteDish = new DeleteDish(selectDish, restaurantRepository);

	CreateDishReview createDishReview = new CreateDishReview(validator, selectDish);
	AddRestaurantObserver addRestaurantObserver = new AddRestaurantObserver(validator);
	CreateRestaurantReview createRestaurantReview = new CreateRestaurantReview(validator, selectRestaurant, addRestaurantObserver);

	ListDishReview listDishReview = new ListDishReview(selectDish);
	ListRestaurantReview listRestaurantReview = new ListRestaurantReview(selectRestaurant);

	@Override
	public Integer execute() {
		Map<Integer, ICommandController> controllers = new HashMap<>();
		controllers.put(1, new CreateRestaurantController(createRestaurant));
		controllers.put(2, new UpdateRestaurantController(updateRestaurant));
		controllers.put(3, new DeleteRestaurantController(deleteRestaurant));
		controllers.put(4, new ListRestaurantController(listRestaurant));
		controllers.put(5, new AddDishController(addDish));
		controllers.put(6, new UpdateDishController(updateDish));
		controllers.put(7, new DeleteDishController(deleteDish));
		controllers.put(8, new CreateDishReviewController(createDishReview));
		controllers.put(9, new ListDishReviewController(listDishReview));
		controllers.put(10, new CreateRestaurantReviewController(createRestaurantReview));
		controllers.put(11, new ListRestaurantReviewController(listRestaurantReview));

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