package org.example.controllers.review;

import org.example.models.DishReview;
import org.example.services.review.ListDishReview;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ListDishReviewControllerTest {
	private ListDishReview mockListDishReview;
	private ListDishReviewController listDishReviewController;

	@BeforeEach
	void setUp() {
		mockListDishReview = mock(ListDishReview.class);
		listDishReviewController = new ListDishReviewController(mockListDishReview);
	}

	@Test
	void execute() {
		List<DishReview> dishes = new ArrayList<>();
		dishes.add(new DishReview("Comentario Test", 5.0f, 4.0f));
		dishes.add(new DishReview("Comentario Test 2", 4.0f, 3.0f));

		when(mockListDishReview.execute()).thenReturn(dishes);

		listDishReviewController.execute();

		verify(mockListDishReview).execute();
	}

	@Test
	void executeWithEmptyList() {
		when(mockListDishReview.execute()).thenReturn(List.of());

		listDishReviewController.execute();

		verify(mockListDishReview).execute();
	}
}