package org.example.reviews.controllers.reviews;

import org.example.reviews.controllers.interfaces.IController;
import org.example.reviews.models.DishReview;
import org.example.reviews.services.reviews.ReviewService;

import java.util.List;

public class FindAllDishesReviewsController implements IController {
    private ReviewService reviewService;

    public FindAllDishesReviewsController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public void execute() {
        List<DishReview> reviews = reviewService.findAllDishesReviews();
        System.out.println("---Comentarios de platos---");
        reviews.forEach(System.out::println);
        System.out.println("---------------------------------");
    }
}
