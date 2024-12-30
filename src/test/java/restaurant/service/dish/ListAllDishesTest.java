package restaurant.service.dish;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.models.Dish;
import restaurant.models.Menu;
import restaurant.models.Restaurant;
import restaurant.repository.RestaurantRepository;
import restaurant.utils.ConsoleUtils;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class ListAllDishesTest {
    private RestaurantRepository repository;
    private ConsoleUtils console;
    private ListAllDishes listAllDishes;
    private Restaurant restaurant;
    private Menu menu;
    private Dish dish;

    @BeforeEach
    public void setUp() {
        repository = mock(RestaurantRepository.class);
        console = mock(ConsoleUtils.class);
        listAllDishes = new ListAllDishes(repository, console);
        restaurant = mock(Restaurant.class);
        menu = mock(Menu.class);
        dish = new Dish("Pasta", "Deliciosa pasta con salsa de tomate", 12.99);
    }

    @Test
    public void testExecuteWithDishes() {
        when(console.getString("Introduce el nombre del restaurante: ")).thenReturn("Restaurante de Prueba");
        when(repository.getRestaurant("Restaurante de Prueba")).thenReturn(restaurant);
        when(restaurant.getMenu()).thenReturn(menu);
        when(menu.getDishes()).thenReturn(Arrays.asList(dish));

        listAllDishes.execute();

        verify(console).getString("Introduce el nombre del restaurante: ");
        verify(repository).getRestaurant("Restaurante de Prueba");
        verify(restaurant).getMenu();
        verify(menu).getDishes();
    }

    @Test
    public void testExecuteWithNoDishes() {
        when(console.getString("Introduce el nombre del restaurante: ")).thenReturn("Restaurante de Prueba");
        when(repository.getRestaurant("Restaurante de Prueba")).thenReturn(restaurant);
        when(restaurant.getMenu()).thenReturn(menu);
        when(menu.getDishes()).thenReturn(Collections.emptyList());

        listAllDishes.execute();

        verify(console).getString("Introduce el nombre del restaurante: ");
        verify(repository).getRestaurant("Restaurante de Prueba");
        verify(restaurant).getMenu();
        verify(menu).getDishes();
    }

    @Test
    public void testExecuteRestaurantNotFound() {
        when(console.getString("Introduce el nombre del restaurante: ")).thenReturn("Restaurante de Prueba");
        when(repository.getRestaurant("Restaurante de Prueba")).thenReturn(null);

        listAllDishes.execute();

        verify(console).getString("Introduce el nombre del restaurante: ");
        verify(repository).getRestaurant("Restaurante de Prueba");
        verify(restaurant, never()).getMenu();
        verify(menu, never()).getDishes();
    }
}