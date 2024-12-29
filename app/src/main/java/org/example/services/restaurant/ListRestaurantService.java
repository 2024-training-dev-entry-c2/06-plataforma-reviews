package org.example.services.restaurant;

import org.example.models.Restaurant;
import org.example.repositories.DataRepository;
import org.example.services.interfaces.ICommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListRestaurantService implements ICommand<List<Restaurant>> {
    private final DataRepository repository;

    public ListRestaurantService(DataRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Restaurant> execute() {
        Map<Integer, Restaurant> restaurantMap = repository.getAllRestaurants();

        return new ArrayList<>(restaurantMap.values());
    }
}
