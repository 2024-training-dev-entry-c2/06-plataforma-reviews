package org.example.services.utils;
import org.example.controllers.menu.MenuController;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;


import java.util.Map;

public class MenuDishFood {
    public final IValidatorScanner validatorScanner;
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    public MenuDishFood(IValidatorScanner validatorScanner, RestaurantRepository restaurantRepository, MenuRepository menuRepository) {
        this.validatorScanner = validatorScanner;
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
    }

    public void showMenu() {
        MenuController menuController = new MenuController(restaurantRepository,menuRepository,validatorScanner);
        Map<Integer, Runnable> menuActions = Map.of(
                1, menuController::addDishFood,
                2, menuController::removeDishFood,
                3, menuController::ShowDishFood,
                4, () -> System.out.println("Volviendo al menú principal...")
        );
        int option;
        do {
            System.out.println("\n===== Gestión de Menús =====");
            System.out.println("1. Añadir Plato");
            System.out.println("2. Eliminar Plato");
            System.out.println("3. Listar platos de Restaurante");
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