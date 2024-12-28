package org.example.services.restaurant;

import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;

import java.util.Collections;
import java.util.List;

public class AddRestaurant implements ICommand {
    private final RestaurantRepository repository = RestaurantRepository.getInstance();


    private String name;
    private String location;

    public AddRestaurant(String name, String location) {
        this.name = name;
        this.location = location;
    }

    @Override
    public void execute() {
        Restaurant restaurant = new Restaurant(name, location);
        repository.getInstance().addRestaurant(restaurant);
        System.out.println("Restaurant added: " + name);

    }
}
