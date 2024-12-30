package restaurant.service.dish;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.models.Dish;
import restaurant.models.Menu;
import restaurant.models.Restaurant;
import restaurant.repository.RestaurantRepository;
import restaurant.utils.ConsoleUtils;
import restaurant.utils.RestaurantUtils;
import restaurant.utils.DishUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RemoveDishTest {
    private RestaurantRepository repository;
    private ConsoleUtils console;
    private RestaurantUtils restaurantUtils;
    private DishUtils dishUtils;
    private RemoveDish removeDish;

    @BeforeEach
    public void setUp() {
        repository = mock(RestaurantRepository.class);
        console = mock(ConsoleUtils.class);
        restaurantUtils = mock(RestaurantUtils.class);
        dishUtils = mock(DishUtils.class);
        removeDish = new RemoveDish(repository, console, restaurantUtils, dishUtils);
    }

    @Test
    public void testExecuteWithValidIndex() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant("Test Restaurant", "Test Address");
        Menu menu = new Menu();
        Dish dish = new Dish("Test Dish", "Test Description", 10.0);
        menu.addDish(dish);
        restaurant.setMenu(menu);
        restaurants.add(restaurant);

        when(repository.getRestaurants()).thenReturn(restaurants);
        when(restaurantUtils.selectMenu(repository, console)).thenReturn(menu);
        when(dishUtils.getDishIndex(console, menu.getDishes(), "Introduce el número del plato que deseas eliminar: ")).thenReturn(0);
        when(restaurantUtils.getRestaurantByMenu(repository, menu)).thenReturn(restaurant);

        removeDish.execute();

        assertTrue(menu.getDishes().isEmpty());
        verify(repository, times(1)).updateRestaurant(restaurant);
    }

    @Test
    public void testExecuteWithInvalidIndex() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant("Test Restaurant", "Test Address");
        Menu menu = new Menu();
        Dish dish = new Dish("Test Dish", "Test Description", 10.0);
        menu.addDish(dish);
        restaurant.setMenu(menu);
        restaurants.add(restaurant);

        when(repository.getRestaurants()).thenReturn(restaurants);
        when(restaurantUtils.selectMenu(repository, console)).thenReturn(menu);
        when(dishUtils.getDishIndex(console, menu.getDishes(), "Introduce el número del plato que deseas eliminar: ")).thenReturn(-1);

        removeDish.execute();

        assertFalse(menu.getDishes().isEmpty());
        verify(repository, never()).updateRestaurant(restaurant);
    }

    @Test
    public void testExecuteWithoutDishes() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant("Test Restaurant", "Test Address");
        Menu menu = new Menu();
        restaurant.setMenu(menu);
        restaurants.add(restaurant);

        when(repository.getRestaurants()).thenReturn(restaurants);
        when(restaurantUtils.selectMenu(repository, console)).thenReturn(menu);

        removeDish.execute();

        assertTrue(menu.getDishes().isEmpty());
        verify(repository, never()).updateRestaurant(restaurant);
    }

    @Test
    public void testExecuteWithoutMenu() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant("Test Restaurant", "Test Address");
        restaurants.add(restaurant);

        when(repository.getRestaurants()).thenReturn(restaurants);
        when(restaurantUtils.selectMenu(repository, console)).thenReturn(null);

        removeDish.execute();

        verify(repository, never()).updateRestaurant(any(Restaurant.class));
    }

    @Test
    public void testExecuteWithEmptyMenu() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant("Test Restaurant", "Test Address");
        Menu menu = new Menu();
        restaurant.setMenu(menu);
        restaurants.add(restaurant);

        when(repository.getRestaurants()).thenReturn(restaurants);
        when(restaurantUtils.selectMenu(repository, console)).thenReturn(menu);
        when(dishUtils.getDishIndex(console, menu.getDishes(), "Introduce el número del plato que deseas eliminar: ")).thenReturn(0);

        removeDish.execute();

        assertTrue(menu.getDishes().isEmpty());
        verify(repository, never()).updateRestaurant(restaurant);
    }
}