package org.example.reviews.services.dishes;

import org.example.reviews.models.Dish;
import org.example.reviews.utils.ConsoleUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DishesServiceTest {

    private ConsoleUtil mockConsole;
    private CreateDishImpl mockCreateDishImpl;
    private FindDishesImpl mockFindDishesImpl;
    private UpdateDishImpl mockUpdateDishImpl;
    private DishesService dishesService;

    @BeforeEach
    void setUp() {
        mockConsole = mock(ConsoleUtil.class);
        mockCreateDishImpl = mock(CreateDishImpl.class);
        mockFindDishesImpl = mock(FindDishesImpl.class);
        mockUpdateDishImpl = mock(UpdateDishImpl.class);

        dishesService = new DishesService(mockConsole);
        dishesService.createDishImpl = mockCreateDishImpl;
        dishesService.findDishesImpl = mockFindDishesImpl;
        dishesService.updateDishImpl = mockUpdateDishImpl;
    }

    @Test
    @DisplayName("Should create a dish successfully")
    void testCreateDish() {
        Dish mockDish = mock(Dish.class);
        when(mockCreateDishImpl.execute()).thenReturn(mockDish);

        Dish result = dishesService.createDish();

        assertNotNull(result);
        assertEquals(mockDish, result);
        verify(mockCreateDishImpl).execute();
    }

    @Test
    @DisplayName("Should get dishes successfully")
    void testGetDishes() {
        Map<Integer, Dish> mockDishes = Map.of(1, mock(Dish.class));
        when(mockFindDishesImpl.execute()).thenReturn(mockDishes);

        Map<Integer, Dish> result = dishesService.getDishes();

        assertNotNull(result);
        assertEquals(mockDishes, result);
        verify(mockFindDishesImpl).execute();
    }

    @Test
    @DisplayName("Should update a dish successfully")
    void testUpdateDish() {
        Dish mockDish = mock(Dish.class);
        when(mockUpdateDishImpl.execute()).thenReturn(mockDish);

        Dish result = dishesService.updateDish();

        assertNotNull(result);
        assertEquals(mockDish, result);
        verify(mockUpdateDishImpl).execute();
    }
}
