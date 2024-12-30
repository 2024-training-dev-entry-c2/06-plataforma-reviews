package org.example.reviews.factory;

import org.example.reviews.factory.creator.ReviewFactory;
import org.example.reviews.models.RestaurantReview;
import org.example.reviews.models.Review;

import java.time.LocalDate;

public class RestaurantReviewFactory extends ReviewFactory {
    @Override
    public Review createReview(Integer restaurantId, Integer id, String author, String comment, Float rating, LocalDate date) {
        return new RestaurantReview(restaurantId,id, author, comment, rating, date);
    }
}