package org.example;

import main.java.org.example.controllers.RestaurantController;
import main.java.org.example.services.restaurant.AddRestaurantService;
import main.java.org.example.utils.consoleUtils.ConsoleUtils;
import main.java.org.example.repositories.RestaurantRepository;

public class App {
    public static void main(String[] args) {
        RestaurantRepository repository = RestaurantRepository.getInstance();
        AddRestaurantService addRestaurantService = new AddRestaurantService(repository);
        ConsoleUtils console = new ConsoleUtils();
        RestaurantController restaurantController = new RestaurantController(addRestaurantService, console);

        restaurantController.addRestaurant();
    }
}
