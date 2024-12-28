package org.example.services.utils;

import org.example.controllers.restaurant.RestaurantController;

public class MenuDishFood {
    public static IValidatorScanner validatorScanner;
    public MenuDishFood(IValidatorScanner validatorScanner) {
        this.validatorScanner = validatorScanner;
    }

    public void showMenu() {
        RestaurantController restaurantController = new RestaurantController(validatorScanner);
        int option;
        do {
            System.out.println("\n===== Gestión de Menús =====");
            System.out.println("1. Añadir Plato");
            System.out.println("2. Eliminar Plato");
            System.out.println("3. Volver al Menú Principal");

            option = validatorScanner.integerScanner("Selecciona una opción:");



        } while (option != 4);

    }
}