package restaurant.service.restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.models.Restaurant;
import restaurant.observer.IObserver;
import restaurant.repository.RestaurantRepository;
import restaurant.utils.ConsoleUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class RemoveRestaurantTest {
    private RestaurantRepository repository;
    private ConsoleUtils console;
    private RemoveRestaurant removeRestaurant;

    @BeforeEach
    public void setUp() {
        repository = mock(RestaurantRepository.class);
        console = mock(ConsoleUtils.class);
        removeRestaurant = new RemoveRestaurant(repository, console);
    }

    @Test
    public void testExecuteWithValidIndex() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant("Test Restaurant", "Test Address");
        restaurants.add(restaurant);

        when(repository.getRestaurants()).thenReturn(restaurants);
        when(console.getInteger(anyString())).thenReturn(1);

        removeRestaurant.execute();

        verify(repository, times(1)).removeRestaurant(restaurant);
    }

    @Test
    public void testExecuteWithInvalidIndex() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant("Test Restaurant", "Test Address");
        restaurants.add(restaurant);

        when(repository.getRestaurants()).thenReturn(restaurants);
        when(console.getInteger(anyString())).thenReturn(2);

        removeRestaurant.execute();

        verify(repository, never()).removeRestaurant(any(Restaurant.class));
    }

    @Test
    public void testExecuteWithNegativeIndex() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant("Test Restaurant", "Test Address");
        restaurants.add(restaurant);

        when(repository.getRestaurants()).thenReturn(restaurants);
        when(console.getInteger(anyString())).thenReturn(-1);

        removeRestaurant.execute();

        verify(repository, never()).removeRestaurant(any(Restaurant.class));
    }

    @Test
    public void testExecuteWithIndexEqualToSize() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant("Test Restaurant", "Test Address");
        restaurants.add(restaurant);

        when(repository.getRestaurants()).thenReturn(restaurants);
        when(console.getInteger(anyString())).thenReturn(2);

        removeRestaurant.execute();

        verify(repository, never()).removeRestaurant(any(Restaurant.class));
    }

    @Test
    public void testExecuteWithoutRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();

        when(repository.getRestaurants()).thenReturn(restaurants);

        removeRestaurant.execute();

        verify(repository, never()).removeRestaurant(any(Restaurant.class));
    }

    @Test
    public void testExecuteWithObservers() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant("Test Restaurant", "Test Address");
        IObserver observer = mock(IObserver.class);
        restaurant.addObserver(observer);
        restaurants.add(restaurant);

        when(repository.getRestaurants()).thenReturn(restaurants);
        when(console.getInteger(anyString())).thenReturn(1);

        removeRestaurant.execute();

        verify(repository, times(1)).removeRestaurant(restaurant);
        verify(observer, times(1)).update("Restaurant removed");
    }

    @Test
    public void testSelectRestaurantWithEmptyList() {
        List<Restaurant> restaurants = new ArrayList<>();

        removeRestaurant.selectRestaurant(restaurants);

        verify(repository, never()).removeRestaurant(any(Restaurant.class));
    }
}