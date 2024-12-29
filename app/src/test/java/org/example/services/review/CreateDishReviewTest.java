package org.example.services.review;

import org.example.models.Dish;
import org.example.models.DishReview;
import org.example.services.menu.SelectDish;
import org.example.utils.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateDishReviewTest {
	private Validator mockValidator;
	private SelectDish mockSelectDish;
	private CreateDishReview createDishReview;

	@BeforeEach
	void setUp() {
		mockValidator = mock(Validator.class);
		mockSelectDish = mock(SelectDish.class);
		createDishReview = new CreateDishReview(mockValidator, mockSelectDish);
	}

	@Test
	void executeWithNullDish() {
		when(mockSelectDish.execute()).thenReturn(null);

		Boolean result = createDishReview.execute();

		assertFalse(result);
	}

	@Test
	void executeWithDish() {
		Dish dish = new Dish("Bandeja paisa", "Bandeja paisa description", 10.0f);

		when(mockSelectDish.execute()).thenReturn(dish);
		when(mockValidator.readString(anyString())).thenReturn("Comentario Test");
		when(mockValidator.readRating(anyString())).thenReturn(5.0f, 4.0f);

		Boolean result = createDishReview.execute();

		assertNotNull(result);
		assertTrue(result);

		assertEquals("Comentario Test", dish.getReviews().get(0).getComment());
		assertEquals(5.0f, ((DishReview)dish.getReviews().get(0)).getTasteRating());
		assertEquals(4.0f, ((DishReview)dish.getReviews().get(0)).getPresentationRating());
		assertEquals(1, dish.getReviews().size());

		verify(mockSelectDish).execute();
		verify(mockValidator).readString(anyString());
		verify(mockValidator, times(2)).readRating(anyString());
	}
}