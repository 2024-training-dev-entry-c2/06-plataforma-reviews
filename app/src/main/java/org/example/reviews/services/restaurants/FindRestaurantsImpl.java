package org.example.reviews.services.restaurants;

import org.example.reviews.models.Restaurant;
import org.example.reviews.repositories.RestaurantRepository;
import org.example.reviews.services.interfaces.ICommand;

import java.util.Map;


public class FindRestaurantsImpl implements ICommand<Map<Integer, Restaurant>> {
    private RestaurantRepository restaurantRepository;

    public FindRestaurantsImpl() {
        this.restaurantRepository = RestaurantRepository.getInstance();
    }

    @Override
    public Map<Integer, Restaurant> execute() {
        return restaurantRepository.getRestaurants();
    }
}
