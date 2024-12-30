package org.example.reviews.factory;

import org.example.reviews.factory.creator.ReviewFactory;
import org.example.reviews.models.DishReview;
import org.example.reviews.models.Review;

import java.time.LocalDate;

public class DishReviewFactory extends ReviewFactory {
    @Override
    public Review createReview(Integer dishId, Integer id, String author, String comment, Float rating, LocalDate date) {
        return new DishReview(dishId, id, author, comment, rating, date);
    }
}
