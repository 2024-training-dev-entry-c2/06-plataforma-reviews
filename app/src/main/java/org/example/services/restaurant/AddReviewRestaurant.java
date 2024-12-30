package org.example.services.restaurant;


import org.example.factory.ReviewFactory;
import org.example.models.IReview;
import org.example.models.User;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;

public class AddReviewRestaurant implements ICommand {
    private final RestaurantRepository repository;
    private final IValidatorScanner validatorScanner;

    public AddReviewRestaurant(RestaurantRepository repository, IValidatorScanner validatorScanner) {
        this.repository = repository;
        this.validatorScanner = validatorScanner;
    }

    @Override
    public void execute() {
        String restaurantName = null;

        restaurantName = validatorScanner.stringScanner("Escribe el nombre del Restaurante");
        User user = new User(validatorScanner.stringScanner("Ingrese su nombre"));
        String comment = validatorScanner.stringScanner("Ingrese su comentario");
        Float placeRating = validatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del del lugar");
        Float menuRating = validatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del del menu");
        Float serviceRating = validatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del del serviceRating");
        IReview review = ReviewFactory.createReview("restaurante", comment, placeRating, menuRating, serviceRating);
        repository.addReview(restaurantName, user, review);
        System.out.println("Review agregado: " + restaurantName);

    }

}

