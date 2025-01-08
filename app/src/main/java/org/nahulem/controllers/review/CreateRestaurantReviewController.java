package org.nahulem.controllers.review;

import org.nahulem.controllers.interfaces.ICommandController;
import org.nahulem.services.review.CreateRestaurantReviewService;
import org.nahulem.utils.Validator;

import java.util.Scanner;

public class CreateRestaurantReviewController implements ICommandController {
    private final CreateRestaurantReviewService createRestaurantReviewService;
    private final Validator validator = new Validator(new Scanner(System.in));

    public CreateRestaurantReviewController(CreateRestaurantReviewService createRestaurantReviewService) {
        this.createRestaurantReviewService = createRestaurantReviewService;
    }

    @Override
    public void execute() {
        if (createRestaurantReviewService.execute()) {
            validator.printMessage("Reseña creada exitosamente.");
            return;
        }
        validator.printMessage("No se pudo crear la reseña.");
    }
}
