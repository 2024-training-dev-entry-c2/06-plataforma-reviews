package main.java.org.example.repositories;


import main.java.org.example.models.Restaurant;

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

}