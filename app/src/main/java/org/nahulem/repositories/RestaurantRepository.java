package org.nahulem.repositories;

import org.nahulem.models.Restaurant;
import org.nahulem.models.RestaurantReview;
import org.nahulem.models.Review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantRepository {
    private static RestaurantRepository instance;
    private Map<Integer, Restaurant> restaurants;

    private RestaurantRepository() {
        this.restaurants = new HashMap<>();
        initialize();
    }

    public static RestaurantRepository getInstance() {
        if (instance == null) {
            instance = new RestaurantRepository();
        }
        return instance;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.put(restaurant.getRestaurantId(), restaurant);
    }

    public Restaurant getRestaurant(Integer id) {
        return restaurants.get(id);
    }

    public Map<Integer, Restaurant> getAllRestaurants() {
        return restaurants;
    }

    public Boolean deleteRestaurant(Integer id) {
        return restaurants.remove(id) != null;
    }

    public Boolean updateRestaurant(Restaurant updatedRestaurant) {
        Integer restaurantId = updatedRestaurant.getRestaurantId();
        if (restaurants.containsKey(restaurantId)) {
            restaurants.put(restaurantId, updatedRestaurant);
            return true;
        }
        return false;
    }

    public void addRestaurantReview(Integer restaurantId, Review review) {
        Restaurant restaurant = getRestaurant(restaurantId);
        if (restaurant != null && review instanceof RestaurantReview) {
            restaurant.addReview((RestaurantReview) review);
        }
    }

    public List<Review> getRestaurantReviews(Integer restaurantId) {
        Restaurant restaurant = getRestaurant(restaurantId);
        return restaurant != null ? new ArrayList<>(restaurant.getReviews()) : new ArrayList<>();
    }

    public void clear() {
        restaurants.clear();
    }

    private void initialize() {
        MenuRepository menuRepository = MenuRepository.getInstance();

        Restaurant parrilla = new Restaurant("La Parrilla Porteña", "Hermosa parrillada en barrio clásico de Buenos Aires", "San Telmo, Buenos Aires", menuRepository.getMenu(1));
        Restaurant pastas = new Restaurant("Casa de Pastas Don Pepe", "Pastas caseras en Buenos Aires", "Recoleta, Buenos Aires", menuRepository.getMenu(2));
        Restaurant urbana = new Restaurant("Cocina Urbana", "Comida rápida y saludable en Buenos Aires", "Palermo, Buenos Aires", menuRepository.getMenu(3));

        parrilla.addReview(new RestaurantReview("Excelente atención y calidad.", 5.0f, 4.8f, 4.9f));
        parrilla.addReview(new RestaurantReview("Muy buena parrilla, pero algo cara.", 4.5f, 4.4f, 4.3f));

        pastas.addReview(new RestaurantReview("Las pastas estaban excelentes.", 4.9f, 5.0f, 4.8f));
        pastas.addReview(new RestaurantReview("Ambiente acogedor.", 4.7f, 4.5f, 4.6f));

        urbana.addReview(new RestaurantReview("Rápido y muy rico.", 4.8f, 4.7f, 4.6f));
        urbana.addReview(new RestaurantReview("Me encantó el ambiente moderno.", 4.6f, 4.5f, 4.7f));

        addRestaurant(parrilla);
        addRestaurant(pastas);
        addRestaurant(urbana);
    }
}
