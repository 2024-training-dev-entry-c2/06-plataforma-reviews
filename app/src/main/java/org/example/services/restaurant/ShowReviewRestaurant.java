package org.example.services.restaurant;

import org.example.models.Restaurant;
import org.example.models.User;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;

public class ShowReviewRestaurant implements ICommand {
    private final RestaurantRepository repository;
    private final IValidatorScanner validatorScanner;

    public ShowReviewRestaurant(RestaurantRepository repository, IValidatorScanner validatorScanner) {
        this.repository = repository;
        this.validatorScanner = validatorScanner;
    }

    @Override
    public void execute() {
        String restaurantName = null;
        try {
            restaurantName = validatorScanner.stringScanner("Escribe el nombre del Restaurante");
            Restaurant restaurant = repository.getRestaurant(restaurantName);
            System.out.println("reviews:");
            repository.showReview(restaurant);
        } catch (NullPointerException e) {
            System.err.println("Error: restaurante no encontrado - " + restaurantName);
        }
    }

}

