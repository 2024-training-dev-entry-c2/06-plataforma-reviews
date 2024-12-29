package org.example.reviews.controllers.restaurant;

import org.example.reviews.controllers.interfaces.IController;
import org.example.reviews.models.Restaurant;
import org.example.reviews.services.restaurants.RestaurantService;

import java.util.Map;

public class FindRestaurantsController implements IController {
    private RestaurantService restaurantService;

    public FindRestaurantsController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }
    @Override
    public void execute() {
        Map<Integer, Restaurant> restaurants = restaurantService.findAllRestaurants();
        restaurants.forEach((id, restaurant) -> System.out.println(restaurant));
    }
}
