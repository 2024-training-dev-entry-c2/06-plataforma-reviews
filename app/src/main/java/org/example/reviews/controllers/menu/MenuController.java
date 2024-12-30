package org.example.reviews.controllers.menu;

import org.example.reviews.controllers.dish.DishController;
import org.example.reviews.controllers.interfaces.IController;
import org.example.reviews.utils.ConsoleUtil;

import java.util.Map;

public class MenuController implements IController {
    private final CreateMenuController createMenuController;
    private final FindMenusController findMenusController;
    private final DishController dishController;
    private ConsoleUtil console;


    public MenuController(
            CreateMenuController createMenuController,
            FindMenusController findMenusController,
            DishController dishController,
            ConsoleUtil console) {

        this.createMenuController = createMenuController;
        this.findMenusController = findMenusController;
        this.dishController = dishController;
        this.console = console;
    }

    @Override
    public void execute() {
        Map<Integer, IController> controllers = Map.of(
                1, createMenuController,
                2, findMenusController,
                3, dishController

        );
        int option = 0;
        do {
            showSubMenu();
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

    public void showSubMenu(){
        System.out.println();
        console.writeLine("1. Crear menu");
        console.writeLine("2. Mostrar menus disponibles");
        console.writeLine("3. Gestionar platos");
        console.writeLine("9. Volver menu principal");
    }
}
