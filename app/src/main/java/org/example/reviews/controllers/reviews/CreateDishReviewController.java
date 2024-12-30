package org.example.reviews.controllers.reviews;

import org.example.reviews.controllers.interfaces.IController;
import org.example.reviews.models.Review;
import org.example.reviews.services.reviews.ReviewService;

public class CreateDishReviewController implements IController {
    private ReviewService reviewService;

    public CreateDishReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public void execute() {
        Review review = reviewService.createDishReview();
        System.out.println(review);
        System.out.println("---Comentario de plato registrada---");
    }
}
