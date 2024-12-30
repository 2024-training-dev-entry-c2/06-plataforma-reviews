package org.nahulem.controllers.review;

import org.nahulem.controllers.interfaces.ICommandController;
import org.nahulem.services.review.CreateDishReviewService;
import org.nahulem.utils.Validator;

import java.util.Scanner;

public class CreateDishReviewController implements ICommandController {
    private final CreateDishReviewService createDishReview;
    private final Validator validator = new Validator(new Scanner(System.in));

    public CreateDishReviewController(CreateDishReviewService createDishReview) {
        this.createDishReview = createDishReview;
    }

    @Override
    public void execute() {
        if (createDishReview.execute()) {
            validator.printMessage("Reseña creada exitosamente.");
            return;
        }
        validator.printMessage("No se pudo crear la reseña.");
    }
}
