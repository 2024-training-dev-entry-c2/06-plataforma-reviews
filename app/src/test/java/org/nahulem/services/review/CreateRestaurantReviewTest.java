package org.nahulem.services.review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.models.Restaurant;
import org.nahulem.models.RestaurantReview;
import org.nahulem.services.restaurant.SelectRestaurantService;
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

class CreateRestaurantReviewTest {
    private SelectRestaurantService mockSelectRestaurantService;
    private Validator mockValidator;
    private CreateRestaurantReviewService mockCreateRestaurantReviewService;

    @BeforeEach
    void setUp() {
        mockSelectRestaurantService = mock(SelectRestaurantService.class);
        mockValidator = mock(Validator.class);
        mockCreateRestaurantReviewService = new CreateRestaurantReviewService(mockSelectRestaurantService, mockValidator);
    }

    @Test
    void testNullRestaurant() {
        when(mockSelectRestaurantService.execute()).thenReturn(null);

        Boolean result = mockCreateRestaurantReviewService.execute();

        assertFalse(result);
    }

    @Test
    void testWithRestaurant() {
        Restaurant restaurant = new Restaurant("El Establo", "Restaurante de comida típica", "Zona 10", null);

        when(mockSelectRestaurantService.execute()).thenReturn(restaurant);
        when(mockValidator.readString("Ingrese un comentario para el restaurante: ")).thenReturn("Comentario Test");
        when(mockValidator.readRating("Ingrese una calificación para el sabor (0-5): ")).thenReturn(5.0f);
        when(mockValidator.readRating("Ingrese una calificación para el servicio (0-5): ")).thenReturn(4.0f);
        when(mockValidator.readRating("Ingrese una calificación para el ambiente (0-5): ")).thenReturn(3.4f);

        Boolean result = mockCreateRestaurantReviewService.execute();

        assertNotNull(result);
        assertTrue(result);
        assertEquals("Comentario Test", restaurant.getReviews().get(0).getComment());
        assertEquals(1, restaurant.getReviews().size());
        assertEquals(5.0f, ((RestaurantReview)restaurant.getReviews().get(0)).getMenuRating());
        assertEquals(4.0f, ((RestaurantReview)restaurant.getReviews().get(0)).getServiceRating());
        assertEquals(3.4f, ((RestaurantReview)restaurant.getReviews().get(0)).getEnvironmentRating());

        verify(mockSelectRestaurantService).execute();
        verify(mockValidator).readString(anyString());
        verify(mockValidator, times(3)).readRating(anyString());
    }
}