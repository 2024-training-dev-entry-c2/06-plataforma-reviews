package org.example.reviews.controllers.dish;

import org.example.reviews.controllers.interfaces.IController;
import org.example.reviews.utils.ConsoleUtil;

import java.util.Map;

public class DishController implements IController {
    private final CreateDishController createDishController;
    private final FindDishesController findDishesController;
    private final UpdateDishController updateDishController;
    private ConsoleUtil console;

    public DishController(CreateDishController createDishController, FindDishesController findDishesController, UpdateDishController updateDishController, ConsoleUtil console) {
        this.createDishController = createDishController;
        this.findDishesController = findDishesController;
        this.updateDishController = updateDishController;
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
        if (option != 9) {
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
