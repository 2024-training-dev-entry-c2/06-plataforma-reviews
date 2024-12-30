package restaurant.service.review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.Interface.IReview;
import restaurant.models.*;
import restaurant.repository.RestaurantRepository;
import restaurant.utils.ConsoleUtils;

import static org.mockito.Mockito.*;

public class ListDishReviewTest {
    private RestaurantRepository repository;
    private ConsoleUtils console;
    private ListDishReview listDishReview;

    @BeforeEach
    public void setUp() {
        repository = mock(RestaurantRepository.class);
        console = mock(ConsoleUtils.class);
        listDishReview = new ListDishReview(repository, console);
    }

    @Test
    public void testExecuteWithValidDish() {
        when(console.getString(anyString())).thenReturn("Test Restaurant");
        Restaurant restaurant = new Restaurant("Test Restaurant", "Test Address");
        Menu menu = new Menu(restaurant);
        Dish dish = new Dish("Test Dish", "Test Description", 10.0);
        DishReview review = new DishReview("Great dish!", 5.0, dish);
        dish.addReview(review);
        menu.addDish(dish);
        restaurant.setMenu(menu);
        when(repository.getRestaurant(anyString())).thenReturn(restaurant);
        when(console.getInteger(anyString())).thenReturn(1);

        listDishReview.execute();

        verify(repository, times(1)).getRestaurant(anyString());
        verify(console, times(1)).getString(anyString());
        verify(console, times(1)).getInteger(anyString());
    }

    @Test
    public void testExecuteWithInvalidDishIndex() {
        when(console.getString(anyString())).thenReturn("Test Restaurant");
        Restaurant restaurant = new Restaurant("Test Restaurant", "Test Address");
        Menu menu = new Menu(restaurant);
        restaurant.setMenu(menu);
        when(repository.getRestaurant(anyString())).thenReturn(restaurant);
        when(console.getInteger(anyString())).thenReturn(-1);

        listDishReview.execute();

        verify(repository, times(1)).getRestaurant(anyString());
        verify(console, times(1)).getString(anyString());
        verify(console, times(1)).getInteger(anyString());
    }

    @Test
    public void testExecuteWithNullRestaurant() {
        when(console.getString(anyString())).thenReturn("Test Restaurant");
        when(repository.getRestaurant(anyString())).thenReturn(null);

        listDishReview.execute();

        verify(repository, times(1)).getRestaurant(anyString());
        verify(console, times(1)).getString(anyString());
    }

    @Test
    public void testExecuteWithNoReviews() {
        when(console.getString(anyString())).thenReturn("Test Restaurant");
        Restaurant restaurant = new Restaurant("Test Restaurant", "Test Address");
        Menu menu = new Menu(restaurant);
        Dish dish = new Dish("Test Dish", "Test Description", 10.0);
        menu.addDish(dish);
        restaurant.setMenu(menu);
        when(repository.getRestaurant(anyString())).thenReturn(restaurant);
        when(console.getInteger(anyString())).thenReturn(1);

        listDishReview.execute();

        verify(repository, times(1)).getRestaurant(anyString());
        verify(console, times(1)).getString(anyString());
        verify(console, times(1)).getInteger(anyString());
    }

    @Test
    public void testExecuteWithNoDishes() {
        when(console.getString(anyString())).thenReturn("Test Restaurant");
        Restaurant restaurant = new Restaurant("Test Restaurant", "Test Address");
        Menu menu = new Menu(restaurant);
        restaurant.setMenu(menu);
        when(repository.getRestaurant(anyString())).thenReturn(restaurant);

        listDishReview.execute();

        verify(repository, times(1)).getRestaurant(anyString());
        verify(console, times(1)).getString(anyString());
    }

    @Test
    public void testExecuteWithDishIndexOutOfBounds() {
        when(console.getString(anyString())).thenReturn("Test Restaurant");
        Restaurant restaurant = new Restaurant("Test Restaurant", "Test Address");
        Menu menu = new Menu(restaurant);
        Dish dish = new Dish("Test Dish", "Test Description", 10.0);
        menu.addDish(dish);
        restaurant.setMenu(menu);
        when(repository.getRestaurant(anyString())).thenReturn(restaurant);
        when(console.getInteger(anyString())).thenReturn(2);

        listDishReview.execute();

        verify(repository, times(1)).getRestaurant(anyString());
        verify(console, times(1)).getString(anyString());
        verify(console, times(1)).getInteger(anyString());
    }

    @Test
    public void testExecuteWithNullMenu() {
        when(console.getString(anyString())).thenReturn("Test Restaurant");
        Restaurant restaurant = new Restaurant("Test Restaurant", "Test Address");
        restaurant.setMenu(null);
        when(repository.getRestaurant(anyString())).thenReturn(restaurant);

        listDishReview.execute();

        verify(repository, times(1)).getRestaurant(anyString());
        verify(console, times(1)).getString(anyString());
    }

    @Test
    public void testExecuteWithNonDishReview() {
        when(console.getString(anyString())).thenReturn("Test Restaurant");
        Restaurant restaurant = new Restaurant("Test Restaurant", "Test Address");
        Menu menu = new Menu(restaurant);
        Dish dish = new Dish("Test Dish", "Test Description", 10.0);
        IReview nonDishReview = mock(IReview.class);
        dish.getReviews().add(nonDishReview);
        menu.addDish(dish);
        restaurant.setMenu(menu);
        when(repository.getRestaurant(anyString())).thenReturn(restaurant);
        when(console.getInteger(anyString())).thenReturn(1);

        listDishReview.execute();

        verify(repository, times(1)).getRestaurant(anyString());
        verify(console, times(1)).getString(anyString());
        verify(console, times(1)).getInteger(anyString());
    }
}