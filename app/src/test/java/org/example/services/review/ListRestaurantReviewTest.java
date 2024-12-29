package org.example.services.review;

import org.example.models.Restaurant;
import org.example.models.RestaurantReview;
import org.example.models.Review;
import org.example.models.ReviewFactory;
import org.example.services.restaurant.SelectRestaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ListRestaurantReviewTest {
	private SelectRestaurant mockSelectRestaurant;
	private ListRestaurantReview listRestaurantReview;

	@BeforeEach
	void setUp() {
		mockSelectRestaurant = mock(SelectRestaurant.class);
		listRestaurantReview = new ListRestaurantReview(mockSelectRestaurant);
	}

	@Test
	void executeWithNullRestaurant() {
		when(mockSelectRestaurant.execute()).thenReturn(null);

		List<RestaurantReview> result = listRestaurantReview.execute();

		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	@Test
	void executeWithRestaurant() {
		Review restaurantReview = ReviewFactory.createReview("Comentario Test", 5.0f, 4.0f, 3.4f);
		Restaurant restaurant = new Restaurant("Restaurante Test", "Descripcion Test", "Ubicacion Test", null);
		restaurant.addReview(restaurantReview);

		when(mockSelectRestaurant.execute()).thenReturn(restaurant);

		List<RestaurantReview> result = listRestaurantReview.execute();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("Comentario Test", result.get(0).getComment());
		assertEquals(5.0f, result.get(0).getServiceRating());
		assertEquals(4.0f, result.get(0).getLocationRating());
		assertEquals(3.4f, result.get(0).getMenuRating());

		verify(mockSelectRestaurant).execute();
	}
}