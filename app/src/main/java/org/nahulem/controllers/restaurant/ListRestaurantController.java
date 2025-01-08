package org.nahulem.controllers.restaurant;

import org.nahulem.controllers.interfaces.ICommandController;
import org.nahulem.models.Restaurant;
import org.nahulem.services.restaurant.ListRestaurantService;
import org.nahulem.utils.Validator;

import java.util.List;
import java.util.Scanner;

public class ListRestaurantController implements ICommandController {
    private final ListRestaurantService listRestaurantService;
    private final Validator validator = new Validator(new Scanner(System.in));

    public ListRestaurantController(ListRestaurantService listRestaurantService) {
        this.listRestaurantService = listRestaurantService;
    }

    @Override
    public void execute() {
        List<Restaurant> restaurants = listRestaurantService.execute();
        if (restaurants.isEmpty()) {
            validator.printMessage("No hay restaurantes registrados");
            return;
        }
        showList(restaurants);
    }

    private void showList(List<Restaurant> restaurants) {
        validator.printMessage("Listado de restaurantes: ");
        restaurants.forEach(restaurant -> validator.printMessage(restaurant.toString()));
    }
}
