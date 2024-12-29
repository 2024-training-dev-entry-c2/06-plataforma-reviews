package org.nahulem.factories;

import org.nahulem.models.DishReview;
import org.nahulem.models.RestaurantReview;
import org.nahulem.models.Review;

import java.util.HashMap;
import java.util.Map;

public class ReviewFactory {

    public static Review createReview(String typeOfReview, String idReview, String comment, Float... ratings) {
        Map<String, Review> reviews = new HashMap<>();
        if ("Dish".equals(typeOfReview) && ratings.length >= 2) {
            reviews.put("Dish", new DishReview(idReview, comment, ratings[0], ratings[1]));
        } else if ("Restaurant".equals(typeOfReview) && ratings.length >= 3) {
            reviews.put("Restaurant", new RestaurantReview(idReview, comment, ratings[0], ratings[1], ratings[2]));
        }
        return reviews.get(typeOfReview);
    }

}
