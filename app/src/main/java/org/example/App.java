package org.example;

import org.example.controllers.RestaurantController;
import org.example.services.restaurant.AddRestaurantService;
import org.example.services.restaurant.DisplayRestaurantsService;
import org.example.services.restaurant.RemoveRestaurantService;
import org.example.utils.consoleUtils.ConsoleUtils;
import org.example.repositories.RestaurantRepository;

public class App {
    public static void main(String[] args) {

        // Repository
        RestaurantRepository repository = RestaurantRepository.getInstance();

        // Services
        AddRestaurantService addRestaurantService = new AddRestaurantService(repository);
        RemoveRestaurantService removeRestaurantService = new RemoveRestaurantService(repository);
        DisplayRestaurantsService displayRestaurantsService = new DisplayRestaurantsService(repository);

        // Utils
        ConsoleUtils console = new ConsoleUtils();

        // Controller
        RestaurantController restaurantController = new RestaurantController(addRestaurantService, removeRestaurantService, displayRestaurantsService ,console);

        restaurantController.addRestaurant();
        restaurantController.displayRestaurants();
        restaurantController.removeRestaurant();
        restaurantController.displayRestaurants();
    }
}
