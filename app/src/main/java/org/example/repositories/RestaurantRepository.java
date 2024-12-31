package org.example.repositories;

import org.example.models.Restaurant;


import java.util.LinkedList;
import java.util.List;

public class RestaurantRepository {
    private static RestaurantRepository instance;
    private final List<Restaurant> restaurants;

    private RestaurantRepository() {
        restaurants = new LinkedList<>();
    }

    public static RestaurantRepository getInstance() {
        if (instance == null) {
            instance = new RestaurantRepository();
        }
        return instance;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public void removeRestaurant(Restaurant restaurant) {
        restaurants.remove(restaurant);
    }

    public Restaurant findByName(String name) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equals(name)) {
                return restaurant;
            }
        }
        return null;
    }

}