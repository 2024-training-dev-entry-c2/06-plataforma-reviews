package org.example;

import org.example.controllers.MenuController;
import org.example.controllers.RestaurantController;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;
import org.example.services.menu.AddDishToMenuService;
import org.example.services.menu.RemoveFromMenuService;
import org.example.services.restaurant.AddRestaurantService;
import org.example.services.restaurant.DisplayRestaurantsService;
import org.example.services.restaurant.RemoveRestaurantService;
import org.example.utils.consoleUtils.ConsoleUtils;

public class App {
    public static void main(String[] args) {

        // Repositories
        RestaurantRepository restaurantRepository = RestaurantRepository.getInstance();
        MenuRepository menuRepository = MenuRepository.getInstance();

        // Restaurant Services
        AddRestaurantService addRestaurantService = new AddRestaurantService(restaurantRepository);
        RemoveRestaurantService removeRestaurantService = new RemoveRestaurantService(restaurantRepository);
        DisplayRestaurantsService displayRestaurantsService = new DisplayRestaurantsService(restaurantRepository);

        // Menu Services
        AddDishToMenuService addDishToMenuService = new AddDishToMenuService(menuRepository);
        RemoveFromMenuService removeFromMenuService = new RemoveFromMenuService(menuRepository);

        // Utils
        ConsoleUtils console = new ConsoleUtils();

        // Controllers
        RestaurantController restaurantController = new RestaurantController(addRestaurantService, removeRestaurantService, displayRestaurantsService, console);
        MenuController menuController = new MenuController(addDishToMenuService, removeFromMenuService, console, restaurantRepository);

        // Restaurant operations
        restaurantController.addRestaurant();
        restaurantController.displayRestaurants();
        restaurantController.removeRestaurant();
        restaurantController.displayRestaurants();

        // Menu operations
        menuController.addDishToMenu();
        menuController.removeDishFromMenu();
    }
}