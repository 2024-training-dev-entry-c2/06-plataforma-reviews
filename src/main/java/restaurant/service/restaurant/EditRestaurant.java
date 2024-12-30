package restaurant.service.restaurant;

import restaurant.models.Restaurant;
import restaurant.repository.RestaurantRepository;
import restaurant.service.Interfaces.ICommand;
import restaurant.utils.ConsoleUtils;
import restaurant.utils.RestaurantUtils;

import java.util.List;

public class EditRestaurant implements ICommand<Restaurant> {
    private final RestaurantRepository repository;
    private final ConsoleUtils console;
    private final RestaurantUtils restaurantUtils = new RestaurantUtils();

    public EditRestaurant(RestaurantRepository repository, ConsoleUtils console) {
        this.repository = repository;
        this.console = console;
    }

    @Override
    public Restaurant execute() {
        List<Restaurant> restaurants = repository.getRestaurants();
        if (restaurants.isEmpty()) {
            System.out.println("No hay restaurantes registrados.");
            return null;
        }

        restaurantUtils.showRestaurants(restaurants);

        return listRestaurant(restaurants);
    }

    private Restaurant listRestaurant(List<Restaurant> restaurants) {
        int index = restaurantUtils.getRestaurantIndex(console, restaurants, "Introduce el número del restaurante que deseas editar: ");
        if (index != -1) {
            Restaurant restaurant = restaurants.get(index);
            String newName = console.getString("Introduce el nuevo nombre del restaurante: ");
            String newAddress = console.getString("Introduce la nueva dirección del restaurante: ");
            restaurant.setName(newName);
            restaurant.setAddress(newAddress);
            System.out.println("Restaurante " + restaurant.getName() + " editado.");
            return restaurant;
        } else {
            return null;
        }
    }
}