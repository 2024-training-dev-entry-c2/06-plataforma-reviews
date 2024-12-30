
package restaurant.controller.dish;

import restaurant.controller.interfaces.IController;
import restaurant.service.dish.AddDish;

public class AddDishController implements IController {
    private final AddDish command;

    public AddDishController(AddDish command) {
        this.command = command;
    }

    @Override
    public void execute() {
        command.execute();
    }
}