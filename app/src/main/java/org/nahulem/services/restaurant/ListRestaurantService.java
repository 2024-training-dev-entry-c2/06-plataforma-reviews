package org.nahulem.services.restaurant;

import org.nahulem.models.Restaurant;
import org.nahulem.repositories.RestaurantRepository;
import org.nahulem.services.interfaces.ICommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListRestaurantService implements ICommand<List<Restaurant>> {
private final RestaurantRepository repository;

    public ListRestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Restaurant> execute() {
        Map<Integer, Restaurant> restaurantMap = repository.getAllRestaurants();

        return new ArrayList<>(restaurantMap.values());
    }
}
