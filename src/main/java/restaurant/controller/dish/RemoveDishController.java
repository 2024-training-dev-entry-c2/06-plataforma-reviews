package restaurant.controller.dish;

import restaurant.controller.interfaces.IController;
import restaurant.service.dish.RemoveDish;

public class RemoveDishController implements IController {
    private final RemoveDish command;

    public RemoveDishController(RemoveDish command) {
        this.command = command;
    }

    @Override
    public void execute() {
        command.execute();
    }
}