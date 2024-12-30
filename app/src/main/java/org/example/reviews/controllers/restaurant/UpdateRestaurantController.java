package org.example.reviews.controllers.restaurant;

import org.example.reviews.controllers.interfaces.IController;
import org.example.reviews.models.Restaurant;
import org.example.reviews.services.restaurants.RestaurantService;

public class UpdateRestaurantController implements IController {
    private RestaurantService restaurantService;

    public UpdateRestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }
    @Override
    public void execute() {
        System.out.println("---Actualizando restaurante---");
        Restaurant restaurant = restaurantService.updateRestaurant();
        System.out.println(restaurant);
        System.out.println("---Restaurante actualizado con exito---");
    }
}
