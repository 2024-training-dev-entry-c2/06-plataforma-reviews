package org.nahulem.services.review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.factories.ReviewFactory;
import org.nahulem.models.Restaurant;
import org.nahulem.models.RestaurantReview;
import org.nahulem.models.Review;
import org.nahulem.services.restaurant.SelectRestaurantService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ShowRestaurantReviewTest {
    private SelectRestaurantService mockSelectRestaurantService;
    private ShowRestaurantReviewService mockShowRestaurantReviewService;

    @BeforeEach
    void setUp() {
        mockSelectRestaurantService = mock(SelectRestaurantService.class);
        mockShowRestaurantReviewService = new ShowRestaurantReviewService(mockSelectRestaurantService);
    }

    @Test
    void testNullRestaurant() {
        when(mockSelectRestaurantService.execute()).thenReturn(null);

        List<RestaurantReview> result = mockShowRestaurantReviewService.execute();

        assertNotNull(result);
        assertTrue(result.isEmpty());
   }

    @Test
    void testWithRestaurant() {
        Review restaurantReview = ReviewFactory.createReview("Comentario Test", 5.0f, 4.0f, 3.4f);
        Restaurant restaurant = new Restaurant("El Establo", "Restaurante de comida típica", "Zona 10", null);
        restaurant.addReview(restaurantReview);

        when(mockSelectRestaurantService.execute()).thenReturn(restaurant);

        List<RestaurantReview> result = mockShowRestaurantReviewService.execute();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Comentario Test", result.get(0).getComment());
        assertEquals(5.0f, result.get(0).getMenuRating());
        assertEquals(4.0f, result.get(0).getServiceRating());
        assertEquals(3.4f, result.get(0).getEnvironmentRating());

        verify(mockSelectRestaurantService).execute();
    }
}