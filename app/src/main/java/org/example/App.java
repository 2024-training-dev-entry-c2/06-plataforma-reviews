package org.example;

import org.example.controllers.RestaurantController;
import org.example.services.restaurant.AddRestaurantService;
import org.example.services.restaurant.RemoveRestaurantService;
import org.example.utils.consoleUtils.ConsoleUtils;
import org.example.repositories.RestaurantRepository;

public class App {
    public static void main(String[] args) {
        RestaurantRepository repository = RestaurantRepository.getInstance();
        AddRestaurantService addRestaurantService = new AddRestaurantService(repository);
        RemoveRestaurantService removeRestaurantService = new RemoveRestaurantService(repository);
        ConsoleUtils console = new ConsoleUtils();
        RestaurantController restaurantController = new RestaurantController(addRestaurantService, removeRestaurantService, console);

        restaurantController.addRestaurant();
        restaurantController.removeRestaurant();
    }
}
