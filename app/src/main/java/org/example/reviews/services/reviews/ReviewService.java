package org.example.reviews.services.reviews;

import org.example.reviews.models.DishReview;
import org.example.reviews.models.RestaurantReview;
import org.example.reviews.models.Review;
import org.example.reviews.services.interfaces.IReview;

import java.util.List;

public class ReviewService implements IReview {
    private CreateRestaurantReviewImpl createRestaurantReview;
    private CreateDishReviewImpl createDishReview;
    private FindAllRestaurantReviewsImpl findAllRestaurantReviews;
    private FindAllDishesReviewsImpl findAllDishesReviews;

    public ReviewService(
            CreateRestaurantReviewImpl createRestaurantReview,
            CreateDishReviewImpl createDishReview,
            FindAllRestaurantReviewsImpl findAllRestaurantReviews,
            FindAllDishesReviewsImpl findAllDishesReviews) {
        this.createRestaurantReview = createRestaurantReview;
        this.createDishReview = createDishReview;
        this.findAllRestaurantReviews = findAllRestaurantReviews;
        this.findAllDishesReviews = findAllDishesReviews;

    }

    @Override
    public Review createRestaurantReview() {
        return createRestaurantReview.execute();
    }

    @Override
    public Review createDishReview() {
        return createDishReview.execute();
    }

    @Override
    public List<RestaurantReview> findAllRestaurantReviews() {
        return findAllRestaurantReviews.execute();
    }

    @Override
    public List<DishReview> findAllDishesReviews() {
        return findAllDishesReviews.execute();
    }
}
