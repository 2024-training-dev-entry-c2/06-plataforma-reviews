package org.nahulem.controllers.review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.models.RestaurantReview;
import org.nahulem.services.review.ShowRestaurantReviewService;
import org.nahulem.utils.Validator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ShowRestaurantReviewControllerTest {
    private ShowRestaurantReviewService mockShowRestaurantReviewService;
    private Validator mockValidator;
    private ShowRestaurantReviewController showRestaurantReviewController;

    @BeforeEach
    void setUp() {
        mockShowRestaurantReviewService = mock(ShowRestaurantReviewService.class);
        mockValidator = mock(Validator.class);
        showRestaurantReviewController = new ShowRestaurantReviewController(mockShowRestaurantReviewService);
    }

    @Test
    void execute() {
        List<RestaurantReview> reviews = new ArrayList<>();
        reviews.add(new RestaurantReview("Comentario Test", 5.0f, 4.0f, 3.4f));
        reviews.add(new RestaurantReview("Comentario Test 2", 4.0f, 3.0f, 2.4f));

        when(mockShowRestaurantReviewService.execute()).thenReturn(reviews);

        showRestaurantReviewController.execute();
        verify(mockShowRestaurantReviewService).execute();
    }

    @Test
    void testEmptyList() {
        when(mockShowRestaurantReviewService.execute()).thenReturn(List.of());

        showRestaurantReviewController.execute();
        verify(mockShowRestaurantReviewService).execute();
    }
}