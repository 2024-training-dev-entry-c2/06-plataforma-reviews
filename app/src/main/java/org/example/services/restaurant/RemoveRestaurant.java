package org.example.services.restaurant;

import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;


public class RemoveRestaurant implements ICommand {
    private final RestaurantRepository repository = RestaurantRepository.getInstance();
    private final IValidatorScanner validatorScanner;

    public RemoveRestaurant(IValidatorScanner validatorScanner) {
        this.validatorScanner = validatorScanner;
    }

    @Override
    public void execute() {
        String name = validatorScanner.stringScanner("Escribe el nombre del Restaurante");
        repository.removeRestaurant(name);
        System.out.println("Restaurant eliminado: " + name);
    }
}
