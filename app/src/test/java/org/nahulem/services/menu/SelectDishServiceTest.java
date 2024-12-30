package org.nahulem.services.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nahulem.models.Dish;
import org.nahulem.models.Menu;
import org.nahulem.models.Restaurant;
import org.nahulem.repositories.DataRepository;
import org.nahulem.services.restaurant.SelectRestaurantService;
import org.nahulem.utils.Validator;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SelectDishServiceTest {
    private Validator mockValidator;
    private SelectRestaurantService mockSelectRestaurantService;
    private SelectDishService mockSelectDishService;
    private DataRepository mockRepository;

    @BeforeEach
    void setUp() {
        mockValidator = mock(Validator.class);
        mockSelectRestaurantService = mock(SelectRestaurantService.class);
        mockRepository = mock(DataRepository.class);
        mockSelectDishService = new SelectDishService(mockValidator, mockSelectRestaurantService);
    }

    @Test
    void testNullRestaurant() {
        when(mockSelectRestaurantService.execute()).thenReturn(null);

        Dish result = mockSelectDishService.execute();

        assertNull(result);
        verify(mockSelectRestaurantService).execute();
    }

    @Test
    void testEmptyDishes() {
        Restaurant restaurant = new Restaurant("La Taberna", "Restaurante de comida española", "Calle 123", new Menu("Menú del día", new ArrayList<>()));

        when(mockSelectRestaurantService.execute()).thenReturn(restaurant);

        Dish result = mockSelectDishService.execute();

        assertNull(result);
    }

    @Test
    void testDishes() {
        ArrayList<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish("Paella", "Paella de mariscos", 12.5f));
        dishes.add(new Dish("Tortilla de patatas", "Tortilla de patatas con cebolla", 8.5f));
        Menu menu = new Menu("Menú del día", dishes);
        Restaurant restaurant = new Restaurant("La Taberna", "Restaurante de comida española", "Calle 123", menu);

        when(mockSelectRestaurantService.execute()).thenReturn(restaurant);
        when(mockValidator.readInt(anyString())).thenReturn(1);

        Dish result = mockSelectDishService.execute();

        assertNotNull(result);
        assertEquals("Paella", result.getName());
        assertEquals("Paella de mariscos", result.getDescription());
        assertEquals(12.5f, result.getPrice());

        verify(mockSelectRestaurantService).execute();
        verify(mockValidator).readInt(anyString());
    }

    @Test
    void testInvalidIndexBelow() {
        ArrayList<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish("Paella", "Paella de mariscos", 12.5f));
        dishes.add(new Dish("Tortilla de patatas", "Tortilla de patatas con cebolla", 8.5f));
        Menu menu = new Menu("Menú del día", dishes);
        Restaurant restaurant = new Restaurant("La Taberna", "Restaurante de comida española", "Calle 123", menu);

        when(mockSelectRestaurantService.execute()).thenReturn(restaurant);
        when(mockValidator.readInt(anyString())).thenReturn(0, 1);

        Dish result = mockSelectDishService.execute();

        assertNotNull(result);
        assertEquals("Paella", result.getName());
        assertEquals("Paella de mariscos", result.getDescription());
        assertEquals(12.5f, result.getPrice());

        verify(mockSelectRestaurantService).execute();
        verify(mockValidator, times(2)).readInt(anyString());
    }

    @Test
    void testInvalidIndexAbove() {
        ArrayList<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish("Paella", "Paella de mariscos", 12.5f));
        dishes.add(new Dish("Tortilla de patatas", "Tortilla de patatas con cebolla", 8.5f));
        Menu menu = new Menu("Menú del día", dishes);
        Restaurant restaurant = new Restaurant("La Taberna", "Restaurante de comida española", "Calle 123", menu);

        when(mockSelectRestaurantService.execute()).thenReturn(restaurant);
        when(mockValidator.readInt(anyString())).thenReturn(3, 2);

        Dish result = mockSelectDishService.execute();

        assertNotNull(result);
        assertEquals("Tortilla de patatas", result.getName());
        assertEquals("Tortilla de patatas con cebolla", result.getDescription());
        assertEquals(8.5f, result.getPrice());

        verify(mockSelectRestaurantService).execute();
        verify(mockValidator, times(2)).readInt(anyString());
    }
}