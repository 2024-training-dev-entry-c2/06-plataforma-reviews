package restaurant.controller.review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.service.review.AddReview;
import restaurant.Interface.IReview;

import static org.mockito.Mockito.*;

public class AddReviewControllerTest {
    private AddReview addReview;
    private AddReviewController addReviewController;

    @BeforeEach
    public void setUp() {
        addReview = mock(AddReview.class);
        addReviewController = new AddReviewController(addReview);
    }

    @Test
    public void testExecuteWithReview() {
        IReview review = mock(IReview.class);
        when(review.getComment()).thenReturn("Great food!");
        when(review.getQualification()).thenReturn(5.0);
        when(addReview.execute()).thenReturn(review);

        addReviewController.execute();

        verify(addReview, times(1)).execute();
    }

    @Test
    public void testExecuteWithNullReview() {
        when(addReview.execute()).thenReturn(null);

        addReviewController.execute();

        verify(addReview, times(1)).execute();
    }
}