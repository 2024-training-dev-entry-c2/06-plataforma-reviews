package org.example.reviews.models;

import org.example.reviews.repositories.RestaurantReviewRepository;

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
    public void reviewType() {
        System.out.println("---Reseña de restaurante---");
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
