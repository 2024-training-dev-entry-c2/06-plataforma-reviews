package org.example.reviews.services.reviews;

import org.example.reviews.models.DishReview;
import org.example.reviews.models.RestaurantReview;
import org.example.reviews.models.Review;
import org.example.reviews.observer.NotificationSystem;
import org.example.reviews.services.interfaces.IReview;
import org.example.reviews.utils.ConsoleUtil;

import java.util.List;

public class ReviewService implements IReview {
    public CreateRestaurantReviewImpl createRestaurantReview;
    public CreateDishReviewImpl createDishReview;
    public FindAllRestaurantReviewsImpl findAllRestaurantReviews;
    public FindAllDishesReviewsImpl findAllDishesReviews;

    public ReviewService(ConsoleUtil console, NotificationSystem notificationSystem) {
        this.createRestaurantReview = new CreateRestaurantReviewImpl(console, notificationSystem);
        this.createDishReview = new CreateDishReviewImpl(console, notificationSystem);
        this.findAllRestaurantReviews = new FindAllRestaurantReviewsImpl(console);
        this.findAllDishesReviews = new FindAllDishesReviewsImpl(console);

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
