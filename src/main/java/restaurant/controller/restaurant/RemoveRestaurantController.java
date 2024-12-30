package restaurant.controller.restaurant;

import restaurant.controller.interfaces.IController;
import restaurant.service.restaurant.RemoveRestaurant;

public class RemoveRestaurantController implements IController {
    private final RemoveRestaurant command;

    public RemoveRestaurantController(RemoveRestaurant command) {
        this.command = command;
    }

    @Override
    public void execute() {
        command.execute();
    }
}