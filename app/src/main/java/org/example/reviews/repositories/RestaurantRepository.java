package org.example.reviews.repositories;

import org.example.reviews.models.Restaurant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Manuel Aguilera / @aguileradev
 */
public class RestaurantRepository {
    private Map<Integer, Restaurant> restaurants;

    private RestaurantRepository() {
         this.restaurants= new HashMap<>();
    }
    private static class RestaurantRepositoryHolder {
        private static final RestaurantRepository INSTANCE = new RestaurantRepository();
    }

    public static RestaurantRepository getInstance() {
        return RestaurantRepositoryHolder.INSTANCE;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.put(restaurant.getId(), restaurant);
    }


    public Map<Integer, Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Map<Integer, Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
