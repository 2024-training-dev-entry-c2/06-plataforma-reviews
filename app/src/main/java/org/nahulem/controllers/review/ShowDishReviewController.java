package org.nahulem.controllers.review;

import org.nahulem.controllers.interfaces.ICommandController;
import org.nahulem.models.DishReview;
import org.nahulem.services.review.ShowDishReviewService;
import org.nahulem.utils.Validator;

import java.util.List;
import java.util.Scanner;

public class ShowDishReviewController implements ICommandController {
    private final ShowDishReviewService showDishReviewService;
    private final Validator validator = new Validator(new Scanner(System.in));

    public ShowDishReviewController(ShowDishReviewService showDishReviewService) {
        this.showDishReviewService = showDishReviewService;
    }

    @Override
    public void execute() {
        List<DishReview> reviews = showDishReviewService.execute();

        if (reviews.isEmpty()) {
            validator.printMessage("No hay reseñas disponibles");
            return;
        }
        validator.printMessage("Listado de reseñas del plato: ");

        reviews.forEach(review -> validator.printMessage(review.toString()));
    }
}