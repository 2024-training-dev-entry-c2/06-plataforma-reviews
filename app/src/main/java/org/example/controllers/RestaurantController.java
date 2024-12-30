package main.java.org.example.controllers;

import main.java.org.example.models.Restaurant;
import main.java.org.example.services.restaurant.AddRestaurantService;
import main.java.org.example.utils.consoleUtils.ConsoleUtils;

public class RestaurantController {
    private final AddRestaurantService addRestaurantService;
    private final ConsoleUtils console;

    public RestaurantController(AddRestaurantService addRestaurantService, ConsoleUtils console) {
        this.addRestaurantService = addRestaurantService;
        this.console = console;
    }

    public void addRestaurant() {
        try {
            String name = console.getString("Entra el nombre del restaurante: ");
            String phone = console.getString("Entra el teléfono del restaurante: ");
            String address = console.getString("Entra la dirección del restaurante: ");
            Integer available = console.getInteger("Entra la disponibilidad del restaurante: ");

            Restaurant restaurant = new Restaurant(name, phone, address, available, null, null, 0.0);
            addRestaurantService.setRestaurant(restaurant);
            addRestaurantService.execute();
            System.out.println("Restaurante agregado: " + restaurant.getName());
        } catch (Exception e) {
            System.out.println("Error al agregar el restaurante: " + e.getMessage());
        }
    }
}