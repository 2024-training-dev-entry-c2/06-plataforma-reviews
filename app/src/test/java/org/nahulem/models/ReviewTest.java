package org.nahulem.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReviewTest {

    @Test
    void getAverageRating() {
        Review mockReview = new Review("Comentario de prueba") {
            @Override
            public void calculateRating() {
                setAverageRating(4.5f);
            }
        };

        mockReview.calculateRating();
        assertEquals(4.5f, mockReview.getAverageRating());
    }
}
