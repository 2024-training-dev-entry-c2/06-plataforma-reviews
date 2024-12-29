package org.example.reviews.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Manuel Aguilera / @aguileradev
 */
public class RestaurantReview extends Review{
    private Integer restaurantId;

    public RestaurantReview(Integer restaurantId, Integer id, String author, String comment, Float rating, LocalDate date) {
        super(id, author, comment, rating, date);
        this.restaurantId = restaurantId;
    }
    @Override
    public Float calculateRating() {
        return 0.0f;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "RestaurantReview: " + super.toString();
    }
}
