package org.example.repositories;


import org.example.models.*;

import java.util.*;

public class RestaurantRepository {
    private static RestaurantRepository instance;
    private final Map<String, Restaurant> restaurants = new HashMap<>();

    public RestaurantRepository() {
    }

    public static synchronized RestaurantRepository getInstance() {
        if (instance == null) {
            instance = new RestaurantRepository();
        }
        return instance;
    }

    public void addRestaurant(Restaurant restaurant) {
        this.restaurants.merge(restaurant.getName(), restaurant, (existing, newRestaurant) -> {
            System.out.println("Restaurante :" + newRestaurant.getName() + " Ya existe!!");
            return existing;
        });

    }

    public boolean removeRestaurant(String name){
        if (this.restaurants.containsKey(name)){
            Restaurant removedRestaurant = this.restaurants.remove(name);
            removedRestaurant.notifyObservers("Restaurante eliminado: " + name);
            return true;
        }
        return false;
    }


    public Restaurant getRestaurant(String name) {
        return restaurants.get(name);
    }

    public Map<String, Restaurant> getAllRestaurants() {
        return restaurants;
    }

    public void addReview(String restaurantName,User user,IReview review){
        Restaurant restaurant = restaurants.get(restaurantName);
        restaurant.addObserver(user);
        restaurant.addReview(user,review);
    }

    public void showReview(Restaurant restaurant){
        restaurant.getReviewMap().forEach((user, reviewRestaurant) -> {
            System.out.println("Usuario: " + user.getName());
            reviewRestaurant.showDetails();
            System.out.println("----------------------------");
        });
    }




}
