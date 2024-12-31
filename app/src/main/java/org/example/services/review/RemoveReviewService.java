package org.example.services.review;

import org.example.models.Review;
import org.example.repositories.ReviewRepository;
import org.example.services.interfaces.ICommand;

public class RemoveReviewService implements ICommand<Review> {

    private final ReviewRepository repository;
    private Review review;

    public RemoveReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Review findByComment(String comment) {
        for (Review r : repository.getReviews()) {
            if (r.getComment().equals(comment)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public Review execute() {
        if (review == null) {
            throw new IllegalStateException("Review data cannot be null");
        }
        repository.removeReview(review);
        return review;
    }
}