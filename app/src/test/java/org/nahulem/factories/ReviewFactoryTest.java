package org.nahulem.factories;

import org.junit.jupiter.api.Test;
import org.nahulem.models.Review;

import static org.junit.jupiter.api.Assertions.*;

class ReviewFactoryTest {

    @Test
    void createRestaurantReview() {
        Review review = ReviewFactory.createReview("Excelente restaurante", 4.5f, 4.0f, 4.8f);
        assertNotNull(review);
        assertEquals(4.4f, review.getAverageRating());
    }

    @Test
    void createDishReview() {
        Review review = ReviewFactory.createReview("Delicioso plato", 4.0f, 4.5f);
        assertNotNull(review);
        assertEquals(4.3f, review.getAverageRating());
    }

    @Test
    void createInvalidReview() {
        Review review = ReviewFactory.createReview("Comentario sin sentido");
        assertNull(review);
    }

    @Test
    void createInvalidReviewWithExtraRatings() {
        Review review = ReviewFactory.createReview("Demasiadas calificaciones", 4.0f, 3.5f, 4.0f, 5.0f);
        assertNull(review);
    }
}
