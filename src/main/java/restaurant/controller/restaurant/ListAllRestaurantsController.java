// src/main/java/restaurant/controller/restaurant/ListAllRestaurantsController.java
package restaurant.controller.restaurant;

import restaurant.controller.interfaces.IController;
import restaurant.service.restaurant.ListAllRestaurants;

public class ListAllRestaurantsController implements IController {
    private final ListAllRestaurants command;

    public ListAllRestaurantsController(ListAllRestaurants command) {
        this.command = command;
    }

    @Override
    public void execute() {
        command.execute();
    }
}