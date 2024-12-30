package org.example.reviews.factory.creator;

import org.example.reviews.models.Review;

import java.time.LocalDate;

public abstract class ReviewFactory {
    public abstract Review createReview(Integer relateId, Integer id, String author, String comment, Float rating, LocalDate date);
}
