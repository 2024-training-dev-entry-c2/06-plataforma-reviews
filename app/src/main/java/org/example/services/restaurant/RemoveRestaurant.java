package org.example.services.restaurant;

import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;


public class RemoveRestaurant implements ICommand {
    private final RestaurantRepository repository;
    private final IValidatorScanner validatorScanner;

    public RemoveRestaurant(RestaurantRepository repository, IValidatorScanner validatorScanner) {
        this.repository = repository;
        this.validatorScanner = validatorScanner;
    }

    @Override
    public void execute() {
        String name = validatorScanner.stringScanner("Escribe el nombre del Restaurante");
        repository.removeRestaurant(name);
        System.out.println("Restaurant eliminado: " + name);
    }
}
