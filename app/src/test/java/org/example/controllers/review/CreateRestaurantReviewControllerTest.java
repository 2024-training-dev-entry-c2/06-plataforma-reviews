package org.example.controllers.review;

import org.example.services.review.CreateRestaurantReview;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateRestaurantReviewControllerTest {
	private CreateRestaurantReview mockCreateRestaurantReview;
	private CreateRestaurantReviewController createRestaurantReviewController;

	@BeforeEach
	void setUp() {
		mockCreateRestaurantReview = mock(CreateRestaurantReview.class);
		createRestaurantReviewController = new CreateRestaurantReviewController(mockCreateRestaurantReview);
	}

	@Test
	void execute() {
		when(mockCreateRestaurantReview.execute()).thenReturn(true);

		createRestaurantReviewController.execute();
		verify(mockCreateRestaurantReview).execute();
	}

	@Test
	void executeWithFalse() {
		when(mockCreateRestaurantReview.execute()).thenReturn(false);

		createRestaurantReviewController.execute();
		verify(mockCreateRestaurantReview).execute();
	}
}