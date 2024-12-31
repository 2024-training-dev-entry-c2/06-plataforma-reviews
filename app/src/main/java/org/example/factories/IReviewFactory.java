package org.example.factories;

import org.example.models.Review;

public interface IReviewFactory {
    Review createReview(String comment, Integer score);
}