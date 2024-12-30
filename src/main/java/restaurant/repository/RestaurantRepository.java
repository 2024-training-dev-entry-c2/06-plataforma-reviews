package restaurant.repository;

import restaurant.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantRepository {
    private List<Restaurant> restaurants = new ArrayList<>();
    private static RestaurantRepository instance;

    private RestaurantRepository() {}

    public static RestaurantRepository getInstance() {
        if (instance == null) {
            instance = new RestaurantRepository();
        }
        return instance;
    }

    public Restaurant getRestaurant(String name) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equals(name)) {
                return restaurant;
            }
        }
        return null;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public Boolean removeRestaurant(Restaurant restaurant) {
        return restaurants.remove(restaurant);
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void updateRestaurant(Restaurant restaurant) {
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).getName().equals(restaurant.getName())) {
                restaurants.set(i, restaurant);
                break;
            }
        }
    }
}