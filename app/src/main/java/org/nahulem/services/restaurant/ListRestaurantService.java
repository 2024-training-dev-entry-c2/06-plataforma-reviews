package org.nahulem.services.restaurant;

import org.nahulem.models.Restaurant;
import org.nahulem.repositories.DataRepository;
import org.nahulem.services.interfaces.ICommand;

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
