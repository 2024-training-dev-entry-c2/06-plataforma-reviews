package restaurant.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.repository.RestaurantRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantRepositoryTest {
    private RestaurantRepository repository;
    private Restaurant restaurant;

    @BeforeEach
    public void setUp() {
        repository = RestaurantRepository.getInstance();
        restaurant = new Restaurant("Restaurante de Prueba", "123 Calle Prueba");
        repository.getRestaurants().clear();
    }

    @Test
    public void testGetInstance() {
        assertNotNull(repository);
    }

    @Test
    public void testGetRestaurants() {
        repository.addRestaurant(restaurant);
        List<Restaurant> restaurants = repository.getRestaurants();

        assertEquals(1, restaurants.size());
        assertTrue(restaurants.contains(restaurant));
    }

    @Test
    public void testGetRestaurant() {
        repository.addRestaurant(restaurant);
        Restaurant foundRestaurant = repository.getRestaurant("Restaurante de Prueba");

        assertNotNull(foundRestaurant);
        assertEquals(restaurant, foundRestaurant);
    }

    @Test
    public void testGetRestaurantNotFound() {
        Restaurant foundRestaurant = repository.getRestaurant("Nonexistent Restaurant");

        assertNull(foundRestaurant);
    }

    @Test
    public void testAddRestaurant() {
        repository.addRestaurant(restaurant);
        List<Restaurant> restaurants = repository.getRestaurants();

        assertEquals(1, restaurants.size());
        assertTrue(restaurants.contains(restaurant));
    }

    @Test
    public void testRemoveRestaurant() {
        repository.addRestaurant(restaurant);
        boolean removed = repository.removeRestaurant(restaurant);

        assertTrue(removed);
        assertFalse(repository.getRestaurants().contains(restaurant));
    }

    @Test
    public void testRemoveRestaurantNotFound() {
        boolean removed = repository.removeRestaurant(restaurant);

        assertFalse(removed);
    }

    @Test
    public void testUpdateRestaurant() {
        repository.addRestaurant(restaurant);
        Restaurant updatedRestaurant = new Restaurant("Restaurante de Prueba", "456 Calle Actualizada");
        repository.updateRestaurant(updatedRestaurant);

        Restaurant foundRestaurant = repository.getRestaurant("Restaurante de Prueba");
        assertNotNull(foundRestaurant);
        assertEquals("456 Calle Actualizada", foundRestaurant.getAddress());
    }
}