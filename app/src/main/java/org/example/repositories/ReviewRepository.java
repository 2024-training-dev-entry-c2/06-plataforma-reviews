package org.example.repositories;

import org.example.models.Review;

import java.util.LinkedList;
import java.util.List;

public class ReviewRepository {
    private static ReviewRepository instance;
    private final List<Review> reviews;

    private ReviewRepository() {
        reviews = new LinkedList<>();
    }

    public static ReviewRepository getInstance() {
        if (instance == null) {
            instance = new ReviewRepository();
        }
        return instance;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
    }

    public List<Review> getReviews() {
        return reviews;
    }
}