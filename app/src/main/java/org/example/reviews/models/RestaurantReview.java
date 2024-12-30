package org.example.reviews.models;

import java.time.LocalDate;


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

    @Override
    public String toString() {
        return "RestaurantReview: " + super.toString();
    }
}
