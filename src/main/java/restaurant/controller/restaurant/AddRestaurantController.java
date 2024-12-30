package restaurant.controller.restaurant;

import restaurant.controller.interfaces.IController;
import restaurant.models.Restaurant;
import restaurant.service.restaurant.AddRestaurant;

public class AddRestaurantController implements IController {
    private final AddRestaurant command;

    public AddRestaurantController(AddRestaurant command) {
        this.command = command;
    }


    @Override
    public void execute() {
        Restaurant restaurant = command.execute();
        System.out.println("Se registro el restaurante:");
    }
}