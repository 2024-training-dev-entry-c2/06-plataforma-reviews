package org.example.controllers;

import org.example.models.Dish;
import org.example.models.Restaurant;
import org.example.observer.ConsoleNotifier;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;
import org.example.services.menu.AddDishToMenuService;
import org.example.services.menu.RemoveFromMenuService;
import org.example.utils.consoleUtils.ConsoleUtils;

import java.util.ArrayList;

public class MenuController {
    private final AddDishToMenuService addDishToMenuService;
    private final RemoveFromMenuService removeDishFromMenuService;
    private final ConsoleUtils console;
    private final RestaurantRepository restaurantRepository;

    public MenuController(AddDishToMenuService addDishToMenuService, RemoveFromMenuService removeFromMenuService, ConsoleUtils console, RestaurantRepository restaurantRepository) {
        this.addDishToMenuService = addDishToMenuService;
        this.removeDishFromMenuService = removeFromMenuService;
        this.console = console;
        this.restaurantRepository = restaurantRepository;

        MenuRepository menuRepository = MenuRepository.getInstance();
        menuRepository.addObserver(new ConsoleNotifier());
    }

    public void addDishToMenu() {
        try {
            String restaurantName = console.getString("Ingrese el nombre del restaurante: ");
            Restaurant restaurant = restaurantRepository.findByName(restaurantName);
            if (restaurant == null) {
                System.out.println("Restaurante no encontrado.");
                return;
            }

            String dishName = console.getString("Ingrese el nombre del plato: ");
            String dishDescription = console.getString("Ingrese la descripción del plato: ");
            double dishPrice = console.getDouble("Ingrese el precio del plato: ");
            Dish dish = new Dish(dishName, dishDescription, dishPrice, new ArrayList<>());

            addDishToMenuService.setRestaurant(restaurant);
            addDishToMenuService.setDish(dish);
            addDishToMenuService.execute();

            System.out.println("Plato agregado al menú.");
        } catch (Exception e) {
            System.out.println("Error al agregar el plato al menú: " + e.getMessage());
        }
    }

    public void removeDishFromMenu() {
        try {
            String restaurantName = console.getString("Ingrese el nombre del restaurante: ");
            Restaurant restaurant = restaurantRepository.findByName(restaurantName);
            if (restaurant == null) {
                System.out.println("Restaurante no encontrado");
                return;
            }

            String dishName = console.getString("Ingrese el nombre del plato a eliminar: ");
            Dish dish = restaurant.getMenu().getDishes().stream()
                    .filter(d -> d.getName().equals(dishName))
                    .findFirst()
                    .orElse(null);

            if (dish == null) {
                System.out.println("Plato no encontrado en el menú");
                return;
            }

            removeDishFromMenuService.setRestaurant(restaurant);
            removeDishFromMenuService.setDish(dish);
            removeDishFromMenuService.execute();
            System.out.println("Plato eliminado del menú");
        } catch (Exception e) {
            System.out.println("Error al eliminar el plato del menú: " + e.getMessage());
        }
    }
}