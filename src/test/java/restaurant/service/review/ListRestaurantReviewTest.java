package restaurant.service.review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.Interface.IReview;
import restaurant.models.Restaurant;
import restaurant.models.RestaurantReview;
import restaurant.repository.RestaurantRepository;
import restaurant.utils.ConsoleUtils;

import static org.mockito.Mockito.*;

public class ListRestaurantReviewTest {
    private RestaurantRepository repository;
    private ConsoleUtils console;
    private ListRestaurantReview listRestaurantReview;

    @BeforeEach
    public void setUp() {
        repository = mock(RestaurantRepository.class);
        console = mock(ConsoleUtils.class);
        listRestaurantReview = new ListRestaurantReview(repository, console);
    }

    @Test
    public void testExecuteWithValidRestaurant() {
        when(console.getString(anyString())).thenReturn("Test Restaurant");
        Restaurant restaurant = new Restaurant("Test Restaurant", "Test Address");
        RestaurantReview review = new RestaurantReview("Great restaurant!", 5.0, restaurant);
        restaurant.addReview(review);
        when(repository.getRestaurant(anyString())).thenReturn(restaurant);

        listRestaurantReview.execute();

        verify(repository, times(1)).getRestaurant(anyString());
        verify(console, times(1)).getString(anyString());
    }

    @Test
    public void testExecuteWithNullRestaurant() {
        when(console.getString(anyString())).thenReturn("Test Restaurant");
        when(repository.getRestaurant(anyString())).thenReturn(null);

        listRestaurantReview.execute();

        verify(repository, times(1)).getRestaurant(anyString());
        verify(console, times(1)).getString(anyString());
    }

    @Test
    public void testExecuteWithNoReviews() {
        when(console.getString(anyString())).thenReturn("Test Restaurant");
        Restaurant restaurant = new Restaurant("Test Restaurant", "Test Address");
        when(repository.getRestaurant(anyString())).thenReturn(restaurant);

        listRestaurantReview.execute();

        verify(repository, times(1)).getRestaurant(anyString());
        verify(console, times(1)).getString(anyString());
    }

    @Test
    public void testExecuteWithNonRestaurantReview() {
        when(console.getString(anyString())).thenReturn("Test Restaurant");
        Restaurant restaurant = new Restaurant("Test Restaurant", "Test Address");
        IReview nonRestaurantReview = mock(IReview.class);
        restaurant.getRestaurantReviews().add(nonRestaurantReview);
        when(repository.getRestaurant(anyString())).thenReturn(restaurant);

        listRestaurantReview.execute();

        verify(repository, times(1)).getRestaurant(anyString());
        verify(console, times(1)).getString(anyString());
    }
}