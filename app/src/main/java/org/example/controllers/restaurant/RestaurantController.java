package org.example.controllers.restaurant;

import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.restaurant.AddRestaurant;
import org.example.services.restaurant.RemoveRestaurant;

public class RestaurantController {
    public void addRestaurant(String name, String location) {
        ICommand command = new AddRestaurant(name, location);
        command.execute();
    }

    public void removeRestaurant(String name) {
        ICommand command = new RemoveRestaurant(name);
        command.execute();
    }

    public void listRestaurants() {
        RestaurantRepository.getInstance().getAllRestaurants().values().forEach(System.out::println);
    }
}
