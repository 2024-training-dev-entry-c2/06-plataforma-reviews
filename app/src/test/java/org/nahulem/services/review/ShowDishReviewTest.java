package org.nahulem.services.review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.factories.ReviewFactory;
import org.nahulem.models.Dish;
import org.nahulem.models.DishReview;
import org.nahulem.models.Review;
import org.nahulem.services.menu.SelectDishService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ShowDishReviewTest {
    private SelectDishService mockSelectDishService;
    private ShowDishReviewService mockShowDishReviewService;

    @BeforeEach
    void setUp() {
        mockSelectDishService = mock(SelectDishService.class);
        mockShowDishReviewService = new ShowDishReviewService(mockSelectDishService);
    }

    @Test
    void testNullDish() {
        when(mockSelectDishService.execute()).thenReturn(null);

        List<DishReview> result = mockShowDishReviewService.execute();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testWithDish() {
        Review dishReview = ReviewFactory.createReview("Comentario Test", 5.0f, 4.0f);
        Dish dish = new Dish("Paella", "Paella de Mariscos", 10.0f);
        dish.addReview(dishReview);

        when(mockSelectDishService.execute()).thenReturn(dish);

        List<DishReview> result = mockShowDishReviewService.execute();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Comentario Test", result.get(0).getComment());
        assertEquals(5.0f, result.get(0).getTasteRating());
        assertEquals(4.0f, result.get(0).getPresentationRating());

        verify(mockSelectDishService).execute();
    }
}