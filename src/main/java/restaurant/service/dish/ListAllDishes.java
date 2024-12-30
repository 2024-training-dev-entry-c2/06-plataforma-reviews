package restaurant.service.dish;

import restaurant.models.Dish;
import restaurant.models.Menu;
import restaurant.models.Restaurant;
import restaurant.repository.RestaurantRepository;
import restaurant.service.Interfaces.ICommand;
import restaurant.utils.ConsoleUtils;

import java.util.List;

public class ListAllDishes implements ICommand<Void> {
    private final RestaurantRepository restaurantRepository;
    private final ConsoleUtils consoleUtils;

    public ListAllDishes(RestaurantRepository restaurantRepository, ConsoleUtils consoleUtils) {
        this.restaurantRepository = restaurantRepository;
        this.consoleUtils = consoleUtils;
    }

    @Override
    public Void execute() {
        String restaurantName = consoleUtils.getString("Introduce el nombre del restaurante: ");
        Restaurant restaurant = restaurantRepository.getRestaurant(restaurantName);
        if (restaurant != null) {
            Menu menu = restaurant.getMenu();
            List<Dish> dishes = menu.getDishes();
            checkDish(dishes);
        } else {
            System.out.println("Restaurante no encontrado");
        }
        return null;
    }

    private static void checkDish(List<Dish> dishes) {
        if (dishes.isEmpty()) {
            System.out.println("No hay platos disponibles en el menú.");
        } else {
            for (Dish dish : dishes) {
                System.out.println("Nombre: " + dish.getName());
                System.out.println("Descripción: " + dish.getDescription());
                System.out.println("Precio: " + dish.getPrice());
                System.out.println("Calificación: " + dish.getAverageRating());
                System.out.println("-----");
            }
        }
    }
}