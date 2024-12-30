// src/main/java/restaurant/Controller/Dish/EditDishController.java
package restaurant.controller.dish;

import restaurant.controller.interfaces.IController;
import restaurant.service.dish.EditDish;

public class EditDishController implements IController {
    private final EditDish command;

    public EditDishController(EditDish command) {
        this.command = command;
    }

    @Override
    public void execute() {
        command.execute();
    }
}