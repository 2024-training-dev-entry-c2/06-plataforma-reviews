// src/test/java/restaurant/service/restaurant/.java
package restaurant.service.restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.models.Restaurant;
import restaurant.repository.RestaurantRepository;
import restaurant.utils.ConsoleUtils;
import restaurant.utils.RestaurantUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class EditRestaurantTest {
    private RestaurantRepository repository;
    private ConsoleUtils console;
    private RestaurantUtils restaurantUtils;
    private EditRestaurant editRestaurant;

    @BeforeEach
    public void setUp() {
        repository = mock(RestaurantRepository.class);
        console = mock(ConsoleUtils.class);
        restaurantUtils = mock(RestaurantUtils.class);
        editRestaurant = new EditRestaurant(repository, console);
    }

    @Test
    public void testExecuteWithRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant("Old Name", "Old Address");
        restaurants.add(restaurant);

        when(repository.getRestaurants()).thenReturn(restaurants);
        when(console.getString("Introduce el nuevo nombre del restaurante: ")).thenReturn("New Name");
        when(console.getString("Introduce la nueva dirección del restaurante: ")).thenReturn("New Address");
        when(console.getInteger(anyString())).thenReturn(1);
        when(restaurantUtils.getRestaurantIndex(console, restaurants, "Introduce el número del restaurante que deseas editar: ")).thenReturn(0);

        Restaurant editedRestaurant = editRestaurant.execute();

        assertNotNull(editedRestaurant);
        assertEquals("New Name", editedRestaurant.getName());
        assertEquals("New Address", editedRestaurant.getAddress());
        verify(repository, times(1)).getRestaurants();
    }

    @Test
    public void testExecuteWithoutRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();

        when(repository.getRestaurants()).thenReturn(restaurants);

        Restaurant editedRestaurant = editRestaurant.execute();

        assertNull(editedRestaurant);
        verify(repository, times(1)).getRestaurants();
    }

    @Test
    public void testExecuteWithInvalidIndex() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant("Old Name", "Old Address");
        restaurants.add(restaurant);

        when(repository.getRestaurants()).thenReturn(restaurants);
        when(console.getInteger(anyString())).thenReturn(2);
        when(restaurantUtils.getRestaurantIndex(console, restaurants, "Introduce el número del restaurante que deseas editar: ")).thenReturn(-1);

        Restaurant editedRestaurant = editRestaurant.execute();

        assertNull(editedRestaurant);
        verify(repository, times(1)).getRestaurants();
    }
}