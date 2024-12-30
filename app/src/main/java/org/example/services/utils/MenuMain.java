package org.example.services.utils;

import org.example.repositories.DishRepository;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;


public class MenuMain {
    public final IValidatorScanner validatorScanner;
    private final MenuRestaurant menuRestaurant;
    private final MenuDishFood menuDishFood;
    private final MenuReview menuReview;


    public MenuMain(IValidatorScanner validatorScanner) {
        RestaurantRepository restaurantRepository = RestaurantRepository.getInstance();
        MenuRepository menuRepository = MenuRepository.getInstance();
        DishRepository dishRepository = DishRepository.getInstance();
        this.validatorScanner = validatorScanner;
        this.menuRestaurant = new MenuRestaurant(validatorScanner, restaurantRepository);
        this.menuDishFood = new MenuDishFood(validatorScanner,restaurantRepository,menuRepository);
        this.menuReview = new MenuReview(validatorScanner,restaurantRepository,menuRepository,dishRepository);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== Menú Principal ===");
            System.out.println("1. Gestión de Restaurantes");
            System.out.println("2. Gestión de Menús");
            System.out.println("3. Reviews");
            System.out.println("0. Salir");
            int option = validatorScanner.integerScanner("Ingrese un Valor : ");

            switch (option) {
                case 1 -> menuRestaurant.showMenu();
                case 2 -> menuDishFood.showMenu();
                case 3 -> menuReview.showMenu();
                case 0 -> {
                    System.out.println("¡Gracias por usar la aplicación!");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }
}

