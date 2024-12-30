package org.nahulem.services.review;

import org.nahulem.factories.ReviewFactory;
import org.nahulem.models.Restaurant;
import org.nahulem.models.Review;
import org.nahulem.services.interfaces.ICommand;
import org.nahulem.services.restaurant.SelectRestaurantService;
import org.nahulem.utils.Validator;

public class CreateRestaurantReviewService implements ICommand<Boolean> {
    private final SelectRestaurantService selectRestaurantService;
    private final Validator validator;

    public CreateRestaurantReviewService(SelectRestaurantService selectRestaurantService, Validator validator) {
        this.selectRestaurantService = selectRestaurantService;
        this.validator = validator;
    }

    @Override
    public Boolean execute() {
        Restaurant restaurant = selectRestaurantService.execute();

        if (restaurant == null) {
            return false;
        }

        Review review = createRestaurantReview();

        restaurant.addReview(review);
        return true;
    }

    private Review createRestaurantReview() {
        String comment = validator.readString("Ingrese un comentario para el restaurante: ");
        Float tasteRating = validator.readRating("Ingrese una calificación para el sabor (0-5): ");
        Float serviceRating = validator.readRating("Ingrese una calificación para el servicio (0-5): ");
        Float environmentRating = validator.readRating("Ingrese una calificación para el ambiente (0-5): ");

        return ReviewFactory.createReview(comment, tasteRating, serviceRating, environmentRating);
    }
}
