package restaurant.service.restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.models.Restaurant;
import restaurant.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ListAllRestaurantsTest {
    private RestaurantRepository repository;
    private ListAllRestaurants listAllRestaurants;

    @BeforeEach
    public void setUp() {
        repository = mock(RestaurantRepository.class);
        listAllRestaurants = new ListAllRestaurants(repository);
    }

    @Test
    public void testExecuteWithRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant("Test Restaurant", "Test Address"));

        when(repository.getRestaurants()).thenReturn(restaurants);

        listAllRestaurants.execute();

        verify(repository, times(1)).getRestaurants();
    }

    @Test
    public void testExecuteWithoutRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();

        when(repository.getRestaurants()).thenReturn(restaurants);

        listAllRestaurants.execute();

        verify(repository, times(1)).getRestaurants();
    }
}