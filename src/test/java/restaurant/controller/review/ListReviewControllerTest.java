package restaurant.controller.review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.service.review.ListRestaurantReview;

import static org.mockito.Mockito.*;

public class ListReviewControllerTest {
    private ListRestaurantReview listRestaurantReview;
    private ListReviewController listReviewController;

    @BeforeEach
    public void setUp() {
        listRestaurantReview = mock(ListRestaurantReview.class);
        listReviewController = new ListReviewController(listRestaurantReview);
    }

    @Test
    public void testExecute() {
        listReviewController.execute();
        verify(listRestaurantReview, times(1)).execute();
    }
}