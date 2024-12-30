package restaurant.controller.restaurant;

import restaurant.controller.interfaces.IController;
import restaurant.service.restaurant.EditRestaurant;

public class EditRestaurantController implements IController {
    private final EditRestaurant command;

    public EditRestaurantController(EditRestaurant command) {
        this.command = command;
    }

    @Override
    public void execute() {
        command.execute();
    }
}
