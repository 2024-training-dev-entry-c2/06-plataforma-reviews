package restaurant;

import restaurant.controller.dish.AddDishController;
import restaurant.controller.dish.EditDishController;
import restaurant.controller.dish.RemoveDishController;
import restaurant.controller.ListAllDishesController;
import restaurant.controller.interfaces.IController;
import restaurant.controller.restaurant.AddRestaurantController;
import restaurant.controller.restaurant.EditRestaurantController;
import restaurant.controller.restaurant.RemoveRestaurantController;
import restaurant.controller.review.AddReviewController;
import restaurant.controller.review.ListReviewController;
import restaurant.controller.review.ListDishReviewController;
import restaurant.controller.restaurant.ListAllRestaurantsController;
import restaurant.repository.RestaurantRepository;
import restaurant.service.dish.AddDish;
import restaurant.service.dish.EditDish;
import restaurant.service.dish.ListAllDishes;
import restaurant.service.dish.RemoveDish;
import restaurant.service.review.AddReview;
import restaurant.service.review.ListRestaurantReview;
import restaurant.service.review.ListDishReview;
import restaurant.service.restaurant.AddRestaurant;
import restaurant.service.restaurant.EditRestaurant;
import restaurant.service.restaurant.RemoveRestaurant;
import restaurant.service.restaurant.ListAllRestaurants;
import restaurant.utils.ConsoleUtils;
import restaurant.utils.DishUtils;
import restaurant.utils.RestaurantUtils;
import restaurant.utils.SeedData;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ConsoleUtils consoleUtils = new ConsoleUtils();
        RestaurantUtils restaurantUtils = new RestaurantUtils();
        DishUtils dishUtils = new DishUtils();
        RestaurantRepository restaurantRepository = RestaurantRepository.getInstance();

        SeedData.preloadData(restaurantRepository);

        AddRestaurant addRestaurantCommand = new AddRestaurant(restaurantRepository, consoleUtils);
        RemoveRestaurant removeRestaurantCommand = new RemoveRestaurant(restaurantRepository, consoleUtils);
        AddReview addReviewCommand = new AddReview(restaurantRepository, consoleUtils);
        EditRestaurant editRestaurantCommand = new EditRestaurant(restaurantRepository, consoleUtils);
        AddDish addDishCommand = new AddDish(restaurantRepository, consoleUtils, restaurantUtils);
        EditDish editDishCommand = new EditDish(restaurantRepository, consoleUtils, restaurantUtils, dishUtils);
        RemoveDish removeDishCommand = new RemoveDish(restaurantRepository, consoleUtils, restaurantUtils, dishUtils);
        ListRestaurantReview listRestaurantReviewCommand = new ListRestaurantReview(restaurantRepository, consoleUtils);
        ListDishReview listDishReviewCommand = new ListDishReview(restaurantRepository, consoleUtils);
        ListAllRestaurants listAllRestaurantsCommand = new ListAllRestaurants(restaurantRepository);
        ListAllDishes listAllDishesCommand = new ListAllDishes(restaurantRepository, consoleUtils);

        Map<Integer, IController> controllers = new HashMap<>();
        controllers.put(1, new AddRestaurantController(addRestaurantCommand));
        controllers.put(2, new RemoveRestaurantController(removeRestaurantCommand));
        controllers.put(3, new EditRestaurantController(editRestaurantCommand));
        controllers.put(4, new AddReviewController(addReviewCommand));
        controllers.put(5, new AddDishController(addDishCommand));
        controllers.put(6, new EditDishController(editDishCommand));
        controllers.put(7, new RemoveDishController(removeDishCommand));
        controllers.put(8, new ListReviewController(listRestaurantReviewCommand));
        controllers.put(9, new ListDishReviewController(listDishReviewCommand));
        controllers.put(10, new ListAllRestaurantsController(listAllRestaurantsCommand));
        controllers.put(11, new ListAllDishesController(listAllDishesCommand));

        int option;
        do {
            option = consoleUtils.getInteger("Bienvenido \n¿Qué deseas hacer?\n1. Añadir Restaurante\n2. Eliminar Restaurante\n3. Editar Restaurante\n4. Crear Reseña\n5. Añadir Plato\n6. Editar Plato\n7. Eliminar Plato\n8. Listar Reseñas de Restaurante\n9. Listar Reseñas de Plato\n10. Listar Todos los Restaurantes\n11. Listar Todos los Platos del Menú\n12. Salir");
            if (option != 12) {
                IController controller = controllers.get(option);
                if (controller != null) {
                    controller.execute();
                } else {
                    System.out.println("Opción no válida");
                }
            }
        } while (option != 12);
    }
}