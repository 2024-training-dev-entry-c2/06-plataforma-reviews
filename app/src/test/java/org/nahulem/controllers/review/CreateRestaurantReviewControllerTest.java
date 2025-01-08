package org.nahulem.controllers.review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.services.review.CreateRestaurantReviewService;
import org.nahulem.utils.Validator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateRestaurantReviewControllerTest {
    private CreateRestaurantReviewService mockCreateRestaurantReviewService;
    private CreateRestaurantReviewController createRestaurantReviewController;
    private Validator mockValidator;

    @BeforeEach
    void setUp() {
        mockCreateRestaurantReviewService = mock(CreateRestaurantReviewService.class);
        mockValidator = mock(Validator.class);
        createRestaurantReviewController = new CreateRestaurantReviewController(mockCreateRestaurantReviewService);
    }

    @Test
    void execute() {
        when(mockCreateRestaurantReviewService.execute()).thenReturn(true);

        createRestaurantReviewController.execute();
        verify(mockCreateRestaurantReviewService).execute();
    }

    @Test
    void testWithFalse() {
        when(mockCreateRestaurantReviewService.execute()).thenReturn(false);

        createRestaurantReviewController.execute();
        verify(mockCreateRestaurantReviewService).execute();
    }
}