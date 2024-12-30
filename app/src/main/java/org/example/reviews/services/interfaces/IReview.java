package org.example.reviews.services.interfaces;


import org.example.reviews.models.DishReview;
import org.example.reviews.models.RestaurantReview;
import org.example.reviews.models.Review;

import java.util.List;

public interface IReview {
    Review createRestaurantReview();
    Review createDishReview();
    List<RestaurantReview> findAllRestaurantReviews();
    List<DishReview> findAllDishesReviews();
}
