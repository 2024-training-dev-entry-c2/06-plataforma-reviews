package org.example.reviews.controllers.restaurant;

import org.example.reviews.controllers.interfaces.IController;
import org.example.reviews.utils.ConsoleUtil;

import java.util.Map;

public class RestaurantController implements IController {
    private CreateRestaurantController createRestaurantController;
    private FindRestaurantsController findRestaurantsController;
    private ConsoleUtil console;

    public RestaurantController(CreateRestaurantController createRestaurantController, FindRestaurantsController findRestaurantsController, ConsoleUtil console) {
        this.createRestaurantController = createRestaurantController;
        this.findRestaurantsController = findRestaurantsController;
        this.console = console;
    }
    @Override
    public void execute() {
        Map<Integer, IController> controllers = Map.of(
                1, createRestaurantController,
                2, findRestaurantsController
        );
        int option = 0;
        do {
            showSubMenuRestaurant();
            option = console.readInt("Seleccione una opcion: ");
            executeController(option, controllers);
        }while (option != 5);

        console.writeLine("Volviendo al menu principal...");

    }

    private void executeController(int option, Map<Integer, IController> controllers) {
        if (option != 5) {
            controllers.get(option).execute();
        }
    }


    public void showSubMenuRestaurant(){
        console.writeLine("1. Crear restaurante");
        console.writeLine("2. Mostrar restaurantes");
        console.writeLine("3. Actualizar restaurante");
        console.writeLine("4. Eliminar restaurante");
        console.writeLine("5. Volver menu principal");
    }

}
