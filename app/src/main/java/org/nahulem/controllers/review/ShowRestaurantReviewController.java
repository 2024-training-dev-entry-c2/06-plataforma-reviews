package org.nahulem.controllers.review;

import org.nahulem.controllers.interfaces.ICommandController;
import org.nahulem.models.RestaurantReview;
import org.nahulem.services.review.ShowRestaurantReviewService;
import org.nahulem.utils.Validator;

import java.util.List;
import java.util.Scanner;

public class ShowRestaurantReviewController implements ICommandController {
    private final ShowRestaurantReviewService showRestaurantReviewService;
    private final Validator validator = new Validator(new Scanner(System.in));

    public ShowRestaurantReviewController(ShowRestaurantReviewService showRestaurantReviewService) {
        this.showRestaurantReviewService = showRestaurantReviewService;
    }

    @Override
    public void execute() {
        List<RestaurantReview> reviews = showRestaurantReviewService.execute();
        if (reviews.isEmpty()) {
            validator.printMessage("No hay reseñas disponibles");
            return;
        }
        validator.printMessage("Listado de reseñas del restaurante: ");

        reviews.forEach(review -> validator.printMessage(review.toString()));
    }
}
