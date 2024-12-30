package org.example.reviews.controllers.reviews;

import org.example.reviews.controllers.interfaces.IController;
import org.example.reviews.models.RestaurantReview;
import org.example.reviews.services.reviews.ReviewService;

import java.util.List;

public class FindAllRestaurantReviewsController implements IController {
    private ReviewService reviewService;

    public FindAllRestaurantReviewsController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public void execute() {
        List<RestaurantReview> reviews = reviewService.findAllRestaurantReviews();
        System.out.println("---Comentarios del restaurante---");
        reviews.forEach(System.out::println);
        System.out.println("---------------------------------");
    }
}
