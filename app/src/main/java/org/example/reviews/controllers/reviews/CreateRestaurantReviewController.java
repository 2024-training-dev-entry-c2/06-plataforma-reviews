package org.example.reviews.controllers.reviews;

import org.example.reviews.controllers.interfaces.IController;
import org.example.reviews.models.Review;
import org.example.reviews.services.reviews.ReviewService;

public class CreateRestaurantReviewController implements IController {
    private ReviewService reviewService;

    public CreateRestaurantReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @Override
    public void execute() {
        Review review = reviewService.createRestaurantReview();
        System.out.println(review);
        System.out.println("---Comentario de restaurante registrado---");

    }
}
