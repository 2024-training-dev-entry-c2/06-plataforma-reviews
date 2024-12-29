package org.example.controllers.review;

import org.example.services.review.CreateDishReview;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateDishReviewControllerTest {
	private CreateDishReview mockCreateDishReview;
	private CreateDishReviewController createDishReviewController;

	@BeforeEach
	void setUp() {
		mockCreateDishReview = mock(CreateDishReview.class);
		createDishReviewController = new CreateDishReviewController(mockCreateDishReview);
	}

	@Test
	void execute() {
		when(mockCreateDishReview.execute()).thenReturn(true);

		createDishReviewController.execute();
		verify(mockCreateDishReview).execute();
	}

	@Test
	void executeWithFalse() {
		when(mockCreateDishReview.execute()).thenReturn(false);

		createDishReviewController.execute();
		verify(mockCreateDishReview).execute();
	}
}