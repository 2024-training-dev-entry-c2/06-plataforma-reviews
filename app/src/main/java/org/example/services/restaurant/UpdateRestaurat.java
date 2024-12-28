package org.example.services.restaurant;

import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;

public class UpdateRestaurat implements ICommand {
    private final RestaurantRepository repository = RestaurantRepository.getInstance();
    private final IValidatorScanner validatorScanner;

    public UpdateRestaurat(IValidatorScanner validatorScanner) {
        this.validatorScanner = validatorScanner;
    }

    @Override
    public void execute() {
        String name = validatorScanner.stringScanner("Escribe el nombre del Restaurante");
        String newName = validatorScanner.stringScanner("Escribe la nuevo nombre del Restaurante");
        String address = validatorScanner.stringScanner("Escribe la nueva direccion del Restaurante");

        Restaurant restaurant=repository.getRestaurant(name);
        if (restaurant!=null) {
            restaurant.setName(newName);
            restaurant.setAddress(address);
            restaurant.getMenu().setName(newName+" Menu");
            repository.updateRestaurant(restaurant);
        }
    }
}
