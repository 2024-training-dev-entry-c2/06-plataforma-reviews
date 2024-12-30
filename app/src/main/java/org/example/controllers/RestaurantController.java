package org.example.controllers;

import org.example.models.Restaurant;
import org.example.services.restaurant.AddRestaurantService;
import org.example.services.restaurant.RemoveRestaurantService;
import org.example.utils.consoleUtils.ConsoleUtils;

public class RestaurantController {
    private final AddRestaurantService addRestaurantService;
    private final RemoveRestaurantService removeRestaurantService;
    private final ConsoleUtils console;

    public RestaurantController(AddRestaurantService addRestaurantService, RemoveRestaurantService removeRestaurantService, ConsoleUtils console) {
        this.addRestaurantService = addRestaurantService;
        this.removeRestaurantService = removeRestaurantService;
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

    public void removeRestaurant() {
        try {
            String name = console.getString("Entra el nombre del restaurante que deseas eliminar: ");
            Restaurant restaurant = removeRestaurantService.findByName(name);
            if (restaurant != null) {
                removeRestaurantService.setRestaurant(restaurant);
                removeRestaurantService.execute();
                System.out.println("Restaurante eliminado: " + restaurant.getName());
            } else {
                System.out.println("Restaurante no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar el restaurante: " + e.getMessage());
        }
    }
}