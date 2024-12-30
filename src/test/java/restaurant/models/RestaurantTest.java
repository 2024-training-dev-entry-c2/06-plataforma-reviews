// src/test/java/restaurant/RestaurantTest.java
package restaurant.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import restaurant.observer.IObserver;
import restaurant.repository.RestaurantRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class RestaurantTest {
    private RestaurantRepository restaurantRepository;
    private Restaurant restaurant;

    @BeforeEach
    public void setUp() {
        restaurantRepository = Mockito.mock(RestaurantRepository.class);
        restaurant = new Restaurant("Test Restaurant", "123 Test St");
    }

    @Test
    public void testAddReview() {
        RestaurantReview review = new RestaurantReview("Great food!", 4.5, restaurant);
        restaurant.addReview(review);

        assertEquals(4.5, restaurant.getAverageRating());
        assertTrue(restaurant.getRestaurantReviews().contains(review));
    }

    @Test
    public void testGetRestaurant() {
        when(restaurantRepository.getRestaurant("Test Restaurant")).thenReturn(restaurant);

        Restaurant foundRestaurant = restaurantRepository.getRestaurant("Test Restaurant");
        assertEquals("Test Restaurant", foundRestaurant.getName());
    }

    @Test
    public void testAddObserver() {
        IObserver observer = mock(IObserver.class);
        restaurant.addObserver(observer);

        assertTrue(restaurant.getObservers().contains(observer));
    }

    @Test
    public void testRemoveObserver() {
        IObserver observer = mock(IObserver.class);
        restaurant.addObserver(observer);
        restaurant.removeObserver(observer);

        assertTrue(!restaurant.getObservers().contains(observer));
    }

    @Test
    public void testNotifyObservers() {
        IObserver observer = mock(IObserver.class);
        restaurant.addObserver(observer);
        restaurant.addReview(new RestaurantReview("Great food!", 4.5, restaurant));

        verify(observer, times(1)).update("Una nueva reseña ha sido agregada al restaurante: Test Restaurant");
    }

    @Test
    public void testSetAndGetMenu() {
        Menu menu = new Menu(restaurant);
        restaurant.setMenu(menu);

        assertEquals(menu, restaurant.getMenu());
    }

    @Test
    public void testSetName() {
        restaurant.setName("New Name");

        assertEquals("New Name", restaurant.getName());
    }

    @Test
    public void testSetAddress() {
        restaurant.setAddress("New Address");

        assertEquals("New Address", restaurant.getAddress());
    }
}