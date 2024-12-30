// src/main/java/restaurant/utils/RestaurantUtils.java
package restaurant.utils;

import restaurant.models.Menu;
import restaurant.models.Restaurant;
import restaurant.repository.RestaurantRepository;

import java.util.List;

public class RestaurantUtils {

    public void showRestaurants(List<Restaurant> restaurants) {
        System.out.println("Lista de restaurantes:");
        for (int i = 0; i < restaurants.size(); i++) {
            System.out.println((i + 1) + ". " + restaurants.get(i).getName());
        }
    }

    public int getRestaurantIndex(ConsoleUtils console, List<Restaurant> restaurants, String message) {
        int index = console.getInteger(message) - 1;
        if (index >= 0 && index < restaurants.size()) {
            return index;
        } else {
            System.out.println("Índice no válido.");
            return -1;
        }
    }

    public Menu selectMenu(RestaurantRepository repository, ConsoleUtils console) {
        List<Restaurant> restaurants = repository.getRestaurants();
        if (restaurants.isEmpty()) {
            System.out.println("No hay restaurantes registrados.");
            return null;
        }

        showRestaurants(restaurants);
        int restaurantIndex = getRestaurantIndex(console, restaurants, "Introduce el número del restaurante: ");
        if (restaurantIndex != -1) {
            Restaurant restaurant = restaurants.get(restaurantIndex);
            return restaurant.getMenu();
        } else {
            return null;
        }
    }

    public Restaurant getRestaurantByMenu(RestaurantRepository repository, Menu menu) {
        List<Restaurant> restaurants = repository.getRestaurants();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getMenu().equals(menu)) {
                return restaurant;
            }
        }
        return null;
    }
}