package org.example.services.utils;

import org.example.controllers.restaurant.RestaurantController;

import java.util.Map;

public class MenuRestaurant {
    public static IValidatorScanner validatorScanner;
    public MenuRestaurant(IValidatorScanner validatorScanner) {
        this.validatorScanner = validatorScanner;
    }

    public void showMenu() {
        RestaurantController restaurantController = new RestaurantController(validatorScanner);
        Map<Integer, Runnable> menuActions = Map.of(
                1, restaurantController::addRestaurant,
                2, restaurantController::removeRestaurant,
                3, restaurantController::listRestaurants,
                4, () -> System.out.println("Volviendo al menú principal...")
        );
        int option;
        do {
            System.out.println("\n===== Gestión de Restaurantes =====");
            System.out.println("1. Añadir Restaurante");
            System.out.println("2. Eliminar Restaurante");
            System.out.println("3. Listar Restaurantes");
            System.out.println("4. Volver al Menú Principal");

            option = validatorScanner.integerScanner("Selecciona una opción:");
            Runnable action = menuActions.get(option);
            if (action != null) {
                action.run();
            } else {
                System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (option != 4);

    }
}