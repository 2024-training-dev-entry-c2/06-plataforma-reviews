package org.example.reviews.services.reviews;

import org.example.reviews.models.DishReview;
import org.example.reviews.models.RestaurantReview;
import org.example.reviews.models.Review;
import org.example.reviews.observer.NotificationSystem;
import org.example.reviews.utils.ConsoleUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReviewServiceTest {

    private ConsoleUtil mockConsole;
    private NotificationSystem mockNotificationSystem;
    private CreateRestaurantReviewImpl mockCreateRestaurantReview;
    private CreateDishReviewImpl mockCreateDishReview;
    private FindAllRestaurantReviewsImpl mockFindAllRestaurantReviews;
    private FindAllDishesReviewsImpl mockFindAllDishesReviews;
    private ReviewService reviewService;

    @BeforeEach
    void setUp() {
        mockConsole = mock(ConsoleUtil.class);
        mockNotificationSystem = mock(NotificationSystem.class);
        mockCreateRestaurantReview = mock(CreateRestaurantReviewImpl.class);
        mockCreateDishReview = mock(CreateDishReviewImpl.class);
        mockFindAllRestaurantReviews = mock(FindAllRestaurantReviewsImpl.class);
        mockFindAllDishesReviews = mock(FindAllDishesReviewsImpl.class);

        reviewService = new ReviewService(mockConsole, mockNotificationSystem);
        reviewService.createRestaurantReview = mockCreateRestaurantReview;
        reviewService.createDishReview = mockCreateDishReview;
        reviewService.findAllRestaurantReviews = mockFindAllRestaurantReviews;
        reviewService.findAllDishesReviews = mockFindAllDishesReviews;
    }

    @Test
    @DisplayName("test create restaurant review")
    void testCreateRestaurantReview() {
        Review mockReview = mock(Review.class);
        when(mockCreateRestaurantReview.execute()).thenReturn(mockReview);

        Review result = reviewService.createRestaurantReview();

        assertNotNull(result);
        assertEquals(mockReview, result);
        verify(mockCreateRestaurantReview).execute();
    }

    @Test
    @DisplayName("test create dish review")
    void testCreateDishReview() {
        Review mockReview = mock(Review.class);
        when(mockCreateDishReview.execute()).thenReturn(mockReview);

        Review result = reviewService.createDishReview();

        assertNotNull(result);
        assertEquals(mockReview, result);
        verify(mockCreateDishReview).execute();
    }

    @Test
    @DisplayName("test find all restaurant reviews")
    void testFindAllRestaurantReviews() {
        List<RestaurantReview> mockReviews = List.of(mock(RestaurantReview.class));
        when(mockFindAllRestaurantReviews.execute()).thenReturn(mockReviews);

        List<RestaurantReview> result = reviewService.findAllRestaurantReviews();

        assertNotNull(result);
        assertEquals(mockReviews, result);
        verify(mockFindAllRestaurantReviews).execute();
    }

    @Test
    @DisplayName("test find all dishes reviews")
    void testFindAllDishesReviews() {
        List<DishReview> mockReviews = List.of(mock(DishReview.class));
        when(mockFindAllDishesReviews.execute()).thenReturn(mockReviews);

        List<DishReview> result = reviewService.findAllDishesReviews();

        assertNotNull(result);
        assertEquals(mockReviews, result);
        verify(mockFindAllDishesReviews).execute();
    }
}
