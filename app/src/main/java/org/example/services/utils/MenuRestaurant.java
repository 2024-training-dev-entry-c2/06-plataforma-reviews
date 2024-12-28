package org.example.services.utils;

import org.example.controllers.restaurant.RestaurantController;

public class MenuRestaurant {
    public static IValidatorScanner validatorScanner;
    RestaurantController restaurantController = new RestaurantController();
    public MenuRestaurant(IValidatorScanner validatorScanner) {
        this.validatorScanner = validatorScanner;
    }

    public void showMenu() {
        int option;
        do {
            System.out.println("\n===== Gestión de Restaurantes =====");
            System.out.println("1. Añadir Restaurante");
            System.out.println("2. Eliminar Restaurante");
            System.out.println("3. Listar Restaurantes");
            System.out.println("4. Volver al Menú Principal");

            option = validatorScanner.integerScanner("Selecciona una opción:");

            switch (option) {
                case 1 -> restaurantController.addRestaurant();
                case 2 -> restaurantController.removeRestaurant();
                case 3 -> restaurantController.listRestaurants();
                case 4 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (option != 4);

    }
}