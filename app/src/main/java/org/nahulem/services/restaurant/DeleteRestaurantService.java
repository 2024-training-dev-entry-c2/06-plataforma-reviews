package org.nahulem.services.restaurant;


import org.nahulem.models.Restaurant;
import org.nahulem.repositories.DataRepository;
import org.nahulem.services.interfaces.ICommand;
import org.nahulem.utils.Validator;

import java.util.Scanner;

public class DeleteRestaurantService implements ICommand<Boolean> {
    private final DataRepository repository;
    private final SelectRestaurantService selectRestaurantService;
    private final Validator validator;

    public DeleteRestaurantService(DataRepository repository, SelectRestaurantService selectRestaurantService, Validator validator) {
        this.repository = repository;
        this.selectRestaurantService = selectRestaurantService;
        this.validator = new Validator(new Scanner(System.in));
    }

    @Override
    public Boolean execute() {
        Restaurant restaurant = selectRestaurantService.execute();

        if (restaurant == null) {
            System.out.println("No se ha seleccionado un restaurante.");
            return false;
        }

        return repository.deleteRestaurant(restaurant.getRestaurantId());
    }
}
