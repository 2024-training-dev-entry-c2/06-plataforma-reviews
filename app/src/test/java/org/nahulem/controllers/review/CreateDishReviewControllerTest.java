package org.nahulem.controllers.review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.services.review.CreateDishReviewService;
import org.nahulem.utils.Validator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateDishReviewControllerTest {
    private CreateDishReviewService mockCreateDishReviewService;
    private Validator mockValidator;
    private CreateDishReviewController createDishReviewController;

    @BeforeEach
    void setUp() {
        mockValidator = mock(Validator.class);
        mockCreateDishReviewService = mock(CreateDishReviewService.class);
        createDishReviewController = new CreateDishReviewController(mockCreateDishReviewService);
    }

    @Test
    void execute() {
        when(mockCreateDishReviewService.execute()).thenReturn(true);

        createDishReviewController.execute();
        verify(mockCreateDishReviewService).execute();
    }

    @Test
    void testWithFalse() {
        when(mockCreateDishReviewService.execute()).thenReturn(false);

        createDishReviewController.execute();
        verify(mockCreateDishReviewService).execute();
    }
}