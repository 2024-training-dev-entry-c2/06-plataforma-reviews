package org.example.reviews.utils;

import org.example.reviews.controllers.interfaces.IController;
import org.example.reviews.controllers.menu.MenuController;
import org.example.reviews.controllers.restaurant.RestaurantController;
import org.example.reviews.controllers.reviews.ReviewController;

import java.util.Map;

public class AppMenu {
    private final RestaurantController restaurantController;
    private final MenuController menuController;
    private final ReviewController reviewController;
    private final Runnable exitAction;
    private ConsoleUtil console;

    public AppMenu(RestaurantController restaurantController, MenuController menuController, ReviewController reviewController, Runnable exitAction, ConsoleUtil console) {
        this.restaurantController = restaurantController;
        this.menuController = menuController;
        this.reviewController = reviewController;
        this.exitAction = exitAction;
        this.console = console;
    }

    public void showMenu() {
        console.writeLine("Bienvenido a la app Restaurant Reviews\n");
        console.writeLine("1. Gestion de restaurantes");
        console.writeLine("2. Gestion de menus");
        console.writeLine("3. Gestion de reviews");
        console.writeLine("4. Salir");
    }

    public void execute(){
        Map<Integer, IController> controllers = Map.of(
                1, restaurantController,
                2, menuController,
                3, reviewController
        );
        int option = 0;

        do {
            showMenu();
            option = console.readInt("Seleccione una opcion: ");
            executeController(option, controllers);
        } while (option != 4);

        exit();
    }

    public void executeController(int option, Map<Integer, IController> controllers) {
        if(option > 0 && option < 4){
            controllers.get(option).execute();
        }
    }

    public void exit() {
        console.writeLine("Gracias por usar la app");
        exitAction.run();
    }
}
