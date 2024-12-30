package restaurant.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestaurantReviewTest {
    private RestaurantReview restaurantReview;
    private Restaurant restaurant;

    @BeforeEach
    public void setUp() {
        restaurant = new Restaurant("Restaurante de Prueba", "123 Calle Prueba");
        restaurantReview = new RestaurantReview("¡Excelente!", 5.0, restaurant);
    }

    @Test
    public void testGetComment() {
        assertEquals("¡Excelente!", restaurantReview.getComment());
    }

    @Test
    public void testGetQualification() {
        assertEquals(5.0, restaurantReview.getQualification());
    }

    @Test
    public void testGetRestaurant() {
        assertEquals(restaurant, restaurantReview.getRestaurant());
    }
}