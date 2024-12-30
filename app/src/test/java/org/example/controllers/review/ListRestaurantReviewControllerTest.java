package org.example.controllers.review;

import org.example.models.RestaurantReview;
import org.example.services.review.ListRestaurantReview;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ListRestaurantReviewControllerTest {
	private ListRestaurantReview mockListRestaurantReview;
	private ListRestaurantReviewController listRestaurantReviewController;

	@BeforeEach
	void setUp() {
		mockListRestaurantReview = mock(ListRestaurantReview.class);
		listRestaurantReviewController = new ListRestaurantReviewController(mockListRestaurantReview);
	}

	@Test
	void execute() {
		List<RestaurantReview> restaurants = new ArrayList<>();
		restaurants.add(new RestaurantReview("Comentario Test", 5.0f, 4.0f, 3.4f));
		restaurants.add(new RestaurantReview("Comentario Test 2", 4.0f, 3.0f, 2.4f));

		when(mockListRestaurantReview.execute()).thenReturn(restaurants);

		listRestaurantReviewController.execute();
		verify(mockListRestaurantReview).execute();
	}

	@Test
	void executeWithEmptyList() {
		when(mockListRestaurantReview.execute()).thenReturn(List.of());

		listRestaurantReviewController.execute();
		verify(mockListRestaurantReview).execute();
	}
}