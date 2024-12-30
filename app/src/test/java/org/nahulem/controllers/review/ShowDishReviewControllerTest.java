package org.nahulem.controllers.review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.models.DishReview;
import org.nahulem.services.review.ShowDishReviewService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ShowDishReviewControllerTest {
    private ShowDishReviewService mockShowDishReviewService;
    private ShowDishReviewController showDishReviewController;

    @BeforeEach
    void setUp() {
        mockShowDishReviewService = mock(ShowDishReviewService.class);
        showDishReviewController = new ShowDishReviewController(mockShowDishReviewService);
    }

    @Test
    void execute() {
        List<DishReview> dishes = new ArrayList<>();
        dishes.add(new DishReview("Comentario Test", 5.0f, 4.0f));
        dishes.add(new DishReview("Comentario Test 2", 4.0f, 3.0f));

        when(mockShowDishReviewService.execute()).thenReturn(dishes);

        showDishReviewController.execute();

        verify(mockShowDishReviewService).execute();
    }

    @Test
    void testEmptyList() {
        when(mockShowDishReviewService.execute()).thenReturn(List.of());

        showDishReviewController.execute();

        verify(mockShowDishReviewService).execute();
    }
}