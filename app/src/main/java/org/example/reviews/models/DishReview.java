package org.example.reviews.models;

import java.time.LocalDateTime;

/**
 * @author Manuel Aguilera / @aguileradev
 */
public class DishReview extends Review {
    public DishReview(Integer id, String author, String comment, Float rating, LocalDateTime date) {
        super(id, author, comment, rating, date);
    }

    @Override
    public Float calculateRating() {
        return 0f;
    }

    @Override
    public String toString() {
        return "DishReview: " + super.toString();
    }
}
