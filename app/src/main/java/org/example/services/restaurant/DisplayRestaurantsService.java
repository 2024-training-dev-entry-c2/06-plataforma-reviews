// DisplayRestaurantsService.java
package org.example.services.restaurant;

import org.example.models.Dish;
import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;

import java.util.List;

public class DisplayRestaurantsService implements ICommand<List<Restaurant>> {
    private final RestaurantRepository repository;

    public DisplayRestaurantsService(RestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Restaurant> execute() {
        List<Restaurant> restaurants = repository.getRestaurants();
        if (restaurants.isEmpty()) {
            System.out.println("No hay restaurantes disponibles.");
        } else {
            System.out.println("Lista de restaurantes:");
            StringBuilder sb = new StringBuilder();
            for (Restaurant restaurant : restaurants) {
                printRestaurantDetails(sb, restaurant);
            }
            System.out.println(sb.toString());
        }
        return restaurants;
    }

    private void printRestaurantDetails(StringBuilder sb, Restaurant restaurant) {
        sb.append("Nombre: ").append(restaurant.getName()).append("\n");
        sb.append("Teléfono: ").append(restaurant.getPhone()).append("\n");
        sb.append("Dirección: ").append(restaurant.getAddress()).append("\n");
        sb.append("Disponibilidad: ").append(restaurant.getAvailable()).append("\n");
        printMenuDetails(sb, restaurant);
        printReviewDetails(sb, restaurant);
        sb.append("Calificación: ").append(restaurant.getRaiting()).append("\n");
        sb.append("-----------------------------\n");
    }

    private void printMenuDetails(StringBuilder sb, Restaurant restaurant) {
        if (restaurant.getMenu() == null) {
            sb.append("Menú: No disponible\n");
        } else {
            sb.append("Menú: ").append(restaurant.getMenu().getDishes().size()).append(" platos\n");
            for (Dish dish : restaurant.getMenu().getDishes()) {
                sb.append("  - Plato: ").append(dish.getName())
                        .append(", Descripción: ").append(dish.getDescription())
                        .append(", Precio: ").append(dish.getPrice()).append("\n");
            }
        }
    }

    private void printReviewDetails(StringBuilder sb, Restaurant restaurant) {
        if (restaurant.getReviews() == null || restaurant.getReviews().isEmpty()) {
            sb.append("Reseñas: No disponibles\n");
        } else {
            sb.append("Reseñas: ").append(restaurant.getReviews().size()).append(" reseñas\n");
        }
    }
}