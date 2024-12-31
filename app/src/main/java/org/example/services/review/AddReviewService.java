package org.example.services.review;

import org.example.models.Review;
import org.example.repositories.ReviewRepository;
import org.example.services.interfaces.ICommand;

public class AddReviewService implements ICommand<Review> {

    private final ReviewRepository repository;
    private Review review;

    public AddReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    @Override
    public Review execute() {
        if (review == null) {
            throw new IllegalStateException("Review data cannot be null");
        }
        repository.addReview(review);
        return review;
    }
}