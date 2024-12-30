package restaurant.models;

import restaurant.Interface.IReview;

public class ReviewFactory {
    public static IReview createRestaurantReview(String comment, Double qualification, Restaurant restaurant) {
        return new RestaurantReview(comment, qualification, restaurant);
    }

    public static IReview createDishReview(String comment, Double qualification, Dish dish) {
        return new DishReview(comment, qualification, dish);
    }
}