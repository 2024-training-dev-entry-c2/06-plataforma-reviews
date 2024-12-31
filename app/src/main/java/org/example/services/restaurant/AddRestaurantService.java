package org.example.services.restaurant;

import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;

public class AddRestaurantService implements ICommand<Restaurant> {

    private final RestaurantRepository repository;
    private Restaurant restaurant;

    public AddRestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public Restaurant execute() {
        if (restaurant == null) {
            throw new IllegalStateException("Restaurant data cannot be null");
        }
        repository.addRestaurant(restaurant);
        return restaurant;
    }
}