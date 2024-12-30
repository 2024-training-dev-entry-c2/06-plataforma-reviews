// ReviewUtilsTest.java
package restaurant.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.models.Dish;
import restaurant.models.Restaurant;
import restaurant.repository.RestaurantRepository;
import restaurant.Interface.IReview;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReviewUtilsTest {
    private ReviewUtils reviewUtils;
    private RestaurantRepository repository;
    private ConsoleUtils console;
    private Restaurant restaurant;
    private Dish dish;

    @BeforeEach
    public void setUp() {
        reviewUtils = new ReviewUtils();
        repository = mock(RestaurantRepository.class);
        console = mock(ConsoleUtils.class);
        restaurant = new Restaurant("Test Restaurant", "Test Address");
        dish = new Dish("Test Dish", "Test Description", 10.0);
    }

    @Test
    public void testGetRestaurant() {
        when(console.getString("Introduce el nombre del restaurante: ")).thenReturn("Test Restaurant");
        when(repository.getRestaurant("Test Restaurant")).thenReturn(restaurant);

        Restaurant result = reviewUtils.getRestaurant(repository, console);

        assertNotNull(result);
        assertEquals("Test Restaurant", result.getName());
    }

    @Test
    public void testGetRestaurantNotFound() {
        when(console.getString("Introduce el nombre del restaurante: ")).thenReturn("Nonexistent Restaurant");
        when(repository.getRestaurant("Nonexistent Restaurant")).thenReturn(null);

        Restaurant result = reviewUtils.getRestaurant(repository, console);

        assertNull(result);
    }

    @Test
    public void testAddRestaurantReview() {
        reviewUtils.addRestaurantReview(restaurant, "Great place!", 4.5);

        assertEquals(1, restaurant.getRestaurantReviews().size());
        IReview review = restaurant.getRestaurantReviews().get(0);
        assertEquals("Great place!", review.getComment());
        assertEquals(4.5, review.getQualification());
    }

    @Test
    public void testAddDishReview() {
        reviewUtils.addDishReview(dish, "Delicious!", 5.0);

        assertEquals(1, dish.getDishReviews().size());
        IReview review = dish.getDishReviews().get(0);
        assertEquals("Delicious!", review.getComment());
        assertEquals(5.0, review.getQualification());
    }
}