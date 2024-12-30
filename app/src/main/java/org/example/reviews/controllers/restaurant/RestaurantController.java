package org.example.reviews.controllers.restaurant;

import org.example.reviews.controllers.interfaces.IController;
import org.example.reviews.services.restaurants.RestaurantService;
import org.example.reviews.utils.ConsoleUtil;

import java.util.Map;

public class RestaurantController implements IController {
    private CreateRestaurantController createRestaurantController;
    private FindRestaurantsController findRestaurantsController;
    private UpdateRestaurantController updateRestaurantController;
    private RemoveRestaurantController removeRestaurantController;
    private ConsoleUtil console;

    public RestaurantController(RestaurantService restaurantService, ConsoleUtil console){;
        this.console = console;
        this.createRestaurantController = new CreateRestaurantController(restaurantService);
        this.findRestaurantsController = new FindRestaurantsController(restaurantService);
        this.updateRestaurantController = new UpdateRestaurantController(restaurantService);
        this.removeRestaurantController = new RemoveRestaurantController(restaurantService);
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
        if (option < 5 && option > 0) {
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
