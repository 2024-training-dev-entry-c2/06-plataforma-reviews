package org.example.controllers.restaurant;

import org.example.controllers.interfaces.ICommandController;
import org.example.models.Restaurant;
import org.example.services.restaurant.ListRestaurantService;

import java.util.List;

public class ListRestaurantController implements ICommandController {
    private final ListRestaurantService listRestaurantService;

    public ListRestaurantController(ListRestaurantService listRestaurantService) {
        this.listRestaurantService = listRestaurantService;
    }

    @Override
    public void execute() {
        List<Restaurant> restaurants = listRestaurantService.execute();
        if (restaurants.isEmpty()) {
            System.out.println("No hay restaurantes registrados");
            return;
        }
        System.out.println("Listado de restaurantes: ");
        restaurants.forEach(restaurant -> System.out.println(restaurant.toString()));
    }
}
