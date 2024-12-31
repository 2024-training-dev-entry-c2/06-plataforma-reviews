package org.example.factories;

import org.example.models.Restaurant;
import org.example.models.Review;

public class RestaurantReviewFactory implements IReviewFactory {
    private Restaurant restaurant;

    public RestaurantReviewFactory(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public Review createReview(String comment, Integer score) {
        return new Review(comment, score, restaurant);
    }
}