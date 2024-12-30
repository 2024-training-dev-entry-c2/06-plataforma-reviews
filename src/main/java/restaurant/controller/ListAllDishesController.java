package restaurant.controller;

import restaurant.controller.interfaces.IController;
import restaurant.service.dish.ListAllDishes;

public class ListAllDishesController implements IController {
    private final ListAllDishes command;

    public ListAllDishesController(ListAllDishes command) {
        this.command = command;
    }

    @Override
    public void execute() {
        command.execute();
    }
}