package org.example.reviews.controllers.restaurant;

import org.example.reviews.controllers.interfaces.IController;
import org.example.reviews.services.restaurants.RestaurantService;

public class RemoveRestaurantController implements IController {
    private RestaurantService restaurantService;

    public RemoveRestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void execute() {
        Boolean isRemoved = restaurantService.removeRestaurant();
        if (isRemoved) {
            System.out.println("Operacion terminada con exito");
        } else {
            System.out.println("Operacion fallida");
        }
    }
}
