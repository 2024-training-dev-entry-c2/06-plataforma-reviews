package org.example.services.restaurant;

import org.example.models.Restaurant;
import org.example.models.User;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;

public class AddReviewRestaurant implements ICommand {
    private final RestaurantRepository repository = RestaurantRepository.getInstance();
    private final IValidatorScanner validatorScanner;

    public AddReviewRestaurant(IValidatorScanner validatorScanner) {
        this.validatorScanner = validatorScanner;
    }

    @Override
    public void execute() {
        String restaurantName = null;
        try {
            restaurantName = validatorScanner.stringScanner("Escribe el nombre del Restaurante");
            User user = new User(validatorScanner.stringScanner("Ingrese su nombre"));
            String comment = validatorScanner.stringScanner("Ingrese su comentario");
            Float placeRating = validatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del del lugar");
            Float menuRating = validatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del del menu");
            Float serviceRating = validatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del del serviceRating");
            repository.addReview(restaurantName, user, comment, serviceRating, placeRating, menuRating);
            System.out.println("Review agregado: " + restaurantName);
        } catch (NullPointerException e) {
            System.err.println("Error: Restaurant not found - " + restaurantName);
        } catch (Exception e) {
            System.err.println("Error while adding review: " + e.getMessage());
        }
    }

}

