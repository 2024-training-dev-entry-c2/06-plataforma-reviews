package restaurant.service.restaurant;

import restaurant.models.Restaurant;
import restaurant.repository.RestaurantRepository;
import restaurant.service.Interfaces.ICommand;

import java.util.List;

public class ListAllRestaurants implements ICommand<Void> {
    private final RestaurantRepository restaurantRepository;

    public ListAllRestaurants(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Void execute() {
        List<Restaurant> restaurants = restaurantRepository.getRestaurants();
        if (restaurants.isEmpty()) {
            System.out.println("No hay restaurantes disponibles.");
        } else {
            for (Restaurant restaurant : restaurants) {
                System.out.println("Nombre: " + restaurant.getName());
                System.out.println("Ubicación: " + restaurant.getAddress());
                System.out.println("Calificación actual: " + restaurant.getAverageRating());
                System.out.println("-----");
            }
        }
        return null;
    }
}