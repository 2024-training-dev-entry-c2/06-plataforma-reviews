package restaurant.controller.review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.service.review.ListDishReview;

import static org.mockito.Mockito.*;

public class ListDishReviewControllerTest {
    private ListDishReview listDishReview;
    private ListDishReviewController listDishReviewController;

    @BeforeEach
    public void setUp() {
        listDishReview = mock(ListDishReview.class);
        listDishReviewController = new ListDishReviewController(listDishReview);
    }

    @Test
    public void testExecute() {
        listDishReviewController.execute();
        verify(listDishReview, times(1)).execute();
    }
}