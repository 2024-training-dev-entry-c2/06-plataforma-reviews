package org.example.services.restaurant;

import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;

public class UpdateRestaurat implements ICommand {
    private final RestaurantRepository repository = RestaurantRepository.getInstance();

    private final String name;
    private final String newLocation;
    private final String newMenuName;

    public UpdateRestaurat(String name, String newLocation, String newMenuName) {
        this.name = name;
        this.newLocation = newLocation;
        this.newMenuName = newMenuName;
    }
    @Override
    public void execute() {
        Restaurant restaurant=repository.getRestaurant(name);
        if (restaurant!=null) {
            restaurant.setAddress(newLocation);
            restaurant.getMenu().setName(newMenuName);
            repository.updateRestaurant(restaurant);
        }
    }
}
