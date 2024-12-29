package org.example.reviews.controllers.restaurant;

import org.example.reviews.controllers.interfaces.IController;
import org.example.reviews.models.Restaurant;
import org.example.reviews.services.restaurants.RestaurantService;

public class CreateRestaurantController implements IController {
    private RestaurantService restaurantService;

    public CreateRestaurantController(RestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }

    @Override
    public void execute() {
        System.out.println("---Creando restaurante---");
        Restaurant restaurant = restaurantService.createRestaurant();
        System.out.println("---Restaurante creado con exito---");
        System.out.println(restaurant);
    }
}
