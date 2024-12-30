package org.nahulem.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.models.Menu;
import org.nahulem.models.Restaurant;
import org.nahulem.models.RestaurantReview;
import org.nahulem.models.Review;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class RestaurantRepositoryTest {
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    void setUp() {
        restaurantRepository = RestaurantRepository.getInstance();
        restaurantRepository.clear();
    }

    @Test
    void getAllRestaurants() {
        Menu mockMenu = mock(Menu.class);
        Restaurant restaurant = new Restaurant("El Restaurante", "Descripcion", "Ubicacion", mockMenu);
        restaurantRepository.addRestaurant(restaurant);

        assertEquals(restaurant, restaurantRepository.getAllRestaurants().get(restaurant.getRestaurantId()));
    }

    @Test
    void deleteRestaurant() {
        Menu mockMenu = mock(Menu.class);
        Restaurant restaurant = new Restaurant("El Restaurante", "Descripcion", "Ubicacion", mockMenu);
        restaurantRepository.addRestaurant(restaurant);

        assertTrue(restaurantRepository.deleteRestaurant(restaurant.getRestaurantId()));
        assertFalse(restaurantRepository.deleteRestaurant(restaurant.getRestaurantId()));
    }

    @Test
    void updateRestaurantWhenExists() {
        Menu mockMenu = mock(Menu.class);
        Restaurant restaurant = new Restaurant("El Restaurante", "Descripcion", "Ubicacion", mockMenu);
        restaurantRepository.addRestaurant(restaurant);

        Restaurant updatedRestaurant = new Restaurant("Restaurante Actualizado", "Descripcion Actualizada", "Ubicacion Actualizada", mockMenu);
        updatedRestaurant.setRestaurantId(restaurant.getRestaurantId());

        assertTrue(restaurantRepository.updateRestaurant(updatedRestaurant));
        assertEquals("Restaurante Actualizado", restaurantRepository.getAllRestaurants().get(restaurant.getRestaurantId()).getName());
    }

    @Test
    void updateRestaurantWhenNotExists() {
        Restaurant updatedRestaurant = new Restaurant("Restaurante Inexistente", "Descripcion Test", "Ubicacion Test", null);

        assertFalse(restaurantRepository.updateRestaurant(updatedRestaurant));
    }

    @Test
    void addRestaurantReview() {
        Menu mockMenu = mock(Menu.class);
        Restaurant restaurant = new Restaurant("El Restaurante", "Descripcion", "Ubicacion", mockMenu);
        restaurantRepository.addRestaurant(restaurant);

        RestaurantReview review = new RestaurantReview("Muy buen lugar", 4.5f, 4.2f, 4.8f);
        restaurantRepository.addRestaurantReview(restaurant.getRestaurantId(), review);

        assertEquals(1, restaurantRepository.getRestaurantReviews(restaurant.getRestaurantId()).size());
        assertEquals(review, restaurantRepository.getRestaurantReviews(restaurant.getRestaurantId()).get(0));
    }

    @Test
    void getRestaurantReviewsWhenNoReviews() {
        Menu mockMenu = mock(Menu.class);
        Restaurant restaurant = new Restaurant("El Restaurante", "Descripcion", "Ubicacion", mockMenu);
        restaurantRepository.addRestaurant(restaurant);

        assertTrue(restaurantRepository.getRestaurantReviews(restaurant.getRestaurantId()).isEmpty());
    }

    @Test
    void getRestaurant() {
        Menu mockMenu = mock(Menu.class);
        Restaurant restaurant = new Restaurant("El Restaurante", "Descripcion", "Ubicacion", mockMenu);
        restaurantRepository.addRestaurant(restaurant);

        assertNotNull(restaurantRepository.getRestaurant(restaurant.getRestaurantId()));
        assertEquals(restaurant, restaurantRepository.getRestaurant(restaurant.getRestaurantId()));
    }

    @Test
    void getRestaurantWhenNotExists() {
        assertNull(restaurantRepository.getRestaurant(999));
    }

    @Test
    void testAddReviewWhenRestaurantDoesNotExist() {
        RestaurantReview review = new RestaurantReview("Excelente comida", 4.5f, 4.2f, 4.8f);
        restaurantRepository.addRestaurantReview(999, review);

        assertTrue(restaurantRepository.getRestaurantReviews(999).isEmpty());
    }

    @Test
    void testAddWhenReviewIsNotInstanceOfRestaurantReview() {
        Menu mockMenu = mock(Menu.class);
        Restaurant restaurant = new Restaurant("El Restaurante", "Descripcion", "Ubicacion", mockMenu);
        restaurantRepository.addRestaurant(restaurant);

        Review genericReview = mock(Review.class);
        restaurantRepository.addRestaurantReview(restaurant.getRestaurantId(), genericReview);

        assertTrue(restaurantRepository.getRestaurantReviews(restaurant.getRestaurantId()).isEmpty());
    }


}
