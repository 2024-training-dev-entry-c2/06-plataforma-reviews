package org.example.services.restaurant;

import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;


public class RemoveRestaurant implements ICommand {
    private final RestaurantRepository repository = RestaurantRepository.getInstance();

    private String name;

    public RemoveRestaurant(String name) {
        this.name = name;
    }

    public void addRestaurant(String nameRestaurant) {
        repository.removeRestaurant(nameRestaurant);
    }

    @Override
    public void execute() {
        repository.removeRestaurant(name);
        System.out.println("Restaurant removed: " + name);
    }
}
