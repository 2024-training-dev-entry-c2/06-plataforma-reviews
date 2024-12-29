package org.example.services.utils;

import org.example.controllers.dishFood.DishFoodController;
import org.example.controllers.menu.MenuController;
import org.example.controllers.restaurant.RestaurantController;
import org.example.repositories.DishRepository;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;

import java.util.Map;

public class MenuReview {
    public final  IValidatorScanner validatorScanner;
    private final  RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final DishRepository dishRepository;

    public MenuReview(IValidatorScanner validatorScanner, RestaurantRepository restaurantRepository, MenuRepository menuRepository, DishRepository dishRepository) {
        this.validatorScanner = validatorScanner;
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
        this.dishRepository = dishRepository;
    }

    public void showMenu() {
        RestaurantController restaurantController = new RestaurantController(validatorScanner,restaurantRepository);
        DishFoodController dishFoodController = new DishFoodController(restaurantRepository,menuRepository,dishRepository,validatorScanner);
        Map<Integer, Runnable> menuActions = Map.of(
                1, restaurantController::addReview,
                2, dishFoodController::addReview,
                3, restaurantController::showReview,
                4, dishFoodController::showReviews,
                5, () -> System.out.println("Volviendo al menú principal...")
        );
        int option;
        do {
            System.out.println("\n===== Gestión de Menús =====");
            System.out.println("1. Añadir Review a Restaurante");
            System.out.println("2. Añadir Review a Plato");
            System.out.println("3. Listar Reviews por Restaurant");
            System.out.println("4. Listar Reviews por plato");
            System.out.println("5. Volver al Menú Principal");

            option = validatorScanner.integerScanner("Selecciona una opción:");
            Runnable action = menuActions.get(option);
            if (action != null) {
                action.run();
            } else {
                System.out.println("Opción no válida. Intenta de nuevo.");
            }

        } while (option != 5);

    }


}