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

    public void save(Restaurant restaurant) {
        restaurants.merge(restaurant.getId(), restaurant, (existing, newRestaurant) ->{
            existing.setName(compareNames(existing, newRestaurant));
            existing.setAddress(compareAddress(existing, newRestaurant));
            existing.setSchedule(compareSchedule(existing, newRestaurant));
            return existing;
        });
    }

    private String compareSchedule(Restaurant existing, Restaurant newRestaurant) {
        return existing.getSchedule() != null && !newRestaurant.getSchedule().isEmpty() ? existing.getSchedule() : newRestaurant.getSchedule();
    }

    private String compareAddress(Restaurant existing, Restaurant newRestaurant) {
        return existing.getAddress() != null && !newRestaurant.getAddress().isEmpty() ? existing.getAddress() : newRestaurant.getAddress();
    }

    private String compareNames(Restaurant existing, Restaurant newRestaurant) {
        return existing.getName() != null && !newRestaurant.getName().isEmpty() ? existing.getName() : newRestaurant.getName();
    }


    public Map<Integer, Restaurant> getRestaurants() {
        return restaurants;
    }
    public Restaurant findRestaurantById(Integer id) {
        Restaurant restaurant;
        try{
            restaurant = restaurants.get(id);
        }catch (NullPointerException e){
            System.out.println("Restaurante con el id "+id+" no encontrado");
            restaurant = null;
        }
        return restaurant;
    }

    public Restaurant findRestaurantByName(String name) {
        return restaurants.values().stream().filter(restaurant -> restaurant.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public Boolean removeRestaurant(Integer id) {
        return restaurants.remove(id) != null;
    }

    public void setRestaurants(Map<Integer, Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
