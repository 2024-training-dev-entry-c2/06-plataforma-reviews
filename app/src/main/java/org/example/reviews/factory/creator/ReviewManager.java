package org.example.reviews.factory.creator;

import org.example.reviews.models.Review;

import java.time.LocalDate;

public class ReviewManager {
    private ReviewFactory factory;

    public ReviewManager(ReviewFactory factory) {
        this.factory = factory;
    }

    public Review createReview(Integer relateId, Integer id, String author, String comment, Float rating, LocalDate date) {
        return factory.createReview(relateId, id, author, comment, rating, date);
    }
}
