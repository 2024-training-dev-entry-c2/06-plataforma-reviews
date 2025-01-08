package org.nahulem.services.review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.models.Dish;
import org.nahulem.models.DishReview;
import org.nahulem.services.menu.SelectDishService;
import org.nahulem.utils.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateDishReviewTest {
    private SelectDishService mockSelectDishService;
    private Validator mockValidator;
    private CreateDishReviewService mockCreateDishReviewService;

    @BeforeEach
    void setUp() {
        mockValidator = mock(Validator.class);
        mockSelectDishService = mock(SelectDishService.class);
        mockCreateDishReviewService = new CreateDishReviewService(mockSelectDishService, mockValidator);
    }

    @Test
    void testNullDish() {
        when(mockSelectDishService.execute()).thenReturn(null);

        Boolean result = mockCreateDishReviewService.execute();

        assertFalse(result);
    }

    @Test
    void testWithDish() {
        Dish dish = new Dish("Paella", "Paella de Mariscos", 10.0f);

        when(mockSelectDishService.execute()).thenReturn(dish);
        when(mockValidator.readString(anyString())).thenReturn("Comentario Test");
        when(mockValidator.readRating(anyString())).thenReturn(5.0f, 4.0f);

        Boolean result = mockCreateDishReviewService.execute();

        assertNotNull(result);
        assertTrue(result);

        assertEquals("Comentario Test", dish.getReviews().get(0).getComment());
        assertEquals(5.0f, ((DishReview)dish.getReviews().get(0)).getTasteRating());
        assertEquals(4.0f, ((DishReview)dish.getReviews().get(0)).getPresentationRating());
        assertEquals(1, dish.getReviews().size());

        verify(mockSelectDishService).execute();
        verify(mockValidator).readString(anyString());
        verify(mockValidator, times(2)).readRating(anyString());

    }
}