package org.example.reviews.controllers.dish;

import org.example.reviews.controllers.interfaces.IController;
import org.example.reviews.services.dishes.DishesService;
import org.example.reviews.utils.ConsoleUtil;

import java.util.Map;

public class DishController implements IController {
    private final CreateDishController createDishController;
    private final FindDishesController findDishesController;
    private final UpdateDishController updateDishController;
    private ConsoleUtil console;

    public DishController(DishesService dishesService, ConsoleUtil console) {
        this.createDishController = new CreateDishController(dishesService);
        this.findDishesController = new FindDishesController(dishesService);
        this.updateDishController = new UpdateDishController(dishesService);
        this.console = console;
    }

    @Override
    public void execute() {
        Map<Integer, IController> controllers = Map.of(
                1, createDishController,
                2, findDishesController,
                3, updateDishController

        );
        int option = 0;
        do {
            showDishesSubMenu();
            option = console.readInt("Seleccione una opcion: ");
            executeController(option, controllers);
        }while (option != 9);

        console.writeLine("Volviendo al menu anterior...");

    }

    private void executeController(int option, Map<Integer, IController> controllers) {
        if (option < 4 && option > 0) {
            controllers.get(option).execute();
        }
    }

    public void showDishesSubMenu(){
        console.writeLine("1. Crear plato");
        console.writeLine("2. Mostrar platos disponibles");
        console.writeLine("3. Actualizar plato");
        console.writeLine("9. Volver menu anterior");
    }
}
