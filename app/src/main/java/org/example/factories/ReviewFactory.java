package org.example.factories;

import org.example.models.DishReview;
import org.example.models.RestaurantReview;
import org.example.models.Review;

import java.util.HashMap;
import java.util.Map;

public class ReviewFactory {

    public static Review createReview(String typeOfReview, String idReview, String comment, Float... ratings) {
        Map<String, Review> reviews = new HashMap<>();
        reviews.put("Dish", new DishReview(idReview, comment, ratings[0], ratings[1]));
        reviews.put("Restaurant", new RestaurantReview(idReview, comment, ratings[0], ratings[1], ratings[2]));
        return reviews.get(typeOfReview);
    }
}
