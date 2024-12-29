package org.example.services.review;

import org.example.models.Dish;
import org.example.models.DishReview;
import org.example.models.Review;
import org.example.models.ReviewFactory;
import org.example.services.menu.SelectDish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ListDishReviewTest {
	private SelectDish mockSelectDish;
	private ListDishReview listDishReview;

	@BeforeEach
	void setUp() {
		mockSelectDish = mock(SelectDish.class);
		listDishReview = new ListDishReview(mockSelectDish);
	}

	@Test
	void executeWithNullDish() {
		when(mockSelectDish.execute()).thenReturn(null);

		List<DishReview> result = listDishReview.execute();

		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	@Test
	void executeWithDish() {
		Review dishReview = ReviewFactory.createReview("Comentario Test", 5.0f, 4.0f);
		Dish dish = new Dish("Bandeja paisa", "Bandeja paisa description", 10.0f);
		dish.addReview(dishReview);

		when(mockSelectDish.execute()).thenReturn(dish);

		List<DishReview> result = listDishReview.execute();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("Comentario Test", result.get(0).getComment());
		assertEquals(5.0f, result.get(0).getTasteRating());
		assertEquals(4.0f, result.get(0).getPresentationRating());

		verify(mockSelectDish).execute();
	}
}