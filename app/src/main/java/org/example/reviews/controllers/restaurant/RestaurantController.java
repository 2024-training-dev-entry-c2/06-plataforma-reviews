package org.example.reviews.controllers.restaurant;

import org.example.reviews.controllers.interfaces.IController;
import org.example.reviews.utils.ConsoleUtil;

import java.util.Map;

public class RestaurantController implements IController {
    private CreateRestaurantController createRestaurantController;
    private FindRestaurantsController findRestaurantsController;
    private UpdateRestaurantController updateRestaurantController;
    private RemoveRestaurantController removeRestaurantController;
    private ConsoleUtil console;

    public RestaurantController(CreateRestaurantController createRestaurantController,
                                FindRestaurantsController findRestaurantsController,
                                UpdateRestaurantController updateRestaurantController,
                                RemoveRestaurantController removeRestaurantController,
                                ConsoleUtil console) {
        this.createRestaurantController = createRestaurantController;
        this.findRestaurantsController = findRestaurantsController;
        this.updateRestaurantController = updateRestaurantController;
        this.removeRestaurantController = removeRestaurantController;
        this.console = console;
    }
    @Override
    public void execute() {
        Map<Integer, IController> controllers = Map.of(
                1, createRestaurantController,
                2, findRestaurantsController,
                3, updateRestaurantController,
                4, removeRestaurantController
        );
        int option = 0;
        do {
            showRestaurantSubMenu();
            option = console.readInt("Seleccione una opcion: ");
            executeController(option, controllers);
        }while (option != 9);

        console.writeLine("Volviendo al menu principal...");

    }

    private void executeController(int option, Map<Integer, IController> controllers) {
        if (option != 9) {
            controllers.get(option).execute();
        }
    }


    public void showRestaurantSubMenu(){
        console.writeLine("1. Crear restaurante");
        console.writeLine("2. Mostrar restaurantes");
        console.writeLine("3. Actualizar restaurante");
        console.writeLine("4. Eliminar restaurante");
        console.writeLine("9. Volver menu principal");
    }

}
