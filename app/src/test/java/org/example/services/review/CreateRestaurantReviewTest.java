package org.example.services.review;

import org.example.models.Restaurant;
import org.example.models.RestaurantReview;
import org.example.services.restaurant.AddRestaurantObserver;
import org.example.services.restaurant.SelectRestaurant;
import org.example.utils.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateRestaurantReviewTest {
	private Validator mockValidator;
	private SelectRestaurant mockSelectRestaurant;
	private AddRestaurantObserver mockAddRestaurantObserver;
	private CreateRestaurantReview createRestaurantReview;

	@BeforeEach
	void setUp() {
		mockValidator = mock(Validator.class);
		mockSelectRestaurant = mock(SelectRestaurant.class);
		mockAddRestaurantObserver = mock(AddRestaurantObserver.class);
		createRestaurantReview = new CreateRestaurantReview(mockValidator, mockSelectRestaurant, mockAddRestaurantObserver);
	}

	@Test
	void executeWithNullRestaurant() {
		when(mockSelectRestaurant.execute()).thenReturn(null);

		Boolean result = createRestaurantReview.execute();

		assertFalse(result);
	}

	@Test
	void executeWithRestaurant() {
		Restaurant restaurant = new Restaurant("Restaurante Test", "Descripcion Test", "Ubicacion Test", null);

		when(mockSelectRestaurant.execute()).thenReturn(restaurant);
		when(mockValidator.readString(anyString())).thenReturn("Comentario Test");
		when(mockValidator.readRating(anyString())).thenReturn(5.0f, 4.0f, 3.4f);
		when(mockAddRestaurantObserver.execute(restaurant)).thenReturn(true);

		Boolean result = createRestaurantReview.execute();

		assertNotNull(result);
		assertTrue(result);
		assertEquals("Comentario Test", restaurant.getReviews().get(0).getComment());
		assertEquals(1, restaurant.getReviews().size());
		assertEquals(5.0f, ((RestaurantReview)restaurant.getReviews().get(0)).getServiceRating());
		assertEquals(4.0f, ((RestaurantReview)restaurant.getReviews().get(0)).getLocationRating());
		assertEquals(3.4f, ((RestaurantReview)restaurant.getReviews().get(0)).getMenuRating());


		verify(mockSelectRestaurant).execute();
		verify(mockValidator).readString(anyString());
		verify(mockValidator, times(3)).readRating(anyString());
		verify(mockAddRestaurantObserver).execute(restaurant);
	}
}