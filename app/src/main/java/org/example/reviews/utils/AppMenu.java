package org.example.reviews.utils;

import org.example.reviews.controllers.interfaces.IController;
import org.example.reviews.controllers.restaurant.RestaurantController;

import java.util.Map;

public class AppMenu {
    private final RestaurantController restaurantController;
    private ConsoleUtil console;

    public AppMenu(RestaurantController restaurantController, ConsoleUtil console) {
        this.restaurantController = restaurantController;
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
                1, restaurantController
        );
        int option = 0;

        do {
            showMenu();
            option = console.readInt("Seleccione una opcion: ");
            executeController(option, controllers);
        } while (option != 4);

        exit();
    }

    private void executeController(int option, Map<Integer, IController> controllers) {
        if(option != 4){
            controllers.get(option).execute();
        }
    }

    public void exit(){
        console.writeLine("Gracias por usar la app");
        System.exit(0);
    }
}
